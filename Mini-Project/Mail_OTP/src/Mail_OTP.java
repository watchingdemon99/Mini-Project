import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
import javax.mail.*;
import javax.mail.internet.*;
//import javax.activation.*;

//class ShuffleString {
//    public String shuffle(String text) {
//        List<Character> characters = text.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
//        StringBuilder result = new StringBuilder();
//        IntStream.range(0, text.length()).forEach((index) -> {
//            int randomPosition = new Random().nextInt(characters.size());
//            result.append(characters.get(randomPosition));
//            characters.remove(randomPosition);
//        });
//        return result.toString();
//    }
//}
//

class Mailer{
    public static void send(final String from,final String password,final String to,String sub,String msg){
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        //get Session
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(sub);
            message.setText(msg);
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {throw new RuntimeException(e);}

    }
}


public class Mail_OTP {

    static int num_of_characters = 3;
    static int num_of_numbers = 3;
    static int length;
    static String sender_mail = "prajwaltupare@gmail.com";
    static String Password = "lzmetvnxubglxicv";
    static Scanner s = new Scanner(System.in);

    // Function to generate a One Time Password
    static String generate_otp() {
        length = num_of_characters + num_of_numbers;
        Random random = new Random();
        String number = "0123456789";
        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        String otp = "";
//        ShuffleString x = new ShuffleString();
        for (int i = 0; i < length; i++) {
            int index;
            if (num_of_numbers > 0) {
                index = random.nextInt(9);
                otp += number.charAt(index);
                num_of_numbers--;
            } else if (num_of_characters > 0) {
                index = random.nextInt(26);
                otp += alphabets.charAt(index);
            }
//            otp = x.shuffle(otp);
        }
        return otp;
    }


    // Function to send OTP using mail
    static void sendOTP() {
        String OTP = generate_otp();
        String msg = "This your OTP: "+ OTP;
        String sub = "OTP";
        System.out.println("Enter receiver's e-mail address: ");
        String receiver_mail = s.nextLine();
        Mailer.send(sender_mail, Password,receiver_mail,sub,msg);
        System.out.println("OTP sent successfully to "+ receiver_mail);
        verify_otp(OTP);
    }

    // Function to configure parameters for the program
    static void Settings() {
        System.out.println("Press\n1 Set up Sender's mail\n2 Set up password\n3 Change OTP parameters");
        int choice = s.nextInt();
        s.nextLine();
        if(choice == 1) setSender_mail();
        else if(choice == 2)  setPassword();
        else if(choice == 3) set_OTP_parameters();
    }



    static void setSender_mail(){
        System.out.println("Enter sender's e-mail address");
        String sender = s.nextLine();
        sender_mail = sender;
        System.out.println("Sender's email has been changed successfully!\n Set up password");
        setPassword();

    }

    static void setPassword(){
        System.out.println("Enter the password: ");
        String pass;
        pass = s.nextLine();
        Password = pass;
        System.out.println("Password has been changed! ");
        main(null);
    }

    static void set_OTP_parameters(){
        System.out.println("Set Up OTP parameters\n Press\n 1 To setup occurences of numbers in OTP\n 2 to set number of characters in OTP \n 3 to return Home");
        int choice = s.nextInt();
        s.nextLine();
        if(choice ==1) set_no_of_numbers();
        else if(choice == 2) set_no_of_characters();
        else if(choice == 3) main(null);
        else {System.out.println("Invalid Choice! Please Try again. "); set_OTP_parameters();}

    }
    static void set_no_of_numbers(){
        System.out.println("Enter the number of numbers you want in OTP: ");
        int num = s.nextInt(); s.nextLine();
        num_of_numbers = num;
        main(null);
    }
    static void set_no_of_characters(){
        System.out.println("Enter the number of characters you want in OTP: ");
        int num = s.nextInt(); s.nextLine();
        num_of_characters = num;
        main(null);
    }

    static void verify_otp(String OTP){
        String user_otp;
        System.out.println("Enter the sent otp: ");
        
        user_otp = s.nextLine();


        if(Objects.equals(user_otp, OTP))
            System.out.println("OTP verified");
        else
        {
            System.out.println("Incorrect OTP!\n Press\n1 to Try again\n 2 to resend OTP\n 3 to Return to Home screen");
            int choice = s.nextInt();
            s.nextLine();
            if (choice == 1) verify_otp(OTP);
            else if (choice == 2) sendOTP();
            else if(choice == 3) main(null);
        }

    }

    // main function
    public static void main(String args[]) {

        System.out.println("Press \n 1 Send OTP\n 2 Setting");
        int choice = s.nextInt();
        s.nextLine();
        if (choice == 1) sendOTP();
        else if (choice == 2) Settings();


    }

}



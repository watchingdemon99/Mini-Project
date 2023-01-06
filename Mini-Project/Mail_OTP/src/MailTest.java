//To run this code,Download the junit file using this link : https://www.javatpoint.com/src/junit/junit4jars.zip




package project;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.Test;

public class Mail_OTPTest {
	
	
	@Test
	// Test case for checking length of OTP
	public void testOTPlength() {
		String otp = Mail_OTP.generate_otp();
		int len = otp.length();
		int i = Mail_OTP.num_of_characters+Mail_OTP.num_of_numbers;
		assertTrue(len>=4);
		assertTrue(len<=10);
		if(len>=4 && len<=10)
			System.out.println("Test case  for OTP length executed Succesfully!");
		else
			System.out.print("Test case  failed\n");

		}

    @Test
  public  void testGenerateOTPContent() {
        String otp = Mail_OTP.generate_otp();
        int numCount = 0;
        int charCount = 0;
        for (int i = 0; i < otp.length(); i++) {
            char c = otp.charAt(i);
            if (Character.isDigit(c)) {
                numCount++;
            } else if (Character.isLetter(c)) {
                charCount++;
            }
        }
        assertEquals(Mail_OTP.num_of_characters, charCount);
        assertEquals(Mail_OTP.num_of_numbers, numCount);
        
    	if(Mail_OTP.num_of_characters == charCount && Mail_OTP.num_of_numbers == numCount)
			System.out.println("Test case  for OTP content executed Succesfully!");
		else
			System.out.print("Test case  failed\n");
    }
    
	
	
	// To check if random OTP is generated every time
	@Test
	public void test_otp_uniqueness() {
		String previous_otp = Mail_OTP.generate_otp();
		String current_otp  = Mail_OTP.generate_otp();
		assertNotEquals(previous_otp, current_otp);
		if(previous_otp == current_otp)
			System.out.println("Test case  failed ");
		else 
			System.out.print("Test case  to check OTP uniqueness executed Succesfully \n");
		
	
	}
	
	// Test case to check if verify function works correctly
    @Test
   public void testVerifyOtpCorrect() {
        String otp = "123";
        assertTrue(Mail_OTP.verify_otp(otp, "123"));
    	if(otp != "123")
			System.out.println("Test case  failed ");
		else 
			System.out.print("Test case  to verify OTP executed Succesfully \n");
    }
    // Test case to check if verify function does not accept incorrect OTP
    @Test
   public void testVerifyOtpIncorrect() {
        String otp = "123";
        assertFalse(Mail_OTP.verify_otp(otp, "456"));
        if(otp != "123")
			System.out.println("Test case  failed ");
		else 
			System.out.print("Test case  to verify incorrect OTP executed Succesfully \n");
    }
    
 // Test case to check if email is valid 
 	@Test
 	public void test_email_id() {
 		
 		 String email = "saurabhcs8802@gmail.com";
 		assertTrue( email.contains("@gmail.com"));
 		if(email.contains("@gmail.com"))
			System.out.println("Test case  to verify OTP executed Succesfully ");
		else 
			System.out.print("Test case  failed \n");
 	}
 
 	 // Test case to check if email is valid or not
 	@Test	
 	public void test_invalid_email_id() {
 		
 		 String email = "saurabhcs8802@gmailcom";
 		assertFalse( email.contains("@gmail.com"));
 		  if(email.contains("saurabhcs8802email.com"))
 				System.out.println("Test case  failed ");
 			else 
 				System.out.print("Test case  to verify invalid email executed Succesfully \n");
 	}


}

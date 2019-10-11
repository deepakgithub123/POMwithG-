package testData;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateData {
	
	public String generateRandomString(int length){
		return RandomStringUtils.randomAlphabetic(length);
	}

	public String generateRandomAlphaNumeric(int length){
		return RandomStringUtils.randomAlphanumeric(length);
	}
	
	public String generateRandomNumeric(int length){
		return RandomStringUtils.randomNumeric(length);
	}
	
	public int generateRandomNumericFromRange(int max, int min){
		Random rdm = new Random();
		int ranNum = rdm.nextInt(max - min) + min;
		return ranNum;
	}

	public String generateStringWithAllowedSplChars(int length,String allowdSplChrs){
		String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
				"1234567890" +   //numbers
				allowdSplChrs;
		return RandomStringUtils.random(length, allowedChars);
	}

	public String generateStringWithNotAllowedSplChars(int length){
		String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
				"1234567890" +   //numbers
				"!#$%^&*()"; //notAllowedSplChrs
		return RandomStringUtils.random(length, allowedChars);
	}

	public String generateValidEmail(int length) {
		String allowedChars="abcdefghijklmnopqrstuvwxyz"  ; 
		String period =	"." ;
		String numbers="1234567890"	 ; 
		String email="";
		
		String temp=RandomStringUtils.random(5,allowedChars);
		String temp1=RandomStringUtils.random(17,numbers);
		String cont=temp+period+temp1;
		email=cont.substring(0,cont.length()-12)+"@yopmail.com";
		return email;
	}

	public String generateInvalidEmail(int length) {
		String allowedChars="abcdefghijklmnopqrstuvwxyz" +   //alphabets
				"1234567890" +   //numbers
				"!#$%&*()";   //special characters
		String email="";
		String temp=RandomStringUtils.random(length,allowedChars);
		email=temp.substring(0,temp.length()-12)+"@yopmail.com";
		return email;
	}
}

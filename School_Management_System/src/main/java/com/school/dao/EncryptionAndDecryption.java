package com.school.dao;

import java.util.Base64;
import java.util.Random;

public class EncryptionAndDecryption {

	public String Encryption(int number) {
		Random random = new Random();

		// Generate a random number between 1000 and 9999
		int randomNumber = random.nextInt(9999) + 1000;
		number = Integer.valueOf(String.valueOf(randomNumber) + String.valueOf(number));

//	        System.out.println(number);

		String encoded = Base64.getEncoder().encodeToString(String.valueOf(number).getBytes());

		String encodedh = javax.xml.bind.DatatypeConverter.printHexBinary(encoded.getBytes());

		return encodedh;
	}

	//Checking the string is 16 Characters or Not
	public static boolean is16Characters(String str) {
		return str.length() == 16;
	}
	
	//Checking the String is uppercase or not
	public static boolean isUppercase(String str) {
        // Compare the original string with its uppercase version
        return str.equals(str.toUpperCase());
    }

	public String Decryption(String encodedStr) {

		if (is16Characters(encodedStr) && isUppercase(encodedStr)) {
			try {

				byte[] decodedBytes = javax.xml.bind.DatatypeConverter.parseHexBinary(encodedStr);
				String decodedString = new String(decodedBytes);

				// Decode the encoded string
				byte[] decodedBytes1 = Base64.getDecoder().decode(decodedString);
				String decoded = new String(decodedBytes1);
				int decodedNumber = Integer.parseInt(decoded);

				String company_id1 = Integer.toString(decodedNumber);

				char a = company_id1.charAt(company_id1.length() - 1);
				String company_id2 = Character.toString(a);

				// System.out.println(company_id2);

				return company_id2;

			} catch (Exception e) {
				return "Token Not Found";
			}

		} else {
			return "A";
		}
	}

}

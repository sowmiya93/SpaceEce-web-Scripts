package utils;

import java.io.File;

public class DataValidation {

    // Check if the name contains only letters and spaces
	public static boolean isValidName(String name) {
	    if (isEmpty(name)) {
	        return false; // Name is empty
	    }

	    // Check length constraints
	    if (name.length() < 3 || name.length() > 20) {
	        return false; // Name length should be between 3 and 20 characters
	    }

	    // Validate that name contains only letters and spaces
	    for (int i = 0; i < name.length(); i++) {
	        char c = name.charAt(i);
	        if (!Character.isLetter(c) && c != ' ') {
	            return false; // Invalid character found
	        }
	    }

	    return true; // Name is valid
	}


    // Check if the email contains '@' and '.'
	public static boolean isValidEmail(String email) {
	    if (isEmpty(email)) {
	        System.out.println("Validation failed: Email is empty.");
	        return false; // Email is empty
	    }

	    // Check if the email contains uppercase letters
	    for (int i = 0; i < email.length(); i++) {
	        if (Character.isUpperCase(email.charAt(i))) {
	            System.out.println("Validation failed: Email must not contain uppercase letters.");
	            return false; // Uppercase letter found
	        }
	    }

	    // Check for the presence of '@' and '.'
	    if (!email.contains("@") || !email.contains(".")) {
	        System.out.println("Validation failed: Email must contain '@' and '.'.");
	        return false; // '@' or '.' is missing
	    }

	    return true; // Email is valid
	}

    // Check if the mobile number starts with 6-9 and has exactly 10 digits
    public static boolean isValidMobile(String mobile) {
        if (isEmpty(mobile)) {
            return false; // Mobile number is empty
        }

        if (mobile.length() != 10) {
            return false; // Mobile number must have exactly 10 digits
        }

        // Check if the first digit is between 6 and 9
        char firstDigit = mobile.charAt(0);
        if (firstDigit < '6' || firstDigit > '9') {
            return false; // Invalid first digit
        }

        // Check if the remaining characters are all digits
        for (int i = 1; i < mobile.length(); i++) {
            if (!Character.isDigit(mobile.charAt(i))) {
                return false; // Non-digit character found
            }
        }

        return true; // Mobile number is valid
    }

    // Check if the password is at least 6 characters long
    public static boolean isValidPassword(String password) {
        if (isEmpty(password)) {
            System.out.println("Validation failed: Password is empty.");
            return false; // Password is empty
        }

        // Check length constraints
        if (password.length() < 6 || password.length() > 20) {
            System.out.println("Validation failed: Password must be between 6 and 20 characters long.");
            return false; // Password length is invalid
        }

        return true; // Password is valid
    }


    // Check if the confirm password matches the password
    public static boolean isPasswordConfirmed(String password, String confirmPassword) {
        if (isEmpty(password) || isEmpty(confirmPassword)) {
            return false; // Either password or confirm password is empty
        }

        return password.equals(confirmPassword); // Both passwords must match
    }

    public static boolean isFileValid(String filePath) {
        if (isEmpty(filePath)) {
            System.out.println("Error: File path is empty.");
            return false;
        }

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Error: File does not exist.");
            return false;
        }

        /* // Check file size in KB (between 50 KB and 2048 KB)
        long fileSizeInKB = file.length() / 1024; // Convert bytes to KB
       if (fileSizeInKB < 50 || fileSizeInKB > 2048) {
            System.out.println("Error: File size must be between 50 KB and 2048 KB.");
            return false;
        }*/

        // Check for valid image extensions (jpg, png, jpeg)
        if (!hasValidExtension(filePath)) {
            System.out.println("Error: Invalid file extension. Only jpg, png, and jpeg are allowed.");
            return false;
        }

        return true; // File is valid (size and extension)
    }

    // Check if the file has a valid image extension
    private static boolean hasValidExtension(String filePath) {
        String[] validExtensions = { ".jpg", ".jpeg", ".png" };
        for (String ext : validExtensions) {
            if (filePath.toLowerCase().endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    // Utility to check if a field is empty
    private static boolean isEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }
}

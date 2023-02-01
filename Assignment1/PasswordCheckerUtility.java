import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

public class PasswordCheckerUtility {
	
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {	// Compares two passwords and throws exception if they are not equal
		if (!password.equals(passwordConfirm))
			throw new UnmatchedException();
	}

	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {	// Compares two passwords and returns a boolean value based on if they are the same
		return (password.equals(passwordConfirm));
	}
	
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {	// Finds all errors in an ArrayList of Strings and returns an ArrayList of error messages
		ArrayList<String> output = new ArrayList<String>();
		for(int i=0;i<passwords.size();i++) {
			try {
				if(isValidPassword(passwords.get(i))){		// Test each password's validity
					
				}
			} catch (LengthException e) {
				output.add(passwords.get(i) + " The password must be at least 6 characters long");	// Catch any exceptions and add the appropriate error message to the output ArrayList
			} catch (NoUpperAlphaException e) {
				output.add(passwords.get(i) + " The password must contain at least one uppercase alphabetic character");
			} catch (NoLowerAlphaException e) {
				output.add(passwords.get(i) + " The password must contain at least one lowercase alphabetic character");
			} catch (NoDigitException e) {
				output.add(passwords.get(i) + " The password must contain at least one digit");
			} catch (NoSpecialCharacterException e) {
				output.add(passwords.get(i) + " The password must contain at least one special character");
			} catch (InvalidSequenceException e) {
				output.add(passwords.get(i) + " The password cannot contain more than two of the same character in sequence");
			}
		}
		return output;
	}
	
	public static boolean hasBetweenSixAndNineChars(String password) {	// Returns a boolean dependent on if the password is between 6 and 9 characters
		return (password.length() >= 6 && password.length() <= 9);
	}
	
	public static boolean hasDigit(String password) throws NoDigitException {	// Tests if a password has a digit in it, if not throw the appropriate exception
		for(int i=0;i<password.length();i++) {
			if(password.charAt(i) >= '0' && password.charAt(i) <= '9')
				return true;
		}
		throw new NoDigitException();
	}
	
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException { // Tests if a password has a lowercase letter in it, if not throw the appropriate exception
		for(int i=0;i<password.length();i++) {
			if(password.charAt(i) >= 'a' && password.charAt(i) <= 'z')
				return true;
		}
		throw new NoLowerAlphaException();
	}
	
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException { // Tests if a password has a special character in it, if not throw the appropriate exception
		Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.find())
			throw new NoSpecialCharacterException();
		return true;
	}
	
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException { // Tests if a password has a uppercase letter in it, if not throw the appropriate exception
		for(int i=0;i<password.length();i++) {
			if(password.charAt(i) >= 'A' && password.charAt(i) <= 'Z')
				return true;
		}
		throw new NoUpperAlphaException();
	}

	public static boolean isValidLength(String password) throws LengthException { // Tests if a password is a valid length, if not throw the appropriate exception
		if (password.length() < 6)
			throw new LengthException();
		return true;
	}
	
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException,
	NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {		// Tests if a password is valid by all 6 rules
return (isValidLength(password) && hasUpperAlpha(password) && hasLowerAlpha(password) && hasDigit(password)
		&& hasSpecialChar(password) && NoSameCharInSequence(password));
}

	public static boolean isWeakPassword(String password) throws WeakPasswordException {	// Tests if a password is weak, if it is then throw the appropriate exception
		try {
			if (isValidPassword(password) && !hasBetweenSixAndNineChars(password))
				return false;
		} catch (Exception e) {
			throw new WeakPasswordException();
		}
		throw new WeakPasswordException();
	}
	
	public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException {	// Tests if a password has more than two of the same character in sequence,
		for(int i=0;i<password.length()-2;i++) {													// if so, throw the appropriate exception
			if(password.charAt(i) == password.charAt(i+1) && password.charAt(i) == password.charAt(i+2))
				throw new InvalidSequenceException();
		}
		return true;
	}

}
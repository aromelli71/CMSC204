import java.util.ArrayList;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {

	ArrayList<String> passwords;	
	@Before
	public void setUp() throws Exception {
		passwords = new ArrayList<String>();
		passwords.add("short");
		passwords.add("nouppercase");
		passwords.add("NOLOWERCASE");
		passwords.add("isWeak");
		passwords.add("BaaadSequence");
		passwords.add("noDigit");
		passwords.add("1ValidPassword!");
		
		
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			PasswordCheckerUtility.isValidLength(passwords.get(6));	// Ensures that a valid password passes the test
		} catch (LengthException e1) {
			fail();
		}
		try {
			PasswordCheckerUtility.isValidLength(passwords.get(0));	// Ensures that an invalid password fails with the proper message
			fail();
		} catch (LengthException e) {
			assertTrue(e.getMessage().equals("The password must be at least 6 characters long"));
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			PasswordCheckerUtility.hasUpperAlpha(passwords.get(6));	// Ensures that a valid password passes the test
		} catch (NoUpperAlphaException e1) {
			fail();
		}
		try {
			PasswordCheckerUtility.hasUpperAlpha(passwords.get(1));	// Ensures that an invalid password fails with the proper message
			fail();
		} catch (NoUpperAlphaException e) {
			assertTrue(e.getMessage().equals("The password must contain at least one uppercase alphabetic character"));
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			PasswordCheckerUtility.hasLowerAlpha(passwords.get(6));	// Ensures that a valid password passes the test
		} catch (NoLowerAlphaException e1) {
			fail();
		}
		try {
			PasswordCheckerUtility.hasLowerAlpha(passwords.get(2));	// Ensures that an invalid password fails with the proper message
			fail();
		} catch (NoLowerAlphaException e) {
			assertTrue(e.getMessage().equals("The password must contain at least one lowercase alphabetic character"));
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			PasswordCheckerUtility.isWeakPassword(passwords.get(6));	// Ensures that a valid password passes the test
		} catch (WeakPasswordException e1) {
			fail();
		}
		try {
			PasswordCheckerUtility.isWeakPassword(passwords.get(3));	// Ensures that an invalid password fails with the proper message
			fail();
		} catch (WeakPasswordException e) {
			assertTrue(e.getMessage().equals("The password is OK but weak - it contains fewer than 10 characters."));
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			PasswordCheckerUtility.NoSameCharInSequence(passwords.get(6));	// Ensures that a valid password passes the test
		} catch (InvalidSequenceException e1) {
			fail();
		}
		try {
			PasswordCheckerUtility.NoSameCharInSequence(passwords.get(4));	// Ensures that an invalid password fails with the proper message
			fail();
		} catch (InvalidSequenceException e) {
			assertTrue(e.getMessage().equals("The password cannot contain more than two of the same character in sequence."));
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			PasswordCheckerUtility.hasDigit(passwords.get(6));	// Ensures that a valid password passes the test
		} catch (NoDigitException e1) {
			fail();
		}
		try {
			PasswordCheckerUtility.hasDigit(passwords.get(5));	// Ensures that an invalid password fails with the proper message
			fail();
		} catch (NoDigitException e) {
			assertTrue(e.getMessage().equals("The password must contain at least one digit"));
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			PasswordCheckerUtility.isValidPassword(passwords.get(6));	// Ensures that a valid password passes the test
		} catch (Exception e) {
			fail();
		}

	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> output = PasswordCheckerUtility.getInvalidPasswords(passwords);	// Ensures that all invalid passwords receive the proper fail message
		assertTrue(output.get(0).equals(passwords.get(0) + " The password must be at least 6 characters long"));
		assertTrue(output.get(1).equals(passwords.get(1) + " The password must contain at least one uppercase alphabetic character"));
		assertTrue(output.get(2).equals(passwords.get(2) + " The password must contain at least one lowercase alphabetic character"));
		assertTrue(output.get(3).equals(passwords.get(3) + " The password must contain at least one digit"));
		assertTrue(output.get(4).equals(passwords.get(4) + " The password must contain at least one digit"));
		assertTrue(output.get(5).equals(passwords.get(5) + " The password must contain at least one digit"));
	}
	
}

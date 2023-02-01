
public class NoDigitException extends Exception {
	NoDigitException(){
		super("The password must contain at least one digit"); // Creates an exception for if the password has no digit
	}
}

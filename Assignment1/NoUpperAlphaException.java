
public class NoUpperAlphaException extends Exception {
	NoUpperAlphaException(){
		super("The password must contain at least one uppercase alphabetic character"); // Creates an exception for if the password has no uppercase letter
	}
}

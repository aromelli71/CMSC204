
public class LengthException extends Exception {
	LengthException(){
		super("The password must be at least 6 characters long"); // Creates an exception for if the password is too short
	}
}
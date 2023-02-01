
public class InvalidSequenceException extends Exception {
	InvalidSequenceException(){
		super("The password cannot contain more than two of the same character in sequence."); // Creates an exception for if the password has an invalid sequence
	}
}

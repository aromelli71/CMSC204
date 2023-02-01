
public class UnmatchedException extends Exception {
	UnmatchedException(){
		super("Passwords do not match"); // Creates an exception for if the passwords in the GUI boxes do not match
	}
}

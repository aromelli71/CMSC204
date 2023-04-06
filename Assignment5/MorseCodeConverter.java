import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	
	private static MorseCodeTree tree = new MorseCodeTree();

	/**
	 * Converts a Morse file to an English string using the convertToEnglish(String) function
	 * @param inputFile containing the Morse to be translated
	 * @return a String containing the English translation
	 * @throws FileNotFoundException if the file is not found
	 */
	public static String convertToEnglish(File inputFile) throws FileNotFoundException {
		Scanner fin = new Scanner(inputFile);
		String contents = fin.nextLine().trim();
		fin.close();
		return convertToEnglish(contents);
	}

	/**
	 * Converts a Morse string to an English string
	 * @param string to be translated
	 * @return an English string translation
	 */
	public static String convertToEnglish(String string) {
		String output = "";
		for(String word : string.split(" ")) {
			if(word.equals("/"))
					output += " ";
			else output += tree.fetch(word);
		}
		return output;
	}

	/**
	 * Prints the tree in an arraylist form
	 * @return the String print of the tree
	 */
	public static String printTree() {
		String output= "";
		ArrayList<String> treeArr = tree.toArrayList();
		for (String element: treeArr) {
			output+= element + " ";

		}
		return output.trim();
	}

}
import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
	public TreeNode<String> treeRoot = new TreeNode<String>();
	
	/**
	 * 
	 * Creates an instance of a tree by building it
	 */
	public MorseCodeTree() {
		buildTree();
	}
	
	/**
	 * Adds a node to the tree
	 * @param root of the tree
	 * @param code for the node
	 * @param letter for the node
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		if (code.length() == 1) {
			if (code.equals("-")) {
				root.rightChild = new TreeNode<String>(letter);
			}
			else root.leftChild = new TreeNode<String>(letter);
		} else {
			if (code.charAt(0) == '.') {
				addNode(root.leftChild, code.substring(1), letter);
			}
			else addNode(root.rightChild, code.substring(1), letter);
		}
	}
	
	/**
	 * Builds the morse tree by inserting all the English letters
	 */
	@Override
	public void buildTree() {
		setRoot(new TreeNode<String>(""));
		insert(".", "e");
		insert("-", "t");
		insert(".-", "a");
		insert("..", "i");
		insert("--", "m");
		insert("-.", "n");
		insert("-..", "d");
		insert("--.", "g");
		insert("-.-", "k");
		insert("---", "o");
		insert(".-.", "r");
		insert("...", "s");
		insert("..-", "u");
		insert(".--", "w");
		insert("-...", "b");
		insert("-.-.", "c");
		insert("..-.", "f");
		insert("....", "h");
		insert(".---", "j");
		insert(".-..", "l");
		insert(".--.", "p");
		insert("--.-", "q");
		insert("...-", "v");
		insert("-..-", "x");
		insert("-.--", "y");
		insert("--..", "z");
	}

	/**
	 * Returns an error, this method is unsupported
	 * @param data
	 * @return
	 * @throws UnsupportedOperationException always, this method is unsupported
	 */
	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new java.lang.UnsupportedOperationException();
	}

	@Override
	/**
	 * fetches the English translation of a morse code representing one letter
	 * @param code for one letter, to be translated
	 * @return a string representation of the letter
	 */
	public String fetch(String code) {
		return fetchNode(treeRoot, code);
	}

	@Override
	/**
	 * fetches the English translation of a morse code representing one letter
	 * @param treeRoot representing the tree to be used
	 * @param code for one letter, to be translated
	 * @return a string representation of the letter
	 */
	public String fetchNode(TreeNode<String> treeRoot, String code) {
		if (code.length() == 1) {
			if (code.equals(".")) {
				return treeRoot.leftChild.getData();
			}
			else return treeRoot.rightChild.getData();
		} else {
			if (code.charAt(0) == '-') {
				return fetchNode(treeRoot.rightChild, code.substring(1));
			}
			else return fetchNode(treeRoot.leftChild, code.substring(1));
		}
	}

	@Override
	/**
	 * Returns the tree's root
	 * @return treeRoot
	 */
	public TreeNode<String> getRoot() {
		return treeRoot;
	}

	@Override
	/**
	 * Inserts a letter to the tree
	 * @param code for the node
	 * @param letter for the node
	 */
	public void insert(String code, String letter) {
		addNode(treeRoot, code, letter);
	}
	
	@Override
	/**
	 * Adds a string representation of an LNR traversal of the tree to an ArrayList
	 * @param treeRoot pointer to the tree to be used
	 * @param list pointer to the ArrayList the data should be added to
	 */
	public void LNRoutputTraversal(TreeNode<String> treeRoot, ArrayList<String> list) {
		if (treeRoot == null) {
			return;
		}
		LNRoutputTraversal(treeRoot.leftChild, list);
		list.add(treeRoot.getData());
		LNRoutputTraversal(treeRoot.rightChild, list);
	}
	
	@Override
	/**
	 * Modifies the root of the tree
	 * @param newNode containing the new root
	 */
	public void setRoot(TreeNode<String> newNode) {
		treeRoot = new TreeNode<String>(newNode);
	}

	@Override
	/**
	 * Returns a string arraylist representation of the tree data
	 * @return
	 */
	public ArrayList<String> toArrayList() {
		ArrayList<String> output = new ArrayList<String>();
		LNRoutputTraversal(treeRoot, output);
		return output;
	}

	@Override
	/**
	 * Returns an error, this method is unsupported
	 * @return
	 * @throws UnsupportedOperationException always, this method is unsupported
	 */
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new java.lang.UnsupportedOperationException();
	}
}
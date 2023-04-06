
public class TreeNode<T> {
	public TreeNode<T> leftChild;
	public T data;
	public TreeNode<T> rightChild;
	
	/**
	 * Creates an empty instance of TreeNode
	 */
	public TreeNode() {
		data  = null;
		leftChild = null;
		rightChild = null;
	}
	
	/**
	 * Creates a copy of a TreeNode
	 * @param node to be copied
	 */
	public TreeNode(TreeNode<T> node) {
		data = node.data;
		leftChild = node.leftChild;
		rightChild = node.rightChild;
	}
	
	/**
	 * Creates an instance of TreeNode with the given data and no children
	 * @param dataNode containing the data to be stored
	 */
	public TreeNode(T dataNode) {
		data  = dataNode;
		leftChild = null;
		rightChild = null;
	}
	
	/**
	 * Returns the data contained in a node
	 * @return the data in the node
	 */
	public T getData() {
		return data;
	}

}

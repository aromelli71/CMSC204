/**
 * Class to handle the storage and maintenance of a SORTED double linked list
 * @author Archer Romelli
 * @param <T>
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{
	java.util.Comparator<T> comparator;
	
	/**
	 * Creates an instance of a sorted double linked list,
	 * sorted via the provided comparator
	 * @param compareableObject
	 */
	SortedDoubleLinkedList(java.util.Comparator<T> compareableObject) {
		super();
		comparator = compareableObject;
	}
	
	/**
	 * Adds the provided data to the proper location in the list
	 * @param data to be added to the list
	 */
	public void add(T data) {
		if (size == 0) {
			super.addToFront(data);
			return;
		}
		if (comparator.compare(head.getData(), data) >= 0) {
			super.addToFront(data);
			return;
		}
		if (comparator.compare(tail.getData(), data) <= 0) {
			super.addToEnd(data);
			return;
		}
		
		int backCompare = -10, frontCompare;
		Node currentFront = head;
		while (true) {
			frontCompare = comparator.compare(currentFront.getData(), data);
			if (backCompare < 0 && frontCompare > 0 || frontCompare == 0) {
				Node insert = new Node(data);
				insert.setPrev(currentFront.getPrev());
				insert.setNext(currentFront);
				currentFront.getPrev().setNext(insert);
				currentFront.setPrev(insert);
				size++;
				break;
			}
			backCompare = frontCompare;
			currentFront = currentFront.getNext();
		}
	}

	/**
	 * This function throws an exception, as this operation is
	 * unsupported for this list
	 * @param data
	 * @throws java.lang.UnsupportedOperationException
	 */
	public void addToEnd(T data) throws java.lang.UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * This function throws an exception, as this operation is
	 * unsupported for this list
	 * @param data
	 * @throws java.lang.UnsupportedOperationException
	 */
	public void addToFront(T data) throws java.lang.UnsupportedOperationException{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * This function returns an iterator for the list
	 * by calling the superclass iterator constructor
	 * @return a reference to the iterator for use
	 */
	public java.util.ListIterator<T> iterator(){
		return super.iterator();
	}
	
	/**
	 * Removes the indicated data from the array using the provided
	 * comparator via the superclass function
	 * @param data
	 * @param comparator
	 * @return
	 */
	public Node remove(T data, java.util.Comparator<T> comparator){
		return super.remove(data, comparator);
	}
}

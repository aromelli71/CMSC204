/**
 * Class to handle the storage and maintenance of a basic double linked list
 * @author Archer Romelli
 * @param <T>
 */
public class BasicDoubleLinkedList<T> implements Iterable<T>{
	protected Node head, tail;
	protected int size;
	
	/**
	 * Creates an instance of a basic double linked list
	 */
	BasicDoubleLinkedList(){
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * returns the size of the basic double linked list
	 * @return size of the list
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Inserts the provided data into the end of the basic double linked list
	 * @param data
	 */
	public void addToEnd(T data) {
		if (size == 0) {
			tail = new Node(data);
			head = tail;
		} else {
			Node newNode = new Node(data);
			newNode.setPrev(tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		size++;
	}
	
	/**
	 * Inserts the provided data into the front of the basic double linked list
	 * @param data
	 */
	public void addToFront(T data) {
		if (size == 0) {
			head = new Node(data);
			tail = head;
		} else {
			Node newNode = new Node(data);
			newNode.setNext(head);
			head.setPrev(newNode);
			head = newNode;
		}
		size++;
	}
	
	/**
	 * returns the first item in the basic double linked list without deleting it
	 * @return data
	 */
	public T getFirst() {
		return head.getData();
	}
	
	/**
	 * returns the last item in the basic double linked list without deleting it
	 * @return data
	 */
	public T getLast() {
		return tail.getData();
	}
	
	/**
	 * Creates an instance of the inner class DoubleLinkedListIterator
	 * @return a reference to the new iterator for use
	 */
	public java.util.ListIterator<T> iterator(){
		DoubleLinkedListIterator itr = new DoubleLinkedListIterator();
		itr.following = head;
		return itr;
		
	}
	
	/**
	 * Removes the indicated targetData by comparing the targetData with each
	 * item in the array via the provided comparator
	 * @param targetData
	 * @param comparator
	 * @return
	 */
	public Node remove(T targetData, java.util.Comparator<T> comparator) {
		Node current = head;
		
		if(size == 0) {
			return null;
		}
		if(size == 1 && comparator.compare(targetData, current.getData())==0) {
			head = tail = null;
			size--;
			return current;
		}
		while(current != null) {
			if(comparator.compare(targetData, current.getData())==0) {
				if(current.equals(head)) {
					head = current.getNext();
					head.setPrev(null);
					current.setNext(null);
					break;
				} else if(current.equals(tail)) {
					tail = current.getPrev();
					tail.setNext(null); 
					current.setPrev(null);
					break;
				} else {
					current.getPrev().setNext(current.getNext());
					current.getNext().setPrev(current.getPrev());
					break;
				}
			}
			current = current.getNext();
		}
		size--;
		return current;
	}
	
	/**
	 * Retrieves the first element of the list and deletes it
	 * @return the retrieved element
	 */
	public T retrieveFirstElement() {
		if (size == 0)
			return null;
		Node remove = head;
		head = head.getNext();
		remove.setNext(null);
		head.setPrev(null);
		size--;
		return remove.getData();
	}
	
	/**
	 * Retrieves the last element of the list and deletes it
	 * @return the retrieved element
	 */
	public T retrieveLastElement() {
		if (size == 0)
			return null;
		Node remove = tail;
		tail = tail.getPrev();
		remove.setPrev(null);
		tail.setNext(null);
		size--;
		return remove.getData();
	}
	
	/**
	 * Converts the list to an ArrayList
	 * @return the created ArrayList
	 */
	public java.util.ArrayList<T> toArrayList() {
		java.util.ArrayList<T> output = new java.util.ArrayList<T>();
		DoubleLinkedListIterator iterator = (DoubleLinkedListIterator) iterator();
		while(iterator.hasNext()) {
			output.add(iterator.next());	
		}	
		return output;
	}
	
	/**
	 * Inner class to represent nodes in the list
	 * @author Archer Romelli
	 */
	protected class Node{
		private T data = null;
		private Node prev = null, next = null;
		
		/**
		 * Creates an instance of a node in the list
		 * @param dataNode to contain the data to be stored in the node
		 */
		Node(T dataNode){
			this.data = dataNode;
		}

		/**
		 * returns the data inside the node
		 * @return the stored data
		 */
		public T getData() {
			return data;
		}
		
		/**
		 * Set the stored node data to the provided data
		 * @param data to contain the new data
		 */
		public void setData(T data) {
			this.data = data;
		}
		
		/**
		 * returns node's pointer to the previous node
		 * @return the previous node pointer
		 */
		public Node getPrev() {
			return prev;
		}
		
		/**
		 * Set the stored node's previous pointer to the provided pointer
		 * @param prev to contain the new previous pointer
		 */
		public void setPrev(Node prev) {
			this.prev = prev;
		}
		
		/**
		 * returns node's pointer to the next node
		 * @return the next ndoe pointer
		 */
		public Node getNext() {
			return next;
		}
		
		/**
		 * Set the stored node's next pointer to the provided pointer
		 * @param prev to contain the new next pointer
		 */
		public void setNext(Node next) {
			this.next = next;
		}
		
	}
	
	/**
	 * Inner class to act as an iterator for the list
	 * @author Archer Romelli
	 */
	protected class DoubleLinkedListIterator implements java.util.ListIterator<T>{

		protected Node following;
		protected Node prior = null;
		
		@Override
		/**
		 * Determines whether or not the iterator has a next value in the list
		 * @return a boolean representing if the iterator has a next value
		 */
		public boolean hasNext() {
			return (following != null);
		}

		@Override
		/**
		 * Shifts the iterator over by one item in the list
		 * @return the item the iterator passed over
		 * @throws java.util.NoSuchElementException
		 */
		public T next() throws java.util.NoSuchElementException {
			if(!hasNext())
				throw new java.util.NoSuchElementException();
			T output = following.getData();
			prior = following;
			following = following.next;
			return output;
		}
		
		@Override
		/**
		 * Determines whether or not the iterator has a previous value in the list
		 * @return a boolean representing if the iterator has a previous value
		 */
		public boolean hasPrevious() {
			return (prior != null);
		}

		@Override
		/**
		 * Shifts the iterator back by one item in the list
		 * @return the item the iterator passed over
		 * @throws java.util.NoSuchElementException
		 */
		public T previous() throws java.util.NoSuchElementException{
			if(!hasPrevious())
				throw new java.util.NoSuchElementException();
			T output = prior.getData();
			following = prior;
			prior = prior.prev;
			return output;
		}

		@Override
		/**
		 * This function throws an exception, as this operation is
		 * unsupported for this list
		 * @return
		 * @throws UnsupportedOperationException
		 */
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		/**
		 * This function throws an exception, as this operation is
		 * unsupported for this list
		 * @return
		 * @throws UnsupportedOperationException
		 */
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		/**
		 * This function throws an exception, as this operation is
		 * unsupported for this list
		 * @return
		 * @throws UnsupportedOperationException
		 */
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		/**
		 * This function throws an exception, as this operation is
		 * unsupported for this list
		 * @return
		 * @throws UnsupportedOperationException
		 */
		public void set(T e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		/**
		 * This function throws an exception, as this operation is
		 * unsupported for this list
		 * @return
		 * @throws UnsupportedOperationException
		 */
		public void add(T arg0) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		
	}
}

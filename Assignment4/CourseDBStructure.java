import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface {
	
	public LinkedList<CourseDBElement>[] hashTable;

	/**
	 * Used only for testing, this creates an instance of a CDS with the provided length,
	 * without performing the arithmetic to calculate the approximate load factor
	 * @param s string to indicate this constructor instead of the regular constructor
	 * @param length with the desired database length
	 */
	public CourseDBStructure(String s, int length) {
		hashTable = new LinkedList[length];
		for (int j = 0; j < length; j++) {
			hashTable[j] = null;
		}
	}

	/**
	 * Creates an instance of a CDS with approximately the desired length
	 * by finding the smallest 4K+3 prime number above the desire length/2
	 * @param length
	 */
	public CourseDBStructure(int length) {
		int size = (int) (length / 1.5);
		while(!isPrime(size) || size % 4 != 3)
			size++;
		hashTable = new LinkedList[size];
		for (int j = 0; j < size; j++) {
			hashTable[j] = null;
		}
	}

	/**
	 * Adds a provided element to the database by hashing it and placing it in the proper bucket
	 * @param element to be added
	 */
	@Override
	public void add(CourseDBElement element) {
		int hashCode = element.crn % hashTable.length;
		if (hashTable[hashCode] == null) {
			hashTable[hashCode] = new LinkedList<CourseDBElement>();
			hashTable[hashCode].add(element);
		} else {
			Iterator<CourseDBElement> i = hashTable[hashCode].iterator();
			CourseDBElement current = null;
			while(i.hasNext()) {
				current = i.next();
				if(current.crn == element.crn) {
					current.courseID = element.courseID;
					current.credits = element.credits;
					current.roomNumber = element.roomNumber;
					current.instructorName = element.instructorName;
					return;
				}
			}
			hashTable[hashCode].addLast(element);
		}
	}

	/**
	 * Searches the database for a CDE with the specfied CRN
	 * @param crn to be searched for
	 * @return the CDE with the provided CRN
	 * @throws IOException if no CDE with the provided CRN is found
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		int hashCode = crn % hashTable.length;
		if (hashTable[hashCode] == null || hashTable[hashCode].isEmpty())
			throw new IOException();
		Iterator<CourseDBElement> i = hashTable[hashCode].iterator();
		CourseDBElement current = null;
		while(i.hasNext()) {
			current = i.next();
			if(current.crn == crn)
				return current;
		}
		throw new IOException();
	}

	/**
	 * Traverses the database to add each CDE element's information to an ArrayList for viewing
	 * @return an ArrayList of Strings displaying the CDE information
	 */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> output = new ArrayList<>();
		CourseDBElement current = null;
		for(LinkedList<CourseDBElement> bucket : hashTable)
			if (bucket != null)
				for(Object element : bucket.toArray()) {
					current = (CourseDBElement)(element);
					output.add("\nCourse:" + current.courseID + " CRN:" + current.crn +
							" Credits:" + current.credits + " Instructor:" +
							current.instructorName + " Room:" + current.roomNumber);
				}
		return output;
	}

	/**
	 * Returns the hash-table's size
	 * @return the hash-table's size
	 */
	@Override
	public int getTableSize() {
		return hashTable.length;
	}

	/**
	 * Non-specified personal function:
	 * Tests if a number is prime
	 * @param n number to be tested
	 * @return true if the number is prime, false otherwise
	 */
	private boolean isPrime(int n) {
		if (n <= 1)
			return false;
		else for (int i = 2; i < n; i++)
			if (n % i == 0)
				return false;
		return true;
	}
	
}

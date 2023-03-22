/**
 * An element class to store one course's information
 * @author Archer Romelli
 */
public class CourseDBElement implements Comparable{

	// Variables to store element information
	public String courseID;
	public int crn;
	public int credits;
	public String roomNumber;
	public String instructorName;
	
	/**
	 * Creates an instance of a data element, containing the provided information
	 * @param courseID
	 * @param crn
	 * @param credits
	 * @param roomNumber
	 * @param instructorName
	 */
	public CourseDBElement(String courseID, int crn, int credits, String roomNumber, String instructorName) {
		this.courseID = courseID;
		this.crn = crn;
		this.credits = credits;
		this.roomNumber = roomNumber;
		this.instructorName = instructorName;
	}
	
	/**
	 * Creates an instance of a database element without any information stored,
	 * as such all fields are set to default values (zero or null)
	 */
	public CourseDBElement() {
		crn = credits = 0;
		courseID = roomNumber = instructorName = null;
	}

	/**
	 * Compares two database elements to see if they contain the same information
	 * @param o representing the other database element
	 * @return an integer representing either equal or unequal
	 */
	@Override
	public int compareTo(Object o) {
		CourseDBElement other;
		other = (CourseDBElement)(o);
		if (other == null)
			return 1;
		else if (other.courseID.equals(this.courseID) && other.crn == this.crn &&
				other.credits == this.credits && other.roomNumber.equals(this.roomNumber) &&
				other.instructorName.equals(this.instructorName))
			return 0;
		return 1;
	}

	/**
	 * Returns the element's CRN
	 * @return the element's CRN
	 */
	public int getCRN() {
		return crn;
	}

	/**
	 * Returns the course ID
	 * @return the course ID
	 */
	public String getID() {
		return courseID;
	}

	/**
	 * Returns the room number
	 * @return the room number
	 */
	public String getRoomNum() {
		return roomNumber;
	}

	/**
	 * Sets the CRN
	 * @param parseInt to set the CRN to
	 */
	public void setCRN(int parseInt) {
		this.crn = parseInt;
		
	}

}

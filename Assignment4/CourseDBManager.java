import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A data manager class for high-level control over the data
 * @author Archer Romelli
 */
public class CourseDBManager implements CourseDBManagerInterface{

	// Stores an instance of the necessary structure class for data storage
	public CourseDBStructure cds = null;
	
	/**
	 * 
	 * Creates an instance of a data structure to hold approximately 20 slots by default
	 */
	CourseDBManager(){
		cds = new CourseDBStructure(20);
	}
	
	/**
	 * Uses the CDS add() function to add a new CDE eith the provided information
	 * @param id
	 * @param crn
	 * @param credits
	 * @param roomNum
	 * @param instructor
	 */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		cds.add(new CourseDBElement(id,crn,credits,roomNum,instructor));
		
	}

	/**
	 * Uses the CDS get() function to retrieve a CDE with the provided CRN
	 * @param crn to search for
	 * @return the CDE with that CRN
	 */
	@Override
	public CourseDBElement get(int crn) {
		try {
			return cds.get(crn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Reads a provided file, scanning the text and inputting the information into the database
	 * @param input file, containing the data to be read into the database
	 * @throws FileNotFoundException if the provided file is not found
	 */
	@Override
	public void readFile(File input) throws FileNotFoundException {
		try {
			BufferedReader b = new BufferedReader(new FileReader(input));
			String s;
			while((s=b.readLine())!=null) {
				String[] elements = s.split(" ");
				String instructorName = "";
				for(int i=4;i<elements.length;i++) {
					instructorName += elements[i];
					if(i != elements.length-1)
						instructorName += " ";
				}
				cds.add(new CourseDBElement(elements[0],Integer.parseInt(elements[1]),Integer.parseInt(elements[2]),elements[3],instructorName)); 
			}
			b.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Uses the CDS showAll() function to display the database information in an ArrayList format
	 * @return
	 */
	@Override
	public ArrayList<String> showAll() {
		return cds.showAll();
	}

}

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseDBManager_STUDENT_Test {

	CourseDBManager cdb;
	
	@BeforeEach
	void setUp() throws Exception {
	cdb = new CourseDBManager();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCourseDBManager() {
		assertEquals(cdb.cds.getTableSize(),19);
	}

	@Test
	void testAdd() {
		cdb.add("CMSC204", 12345, 4, "HT301", "Prof. Kuijt");
		try {
			cdb.cds.get(12345);
		} catch(IOException e){
			fail("should not have thrown exception");
		}
	}

	@Test
	void testGet() {
		cdb.add("CMSC204", 12345, 4, "HT301", "Prof. Kuijt");
		assertTrue(cdb.get(12345).courseID.equals("CMSC204"));
		assertTrue(cdb.get(12345).credits==4);
		assertTrue(cdb.get(12345).roomNumber.equals("HT301"));
		assertTrue(cdb.get(12345).instructorName.equals("Prof. Kuijt"));
	}

	@Test
	void testReadFile() throws FileNotFoundException {
		File inputFile = new File("Test1.txt");
		PrintWriter inFile = new PrintWriter(inputFile);
		inFile.println("CMSC204 12345 4 HT301 Prof. Kuijt");
		inFile.close();
		cdb.readFile(inputFile);
		try {
			cdb.cds.get(12345);
		} catch(IOException e){
			fail("should not have thrown exception");
		}
	}

	@Test
	void testShowAll() {
		cdb.add("CMSC204", 11111, 4, "HT301", "Prof. Kuijt");
		cdb.add("CMSC204", 22222, 4, "HT301", "Prof. Kuijt");
		cdb.add("CMSC204", 33333, 4, "HT301", "Prof. Kuijt");
		ArrayList<String> output = cdb.showAll();
		assertEquals(output.get(0).trim(),"Course:CMSC204 CRN:33333 Credits:4 Instructor:Prof. Kuijt Room:HT301");
		assertEquals(output.get(1).trim(),"Course:CMSC204 CRN:22222 Credits:4 Instructor:Prof. Kuijt Room:HT301");
		assertEquals(output.get(2).trim(),"Course:CMSC204 CRN:11111 Credits:4 Instructor:Prof. Kuijt Room:HT301");
	}

}

package oopslab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class OnlineTestQP2SolutionTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalErr = System.err;
	private final InputStream originalIn = System.in;

	private String studID = "QP2_2019A7PS0051P";
	private PrintStream fileOut;

	@BeforeAll
	public void setup() {
		try {
			File dir = new File("reports");
			if (!dir.exists()) {
				dir.mkdir();
			}

			fileOut = new PrintStream(new File("reports\\Report_" + studID + ".txt"));
			System.setOut(fileOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Question 1. [BINARY MARKING: 2.5 MARKS]
	 * 
	 * 4 cases for name
	 * 
	 */
	@Test
	@Order(1)
	public void testSetName() {

		System.out.println("-------------------------------------");
		System.out.println("OOPS LAB Q2 Eval Report : " + studID);
		System.out.println("-------------------------------------");
		System.out.println("NOTE. Please change the main classname to one mentioned in student's file.");
		System.out
				.println("NOTE. Please change the Student and Aggregate classes according to student submitted file.");
		System.out.println("NOTE. Please change the id name for the student whose submission is being evaluated.");

		System.out.println("\n\n-------------------------------------");
		System.out.println("Test 1:");
		System.out.println("-------------------------------------");

		String message1 = "First or Last Name must contain characters only";
		String message2 = "First or Last Name should start with a capital letter";

		System.out.println("Case1: ja1, Kumar, 1");
		System.out.println("-----------------");
		try {
			Student s = new Student("ja1", "Kumar", 1);
			fail("Case1");
			System.out.println("Failed: No exception thrown.");
		} catch (ValidationException e) {
			String out = new ArrayList<Exception>(e.get()).get(0).getMessage();
			System.out.println(out);
			assertEquals(message1, out);
			System.out.println("Pass");
		}

		System.out.println("Case2: Jay, Ku34r, 1");
		System.out.println("-----------------");
		try {
			Student s = new Student("Jay", "Ku34r", 1);
			fail("Case2");
			System.out.println("Failed: No exception thrown.");
		} catch (ValidationException e) {
			String out = new ArrayList<Exception>(e.get()).get(0).getMessage();
			System.out.println(out);
			assertEquals(message1, out);
			System.out.println("Pass");
		}

		System.out.println("Case3: jay, Kumar, 1");
		System.out.println("-----------------");
		try {
			Student s = new Student("jay", "Kumar", 1);
			fail("Case3");
			System.out.println("Failed: No exception thrown.");
		} catch (ValidationException e) {
			String out = new ArrayList<Exception>(e.get()).get(0).getMessage();
			System.out.println(out);
			assertEquals(message2, out);
			System.out.println("Pass");
		}

		System.out.println("Case4: Jay, kumar, 1");
		System.out.println("-----------------");
		try {
			Student s = new Student("Jay", "kumar", 1);
			fail("Case4");
			System.out.println("Failed: No exception thrown.");
		} catch (ValidationException e) {
			String out = new ArrayList<Exception>(e.get()).get(0).getMessage();
			System.out.println(out);
			assertEquals(message2, out);
			System.out.println("Pass");
		}

		// valid case
		System.out.println("Case5: Jay, Kumar, 1");
		System.out.println("-----------------");
		try {
			Student s = new Student("Jay", "Kumar", 1);
			System.out.println("Pass");
		} catch (ValidationException e) {
			fail("Exception thrown for valid case.");
			System.out.println("Fail. Exception thrown for valid case.");
		}

	}

	/*
	 * Question 2. [BINARY MARKING: 0.5 MARKS]
	 * 
	 * CGPA is not in the range [0 – 10]
	 * 
	 */
	@Test
	@Order(2)
	public void testSetCGPA() {
		System.out.println("\n\n\n-------------------------------------");
		System.out.println("Test 2:");
		System.out.println("-------------------------------------");

		String message = "CGPA not in range";

		System.out.println("Higher: 12");
		System.out.println("-----------------");
		try {
			Student s = new Student("Jay", "Kumar", 12);
			fail("Exception not thrown for value higher than 10");
			System.out.println("Failed: No exception thrown.");
		} catch (ValidationException e) {
			String out = new ArrayList<Exception>(e.get()).get(0).getMessage();
			System.out.println(out);
			assertEquals(message, out);
			System.out.println("Pass");
		}

		System.out.println("\nLower: -1");
		System.out.println("-----------------");
		try {
			Student s = new Student("Jay", "Kumar", -1);
			fail("Exception not thrown for value lesser than 0");
			System.out.println("Failed: No exception thrown.");
		} catch (ValidationException e) {
			String out = new ArrayList<Exception>(e.get()).get(0).getMessage();
			System.out.println(out);
			assertEquals(message, out);
			System.out.println("Pass");
		}

		System.out.println("\nValid: 5");
		System.out.println("-----------------");
		try {
			Student s = new Student("Jay", "Kumar", 5);
			System.out.println("Pass");
		} catch (ValidationException e) {
			fail("Exception thrown for valid case.");
			System.out.println("Fail. Exception thrown for valid case.");
		}
	}

	/*
	 * Question 3. hashcode (manual) [BINARY MARKING: 0.5 MARKS]
	 * 
	 * 
	 */
	@Test
	@Order(3)
	public void testHashCode() {
		System.out.println("\n\n\n-------------------------------------");
		System.out.println("Test 3:");
		System.out.println("-------------------------------------");
		System.out.println("Note: Check code manually.");

		try {

			Student s = new Student("Jay", "Kumar", 5);
			if (s.idNo >= 1 && s.idNo <= 1000) {
				System.out.println("Pass");
			} else {
				System.out.println("Fail. Invalid range for hashcode.");
				fail("Fail. Invalid range for hashcode.");
			}

		} catch (ValidationException e) {
			fail("Exception thrown for valid case.");
			System.out.println("Fail. Exception thrown for valid case.");
		}
	}

	/*
	 * Question 4. [BINARY MARKING: 02 MARKS]
	 * 
	 *
	 * 
	 * 
	 */
	@Test
	@Order(4)
	public void testStudentConstructor() {
		System.out.println("\n\n\n-------------------------------------");
		System.out.println("Test 4:");
		System.out.println("-------------------------------------");

		System.out.println("Case1: Invalid");
		System.out.println("-----------------");
		// change according to student class of file
		try {
			Student s = new Student("jay", "Kumar123", 4);
			fail("Case1");
			System.out.println("Failed: No exception thrown.");
		} catch (ValidationException e) {
			System.out.println("Pass");
		}

		System.out.println("Case2: Valid");
		System.out.println("-----------------");
		// change according to student class of file
		try {
			Student s = new Student("Jay", "Kumar", 5);
			System.out.println("Pass");
		} catch (ValidationException e) {
			fail("Exception thrown for valid case.");
			System.out.println("Fail. Exception thrown for valid case.");
		}

	}

	/*
	 * Question 5. [BINARY MARKING: 5 MARKS]
	 * 
	 * NOT ALLOWED TO USE THE STRING TOKENIZER
	 * 
	 * Use lambda expression to print the array list of exceptions
	 * 
	 * 
	 */
	@Test
	@Order(5)
	public void testPopulateMap() {

		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));

		Map<String, Student> hmMaster = MasterQP2Solution.populateMap();
		String outMaster = outContent.toString();

		outContent.reset();

		// Change here
		Map<String, Student> hm = StudentSol.populateMap();

		String out = outContent.toString();

		System.setOut(fileOut);
		System.setErr(originalErr);

		System.out.println("\n\n\n-------------------------------------");
		System.out.println("Test 5:");
		System.out.println("-------------------------------------");
		System.out.println("NOTE. Manual check: NOT ALLOWED TO USE THE STRING TOKENIZER.");
		System.out.println("NOTE. Manual check: Use lambda expression to print the array list of exceptions.");
		System.out.println("NOTE. Check assert failures.");
		System.out.println("Expected Output: ");
		System.out.println("-----------------");
		System.out.println(outMaster);
		System.out.println("Actual Output: ");
		System.out.println("---------------");
		System.out.println(out);

		assertNotNull(hm);
		assertEquals(hmMaster.size(), hm.size());
		assertEquals(outMaster, out);

		// this also checks validity of key
		for (String key : hmMaster.keySet()) {
			Student s1 = hmMaster.get(key);
			Student s2 = hm.getOrDefault(key, null);
			assertNotNull(s2);
			// dont check id coz its randomly generated
			// assertEquals(s1.idNo,s2.idNo);
			assertEquals(s1.fName, s2.fName);
			assertEquals(s1.lName, s2.lName);
			assertEquals(s1.cgpa, s2.cgpa, 0.0000000001);
		}
	}

	/*
	 * Question 6. [BINARY MARKING: 4 MARKS]
	 * 
	 * lambda expression for the Comparator interface
	 * 
	 * Use lambda expression to print the array list of exceptions
	 * 
	 * 
	 */
	@Test
	@Order(6)
	public void testSortByDept() {

		

		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));

		Map<String, Student> hmMaster = MasterQP2Solution.populateMap();

		System.setOut(fileOut);
		System.setErr(originalErr);

		String[] depts = new String[] { "CSE", "EEE", "Mech" };

		System.out.println("\n\n\n-------------------------------------");
		System.out.println("Test 6:");
		System.out.println("-------------------------------------");
		System.out.println("NOTE. Manual check: lambda expression for the Comparator interface.");
		System.out.println("NOTE. Manual check: Use lambda expression to print the array list of exceptions.");

		for (String s : depts) {
			List<Student> sortedListMaster = MasterQP2Solution.sortByDept(hmMaster, s);

			// Change here
			List<Student> sortedList = StudentSol.sortByDept(hmMaster, s);
			// pass master map as input

			System.out.println("Dept: " + s);
			System.out.println("---------------------------------");
			System.out.println("Expected: ");
			System.out.println(Arrays.toString(sortedListMaster.toArray()));
			System.out.println("Actual: ");
			System.out.println(Arrays.toString(sortedList.toArray()));

			assertNotNull(sortedList);
			assertEquals(sortedListMaster.size(), sortedList.size());

			for (int i = 0; i < sortedListMaster.size(); i++) {
				Student s1 = sortedListMaster.get(i);
				Student s2 = sortedList.get(i);
				// dont check id coz its randomly generated
				// assertEquals(s1.idNo,s2.idNo);
				assertEquals(s1.fName, s2.fName);
				assertEquals(s1.lName, s2.lName);
				assertEquals(s1.cgpa, s2.cgpa, 0.0000000001);
			}
			System.out.println("");
		}
	}

	/*
	 * Question 7. [BINARY MARKING: 2 MARKS]
	 * 
	 * 
	 * 
	 */
	@Test
	@Order(7)
	public void testWriteRecords() {

		String[] depts = new String[] { "CSE", "EEE", "Mech" };

		System.out.println("\n\n\n-------------------------------------");
		System.out.println("Test 7:");
		System.out.println("-------------------------------------");
		System.out.println("NOTE. Manual check: Use lambda expression to print the array list of exceptions.");
		System.out.println("NOTE. Change output file according to the student submitted file.");
		System.out.println("NOTE. Make sure MasterOutput*.txt files are present in classpath. Pull code from github.");

		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		// Change here
		Map<String, Student> hmMaster = MasterQP2Solution.populateMap();
		System.setOut(fileOut);
		System.setErr(originalErr);

		for (String s : depts) {
			try {
				System.out.println("Dept: " + s);
				System.out.println("---------------------------------");

				String outMaster = new String(Files.readAllBytes(Paths.get("MasterOutput" + s + ".txt")));

				System.out.println("Expected: ");
				System.out.println(outMaster);

				List<Student> sortedList = MasterQP2Solution.sortByDept(hmMaster, s);
				//// Change here
				StudentSol.writeRecords(sortedList);
				String out = new String(Files.readAllBytes(Paths.get("output.txt")));

				System.out.println("Actual: ");
				System.out.println(out);
				// assertEquals(outMaster, out);
				System.out.println("");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	@Order(8)
	public void testMain() {

		System.out.println("\n\n\n-------------------------------------");
		System.out.println("Test 8:");
		System.out.println("-------------------------------------");
		System.out.println("NOTE. Manual check: Print the elements of the sorted list using a lambda expression.");

		String[] depts = new String[] { "CSE", "EEE", "Mech" };

		for (String s : depts) {

			System.out.println("\nDept: " + s);
			System.out.println("---------------------------------");
			InputStream inStream = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
			System.setIn(inStream);
			// change here
			StudentSol.main(null);
			System.setIn(originalIn);
		}

		if (fileOut != null)
			fileOut.close();
	}

}

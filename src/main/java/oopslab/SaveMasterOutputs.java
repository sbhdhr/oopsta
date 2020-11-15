package oopslab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import oopslab.Student.ValidationException;


public class SaveMasterOutputs {
	
	private static PrintStream originalOut = System.out;

//	public static void main(String[] args) {
//		try {
//			File dir = new File("correct");
//			if (!dir.exists()) {
//				dir.mkdir();
//			}
//			PrintStream fileOut = new PrintStream(new File("correct\\P5_text.txt"));
//			System.setOut(fileOut);
//			Map<String, Student> hmMaster = MasterQP2Solution.populateMap();
//			System.setOut(originalOut);
//			Properties properties = new Properties();
//
//			for (Map.Entry<String, Student> entry : hmMaster.entrySet()) {
//				Student s = entry.getValue();
//				properties.put(entry.getKey(), s.idNo + ";" + s.fName + ";" + s.lName + ";" + s.cgpa);
//			}
//
//			properties.store(new FileOutputStream("correct\\P5_map.properties"), null);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		readCorrectMap();
//	}

	public static Map<String, Student> readCorrectMap() {
		Map<String, Student> hmMaster = new HashMap<>();

		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("correct\\P5_map.properties"));
			for (String key : properties.stringPropertyNames()) {
				String[] arr=properties.get(key).toString().split(";");
				Student s = new Student(arr[1], arr[2],Double.parseDouble(arr[3]));
				hmMaster.put(key, s);
				//System.out.println(key+"="+s);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hmMaster;
	}
}

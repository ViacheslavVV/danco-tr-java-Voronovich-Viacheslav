package com.danco.training.ui.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleReader {

	private static final int MONTH_SHIFT = 1;
	private static final String INPUT_STUDENT_ID = "Input student id";
	private static final String INPUT_LECTURER_ID = "Input lecturer id";
	private static final String INPUT_LECTION_ID = "Input lection id";
	private static final String INPUT_COURSE_ID = "Input course id";

	private static final Logger LOGGER = LogManager.getLogger(ConsoleReader.class);

	private static Scanner scanner = new Scanner(System.in);

	public static int getInt(String message) throws RuntimeException {
		int num = 0;
		boolean validData = false;
		try {
			while (!validData) {
				try {
					System.out.print(message + ": ");
					String string = scanner.next();
					num = Integer.parseInt(string);
					validData = true;
				} catch (InputMismatchException | NumberFormatException e) {
					System.out.println("Incorrect input, try again: ");
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return num;
	}

	public static String getString(String message) throws RuntimeException {
		String resultStr = null;
		boolean validData = false;
		try {
			while (!validData) {
				try {
					System.out.print(message + ": ");
					resultStr = scanner.next();
					validData = true;
				} catch (InputMismatchException e) {
					System.out.println("Incorrect input, try again: ");
					scanner.nextLine();
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return resultStr;
	}

	public static Date getDate(String message) throws RuntimeException {
		Date resultDate = null;
		String tempStr = null;
		boolean validData = false;
		try {
			while (!validData) {
				try {
					System.out.print(message + " (dd-mm-yyyy-hour-min): ");
					tempStr = scanner.next("\\d{1,2}-\\d{1,2}-\\d{4}-\\d{1,2}-\\d{1,2}");
					String[] dateParts = tempStr.split("-");
					resultDate = new GregorianCalendar(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1])-MONTH_SHIFT,
							Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[3]),
							Integer.parseInt(dateParts[4])).getTime();
					validData = true;
				} catch (InputMismatchException e) {
					System.out.println("Incorrect input, try again.");
					scanner.nextLine();
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return resultDate;
	}

	public static String getCourseName() throws RuntimeException {
		return getString("Input name of course");
	}

	public static Date getCourseStartDate() throws RuntimeException {
		return getDate("Input start date of course");
	}

	public static Date getCourseFinalDate() throws RuntimeException {
		return getDate("Input final date of course");
	}

	public static String getLectionName() throws RuntimeException {
		return getString("Input lections's name");
	}

	public static Date getLectionDate() throws RuntimeException {
		return getDate("Input lection's date");
	}

	public static String getLecturerName() throws RuntimeException {
		return getString("Input lecturer's name");
	}

	public static int getLecturerAge() throws RuntimeException {
		int resultAge = 0;
		while (resultAge < 22 || resultAge > 100) {
			resultAge = getInt("Input lecturer's age");
		}
		return resultAge;
	}

	public static String getStudentName() throws RuntimeException {
		return getString("Input student's name");
	}

	public static int getStudentAge() throws RuntimeException {
		int resultAge = 0;
		while (resultAge < 16 || resultAge > 100) {
			resultAge = getInt("Input student's age");
		}
		return resultAge;
	}

	public static int getMenuMode() throws RuntimeException {
		return getInt("Input section number");
	}

	public static int getMaxStudentsNumber() {
		return getInt("Input max number of students");
	}

	public static int getMaxLectionsNumber() {
		return getInt("Input max number of lections");
	}

	public static String getImportFileName() {
		File file;
		String fileName = getString("Input file name('cancel' to exit)");
		while (!fileName.equals("cancel")) {
			file = new File(fileName);
			if (!file.exists()) {
				System.out.println("File not found!");
				fileName = getString("Input file name('cancel' to exit)");
			} else {
				return fileName;
			}
		}
		return null;
	}

	public static String getExportFileName() {

		return getString("Input export file name");
	}

	public static List<Object> getCourseIds() {

		return getIds(INPUT_COURSE_ID);
	}

	public static List<Object> getLectionIds() {

		return getIds(INPUT_LECTION_ID);
	}

	public static List<Object> getLecturerIds() {

		return getIds(INPUT_LECTURER_ID);
	}

	public static List<Object> getStudentIds() {

		return getIds(INPUT_STUDENT_ID);
	}

	private static List<Object> getIds(String message) {
		List<String> stringValues = new ArrayList<String>();
		List<Object> result = new ArrayList<Object>();
		String tempStr;
		System.out.println("Input '-1' to exit");
		int entityId;
		do {
			entityId = getInt(message);
			if (entityId != -1) {
				tempStr = Integer.toString(entityId);
				if (!stringValues.contains(tempStr)) {
					stringValues.add(tempStr);
					result.add(entityId);
				}
			} else {
				break;
			}
		} while (true);
		return result;
	}

	public static int getCourseId() {
		return getInt(INPUT_COURSE_ID);
	}

	public static int getLectionId() {
		return getInt(INPUT_LECTION_ID);
	}

	public static int getLecturerId() {
		return getInt(INPUT_LECTURER_ID);
	}

	public static int getStudentId() {
		return getInt(INPUT_STUDENT_ID);
	}
	
}

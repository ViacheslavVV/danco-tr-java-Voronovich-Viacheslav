package com.danco.training.ui.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleReader {

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
			throw new RuntimeException("Something wrong!");
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
			throw new RuntimeException("Something wrong!");
		}
		return resultStr;
	}

	@SuppressWarnings("deprecation")
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
					resultDate = new Date(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]),
							Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[3]),
							Integer.parseInt(dateParts[4]));
					validData = true;
				} catch (InputMismatchException e) {
					System.out.println("Incorrect input, try again.");
					scanner.nextLine();
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new RuntimeException("Something wrong!");
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

	public static int getCourseId() throws RuntimeException {
		return getInt("Input course id");
	}

	public static int getLecturerId() throws RuntimeException {
		return getInt("Input lecturer id");
	}

	public static int getLectionId() throws RuntimeException {
		return getInt("Input lection id");
	}

	public static int getStudentId() throws RuntimeException {
		return getInt("Input student id");
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
		List<String> stringValues = new ArrayList<String>();
		List<Object> result = new ArrayList<Object>();
		String tempStr;
		System.out.println("Input '-1' to exit");
		int courseId;
		do {
			courseId = getCourseId();
			if (courseId != -1) {
				tempStr = Integer.toString(courseId);
				if (!stringValues.contains(tempStr)) {
					stringValues.add(tempStr);
					result.add(courseId);
				}
			} else {
				break;
			}
		} while (true);
		return result;
	}

	public static List<Object> getLectionIds() {
		List<String> stringValues = new ArrayList<String>();
		List<Object> result = new ArrayList<Object>();
		String tempStr;
		System.out.println("Input '-1' to exit");
		int courseId;
		do {
			courseId = getLectionId();
			if (courseId != -1) {
				tempStr = Integer.toString(courseId);
				if (!stringValues.contains(tempStr)) {
					stringValues.add(tempStr);
					result.add(courseId);
				}
			} else {
				break;
			}
		} while (true);
		return result;
	}

	public static List<Object> getLecturerIds() {
		List<String> stringValues = new ArrayList<String>();
		List<Object> result = new ArrayList<Object>();
		String tempStr;
		System.out.println("Input '-1' to exit");
		int courseId;
		do {
			courseId = getLecturerId();
			if (courseId != -1) {
				tempStr = Integer.toString(courseId);
				if (!stringValues.contains(tempStr)) {
					stringValues.add(tempStr);
					result.add(courseId);
				}
			} else {
				break;
			}
		} while (true);
		return result;
	}

	public static List<Object> getStudentIds() {
		List<String> stringValues = new ArrayList<String>();
		List<Object> result = new ArrayList<Object>();
		String tempStr;
		System.out.println("Input '-1' to exit");
		int courseId;
		do {
			courseId = getStudentId();
			if (courseId != -1) {
				tempStr = Integer.toString(courseId);
				if (!stringValues.contains(tempStr)) {
					stringValues.add(tempStr);
					result.add(courseId);
				}
			} else {
				break;
			}
		} while (true);
		return result;
	}
}

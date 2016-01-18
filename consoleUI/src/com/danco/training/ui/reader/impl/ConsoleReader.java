package com.danco.training.ui.reader.impl;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.danco.training.ui.reader.api.IReader;

public class ConsoleReader implements IReader {

	private Scanner scanner;
	
	public ConsoleReader(Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public int getInt(String message) throws RuntimeException{
		int num = 0;
		boolean validData = false;
		try{
			while(!validData){
				try{
					System.out.print(message+": ");
					String string = scanner.next();
					num = Integer.parseInt(string);
					validData = true;
				}catch (InputMismatchException | NumberFormatException e) {
					System.out.println("Incorrect input, try again: ");
				}
			}
		}catch (Exception e){
			throw new RuntimeException("Something wrong!");
		}
		return num;
	}
	
	@Override
	public String getString(String message) throws RuntimeException{
		String resultStr = null;
		boolean validData = false;
		try{
			while(!validData){
				try{
					System.out.print(message+": ");
					resultStr = scanner.next();
					validData = true;
				}catch (InputMismatchException e) {
					System.out.println("Incorrect input, try again: ");
					scanner.nextLine();
				}
			}
		}catch (Exception e){
			throw new RuntimeException("Something wrong!");
		}
		return resultStr;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public Date getDate(String message) throws RuntimeException{
		Date resultDate = null;
		String tempStr = null;
		boolean validData = false;
		try{
			while(!validData){
				try{
					System.out.print(message+" (dd-mm-yyyy): ");
					tempStr = scanner.next("\\d{1,2}-\\d{1,2}-\\d{4}");
					String[] dateParts = tempStr.split("-");
					resultDate = new Date(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[0]));
					validData = true;
				}catch (InputMismatchException e) {
					System.out.println("Incorrect input, try again.");
					scanner.nextLine();
				}
			}
		}catch (Exception e){
			throw new RuntimeException("Something wrong!");
		}
		return resultDate;
	}

	@Override
	public String getCourseName() throws RuntimeException{
		return this.getString("Input name of course");
	}

	@Override
	public Date getCourseStartDate() throws RuntimeException{
		return this.getDate("Input start date of course");
	}

	@Override
	public Date getCourseFinalDate() throws RuntimeException{
		return this.getDate("Input final date of course");
	}

	@Override
	public String getLectionName() throws RuntimeException{
		return this.getString("Input lections's name");
	}

	@Override
	public Date getLectionDate() throws RuntimeException{
		return this.getDate("Input lection's date");
	}

	@Override
	public String getLecturerName() throws RuntimeException{
		return this.getString("Input lecturer's name");
	}

	@Override
	public int getLecturerAge() throws RuntimeException{
		int resultAge = 0;
		while (resultAge < 22 || resultAge > 100){
			resultAge = this.getInt("Input lecturer's age");
		}
		return resultAge;
	}

	@Override
	public String getStudentName() throws RuntimeException{
		return this.getString("Input student's name");
	}

	@Override
	public int getStudentAge() throws RuntimeException{
		int resultAge = 0;
		while (resultAge < 16 || resultAge > 100){
			resultAge = this.getInt("Input student's age");
		}
		return resultAge;
	}

	@Override
	public int getMenuMode() throws RuntimeException{
		return this.getInt("Input section number");
	}

	@Override
	public int getCourseId() throws RuntimeException{
		return this.getInt("Input course id");
	}

	@Override
	public int getLecturerId() throws RuntimeException{
		return this.getInt("Input lecturer id");
	}

	@Override
	public int getLectionId() throws RuntimeException{
		return this.getInt("Input lection id");
	}

	@Override
	public int getStudentId() throws RuntimeException{
		return this.getInt("Input student id");
	}

	@Override
	public int getMaxStudentsNumber() {
		return this.getInt("Input max number of students");
	}

	@Override
	public int getMaxLectionsNumber() {
		return this.getInt("Input max number of lections");
	}
}

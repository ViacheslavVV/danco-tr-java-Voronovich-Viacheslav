package com.training.danco.text.io.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.training.danco.facade.api.IFacade;
import com.training.danco.model.BaseModel;
import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;

public class CSVConverter {

	private static final String SPLIT_DELIMITER = "[;]";
	private static final String DELIMITER = ";";
	private static final String SPACE = " ";
	
	public static String convertCourseToString(Course course)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(course.getName());
		sb.append(DELIMITER);
		appendDateAsString(sb, course.getStartDate(), DELIMITER);
		sb.append(DELIMITER);
		appendDateAsString(sb, course.getFinalDate(), DELIMITER);
		sb.append(DELIMITER);
		sb.append(course.getMaxStudents());
		sb.append(DELIMITER);
		sb.append(course.getMaxLections());
		sb.append(DELIMITER);
		sb.append(course.getId());
		sb.append(DELIMITER);
		sb.append((course.getLecturer() == null) ? (SPACE) : course.getLecturer().getId());
		sb.append(DELIMITER);
		appendListAsString(sb, course.getStudents(), SPACE);
		sb.append(DELIMITER);
		appendListAsString(sb, course.getLections(), SPACE);
		return sb.toString();
	}

	private static void appendListAsString(StringBuilder sb, List<? extends BaseModel> list, String delimiter)
	{
		boolean isEmpty = true;
		for(BaseModel bm : list)
		{
			if (bm != null)
			{
				sb.append(bm.getId());
				sb.append(delimiter);
				isEmpty = false;
			}
		}
		if (isEmpty)
		{
			sb.append(delimiter);
		}
	}
	
	@SuppressWarnings("deprecation")
	private static void appendDateAsString(StringBuilder sb, Date date, String delimiter)
	{
		sb.append(date.getYear());
		sb.append(delimiter);
		sb.append(date.getMonth());
		sb.append(delimiter);
		sb.append(date.getDay());
		sb.append(delimiter);
		sb.append(date.getHours());
		sb.append(delimiter);
		sb.append(date.getMinutes());
	}
	
	public static String convertLecturerToString(Lecturer lecturer)
	{
		
		return lecturer.getName()+DELIMITER+lecturer.getAge()+DELIMITER+lecturer.getId();
	}

	public static String convertLectionToString(Lection lection)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(lection.getName());
		sb.append(DELIMITER);
		appendDateAsString(sb, lection.getDate(), DELIMITER);
		sb.append(DELIMITER);
		sb.append(lection.getId());
		return sb.toString();
	}
	
	public static String convertStudentToString(Student student)
	{
		
		return student.getName()+DELIMITER+student.getAge()+DELIMITER+student.getId();
	}
	

	@SuppressWarnings("deprecation")
	public static Course convertStringToCourse(String string, IFacade facade)
	{
		String[] params = string.split(SPLIT_DELIMITER);
		
		Course newCourse = new Course(params[0], 
				new Date(Integer.parseInt(params[1]),Integer.parseInt(params[2]),Integer.parseInt(params[3]),
						Integer.parseInt(params[4]),Integer.parseInt(params[5])),
				new Date(Integer.parseInt(params[6]),Integer.parseInt(params[7]),Integer.parseInt(params[8]),
						Integer.parseInt(params[9]),Integer.parseInt(params[10])),Integer.parseInt(params[11]),Integer.parseInt(params[12]));
		newCourse.setId(Integer.parseInt(params[13]));
		
		if (!params[14].isEmpty()){
			int lecturerId = Integer.parseInt(params[14]);
			newCourse.setLecturer(facade.getLecturer(lecturerId));
		}
		
		String[] studentsIds = params[15].split(SPACE);
		List<Student> students = getStudentsByIds(studentsIds, Integer.parseInt(params[11]), facade);
		newCourse.setStudents(students);
		
		String[] lectionsIds = params[16].split(SPACE);
		List<Lection> lections = getLectionsByIds(lectionsIds, Integer.parseInt(params[12]), facade);
		newCourse.setLections(lections);
		return newCourse;
	}
	
	private static List<Student> getStudentsByIds(String[] studentsIds, int maxStudents, IFacade facade)
	{
		List<Student> students = new ArrayList<Student>();
		Student curStudent = null;
		for (String id : studentsIds )
		{
			if (!id.isEmpty()){
				try{
				curStudent = facade.getStudent(Integer.parseInt(id));
				} catch (NumberFormatException e){
					throw new RuntimeException("Not valid id of student!");
				}
				if (curStudent != null){
					
					students.add(curStudent);
				}
			 }
		}
		return students;
	}
	
	private static List<Lection> getLectionsByIds(String[] lectionsIds, int maxLections, IFacade facade)
	{
		List<Lection> lections = new ArrayList<Lection>();
		Lection curLection = null;
		for (String id : lectionsIds )
		{
			if (!id.isEmpty()){
				try{
				curLection = facade.getLection(Integer.parseInt(id));
				} catch (NumberFormatException e){
					throw new RuntimeException("Not valid id of lection!");
				}
				if (curLection != null){
					
					lections.add(curLection);
				}
			 }
		}
		return lections;
	}

	@SuppressWarnings("deprecation")
	public static Lection convertStringToLection(String string)
	{
		String[] params = string.split(SPLIT_DELIMITER);
		Lection newLection = new Lection(params[0], 
				new Date(Integer.parseInt(params[1]),Integer.parseInt(params[2]),Integer.parseInt(params[3]),
						Integer.parseInt(params[4]),Integer.parseInt(params[5])));
		newLection.setId(Integer.parseInt(params[6]));
		return newLection;
	}

	public static Lecturer convertStringToLecturer(String string)
	{
		
		String[] params = string.split(SPLIT_DELIMITER);
		Lecturer newLecturer = new Lecturer(params[0], Integer.parseInt(params[1]));
		newLecturer.setId(Integer.parseInt(params[2]));
		return newLecturer;
	}

	public static Student convertStringToStudent(String string)
	{
		
		String[] params = string.split(SPLIT_DELIMITER);
		Student newStudent = new Student(params[0], Integer.parseInt(params[1]));
		newStudent.setId(Integer.parseInt(params[2]));
		return newStudent;
	}

}

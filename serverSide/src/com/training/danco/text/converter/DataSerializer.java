package com.training.danco.text.converter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;

public class DataSerializer {
	
	private final String FILE_NAME; 
	
	public DataSerializer(String fileName) {
		super();
		this.FILE_NAME = fileName;
	}
	
	private List<Object> groupData(List<Student> students, List<Lection> lections, List<Lecturer> lecturers, List<Course> courses){
		
		List<Object> data = new ArrayList<Object>();
		data.add(students);
		data.add(lections);
		data.add(lecturers);
		data.add(courses);
		return data;
	}
	
	public boolean saveData(List<Student> students, List<Lection> lections, List<Lecturer> lecturers, List<Course> courses) throws IOException
	{
		boolean result = true;
		List<Object> data = this.groupData(students, lections, lecturers, courses);
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		
		try {
			fileOutputStream = new FileOutputStream(FILE_NAME,false);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(data);
		} catch (Exception e) {
			result = false;
		} finally {
			objectOutputStream.close();
		}
		return result;
	}
}
package com.danco.training.ui.reader.api;

import java.util.Date;
import java.util.List;

public interface IReader {

	public String getCourseName();
	public Date getCourseStartDate();
	public Date getCourseFinalDate();
	public int getMaxStudentsNumber();
	public int getMaxLectionsNumber();
	public int getInt(String message);
	
	public String getLectionName();
	public String getString(String message);
	public Date getLectionDate();
	public Date getDate(String message);
	
	public String getLecturerName();
	public int getLecturerAge();
	
	public String getStudentName();
	public int getStudentAge();
	
	public int getMenuMode();
	
	public int getCourseId();
	public int getLecturerId();
	public int getLectionId();
	public int getStudentId();
	
	public String getImportFileName();
	public String getExportFileName();
	
	public List<Object> getCourseIds();
	public List<Object> getLectionIds();
	public List<Object> getLecturerIds();
	public List<Object> getStudentIds();
}

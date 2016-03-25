package com.training.danco.comparator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import com.training.danco.model.Course;

public class CourseStartDateComparator implements Comparator<Course> {
	private static final DateFormat FORMATTER = new SimpleDateFormat(("dd.MM.yyyy"));
	
	@Override
	public int compare(Course o1, Course o2) {
		
		return FORMATTER.format(o1.getStartDate()).compareTo(FORMATTER.format(o2.getStartDate()));
	}

}

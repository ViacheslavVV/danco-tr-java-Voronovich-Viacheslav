package com.training.danco.comparator;

import java.util.Comparator;

import com.training.danco.model.Course;

public class CourseNameComparator implements Comparator<Course> {

	@Override
	public int compare(Course o1, Course o2) {
		
		return o1.getName().compareTo(o2.getName());
	}

}

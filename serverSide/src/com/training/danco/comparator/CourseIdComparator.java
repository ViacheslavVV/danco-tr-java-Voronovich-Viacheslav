package com.training.danco.comparator;

import java.util.Comparator;

import com.training.danco.model.Course;

public class CourseIdComparator implements Comparator<Course> {

	@Override
	public int compare(Course o1, Course o2) {
		
		return Integer.compare(o1.getId(),o2.getId());
	}

}
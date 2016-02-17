package com.training.danco.comparator;

import java.util.Comparator;

import com.training.danco.model.Student;

public class StudentIdComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		
		return Integer.compare(o1.getId(), o2.getId());
	}

	

}

package com.training.danco.comparator;

import java.util.Comparator;

import com.training.danco.model.Lecturer;

public class LecturerIdComparator implements Comparator<Lecturer> {

	@Override
	public int compare(Lecturer arg0, Lecturer arg1) {
		
		return Integer.compare(arg0.getId(),arg1.getId());
	}

	

}

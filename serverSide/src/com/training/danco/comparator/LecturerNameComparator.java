package com.training.danco.comparator;

import java.util.Comparator;

import com.training.danco.model.Lecturer;

public class LecturerNameComparator implements Comparator<Lecturer> {

	@Override
	public int compare(Lecturer o1, Lecturer o2) {
		
		return o1.getName().compareTo(o2.getName());
	}

}

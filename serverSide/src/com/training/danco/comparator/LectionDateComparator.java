package com.training.danco.comparator;

import java.util.Comparator;

import com.training.danco.model.Lection;

public class LectionDateComparator implements Comparator<Lection> {

	@Override
	public int compare(Lection o1, Lection o2) {
		
		return o1.getDate().compareTo(o2.getDate());
	}

}

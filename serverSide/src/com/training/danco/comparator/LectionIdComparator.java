package com.training.danco.comparator;

import java.util.Comparator;

import com.training.danco.model.Lection;

public class LectionIdComparator implements Comparator<Lection> {

	@Override
	public int compare(Lection o1, Lection o2) {
		
		return Integer.compare(o1.getId(), o2.getId());
	}

	

}

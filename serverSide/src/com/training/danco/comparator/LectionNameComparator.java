package com.training.danco.comparator;

import java.util.Comparator;

import com.training.danco.model.Lection;

public class LectionNameComparator implements Comparator<Lection> {

	@Override
	public int compare(Lection arg0, Lection arg1) {

		return arg0.getName().compareTo(arg1.getName());
	}

}

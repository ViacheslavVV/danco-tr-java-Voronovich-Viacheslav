package com.training.danco.comparator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import com.training.danco.model.Lection;

public class LectionDateComparator implements Comparator<Lection> {
	private static final DateFormat FORMATTER = new SimpleDateFormat(("dd.MM.yyyy"));
	@Override
	public int compare(Lection o1, Lection o2) {
		
		return FORMATTER.format(o1.getDate()).compareTo(FORMATTER.format(o2.getDate()));
	}

}

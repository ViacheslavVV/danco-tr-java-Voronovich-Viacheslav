package com.training.danco.text.io.validator.impl;

import com.training.danco.text.io.validator.api.IValidator;

public class CSVStudentValidator implements IValidator{

	@Override
	public boolean isValid(String string) {
		
		return string.split("[;]").length == 3;
	}

}

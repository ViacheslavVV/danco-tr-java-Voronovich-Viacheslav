package com.training.danco.text.io.validator.impl;

import com.training.danco.text.io.validator.api.IValidator;

public class CSVLecturerValidator implements IValidator {

	@Override
	public Boolean isValid(String string) {
		
		return string.split("[;]").length == 3;
	}

	
}

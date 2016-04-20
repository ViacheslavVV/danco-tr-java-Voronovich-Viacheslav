package training.danco.text.io.validator.impl;

import training.danco.text.io.validator.api.IValidator;

public class CSVEmployeeValidator implements IValidator{

	@Override
	public Boolean isValid(String string) {
		Integer count = string.split("[;]").length;
		return count == 6;
	}

}

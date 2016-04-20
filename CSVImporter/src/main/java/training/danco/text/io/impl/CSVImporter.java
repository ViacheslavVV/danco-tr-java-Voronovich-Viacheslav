package training.danco.text.io.impl;

import java.util.ArrayList;
import java.util.List;

import training.danco.dao.api.IEmployeeRepository;
import training.danco.model.*;
import training.danco.text.io.api.IImporter;
import training.danco.text.io.converter.CSVConverter;
import training.danco.text.io.validator.impl.CSVValidator;

public class CSVImporter implements IImporter{


	public List<Employee> importEmployees(String fileName) {
		
		List<String> lines = CSVValidator.getCorrectStrings(CSVValidator.EMPLOYEE_VALIDATOR, fileName);
		List<Employee> employees = new ArrayList<Employee>();
		for (String line : lines){
			employees.add(CSVConverter.convertStringToEmployee(line));
		}
		return employees;
	}

}

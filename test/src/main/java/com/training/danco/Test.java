package com.training.danco;

import java.util.List;

import training.danco.model.Employee;
import training.danco.text.io.impl.CSVImporter;

public class Test {

	public static void main(String[] args) {
		CSVImporter csvImporter = new CSVImporter();
		List<Employee> e =csvImporter.importEmployees("temp.txt");
	}

}

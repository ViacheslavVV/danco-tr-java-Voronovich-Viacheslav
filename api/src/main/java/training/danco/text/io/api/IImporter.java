package training.danco.text.io.api;

import java.util.List;

import training.danco.model.Employee;

public interface IImporter {

	public List<Employee> importEmployees(String fileName);
	
}

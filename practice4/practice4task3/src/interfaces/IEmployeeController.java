package interfaces;

import classes.AEmployee;

public interface IEmployeeController {

	void addEmployee(AEmployee employee);
	
	AEmployee getEmployee(int id);

	void deleteEmployee(AEmployee employee);
	
	public void deleteEmployeeById(int id);
}

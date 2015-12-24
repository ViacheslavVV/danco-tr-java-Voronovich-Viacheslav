package classes;

import interfaces.IEmployer;
import interfaces.ISalaryGetter;
import managers.EmployeeManager;

public class Company implements IEmployer, ISalaryGetter{
	

	static final private Integer MAX_EMPLOYEES = 100;
	
	private String name;
	private EmployeeManager employeeManager;
	
	public Company(String name) {
		this.name = name;
		employeeManager = new EmployeeManager(MAX_EMPLOYEES);
	}

	public EmployeeManager getEmployeeManager() {
		return employeeManager;
	}

	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void recruitEmployee(AEmployee employee) {
		
		this.employeeManager.addEmployee(employee);
		employee.setCompany(this);
	}
	
	@Override
	public void fireEmployee(AEmployee employee) {

		this.employeeManager.deleteEmployee(employee);
		employee.setCompany(null);
	}
	
	@Override
	public double getWholeSalary() {
		int salary = 0;
		for (AEmployee employee : employeeManager.getEmployees())
		{
			if (employee != null)
			{
			salary += employee.getSalary();
			}
		}
		return salary;
	}
}

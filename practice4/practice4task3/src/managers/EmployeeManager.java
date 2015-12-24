package managers;

import classes.*;
import interfaces.*;

public class EmployeeManager 
	implements IEmployeeController, IAccountantController, IDeveloperController, IDirectorController, IProjectManagerController {

	private final int MAX_EMPLOYEE_NUMBER;

	private AEmployee[] employees;
	
	public EmployeeManager()
	{
		MAX_EMPLOYEE_NUMBER = 100;
		employees = new AEmployee[MAX_EMPLOYEE_NUMBER];
	}
	
	public EmployeeManager(int maxEmployeeNumber) {
		MAX_EMPLOYEE_NUMBER = maxEmployeeNumber;
		employees = new AEmployee[MAX_EMPLOYEE_NUMBER];
	}

	public AEmployee[] getEmployees() {
		return employees;
	}

	public void setEmployees(AEmployee[] employees) {
		this.employees = employees;
	}

	@Override
	public void addEmployee(AEmployee employee) {
		
		int index = getVocantSeatNumber();
		if (index != -1)
		{
		employees[getVocantSeatNumber()] = employee;
		}
	}
	
	@Override
	public AEmployee getEmployee(int id) {
		
		int index = getEmployeeIndexById(id);
		if (index != -1)
		{
			return employees[index];
		}
		return null;
	}

	@Override
	public void deleteEmployee(AEmployee employee) {
		
		int index = getEmployeeIndex(employee);
		if (index != -1)
		{
		employees[index] = null;
		}
	}
	
	@Override
	public void deleteEmployeeById(int id) {
		
		int index = getEmployeeIndexById(id);
		if (index != -1)
		{
			employees[index] = null;
		}
	}

	@Override
	public ProjectManager getProjectManager(int id) {
		
		int index = getEmployeeIndexById(id);
		if (index != -1)
		{
			if (employees[index] instanceof ProjectManager)
			{
				return (ProjectManager) employees[index];
			}
		}
		return null;
	}

	@Override
	public Director getDirector(int id) {

		int index = getEmployeeIndexById(id);
		if (index != -1)
		{
			if (employees[index] instanceof Director)
			{
				return (Director) employees[index];
			}
		}
		return null;
	}

	@Override
	public Developer getDeveloper(int id) {

		int index = getEmployeeIndexById(id);
		if (index != -1)
		{
			if (employees[index] instanceof Developer)
			{
				return (Developer) employees[index];
			}
		}
		return null;
	}

	@Override
	public Accountant getAccountant(int id) {

		int index = getEmployeeIndexById(id);
		if (index != -1)
		{
			if (employees[index] instanceof Accountant)
			{
				return (Accountant) employees[index];
			}
		}
		return null;
	}
	
	private int getVocantSeatNumber()
	{
		for (int i=0; i<employees.length; i++)
		{
			if (employees[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
	
	private int getEmployeeIndex(AEmployee employee)
	{
		return getEmployeeIndexById(employee.getId());
	}
	
	private int getEmployeeIndexById(int id)
	{
		for (int i=0; i<employees.length; i++)
		{
			if (employees[i].getId() == id)
			{
				return i;
			}
		}
		return -1;
	}

}

package main;

import classes.*;

public class Main {

	public static void main(String[] args){
		
		Company company = new Company("MyCompany");
		Director director = new Director("Ivan Ivanov", 499.99, "director");
		Developer developer1 = new Developer("Kolya Ivanov", 200.1, "developer");
		Developer developer2 = new Developer("Vasya Ivanov", 200.2, "developer");
		company.recruitEmployee(director);
		company.recruitEmployee(developer1);
		company.recruitEmployee(developer2);
		Accountant accountant = new Accountant("Irina Ivanova", 170, "accountant");
		company.recruitEmployee(accountant);
		ProjectManager projectManager = new ProjectManager("Oleg Ivanov", 300.54, "projectManager");
		company.recruitEmployee(projectManager);
		System.out.println("Whole salary "+company.getWholeSalary());
		company.fireEmployee(developer1);
		System.out.println("Whole salary "+company.getWholeSalary());
	}

}

package training.danco.text.io.converter;

import java.util.Date;
import java.util.GregorianCalendar;

import training.danco.model.Employee;

public class CSVConverter {

	private static final int CHAR_POSITION = 0;
	private static final String DATE_SEPARATOR = "[.]";
	private static final String SPLIT_DELIMITER = ";";

	public static Employee convertStringToEmployee(String string) {

		String[] params = string.split(SPLIT_DELIMITER);
		
		Employee employee = new Employee();
		
		employee.setId(Integer.parseInt(params[0]));
		employee.setFirstName(params[1]);
		employee.setLastName(params[2]);
		
		String[] dateParams = params[3].split(DATE_SEPARATOR);
		
		Date date = new GregorianCalendar(Integer.parseInt(dateParams[2]), Integer.parseInt(dateParams[1]), Integer.parseInt(dateParams[0])).getTime();
		
		employee.setBirthDate(date);
		employee.setGender(params[4].charAt(CHAR_POSITION) == 'M' ? 'M' : 'F');
		employee.setTitle(params[5]);
		
		return employee;
	}

}

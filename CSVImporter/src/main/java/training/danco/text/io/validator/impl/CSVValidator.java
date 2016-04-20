package training.danco.text.io.validator.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import training.danco.text.io.validator.api.IValidator;


public class CSVValidator {
	

	public static final IValidator EMPLOYEE_VALIDATOR = new CSVEmployeeValidator();
	
	public static List<String> getCorrectStrings(IValidator validator, String fileName){
		
		List<String> lines = new ArrayList<String>();
		File file = new File(fileName);
		if (!file.exists()){
			return lines;
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = reader.readLine()) != null) {
			    if (validator.isValid(line)){
			    	lines.add(line);
			    }
			}
		} catch (Exception e) {
			
		} finally {
			if (reader != null)
			try {
				reader.close();
			} catch (IOException e) {
				
			}
		}
		return lines;
	}
}

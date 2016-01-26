package com.training.danco.text.io.validator.impl;

import com.training.danco.text.io.validator.api.IValidator;

public class CSVValidator {

	public static final IValidator LECTION_VALIDATOR = new CSVLectionValidator();
	public static final IValidator LECTURER_VALIDATOR = new CSVLecturerValidator();
	public static final IValidator COURSE_VALIDATOR = new CSVCourseValidator();
	public static final IValidator STUDENT_VALIDATOR = new CSVStudentValidator();
}

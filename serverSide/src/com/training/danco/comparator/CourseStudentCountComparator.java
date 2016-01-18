package com.training.danco.comparator;

import java.util.Comparator;

import com.training.danco.model.Course;
import com.training.danco.model.Student;

public class CourseStudentCountComparator implements Comparator<Course> {

	@Override
	public int compare(Course o1, Course o2) {
		
		int count1 = 0;
		int count2 = 0;
		for (Student student : o1.getStudents())
		{
			if (student != null)
			{
				count1++;
			}
		}
		for (Student student : o2.getStudents())
		{
			if (student != null)
			{
				count2++;
			}
		}
		return Integer.compare(count1, count2);
	}

}

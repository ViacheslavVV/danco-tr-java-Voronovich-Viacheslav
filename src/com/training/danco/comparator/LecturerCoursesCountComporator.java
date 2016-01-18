package com.training.danco.comparator;

import java.util.Comparator;

import com.training.danco.dao.api.ICourseRepository;
import com.training.danco.model.Course;
import com.training.danco.model.Lecturer;

public class LecturerCoursesCountComporator implements Comparator<Lecturer> {

	private ICourseRepository courseRepository;
	
	public LecturerCoursesCountComporator(ICourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	@Override
	public int compare(Lecturer o1, Lecturer o2) {
		
		int count1 = 0;
		int count2 = 0;
		for (Course course : courseRepository.getAll())
		{
			if (course.getLecturer().getId() == o1.getId())
			{
				count1++;
			}
			else if (course.getLecturer().getId() == o2.getId())
			{
					count2++;
			}
		}
		return Integer.compare(count1, count2);
	}

}

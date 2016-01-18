package com.training.danco.dao.impl;

import java.util.Arrays;

import com.training.danco.comparator.Comparator;
import com.training.danco.comparator.LecturerCoursesCountComporator;
import com.training.danco.dao.api.ICourseRepository;
import com.training.danco.dao.api.ILecturerRepository;
import com.training.danco.model.Course;
import com.training.danco.model.Lecturer;

public class LecturerRepository implements ILecturerRepository {

	
	private Lecturer[] lecturers;
	
	public LecturerRepository(Lecturer[] lecturers) {
		this.lecturers = lecturers;
	}

	@Override
	public boolean set(Lecturer lecturer) {
		int index = getVocantLecturerNumber();
		if (index != -1)
		{
			lecturers[index] = lecturer;
			return true;
		}
		return false;
	}

	@Override
	public Lecturer get(int id) {
		int index = getLecturerIndexById(id);
		if (index != -1)
		{
			return lecturers[index];
		}
		return null;
	}

	@Override
	public boolean update(Lecturer lecturer) {
		int index = getLecturerIndexById(lecturer.getId());
		if (index != -1)
		{
			lecturers[index] = lecturer;
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Lecturer lecturer, ICourseRepository courseRepository) {
		int index = getLecturerIndexById(lecturer.getId());
		if (index != -1)
		{
			for (Course course : courseRepository.getAll())
			{
				course.setLecturer(null);
			}
			lecturers[index] = null;
			return true;
		}
		return false;
	}

	@Override
	public Lecturer[] getAll() {

		return getNotNullLecturers();
	}
	
	private int getVocantLecturerNumber()
	{
		for (int i=0; i<lecturers.length; i++)
		{
			if (lecturers[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
	
	private int getLecturerIndexById(int id)
	{
		for (int i=0; i<lecturers.length; i++)
		{
			if (lecturers[i] == null)
			{
				continue;
			}
			
			if (lecturers[i].getId() == id)
			{
				return i;
			}
		}
		return -1;
	}

	private Lecturer[] getNotNullLecturers()
	{
		Lecturer[] notNullLecturers = new Lecturer[getCount()];
		int currentIndex =0;
		for (Lecturer lecturer: this.lecturers)
		{
			if (lecturer != null)
			{
				notNullLecturers[currentIndex++] = lecturer;
			}
		}
		return notNullLecturers;
	}
	
	@Override
	public Lecturer[] getSortedByName() {
		Lecturer[] lects = getNotNullLecturers();
		Arrays.sort(lects, Comparator.LECTURER_NAME_COMPARATOR);
		return lects;
	}

	@Override
	public Lecturer[] getSortedByCoursesCount(ICourseRepository courseRepository) {
		Lecturer[] lects = getNotNullLecturers();
		Arrays.sort(lects, new LecturerCoursesCountComporator(courseRepository));
		return lects;
	}

	
	@Override
	public int getCount() {
		int count = 0;
		for (Lecturer lecturer : this.lecturers)
		{
			if (lecturer != null)
			{
				count++;
			}
		}
		return count;
	}
	

}

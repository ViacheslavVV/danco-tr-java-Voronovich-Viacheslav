package com.training.danco.dao.api;

import com.training.danco.model.Lecturer;

public interface ILecturerRepository {

	public boolean set(Lecturer lecturer);
	public Lecturer get(int id);
	public boolean update(Lecturer lecturer);
	public boolean delete(Lecturer lecturer,ICourseRepository courseRepository);
	public Lecturer[] getAll();
	public Lecturer[] getSortedByName();
	public Lecturer[] getSortedByCoursesCount(ICourseRepository courseRepository);
	public int getCount();
}

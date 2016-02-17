package com.training.danco.dao.api;

import java.util.List;

import com.training.danco.model.Lecturer;

public interface ILecturerRepository {

	public boolean set(Lecturer lecturer);
	public Lecturer get(int id);
	public boolean update(Lecturer lecturer);
	public boolean delete(Lecturer lecturer,ICourseRepository courseRepository);
	public List<Lecturer> getAll();
	public List<Lecturer> getSortedByName();
	public List<Lecturer> getSortedByCoursesCount(ICourseRepository courseRepository);
	public int getCount();
}

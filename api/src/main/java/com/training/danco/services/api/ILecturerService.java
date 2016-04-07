package com.training.danco.services.api;

import java.util.List;

import com.training.danco.model.Lecturer;

public interface ILecturerService {

	public Integer set(Lecturer lecturer);
	public Lecturer get(int id);
	public boolean update(Lecturer lecturer);
	public boolean delete(Lecturer lecturer);
	public List<Lecturer> getAll();
	public List<Lecturer> getSortedByName();
	public List<Lecturer> getSortedByCoursesCount();
	public int getCount();
}

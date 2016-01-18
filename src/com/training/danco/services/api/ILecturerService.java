package com.training.danco.services.api;

import com.training.danco.model.Lecturer;

public interface ILecturerService {

	public boolean set(Lecturer lecturer);
	public Lecturer get(int id);
	public boolean update(Lecturer lecturer);
	public boolean delete(Lecturer lecturer);
	public Lecturer[] getAll();
	public Lecturer[] getSortedByName();
	public Lecturer[] getSortedByCoursesCount();
	public int getCount();
}

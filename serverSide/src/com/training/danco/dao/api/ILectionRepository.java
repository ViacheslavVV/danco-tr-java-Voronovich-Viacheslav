package com.training.danco.dao.api;

import java.util.Date;
import java.util.List;

import com.training.danco.model.Lection;

public interface ILectionRepository {

	public boolean set(Lection lection);
	public Lection get(int id);
	public boolean update(Lection lection);
	public boolean delete(Lection lection,ICourseRepository courseRepository);
	public List<Lection> getAll();
	public List<Lection> getSortedByDate();
	public List<Lection> getSortedByName();
	public List<Lection> getLectionsByDate(Date date);
	public int getCount();
}

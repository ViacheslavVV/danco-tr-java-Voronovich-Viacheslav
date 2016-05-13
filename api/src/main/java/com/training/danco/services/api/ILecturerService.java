package com.training.danco.services.api;

import java.util.List;

import com.training.danco.model.Lecturer;
import com.training.danco.params.SortingParam;

public interface ILecturerService {

	public Integer set(Lecturer lecturer);
	public Lecturer get(Integer id);
	public Boolean update(Lecturer lecturer);
	public Boolean delete(Integer lecturerId);
	public List<Lecturer> getAll();
	public List<Lecturer> getSorted(SortingParam sortingParam);
	public Integer getCount();
}

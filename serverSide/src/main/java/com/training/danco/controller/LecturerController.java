package com.training.danco.controller;

import java.util.List;

import com.training.danco.model.Lecturer;
import com.training.danco.params.SortingParam;
import com.training.danco.services.api.ILecturerService;

public class LecturerController {

	private ILecturerService lecturerService;

	public LecturerController(ILecturerService lecturerService) {
		
		this.lecturerService = lecturerService;
	}
	
	public Integer setLecturer(Lecturer lecturer)
	{
		return this.lecturerService.set(lecturer);
	}
	
	public Lecturer getLecturer(Integer lecturerId)
	{
		return this.lecturerService.get(lecturerId);
	}
	
	public Boolean updateLecturer(Lecturer lecturer)
	{
		return this.lecturerService.update(lecturer);
	}
	
	public Boolean deleteLecturer(Integer lecturerId)
	{
		Lecturer lecturer = this.lecturerService.get(lecturerId);
		return this.lecturerService.delete(lecturer);
	}
	
	public List<Lecturer> getAll()
	{
		return this.lecturerService.getAll();
	}
	

	public List<Lecturer> getSorted(SortingParam sortingParam)
	{
		return this.lecturerService.getSorted(sortingParam);
	}
	
	public Integer getCount()
	{
		return this.lecturerService.getCount();
	}
}

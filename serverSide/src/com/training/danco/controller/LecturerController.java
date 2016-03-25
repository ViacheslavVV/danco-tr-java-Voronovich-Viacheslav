package com.training.danco.controller;

import java.util.List;

import com.training.danco.model.Lecturer;
import com.training.danco.services.api.ILecturerService;

public class LecturerController {

	private ILecturerService lecturerService;

	public LecturerController(ILecturerService lecturerService) {
		
		this.lecturerService = lecturerService;
	}
	
	public boolean setLecturer(Lecturer lecturer)
	{
		return this.lecturerService.set(lecturer);
	}
	
	public Lecturer getLecturer(int lecturerId)
	{
		return this.lecturerService.get(lecturerId);
	}
	
	public boolean updateLecturer(Lecturer lecturer)
	{
		return this.lecturerService.update(lecturer);
	}
	
	public boolean deleteLecturer(int lecturerId)
	{
		Lecturer lecturer = this.lecturerService.get(lecturerId);
		return this.lecturerService.delete(lecturer);
	}
	
	public List<Lecturer> getAll()
	{
		return this.lecturerService.getAll();
	}
	

	public List<Lecturer> getSortedByName()
	{
		return this.lecturerService.getSortedByName();
	}
	public List<Lecturer> getSortedByCoursesCount()
	{
		return this.lecturerService.getSortedByCoursesCount();
	}
	public int getCount()
	{
		return this.lecturerService.getCount();
	}
}

package com.training.danco.controller;

import java.util.Date;
import java.util.List;

import com.training.danco.model.Lection;
import com.training.danco.services.api.ILectionService;

public class LectionController {

	private ILectionService lectionService;

	public LectionController(ILectionService lectionService) {
		
		this.lectionService = lectionService;
	}
	
	public boolean setLection(Lection lection)
	{
		return this.lectionService.set(lection);
	}
	
	public Lection getLection(int lectionId)
	{
		return this.lectionService.get(lectionId);
	}
	
	public boolean updateLection(Lection lection)
	{
		return this.lectionService.update(lection);
	}
	
	public boolean deleteLection(int lectionId)
	{
		Lection lection = this.lectionService.get(lectionId);
		return this.lectionService.delete(lection);
	}
	
	public List<Lection> getAll()
	{
		return this.lectionService.getAll();
	}

	public List<Lection> getSortedByDate()
	{
		return this.lectionService.getSortedByDate();
	}
	public List<Lection> getSortedByName()
	{
		return this.lectionService.getSortedByName();
	}
	public List<Lection> getLectionsByDate(Date date)
	{
		return this.lectionService.getLectionsByDate(date);
	}
	public int getCount()
	{
		return this.lectionService.getCount();
	}
}

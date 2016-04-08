package com.training.danco.controller;

import java.util.Date;
import java.util.List;

import com.training.danco.model.Lection;
import com.training.danco.params.SortingParam;
import com.training.danco.services.api.ILectionService;

public class LectionController {

	private ILectionService lectionService;

	public LectionController(ILectionService lectionService) {
		
		this.lectionService = lectionService;
	}
	
	public Integer setLection(Lection lection)
	{
		return this.lectionService.set(lection);
	}
	
	public Lection getLection(Integer lectionId)
	{
		return this.lectionService.get(lectionId);
	}
	
	public Boolean updateLection(Lection lection)
	{
		return this.lectionService.update(lection);
	}
	
	public Boolean deleteLection(Integer lectionId)
	{
		Lection lection = this.lectionService.get(lectionId);
		return this.lectionService.delete(lection);
	}
	
	public List<Lection> getAll()
	{
		return this.lectionService.getAll();
	}

	public List<Lection> getSorted(SortingParam sortingParam)
	{
		return this.lectionService.getSorted(sortingParam);
	}
	
	public List<Lection> getLectionsByDate(Date date)
	{
		return this.lectionService.getLectionsByDate(date);
	}
	public Integer getCount()
	{
		return this.lectionService.getCount();
	}
}

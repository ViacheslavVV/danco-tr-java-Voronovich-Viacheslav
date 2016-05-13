package com.training.danco.services.api;

import java.util.Date;
import java.util.List;

import com.training.danco.model.Lection;
import com.training.danco.params.SortingParam;

public interface ILectionService {

	public Integer set(Lection lection);
	public Lection get(Integer id);
	public Boolean update(Lection lection);
	public Boolean delete(Integer lectionId);
	public List<Lection> getAll();
	public List<Lection> getSorted(SortingParam sortingParam);
	public List<Lection> getLectionsByDate(Date date);
	public Integer getCount();
}

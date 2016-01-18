package com.training.danco.services.api;

import java.util.Date;

import com.training.danco.model.Lection;

public interface ILectionService {

	public boolean set(Lection lection);
	public Lection get(int id);
	public boolean update(Lection lection);
	public boolean delete(Lection lection);
	public Lection[] getAll();
	public Lection[] getSortedByDate();
	public Lection[] getSortedByName();
	public Lection[] getLectionsByDate(Date date);
	public int getCount();
}

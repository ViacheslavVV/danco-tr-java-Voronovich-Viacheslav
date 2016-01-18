package com.training.danco.dao.impl;

import java.util.Arrays;
import java.util.Date;

import com.training.danco.comparator.Comparator;
import com.training.danco.dao.api.ICourseRepository;
import com.training.danco.dao.api.ILectionRepository;
import com.training.danco.model.Course;
import com.training.danco.model.Lection;

public class LectionRepository implements ILectionRepository {

	
	private Lection[] lections; 
	
	public LectionRepository(Lection[] lections) {
		this.lections = lections;
	}

	@Override
	public boolean set(Lection lection) {
		int index = getVocantLectionNumber();
		if (index != -1)
		{
			lections[index] = lection;
			return true;
		}
		return false;
	}

	@Override
	public Lection get(int id) {
		int index = getLectionIndexById(id);
		if (index != -1)
		{
			return lections[index];
		}
		return null;
	}

	@Override
	public boolean update(Lection lection) {
		int index = getLectionIndexById(lection.getId());
		if (index != -1)
		{
			lections[index] = lection;
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Lection lection, ICourseRepository courseRepository) {
		int index = getLectionIndexById(lection.getId());
		if (index != -1)
		{
			for (Course course : courseRepository.getAll())
			{
				course.removeLection(lection);
			}
			lections[index] = null;
			return true;
		}
		return false;
	}

	@Override
	public Lection[] getAll() {

		return getNotNullLections();
	}

	private int getVocantLectionNumber()
	{
		for (int i=0; i<lections.length; i++)
		{
			if (lections[i] == null)
			{
				return i;
			}
		}
		return -1;
	}
	
	private int getLectionIndexById(int id)
	{
		for (int i=0; i<lections.length; i++)
		{
			if (lections[i] == null)
			{
				continue;
			}
			
			if (lections[i].getId() == id)
			{
				return i;
			}
		}
		return -1;
	}
	
	private Lection[] getNotNullLections()
	{
		Lection[] notNullLections = new Lection[getCount()];
		int currentIndex =0;
		for (Lection lection: this.lections)
		{
			if (lection != null)
			{
				notNullLections[currentIndex++] = lection;
			}
		}
		return notNullLections;
	}
	
	private int getLectionsNumberByDate(Date date)
	{
		int count = 0;
		for (Lection lection : this.lections)
		{
			if (lection == null)
			{
				continue;
			}
			if (lection.getDate().equals(date))
			{
				count++;
			}
		}
		return count;
	}
	@Override
	public Lection[] getSortedByDate() {
		
		Lection[] lects = getNotNullLections();
		Arrays.sort(lects, Comparator.LECTION_DATE_COMPARATOR);
		return lects;
	}

	@Override
	public Lection[] getSortedByName() {
		Lection[] lects = getNotNullLections();
		Arrays.sort(lects, Comparator.LECTION_NAME_COMPARATOR);
		return lects;
	}

	@Override
	public Lection[] getLectionsByDate(Date date) {
		Lection[] lects = new Lection[getLectionsNumberByDate(date)];
		int currentLection = 0;
		for (Lection lection : lects)
		{
			if (lection ==  null)
			{
				continue;
			}
			if (lection.getDate().equals(date))
			{
				lects[currentLection++] = lection;
			}
		}
		
		return lects;
	}

	@Override
	public int getCount() {
		int count = 0;
		for (Lection lection : this.lections)
		{
			if (lection != null)
			{
				count++;
			}
		}
		return count;
	}
	
}

package com.training.danco.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.training.danco.comparator.Comparator;
import com.training.danco.dao.api.ICourseRepository;
import com.training.danco.dao.api.ILectionRepository;
import com.training.danco.model.Course;
import com.training.danco.model.Lection;

public class LectionRepository implements ILectionRepository {

	private List<Lection> lections;
	static private int lectionId = 1;
	static private final int MIN_CORRECT_ID = 1;

	static public void setLectionId(int id) {
		lectionId = id;
	}

	public LectionRepository(List<Lection> lections) {
		this.lections = lections;
	}

	@Override
	public boolean set(Lection lection) {
		int id = lection.getId();
		if (id < MIN_CORRECT_ID) {
			lection.setId(lectionId++);
		} else if (id >= lectionId){
			lectionId = ++id;
		}
		return this.lections.add(lection);
	}

	@Override
	public Lection get(int id) {
		int index = getLectionIndexById(id);
		if (index != -1) {
			return this.lections.get(index);
		}
		return null;
	}

	@Override
	public boolean update(Lection lection) {
		int index = getLectionIndexById(lection.getId());
		if (index != -1) {
			this.lections.set(index, lection);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Lection lection, ICourseRepository courseRepository) {
		int index = getLectionIndexById(lection.getId());
		if (index != -1) {
			if (this.lections.remove(index) != null) {
				for (Course course : courseRepository.getAll()) {
					course.removeLection(lection);
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Lection> getAll() {

		Collections.sort(this.lections, Comparator.LECTION_ID_COMPARATOR);
		return this.lections;
	}

	private int getLectionIndexById(int id) {
		for (int i = 0; i < this.lections.size(); i++) {
			if (this.lections.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public List<Lection> getSortedByDate() {

		Collections.sort(this.lections, Comparator.LECTION_DATE_COMPARATOR);
		return this.lections;
	}

	@Override
	public List<Lection> getSortedByName() {
		Collections.sort(this.lections, Comparator.LECTION_NAME_COMPARATOR);
		return this.lections;
	}

	@Override
	public List<Lection> getLectionsByDate(Date date) {
		List<Lection> lectionsByDate = new ArrayList<Lection>();
		for (Lection lection : this.lections) {
			if (lection.getDate().equals(date)) {
				lectionsByDate.add(lection);
			}
		}

		return lectionsByDate;
	}

	@Override
	public int getCount() {
		return this.lections.size();
	}

}

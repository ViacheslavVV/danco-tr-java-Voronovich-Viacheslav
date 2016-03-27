package com.training.danco.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.dao.api.ICourseRepository;
import com.training.danco.dao.api.ILectionRepository;
import com.training.danco.model.Lection;
import com.training.danco.services.api.ILectionService;

public class LectionService implements ILectionService {

	private static final Logger LOGGER = LogManager.getLogger(LectionService.class);

	private ILectionRepository lectionRepository;
	private ICourseRepository courseRepository;

	public LectionService(ILectionRepository lectionRepository, ICourseRepository courseRepository) {
		this.lectionRepository = lectionRepository;
		this.courseRepository = courseRepository;
	}

	@Override
	public boolean set(Lection lection) {

		boolean result = true;
		try {
			result = this.lectionRepository.set(lection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		}
		return result;
	}

	@Override
	public Lection get(int id) {

		Lection resultLection = null;
		try {
			resultLection = this.lectionRepository.get(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return resultLection;
	}

	@Override
	public boolean update(Lection lection) {

		boolean result = false;
		try {
			Lection resultLection = this.get(lection.getId());

			if (resultLection == null) {

				result = this.set(lection);
			} else {
				result = this.lectionRepository.update(lection);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean delete(Lection lection) {

		boolean result = true;
		try {
			result = this.lectionRepository.delete(lection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		}
		return result;
	}

	@Override
	public List<Lection> getAll() {

		List<Lection> resultLections = null;
		try {
			resultLections = this.lectionRepository.getAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultLections = new ArrayList<Lection>();
		}
		return resultLections;
	}

	@Override
	public List<Lection> getSortedByDate() {
		List<Lection> tempLections = null;
		try {
			tempLections = this.lectionRepository.getSortedByDate();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLections = new ArrayList<Lection>();
		}
		return tempLections;
	}

	@Override
	public List<Lection> getSortedByName() {
		List<Lection> tempLections = null;
		try {
			tempLections = this.lectionRepository.getSortedByName();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLections = new ArrayList<Lection>();
		}
		return tempLections;
	}

	@Override
	public List<Lection> getLectionsByDate(Date date) {
		List<Lection> tempLections = null;
		try {
			tempLections = this.lectionRepository.getLectionsByDate(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLections = new ArrayList<Lection>();
		}
		return tempLections;
	}

	@Override
	public int getCount() {
		int count = 0;
		try {
			count = this.lectionRepository.getCount();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return count;
	}

}

package com.training.danco.services.impl;

import java.util.Date;

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
		
		Lection resultLection = null;
		try {
			
			resultLection = this.get(lection.getId());
			
			if (resultLection == null){
				if (this.set(lection))
				{
					resultLection = lection;
				}
			}
			
			
			this.lectionRepository.update(resultLection);
		
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultLection = null;
		}
		return resultLection != null;
	}

	@Override
	public boolean delete(Lection lection) {
		
		boolean result = true;
		try {
			result = this.lectionRepository.delete(lection, this.courseRepository);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		}
		return result;
	}

	@Override
	public Lection[] getAll() {

		Lection[] resultLections = null;
		try {
			resultLections = this.lectionRepository.getAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return resultLections;
	}

	@Override
	public Lection[] getSortedByDate() {
		Lection[] tempLections = null;
		try {
			tempLections = this.lectionRepository.getSortedByDate();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempLections;
	}

	@Override
	public Lection[] getSortedByName() {
		Lection[] tempLections = null;
		try {
			tempLections = this.lectionRepository.getSortedByName();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempLections;
	}

	@Override
	public Lection[] getLectionsByDate(Date date) {
		Lection[] tempLections = null;
		try {
			tempLections = this.lectionRepository.getLectionsByDate(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
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

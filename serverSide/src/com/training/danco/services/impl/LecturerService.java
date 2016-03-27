package com.training.danco.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.dao.api.ICourseRepository;
import com.training.danco.dao.api.ILecturerRepository;
import com.training.danco.model.Lecturer;
import com.training.danco.services.api.ILecturerService;

public class LecturerService implements ILecturerService {

	private static final Logger LOGGER = LogManager.getLogger(LecturerService.class);

	private ILecturerRepository lecturerRepository;

	public LecturerService(ILecturerRepository lecturerRepository) {
		this.lecturerRepository = lecturerRepository;
	}

	@Override
	public boolean set(Lecturer lecturer) {

		boolean result = true;
		try {
			result = this.lecturerRepository.set(lecturer);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		}
		return result;
	}

	@Override
	public Lecturer get(int id) {

		Lecturer resultLecturer = null;
		try {
			resultLecturer = this.lecturerRepository.get(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		return resultLecturer;
	}

	@Override
	public boolean update(Lecturer lecturer) {
		boolean result = false;
		try {
			Lecturer resultLecturer = this.get(lecturer.getId());

			if (resultLecturer == null) {

				result = this.set(lecturer);
			} else {
				result = this.lecturerRepository.update(lecturer);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean delete(Lecturer lecturer) {

		boolean result = true;
		try {
			result = this.lecturerRepository.delete(lecturer);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		}
		return result;
	}

	@Override
	public List<Lecturer> getAll() {

		List<Lecturer> resultLecturers = null;
		try {
			resultLecturers = this.lecturerRepository.getAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultLecturers = new ArrayList<Lecturer>();
		}
		return resultLecturers;
	}

	@Override
	public List<Lecturer> getSortedByName() {

		List<Lecturer> tempLecturers = null;
		try {
			tempLecturers = this.lecturerRepository.getSortedByName();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLecturers = new ArrayList<Lecturer>();
		}
		return tempLecturers;
	}

	@Override
	public List<Lecturer> getSortedByCoursesCount() {

		List<Lecturer> tempLecturers = null;
		try {
			tempLecturers = this.lecturerRepository.getSortedByCoursesCount(courseRepository);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLecturers = new ArrayList<Lecturer>();
		}
		return tempLecturers;
	}

	@Override
	public int getCount() {
		int count = 0;
		try {
			count = this.lecturerRepository.getCount();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return count;
	}

}

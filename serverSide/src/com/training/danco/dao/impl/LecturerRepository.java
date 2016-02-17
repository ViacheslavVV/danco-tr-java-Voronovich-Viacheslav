package com.training.danco.dao.impl;

import java.util.Collections;
import java.util.List;

import com.training.danco.comparator.Comparator;
import com.training.danco.comparator.LecturerCoursesCountComparator;
import com.training.danco.dao.api.ICourseRepository;
import com.training.danco.dao.api.ILecturerRepository;
import com.training.danco.model.Course;
import com.training.danco.model.Lecturer;

public class LecturerRepository implements ILecturerRepository {

	private List<Lecturer> lecturers;
	private static int lecturerId = 1;
	static private final int MIN_CORRECT_ID = 1;

	public static void setLecturerId(int id) {
		lecturerId = id;
	}

	public LecturerRepository(List<Lecturer> lecturers) {
		this.lecturers = lecturers;
	}

	@Override
	public boolean set(Lecturer lecturer) {
		int id = lecturer.getId();
		if (id < MIN_CORRECT_ID) {
			lecturer.setId(lecturerId++);
		}  else if (id >= lecturerId){
			lecturerId = ++id;
		}
		return this.lecturers.add(lecturer);
	}

	@Override
	public Lecturer get(int id) {
		int index = getLecturerIndexById(id);
		if (index != -1) {
			return this.lecturers.get(index);
		}
		return null;
	}

	@Override
	public boolean update(Lecturer lecturer) {
		int index = getLecturerIndexById(lecturer.getId());
		if (index != -1) {
			this.lecturers.set(index, lecturer);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Lecturer lecturer, ICourseRepository courseRepository) {
		int index = getLecturerIndexById(lecturer.getId());
		if (index != -1) {
			if (this.lecturers.remove(index) != null) {
				for (Course course : courseRepository.getAll()) {
					if (course.getLecturer() == null) {
						continue;
					}
					if (course.getLecturer().getId() == lecturer.getId())
						course.setLecturer(null);
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Lecturer> getAll() {

		Collections.sort(this.lecturers, Comparator.LECTURER_ID_COMPARATOR);
		return this.lecturers;
	}

	private int getLecturerIndexById(int id) {
		for (int i = 0; i < this.lecturers.size(); i++) {
			if (this.lecturers.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public List<Lecturer> getSortedByName() {
		Collections.sort(this.lecturers, Comparator.LECTURER_NAME_COMPARATOR);
		return this.lecturers;
	}

	@Override
	public List<Lecturer> getSortedByCoursesCount(ICourseRepository courseRepository) {
		Collections.sort(this.lecturers, new LecturerCoursesCountComparator(courseRepository));
		return this.lecturers;
	}

	@Override
	public int getCount() {
		return this.lecturers.size();
	}

}

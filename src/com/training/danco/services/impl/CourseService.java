package com.training.danco.services.impl;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.dao.api.ICourseRepository;
import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;
import com.training.danco.services.api.ICourseService;

public class CourseService implements ICourseService {

	private static final Logger LOGGER = LogManager.getLogger(CourseService.class);
	
	private ICourseRepository courseRepository;
	
	public CourseService(ICourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public boolean set(Course course) {
		boolean result = true;
		try {
			result = courseRepository.set(course);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		}
		return result;
	}

	@Override
	public Course get(int id) {
		
		Course resultCourse = null;
		try {
			resultCourse = this.courseRepository.get(id);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return resultCourse;
	}

	@Override
	public boolean update(Course course) {
		
		Course resultCourse = null;
		try {
			
			resultCourse = this.get(course.getId());
			
			if (resultCourse == null){
				if (this.set(course))
				{
					resultCourse = course;
				}
			}
			
			this.courseRepository.update(resultCourse);
		
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultCourse = null;
		}
		return resultCourse != null;
	}

	@Override
	public boolean delete(Course course) {
		boolean result = true;
		try {
			result = this.courseRepository.delete(course);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		}
		return result;
	}

	@Override
	public Course[] getAll() {

		Course[] resultCourses = null;
		try {
			resultCourses = this.courseRepository.getAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return resultCourses;
	}

	@Override
	public int getCount() {
		
		int count = 0;
		try {
			count = this.courseRepository.getCount();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return count;
	}

	@Override
	public boolean setLecturer(Course course, Lecturer lecturer) {
		boolean result = true;
		try {
			course.setLecturer(lecturer);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		}
		return result;
		
	}

	@Override
	public boolean addLection(Course course, Lection lection) {
		boolean result = true;
		try {
			result = course.setLection(lection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		}
		return result;
	}

	@Override
	public boolean addStudent(Course course, Student student) {
		boolean result = true;
		try {
			result = course.setStudent(student);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		}
		return result;
	}

	@Override
	public boolean removeLection(Course course, Lection lection) {
		boolean result = true;
		try {
			result = course.removeLection(lection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		}
		return result;
	}

	@Override
	public boolean removeStudent(Course course, Student student) {
		boolean result = true;
		try {
			result = course.removeStudent(student);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		}
		return result;
	}

	
	@Override
	public Course[] getCoursesInInterval(Date dateFrom, Date dateTo) {
		Course[] tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCoursesInInterval(dateFrom, dateTo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempCourses;
	}

	@Override
	public Course[] getSortedByStartDate() {
		Course[] tempCourses = null;
		try {
			tempCourses = this.courseRepository.getSortedByStartDate();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempCourses;
	}

	@Override
	public Course[] getSortedByStudentsCount() {
		Course[] tempCourses = null;
		try {
			tempCourses = this.courseRepository.getSortedByStudentsCount();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempCourses;
	}

	@Override
	public Course[] getSortedByLecturer() {
		Course[] tempCourses = null;
		try {
			tempCourses = this.courseRepository.getSortedByLecturer();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempCourses;
	}

	@Override
	public Course[] getSortedByName() {
		Course[] tempCourses = null;
		try {
			tempCourses = this.courseRepository.getSortedByName();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempCourses;
	}

	@Override
	public Course[] getCurrentCoursesSortedByStartDate() {
		Course[] tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCurrentCoursesSortedByStartDate();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempCourses;
	}

	@Override
	public Course[] getCurrentCoursesSortedByStudentsCount() {
		Course[] tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCurrentCoursesSortedByStudentsCount();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempCourses;
	}

	@Override
	public Course[] getCurrentCoursesSortedByLecturer() {
		Course[] tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCurrentCoursesSortedByLecturer();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempCourses;
	}

	@Override
	public Course[] getCurrentCoursesSortedByName() {
		Course[] tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCurrentCoursesSortedByName();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempCourses;
	}

	@Override
	public Course[] getCoursesAfterDateSortedByStartDate(Date date) {
		Course[] tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByStartDate(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempCourses;
	}

	@Override
	public Course[] getCoursesAfterDateSortedByStudentsCount(Date date) {
		Course[] tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByStudentsCount(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempCourses;
	}

	@Override
	public Course[] getCoursesAfterDateSortedByLecturer(Date date) {
		Course[] tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByLecturer(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempCourses;
	}

	@Override
	public Course[] getCoursesAfterDateSortedByName(Date date) {
		Course[] tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByName(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempCourses;
	}

	@Override
	public Lection[] getLectionsByCourse(Course course) {
		Lection[] tempLections = null;
		try {
			if (course != null){
				tempLections = course.getLections();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempLections;
	}

	@Override
	public Student[] getStudentsByCourse(Course course) {
		Student[] tempStudents = null;
		try {
			if (course != null){
				tempStudents = course.getStudents();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempStudents;
	}

	@Override
	public Lecturer getLectureByCourse(Course course) {
		
		Lecturer lecturer = null;
		try {
			if (course != null){
				lecturer = course.getLecturer();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lecturer;
	}

}

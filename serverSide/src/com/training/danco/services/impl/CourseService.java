package com.training.danco.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public List<Course> getAll() {

		List<Course> resultCourses = null;
		try {
			resultCourses = this.courseRepository.getAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			resultCourses = new ArrayList<Course>();
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
	public List<Course> getCoursesInInterval(Date dateFrom, Date dateTo) {
		List<Course> tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCoursesInInterval(dateFrom, dateTo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByStartDate() {
		List<Course> tempCourses = null;
		try {
			tempCourses = this.courseRepository.getSortedByStartDate();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByStudentsCount() {
		List<Course> tempCourses = null;
		try {
			tempCourses = this.courseRepository.getSortedByStudentsCount();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByLecturer() {
		List<Course> tempCourses = null;
		try {
			tempCourses = this.courseRepository.getSortedByLecturer();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		}
		return tempCourses;
	}

	@Override
	public List<Course> getSortedByName() {
		List<Course> tempCourses = null;
		try {
			tempCourses = this.courseRepository.getSortedByName();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStartDate() {
		List<Course> tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCurrentCoursesSortedByStartDate();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStudentsCount() {
		List<Course> tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCurrentCoursesSortedByStudentsCount();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByLecturer() {
		List<Course> tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCurrentCoursesSortedByLecturer();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCurrentCoursesSortedByName() {
		List<Course> tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCurrentCoursesSortedByName();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStartDate(Date date) {
		List<Course> tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByStartDate(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Date date) {
		List<Course> tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByStudentsCount(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByLecturer(Date date) {
		List<Course> tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByLecturer(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		}
		return tempCourses;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByName(Date date) {
		List<Course> tempCourses = null;
		try {
			tempCourses = this.courseRepository.getCoursesAfterDateSortedByName(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempCourses = new ArrayList<Course>();
		}
		return tempCourses;
	}

	@Override
	public List<Lection> getLectionsByCourse(Course course) {
		List<Lection> tempLections = null;
		try {
			if (course != null){
				tempLections = course.getLections();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempLections = new ArrayList<Lection>();
		}
		return tempLections;
	}

	@Override
	public List<Student> getStudentsByCourse(Course course) {
		List<Student> tempStudents = null;
		try {
			if (course != null){
				tempStudents = course.getStudents();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			tempStudents = new ArrayList<Student>();
		}
		return tempStudents;
	}

	@Override
	public Lecturer getLecturerByCourse(Course course) {
		
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

	@Override
	public boolean cloneCourse(Course course) {
		boolean result = true;
		try {
			Course newCourse = this.courseRepository.cloneCourse(course);
			if (newCourse == null){
				result = false;
			}
			result = this.courseRepository.set(newCourse);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			result = false;
		}
		
		return result;
	}

}

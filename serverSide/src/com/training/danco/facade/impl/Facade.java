package com.training.danco.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.dim.DependencyInjectionManager;
import com.training.danco.controller.*;
import com.training.danco.data.manager.api.*;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.*;
import com.training.danco.text.converter.DataConverter;
import com.training.danco.text.io.api.IExporter;
import com.training.danco.text.io.api.IImporter;

public class Facade implements IFacade {

	private static final Logger LOGGER = LogManager.getLogger(Facade.class);

	private CourseController courseController;
	private LectionController lectionController;
	private LecturerController lecturerController;
	private StudentController studentController;

	private IDeserializer dataDeserializer;
	private ISerializer dataSerializer;
	private DataConverter dataConverter;

	private IImporter importer;
	private IExporter exporter;

	public Facade() {
		this.importer = (IImporter) DependencyInjectionManager.getClassInstance(IImporter.class);
		this.exporter = (IExporter) DependencyInjectionManager.getClassInstance(IExporter.class);
		this.dataDeserializer = (IDeserializer) DependencyInjectionManager.getClassInstance(IDeserializer.class);
		this.dataSerializer = (ISerializer) DependencyInjectionManager.getClassInstance(ISerializer.class);
		this.dataConverter = new DataConverter();
		List<Object> data = this.dataDeserializer.getDataObjects();
		this.dataConverter.convertObjectsToEntities(data);
		this.dataConverter.fillControllers();
		this.fillControllersFromConvertor();
	}

	private void fillControllersFromConvertor() {

		this.courseController = this.dataConverter.getCourseController();
		this.lectionController = this.dataConverter.getLectionController();
		this.lecturerController = this.dataConverter.getLecturerController();
		this.studentController = this.dataConverter.getStudentController();
	}

	// Course Region

	public boolean setCourse(Object course) {
		Course tempCourse = null;
		try {
			@SuppressWarnings("unchecked")
			List<Object> courseData = (List<Object>) course;

			tempCourse = new Course((String) courseData.get(0), (Date) courseData.get(1), (Date) courseData.get(2),
					(int) courseData.get(3), (int) courseData.get(4));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempCourse == null ? false : courseController.setCourse(tempCourse);
	}

	public Course getCourse(Object courseId) {
		Course course = null;
		try {
			course = courseController.getCourse((int) courseId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return course;
	}

	public boolean updateCourse(Object course) {
		boolean result = false;
		try {
			result = courseController.updateCourse((Course) course);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	public boolean deleteCourse(Object courseId) {
		boolean result = false;
		try {
			result = courseController.deleteCourse((int) courseId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	public Lecturer getLecturerByCourse(Object courseId) {
		Lecturer lecturer = null;
		try {
			lecturer = courseController.getLecturerByCourse((int) courseId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lecturer;
	}

	public List<Course> getAllCourses() {
		return courseController.getAll();
	}

	public boolean setLecturerToCourse(Object courseAndLecturerId) {
		boolean result = false;
		try {
			@SuppressWarnings("unchecked")
			List<Object> data = (List<Object>) courseAndLecturerId;
			int courseId = (int) data.get(0);
			int lecturerId = (int) data.get(1);
			result = courseController.setLecturer(courseId, lecturerId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	public boolean addLectionToCourse(Object courseAndLectionId) {
		boolean result = false;
		try {
			@SuppressWarnings("unchecked")
			List<Object> data = (List<Object>) courseAndLectionId;
			int courseId = (int) data.get(0);
			int lectionId = (int) data.get(1);
			result = courseController.addLection(courseId, lectionId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	public boolean addStudentToCourse(Object courseAndStudentId) {
		boolean result = false;
		try {
			@SuppressWarnings("unchecked")
			List<Object> data = (List<Object>) courseAndStudentId;
			int courseId = (int) data.get(0);
			int studentId = (int) data.get(1);
			result = courseController.addStudent(courseId, studentId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	public boolean removeLectionFromCourse(Object courseAndLectionId) {
		boolean result = false;
		try {
			@SuppressWarnings("unchecked")
			List<Object> data = (List<Object>) courseAndLectionId;
			int courseId = (int) data.get(0);
			int lectionId = (int) data.get(1);
			result = courseController.removeLection(courseId, lectionId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	public boolean removeStudentFromCourse(Object courseAndStudentId) {
		boolean result = false;
		try {
			@SuppressWarnings("unchecked")
			List<Object> data = (List<Object>) courseAndStudentId;
			int courseId = (int) data.get(0);
			int studentId = (int) data.get(1);
			result = courseController.removeStudent(courseId, studentId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	public int getCoursesCount() {
		return courseController.getCount();
	}

	public List<Lection> getLectionsByCourse(Object courseId) {
		List<Lection> lections = new ArrayList<>();
		try {
			lections = courseController.getLectionsByCourse((int) courseId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lections;
	}

	public List<Student> getStudentsByCourse(Object courseId) {

		List<Student> students = new ArrayList<>();
		try {
			students = courseController.getStudentsByCourse((int) courseId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return students;
	}

	public List<Course> getCoursesInInterval(Object dateFromAndTo) {
		List<Course> courses = new ArrayList<>();
		try {
			@SuppressWarnings("unchecked")
			List<Object> data = (List<Object>) dateFromAndTo;
			Date dateFrom = (Date) data.get(0);
			Date dateTo = (Date) data.get(1);
			courses = courseController.getCoursesInInterval(dateFrom, dateTo);
		} catch (Exception e) {

		}
		return courses;
	}

	public List<Course> getCoursesSortedByStartDate() {
		return courseController.getSortedByStartDate();
	}

	public List<Course> getCoursesSortedByStudentsCount() {
		return courseController.getSortedByStudentsCount();
	}

	public List<Course> getCoursesSortedByLecturer() {
		return courseController.getSortedByLecturer();
	}

	public List<Course> getCoursesSortedByName() {
		return courseController.getSortedByName();
	}

	public List<Course> getCurrentCoursesSortedByStartDate() {
		return courseController.getCurrentCoursesSortedByStartDate();
	}

	public List<Course> getCurrentCoursesSortedByStudentsCount() {
		return courseController.getCurrentCoursesSortedByStudentsCount();
	}

	public List<Course> getCurrentCoursesSortedByLecturer() {
		return courseController.getCurrentCoursesSortedByLecturer();
	}

	public List<Course> getCurrentCoursesSortedByName() {
		return courseController.getCurrentCoursesSortedByName();
	}

	public List<Course> getCoursesAfterDateSortedByStartDate(Object date) {
		List<Course> lections = new ArrayList<>();
		try {
			lections = courseController.getCoursesAfterDateSortedByStartDate((Date) date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lections;
	}

	public List<Course> getCoursesAfterDateSortedByStudentsCount(Object date) {
		List<Course> lections = new ArrayList<>();
		try {
			lections = courseController.getCoursesAfterDateSortedByStudentsCount((Date) date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lections;
	}

	public List<Course> getCoursesAfterDateSortedByLecturer(Object date) {
		List<Course> lections = new ArrayList<>();
		try {
			lections = courseController.getCoursesAfterDateSortedByLecturer((Date) date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lections;
	}

	public List<Course> getCoursesAfterDateSortedByName(Object date) {
		List<Course> lections = new ArrayList<>();
		try {
			lections = courseController.getCoursesAfterDateSortedByName((Date) date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lections;
	}

	// Lection Region

	public boolean setLection(Object lection) {
		Lection tempLection = null;
		try {
			@SuppressWarnings("unchecked")
			List<Object> lectionData = (List<Object>) lection;

			tempLection = new Lection((String) lectionData.get(0), (Date) lectionData.get(1));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempLection == null ? false : lectionController.setLection(tempLection);
	}

	public Lection getLection(Object lectionId) {
		Lection lection = null;
		try {
			lection = lectionController.getLection((int) lectionId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lection;
	}

	public boolean updateLection(Object lection) {
		boolean result = false;
		try {
			result = lectionController.updateLection((Lection) lection);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	public boolean deleteLection(Object lectionId) {
		boolean result = false;
		try {
			result = lectionController.deleteLection((int) lectionId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	public List<Lection> getAllLections() {
		return lectionController.getAll();
	}

	public List<Lection> getLectionsSortedByDate() {
		return lectionController.getSortedByDate();
	}

	public List<Lection> getLectionsSortedByName() {
		return lectionController.getSortedByName();
	}

	public List<Lection> getLectionsByDate(Object date) {
		List<Lection> lections = new ArrayList<>();
		try {
			lections = lectionController.getLectionsByDate((Date) date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lections;
	}

	public int getLectionsCount() {
		return lectionController.getCount();
	}

	// LEcturer Region

	public boolean setLecturer(Object lecturer) {
		Lecturer tempLecturer = null;
		try {
			@SuppressWarnings("unchecked")
			List<Object> lecturerData = (List<Object>) lecturer;

			tempLecturer = new Lecturer((String) lecturerData.get(0), (int) lecturerData.get(1));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempLecturer == null ? false : lecturerController.setLecturer(tempLecturer);
	}

	public Lecturer getLecturer(Object lecturerId) {
		Lecturer lecturer = null;
		try {
			lecturer = lecturerController.getLecturer((int) lecturerId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lecturer;
	}

	public boolean updateLecturer(Object lecturer) {
		boolean result = false;
		try {
			result = lecturerController.updateLecturer((Lecturer) lecturer);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	public boolean deleteLecturer(Object lecturerId) {
		boolean result = false;
		try {
			result = lecturerController.deleteLecturer((int) lecturerId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	public List<Lecturer> getAllLecturers() {
		return lecturerController.getAll();
	}

	public List<Lecturer> getLecturersSortedByName() {
		return lecturerController.getSortedByName();
	}

	public List<Lecturer> getLecturersSortedByCoursesCount() {
		return lecturerController.getSortedByCoursesCount();
	}

	public int getLecturersCount() {
		return lecturerController.getCount();
	}

	// Student Region

	public boolean setStudent(Object student) {
		Student tempStudent = null;
		try {
			@SuppressWarnings("unchecked")
			List<Object> studentData = (List<Object>) student;

			tempStudent = new Student((String) studentData.get(0), (int) studentData.get(1));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return tempStudent == null ? false : studentController.setStudent(tempStudent);
	}

	public Student getStudent(Object studentId) {
		Student student = null;
		try {
			student = studentController.getStudent((int) studentId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return student;
	}

	public boolean updateStudent(Object student) {
		boolean result = false;
		try {
			result = studentController.updateStudent((Student) student);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	public boolean deleteStudent(Object studentId) {
		boolean result = false;
		try {
			result = studentController.deleteStudent((int) studentId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	public List<Student> getAllStudents() {
		return studentController.getAll();
	}

	public int getStudentsCount() {
		return studentController.getCount();
	}

	public boolean loadDataFromFIle() {
		List<Object> data = this.dataDeserializer.getDataObjects();
		if (this.dataConverter.convertObjectsToEntities(data)) {
			this.dataConverter.fillControllers();
			this.fillControllersFromConvertor();
			return true;
		}
		return false;
	}

	public boolean saveDataToFile() {
		Object data = this.dataConverter.convertDataToObject(this.getAllStudents(), this.getAllLections(),
				this.getAllLecturers(), this.getAllCourses());
		return this.dataSerializer.saveData(data);
	}

	@Override
	public boolean cloneCourse(Object courseId) {
		boolean result = false;
		try {
			result = this.courseController.cloneCourse((int) courseId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean importCourses(Object fileName) {
		boolean result = false;
		try {
			List<Course> courses = this.importer.importCourses((String) fileName, this);
			if (courses == null) {
				return result;
			}

			for (Course course : courses) {
				if (this.updateCourse(course)) {
					result = true;
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean importStudents(Object fileName) {
		boolean result = false;
		try {
			List<Student> students = this.importer.importStudents((String) fileName);
			if (students == null) {
				return result;
			}

			for (Student student : students) {
				if (this.updateStudent(student)) {
					result = true;
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean importLections(Object fileName) {
		boolean result = false;
		try {
			List<Lection> lections = this.importer.importLections((String) fileName);
			if (lections == null) {
				return result;
			}

			for (Lection lection : lections) {
				if (this.updateLection(lection)) {
					result = true;
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean importLecturers(Object fileName) {
		boolean result = false;
		try {
			List<Lecturer> lecturers = this.importer.importLecturers((String) fileName);
			if (lecturers == null) {
				return result;
			}

			for (Lecturer lecturer : lecturers) {
				if (this.updateLecturer(lecturer)) {
					result = true;
				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean exportAllCourses(Object fileName) {

		boolean result = false;
		try {
			result = this.exporter.exportCourses((String) fileName, this.getAllCourses());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean exportAllStudents(Object fileName) {

		boolean result = false;
		try {
			result = this.exporter.exportStudents((String) fileName, this.getAllStudents());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean exportAllLections(Object fileName) {

		boolean result = false;
		try {
			result = this.exporter.exportLections((String) fileName, this.getAllLections());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public boolean exportAllLecturers(Object fileName) {
		boolean result = false;
		try {
			result = this.exporter.exportLecturers((String) fileName, this.getAllLecturers());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean exportCourses(Object fileNameAndCourseIds) {
		List<Object> data = null;
		List<Integer> lectionIds = null;
		List<Course> courses = new ArrayList<Course>();
		try {
			Course course;
			data = (List<Object>) fileNameAndCourseIds;
			lectionIds = (List<Integer>) data.get(1);
			for (Object courseId : lectionIds) {
				course = this.getCourse(courseId);
				if (course != null) {
					courses.add(course);
				}
			}
			return this.exporter.exportCourses((String) data.get(0), courses);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean exportStudents(Object fileNameAndStudentIds) {
		List<Object> data = null;
		List<Student> students = new ArrayList<Student>();
		List<Integer> studentIds = null;
		try {
			Student student;
			data = (List<Object>) fileNameAndStudentIds;
			studentIds = (List<Integer>) data.get(1);
			for (Object studentId : studentIds) {
				student = this.getStudent(studentId);
				if (student != null) {
					students.add(student);
				}
			}
			return this.exporter.exportStudents((String) data.get(0), students);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean exportLections(Object fileNameAndLectionIds) {
		List<Object> data = null;
		List<Integer> lectionIds = null;
		List<Lection> lections = new ArrayList<Lection>();
		try {
			Lection lection;
			data = (List<Object>) fileNameAndLectionIds;
			lectionIds = (List<Integer>) data.get(1);
			for (Integer lectionId : lectionIds) {
				lection = this.getLection(lectionId);
				if (lection != null) {
					lections.add(lection);
				}
			}
			return this.exporter.exportLections((String) data.get(0), lections);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean exportLecturers(Object fileNameAndLecturerIds) {
		List<Object> data = null;
		List<Integer> lecturerIds = null;
		List<Lecturer> lecturers = new ArrayList<Lecturer>();
		try {
			Lecturer lecturer;
			data = (List<Object>) fileNameAndLecturerIds;
			lecturerIds = (List<Integer>) data.get(1);
			for (Object lecturerId : lecturerIds) {
				lecturer = this.getLecturer(lecturerId);
				if (lecturer != null) {
					lecturers.add(lecturer);
				}
			}
			return this.exporter.exportLecturers((String) data.get(0), lecturers);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return false;
	}

}
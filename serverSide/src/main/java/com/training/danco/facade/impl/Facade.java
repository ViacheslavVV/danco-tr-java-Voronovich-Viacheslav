package com.training.danco.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.training.danco.dim.DependencyInjectionManager;
import com.training.danco.controller.*;
import com.training.danco.dao.api.ICourseRepository;
import com.training.danco.dao.api.ILectionRepository;
import com.training.danco.dao.api.ILecturerRepository;
import com.training.danco.dao.api.IStudentRepository;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.*;
import com.training.danco.params.CourseDateParam;
import com.training.danco.params.SortingParam;
import com.training.danco.services.api.ICourseService;
import com.training.danco.services.api.ILectionService;
import com.training.danco.services.api.ILecturerService;
import com.training.danco.services.api.IStudentService;
import com.training.danco.services.impl.CourseService;
import com.training.danco.services.impl.LectionService;
import com.training.danco.services.impl.LecturerService;
import com.training.danco.services.impl.StudentService;
import com.training.danco.text.io.api.IExporter;
import com.training.danco.text.io.api.IImporter;

public class Facade implements IFacade {

	private static final Logger LOGGER = LogManager.getLogger(Facade.class);

	private CourseController courseController;
	private LectionController lectionController;
	private LecturerController lecturerController;
	private StudentController studentController;

	private IImporter importer;
	private IExporter exporter;

	public Facade() {
		this.importer = (IImporter) DependencyInjectionManager.getClassInstance(IImporter.class);
		this.exporter = (IExporter) DependencyInjectionManager.getClassInstance(IExporter.class);
		this.initControllers();
	}

	public Integer setCourse(Object course) {
		Course tempCourse = null;
		Integer result = null;
		synchronized (this.courseController) {
			try {
				@SuppressWarnings("unchecked")
				List<Object> courseData = (List<Object>) course;

				tempCourse = new Course((String) courseData.get(0), (Date) courseData.get(1), (Date) courseData.get(2),
						(Integer) courseData.get(3), (Integer) courseData.get(4));
				
				result = this.courseController.setCourse(tempCourse);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	public Course getCourse(Object courseId) {
		Course course = null;
		try {
			course = courseController.getCourse((Integer) courseId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return course;
	}

	public Boolean updateCourse(Object course) {
		Boolean result = false;
		synchronized (this.courseController) {
			try {
				result = courseController.updateCourse((Course) course);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	public Boolean deleteCourse(Object courseId) {
		Boolean result = false;
		synchronized (this.courseController) {
			try {
				result = courseController.deleteCourse((Integer) courseId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	public List<Course> getAllCourses() {
		return courseController.getAll();
	}

	@Override
	public Boolean setLecturerToCourse(Object courseAndLecturerId) {
		Boolean result = false;
		synchronized (this.courseController) {
			try {
				@SuppressWarnings("unchecked")
				List<Object> data = (List<Object>) courseAndLecturerId;
				Integer courseId = (Integer) data.get(0);
				Integer lecturerId = (Integer) data.get(1);
				result = courseController.setLecturer(courseId, lecturerId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@Override
	public Boolean addLectionToCourse(Object courseAndLectionId) {
		Boolean result = false;
		try {
			@SuppressWarnings("unchecked")
			List<Object> data = (List<Object>) courseAndLectionId;
			Integer courseId = (Integer) data.get(0);
			Integer lectionId = (Integer) data.get(1);
			result = courseController.addLection(courseId, lectionId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public Boolean addStudentToCourse(Object courseAndStudentId) {
		Boolean result = false;
		synchronized (this.courseController) {
			try {
				@SuppressWarnings("unchecked")
				List<Object> data = (List<Object>) courseAndStudentId;
				Integer courseId = (Integer) data.get(0);
				Integer studentId = (Integer) data.get(1);
				result = courseController.addStudent(courseId, studentId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@Override
	public Boolean removeLectionFromCourse(Object courseAndLectionId) {
		Boolean result = false;
		synchronized (this.courseController) {
			try {
				@SuppressWarnings("unchecked")
				List<Object> data = (List<Object>) courseAndLectionId;
				Integer courseId = (Integer) data.get(0);
				Integer lectionId = (Integer) data.get(1);
				result = courseController.removeLection(courseId, lectionId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@Override
	public Boolean removeStudentFromCourse(Object courseAndStudentId) {
		Boolean result = false;
		synchronized (this.courseController) {
			try {
				@SuppressWarnings("unchecked")
				List<Object> data = (List<Object>) courseAndStudentId;
				Integer courseId = (Integer) data.get(0);
				Integer studentId = (Integer) data.get(1);
				result = courseController.removeStudent(courseId, studentId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@Override
	public Integer getCoursesCount() {
		return courseController.getCount();
	}

	@Override
	public List<Course> getCoursesInInterval(Object dateFromAndTo) {
		List<Course> courses = new ArrayList<>();
		try {
			@SuppressWarnings("unchecked")
			List<Object> data = (List<Object>) dateFromAndTo;
			Date dateFrom = (Date) data.get(0);
			Date dateTo = (Date) data.get(1);
			courses = courseController.getCoursesInInterval(dateFrom, dateTo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return courses;
	}

	@Override
	public List<Course> getCoursesSortedByStartDate() {
		return courseController.getSorted(CourseDateParam.NONE, SortingParam.START_DATE, null);
	}

	@Override
	public List<Course> getCoursesSortedByStudentsCount() {
		return courseController.getSorted(CourseDateParam.NONE, SortingParam.STUDENTS_COUNT, null);
	}

	@Override
	public List<Course> getCoursesSortedByLecturer() {
		return courseController.getSorted(CourseDateParam.NONE, SortingParam.LECTURER, null);
	}

	@Override
	public List<Course> getCoursesSortedByName() {
		return courseController.getSorted(CourseDateParam.NONE, SortingParam.NAME, null);
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStartDate() {
		return courseController.getSorted(CourseDateParam.CURRENT, SortingParam.START_DATE, null);
	}

	@Override
	public List<Course> getCurrentCoursesSortedByStudentsCount() {
		return courseController.getSorted(CourseDateParam.CURRENT, SortingParam.STUDENTS_COUNT, null);
	}

	@Override
	public List<Course> getCurrentCoursesSortedByLecturer() {
		return courseController.getSorted(CourseDateParam.CURRENT, SortingParam.LECTURER, null);
	}

	@Override
	public List<Course> getCurrentCoursesSortedByName() {
		return courseController.getSorted(CourseDateParam.CURRENT, SortingParam.NAME, null);
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStartDate(Object date) {
		List<Course> lections = new ArrayList<>();
		try {
			lections = courseController.getSorted(CourseDateParam.AFTER_DATE, SortingParam.START_DATE,(Date) date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lections;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Object date) {
		List<Course> lections = new ArrayList<>();
		try {
			lections = courseController.getSorted(CourseDateParam.AFTER_DATE, SortingParam.STUDENTS_COUNT,(Date) date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lections;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByLecturer(Object date) {
		List<Course> lections = new ArrayList<>();
		try {
			lections = courseController.getSorted(CourseDateParam.AFTER_DATE, SortingParam.LECTURER,(Date) date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lections;
	}

	@Override
	public List<Course> getCoursesAfterDateSortedByName(Object date) {
		List<Course> lections = new ArrayList<>();
		try {
			lections = courseController.getSorted(CourseDateParam.AFTER_DATE, SortingParam.NAME,(Date) date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lections;
	}

	// Lection Region
	@Override
	public Integer setLection(Object lection) {
		Lection tempLection = null;
		Integer result = null;
		synchronized (this.lectionController) {
			try {
				@SuppressWarnings("unchecked")
				List<Object> lectionData = (List<Object>) lection;

				tempLection = new Lection((String) lectionData.get(0), (Date) lectionData.get(1));
				result = lectionController.setLection(tempLection);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
			
		}
		return result;
	}

	@Override
	public Lection getLection(Object lectionId) {
		Lection lection = null;
		try {
			lection = lectionController.getLection((Integer) lectionId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lection;
	}

	@Override
	public Boolean updateLection(Object lection) {
		Boolean result = false;
		synchronized (this.lectionController) {
			try {
				result = lectionController.updateLection((Lection) lection);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@Override
	public Boolean deleteLection(Object lectionId) {
		Boolean result = false;
		synchronized (this.lectionController) {
			try {
				result = lectionController.deleteLection((Integer) lectionId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@Override
	public List<Lection> getAllLections() {
		return lectionController.getAll();
	}
	
	@Override
	public List<Lection> getLectionsSortedByDate() {
		return lectionController.getSorted(SortingParam.DATE);
	}

	@Override
	public List<Lection> getLectionsSortedByName() {
		return lectionController.getSorted(SortingParam.NAME);
	}
	
	@Override
	public List<Lection> getLectionsByDate(Object date) {
		List<Lection> lections = new ArrayList<>();
		try {
			lections = lectionController.getLectionsByDate((Date) date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lections;
	}

	@Override
	public Integer getLectionsCount() {
		return lectionController.getCount();
	}

	// LEcturer Region

	@Override
	public Integer setLecturer(Object lecturer) {
		Lecturer tempLecturer = null;
		Integer result = null;
		synchronized (this.lecturerController) {
			try {
				@SuppressWarnings("unchecked")
				List<Object> lecturerData = (List<Object>) lecturer;
				tempLecturer = new Lecturer((String) lecturerData.get(0), (Integer) lecturerData.get(1));
				result = lecturerController.setLecturer(tempLecturer);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
			
		}
		return result;
	}

	@Override
	public Lecturer getLecturer(Object lecturerId) {
		Lecturer lecturer = null;
		try {
			lecturer = lecturerController.getLecturer((Integer) lecturerId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return lecturer;
	}

	@Override
	public Boolean updateLecturer(Object lecturer) {
		Boolean result = false;
		synchronized (this.lecturerController) {
			try {
				result = lecturerController.updateLecturer((Lecturer) lecturer);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@Override
	public Boolean deleteLecturer(Object lecturerId) {
		Boolean result = false;
		synchronized (this.lecturerController) {
			try {
				result = lecturerController.deleteLecturer((Integer) lecturerId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@Override
	public List<Lecturer> getAllLecturers() {
		return lecturerController.getAll();
	}

	@Override
	public List<Lecturer> getLecturersSortedByName() {
		return lecturerController.getSorted(SortingParam.NAME);
	}

	@Override
	public List<Lecturer> getLecturersSortedByCoursesCount() {
		return lecturerController.getSorted(SortingParam.COURSE_COUNT);
	}

	@Override
	public Integer getLecturersCount() {
		return lecturerController.getCount();
	}

	// Student Region

	@Override
	public Integer setStudent(Object student) {
		Student tempStudent = null;
		Integer result = null;
		synchronized (this.studentController) {
			try {
				@SuppressWarnings("unchecked")
				List<Object> studentData = (List<Object>) student;

				tempStudent = new Student((String) studentData.get(0), (Integer) studentData.get(1));
				result = studentController.setStudent(tempStudent);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
			
		}
		return result;
	}

	@Override
	public Student getStudent(Object studentId) {
		Student student = null;
		synchronized (this.studentController) {
			try {
				student = studentController.getStudent((Integer) studentId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return student;
	}

	@Override
	public Boolean updateStudent(Object student) {
		Boolean result = false;
		synchronized (this.studentController) {
			try {
				result = studentController.updateStudent((Student) student);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@Override
	public Boolean deleteStudent(Object studentId) {
		Boolean result = false;
		synchronized (this.studentController) {
			try {
				result = studentController.deleteStudent((Integer) studentId);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentController.getAll();
	}

	@Override
	public Integer getStudentsCount() {
		return studentController.getCount();
	}

	@Override
	public Boolean cloneCourse(Object courseId) {
		Boolean result = false;
		try {
			result = this.courseController.cloneCourse((Integer) courseId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}

	@Override
	public Boolean importCourses(Object fileName) {
		Boolean result = false;
		synchronized (this.courseController) {
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
		}
		return result;
	}

	@Override
	public Boolean importStudents(Object fileName) {
		Boolean result = false;
		synchronized (this.studentController) {
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
		}
		return result;
	}

	@Override
	public Boolean importLections(Object fileName) {
		Boolean result = false;
		synchronized (this.lectionController) {
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
		}
		return result;
	}

	@Override
	public Boolean importLecturers(Object fileName) {
		Boolean result = false;
		synchronized (this.lecturerController) {
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
		}
		return result;
	}

	@Override
	public Boolean exportAllCourses(Object fileName) {

		Boolean result = false;
		synchronized (this.exporter) {
			try {
				result = this.exporter.exportCourses((String) fileName, this.getAllCourses());
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@Override
	public Boolean exportAllStudents(Object fileName) {

		Boolean result = false;
		synchronized (this.exporter) {
			try {
				result = this.exporter.exportStudents((String) fileName, this.getAllStudents());
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@Override
	public Boolean exportAllLections(Object fileName) {

		Boolean result = false;
		synchronized (this.exporter) {
			try {
				result = this.exporter.exportLections((String) fileName, this.getAllLections());
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@Override
	public Boolean exportAllLecturers(Object fileName) {
		Boolean result = false;
		synchronized (this.exporter) {
			try {
				result = this.exporter.exportLecturers((String) fileName, this.getAllLecturers());
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean exportCourses(Object fileNameAndCourseIds) {
		Boolean result = false;
		synchronized (this.exporter) {
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
				result = this.exporter.exportCourses((String) data.get(0), courses);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean exportStudents(Object fileNameAndStudentIds) {
		Boolean result = false;
		synchronized (this.exporter) {
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
				result = this.exporter.exportStudents((String) data.get(0), students);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean exportLections(Object fileNameAndLectionIds) {
		Boolean result = false;
		synchronized (this.exporter) {
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
				result = this.exporter.exportLections((String) data.get(0), lections);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean exportLecturers(Object fileNameAndLecturerIds) {
		Boolean result = false;
		synchronized (this.exporter) {
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
				result = this.exporter.exportLecturers((String) data.get(0), lecturers);
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
		return result;
	}

	private void initControllers() {
		
		ICourseRepository courseRepository = (ICourseRepository)DependencyInjectionManager.getClassInstance(ICourseRepository.class);
		ILectionRepository lectionRepository = (ILectionRepository)DependencyInjectionManager.getClassInstance(ILectionRepository.class);
		IStudentRepository studentRepository = (IStudentRepository)DependencyInjectionManager.getClassInstance(IStudentRepository.class);
		ICourseService courseService = new CourseService(courseRepository, lectionRepository, studentRepository);
		IStudentService studentService = new StudentService(studentRepository);
		ILectionService lectionService = new LectionService(lectionRepository);
		ILecturerService lecturerService = new LecturerService((ILecturerRepository)DependencyInjectionManager.getClassInstance(ILecturerRepository.class));
		this.courseController = new CourseController(courseService, lecturerService, lectionService, studentService);
		this.lectionController = new LectionController(lectionService);
		this.lecturerController = new LecturerController(lecturerService);
		this.studentController = new StudentController(studentService);
	}

}
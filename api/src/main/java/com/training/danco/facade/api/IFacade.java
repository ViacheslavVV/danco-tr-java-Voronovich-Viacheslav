package com.training.danco.facade.api;

import java.util.List;

import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;

public interface IFacade {
	public Integer setCourse(Object course);
	
	public Course getCourse(Object courseId);
	
	public Boolean updateCourse(Object course);
	
	public Boolean deleteCourse(Object courseId);
	
	public Boolean cloneCourse(Object courseId);
	
	public List<Course> getAllCourses();
	
	public Integer getCoursesCount();
	
	public Boolean removeStudentFromCourse(Object courseAndStudentId);
	
	public Boolean removeLectionFromCourse(Object courseAndLectionId);
	
	public Boolean addStudentToCourse(Object courseAndStudentId);
	
	public Boolean addLectionToCourse(Object courseAndLectionId);
	
	public Boolean setLecturerToCourse(Object courseAndLecturerId);
	
	public List<Course> getCoursesInInterval(Object dateFromAndTo);
	
	public List<Course> getCoursesSortedByStartDate();
	
	public List<Course> getCoursesSortedByStudentsCount();
	
	public List<Course> getCoursesSortedByLecturer();
	
	public List<Course> getCoursesSortedByName();
	
	public List<Course> getCurrentCoursesSortedByStartDate();
	
	public List<Course> getCurrentCoursesSortedByStudentsCount();
	
	public List<Course> getCurrentCoursesSortedByLecturer();
	
	public List<Course> getCurrentCoursesSortedByName();
	
	public List<Course> getCoursesAfterDateSortedByStartDate(Object date);
	
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Object date);
	
	public List<Course> getCoursesAfterDateSortedByLecturer(Object date);
	
	public List<Course> getCoursesAfterDateSortedByName(Object date);
		
	public Integer setLection(Object lection);
	
	public Lection getLection(Object lectionId);
	
	public Boolean updateLection(Object lection);
	
	public Boolean deleteLection(Object lectionId);
	
	public List<Lection> getAllLections();

	public List<Lection> getLectionsSortedByDate();
	
	public List<Lection> getLectionsSortedByName();
	
	public List<Lection> getLectionsByDate(Object date);
	
	public Integer getLectionsCount();
	
	public Integer setLecturer(Object lecturer);
	
	public Lecturer getLecturer(Object lecturerId);
	
	public Boolean updateLecturer(Object lecturer);
	
	public Boolean deleteLecturer(Object lecturerId);
	
	public List<Lecturer> getAllLecturers();

	public List<Lecturer> getLecturersSortedByName();
	
	public List<Lecturer> getLecturersSortedByCoursesCount();
	
	public Integer getLecturersCount();
	
	public Integer setStudent(Object student);
	
	public Student getStudent(Object studentId);
	
	public Boolean updateStudent(Object student);
	
	public Boolean deleteStudent(Object studentId);
	
	public List<Student> getAllStudents();
	
	public Integer getStudentsCount();
	
	public Boolean importCourses(Object fileName);

	public Boolean importStudents(Object fileName);
	
	public Boolean importLections(Object fileName);
	
	public Boolean importLecturers(Object fileName);
	
	public Boolean exportAllCourses(Object fileName);
	
	public Boolean exportAllStudents(Object fileName);
	
	public Boolean exportAllLections(Object fileName);
	
	public Boolean exportAllLecturers(Object fileName);
	
	public Boolean exportCourses(Object fileNameAndCourseIds);
	
	public Boolean exportStudents(Object fileNameAndStudentIds);
	
	public Boolean exportLections(Object fileNameAndLectionIds);
	
	public Boolean exportLecturers(Object fileNameAndLecturerIds);
}

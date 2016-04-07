package com.training.danco.facade.api;

import java.util.List;

import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;

public interface IFacade {
	public Integer setCourse(Object course);
	
	public Course getCourse(Object courseId);
	
	public boolean updateCourse(Object course);
	
	public boolean deleteCourse(Object courseId);
	
	public boolean cloneCourse(Object courseId);
	
	public List<Course> getAllCourses();
	
	public int getCoursesCount();
	
	public boolean removeStudentFromCourse(Object courseAndStudentId);
	
	public boolean removeLectionFromCourse(Object courseAndLectionId);
	
	public boolean addStudentToCourse(Object courseAndStudentId);
	
	public boolean addLectionToCourse(Object courseAndLectionId);
	
	public boolean setLecturerToCourse(Object courseAndLecturerId);
	
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
	
	public boolean updateLection(Object lection);
	
	public boolean deleteLection(Object lectionId);
	
	public List<Lection> getAllLections();

	public List<Lection> getLectionsSortedByDate();
	
	public List<Lection> getLectionsSortedByName();
	
	public List<Lection> getLectionsByDate(Object date);
	
	public int getLectionsCount();
	
	public Integer setLecturer(Object lecturer);
	
	public Lecturer getLecturer(Object lecturerId);
	
	public boolean updateLecturer(Object lecturer);
	
	public boolean deleteLecturer(Object lecturerId);
	
	public List<Lecturer> getAllLecturers();

	public List<Lecturer> getLecturersSortedByName();
	
	public List<Lecturer> getLecturersSortedByCoursesCount();
	
	public int getLecturersCount();
	
	public Integer setStudent(Object student);
	
	public Student getStudent(Object studentId);
	
	public boolean updateStudent(Object student);
	
	public boolean deleteStudent(Object studentId);
	
	public List<Student> getAllStudents();
	
	public int getStudentsCount();
	
	public boolean importCourses(Object fileName);

	public boolean importStudents(Object fileName);
	
	public boolean importLections(Object fileName);
	
	public boolean importLecturers(Object fileName);
	
	public boolean exportAllCourses(Object fileName);
	
	public boolean exportAllStudents(Object fileName);
	
	public boolean exportAllLections(Object fileName);
	
	public boolean exportAllLecturers(Object fileName);
	
	public boolean exportCourses(Object fileNameAndCourseIds);
	
	public boolean exportStudents(Object fileNameAndStudentIds);
	
	public boolean exportLections(Object fileNameAndLectionIds);
	
	public boolean exportLecturers(Object fileNameAndLecturerIds);
}

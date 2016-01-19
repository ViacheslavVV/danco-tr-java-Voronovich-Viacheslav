package com.training.danco.facade.api;

import java.util.Date;
import java.util.List;

import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;

public interface IFacade {
	public boolean setCourse(Course course);
	
	public Course getCourse(int courseId);
	
	public boolean updateCourse(Course course);
	
	public boolean deleteCourse(int courseId);
	
	public Lecturer getLecturerByCourse(int courseId);
	
	public List<Course> getAllCourses();
	
	public boolean setLecturerToCourse(int courseId, int lecturerId);
	
	public boolean addLectionToCourse(int courseId, int lectionId);
	
	public boolean addStudentToCourse(int courseId, int studentId);
	
	public boolean removeLectionFromCourse(int courseId, int lectionId);
	
	public boolean removeStudentFromCourse(int courseId, int studentId);
	
	public int getCoursesCount();
	
	public List<Lection> getLectionsByCourse(int courseId);
	
	public List<Student> getStudentsByCourse(int courseId);
	
	public List<Course> getCoursesInInterval(Date dateFrom, Date dateTo);
	
	public List<Course> getCoursesSortedByStartDate();
	
	public List<Course> getCoursesSortedByStudentsCount();
	
	public List<Course> getCoursesSortedByLecturer();
	
	public List<Course> getCoursesSortedByName();
	
	public List<Course> getCurrentCoursesSortedByStartDate();
	
	public List<Course> getCurrentCoursesSortedByStudentsCount();
	
	public List<Course> getCurrentCoursesSortedByLecturer();
	
	public List<Course> getCurrentCoursesSortedByName();
	
	public List<Course> getCoursesAfterDateSortedByStartDate(Date date);
	
	public List<Course> getCoursesAfterDateSortedByStudentsCount(Date date);
	
	public List<Course> getCoursesAfterDateSortedByLecturer(Date date);
	
	public List<Course> getCoursesAfterDateSortedByName(Date date);
		
	public boolean setLection(Lection lection);
	
	public Lection getLection(int lectionId);
	
	public boolean updateLection(Lection lection);
	
	public boolean deleteLection(int lectionId);
	
	public List<Lection> getAllLections();

	public List<Lection> getLectionsSortedByDate();
	
	public List<Lection> getLectionsSortedByName();
	
	public List<Lection> getLectionsByDate(Date date);
	
	public int getLectionsCount();
	
	public boolean setLecturer(Lecturer lecturer);
	
	public Lecturer getLecturer(int lecturerId);
	
	public boolean updateLecturer(Lecturer lecturer);
	
	public boolean deleteLecturer(int lecturerId);
	
	public List<Lecturer> getAllLecturers();

	public List<Lecturer> getLecturersSortedByName();
	
	public List<Lecturer> getLecturersSortedByCoursesCount();
	
	public int getLecturersCount();
	
	public boolean setStudent(Student student);
	
	public Student getStudent(int studentId);
	
	public boolean updateStudent(Student student);
	
	public boolean deleteStudent(int studentId);
	
	public List<Student> getAllStudents();
	
	public int getStudentsCount();
	
	public void loadDataFromFIle();

	public void saveDataToFile();
	
	public boolean setFileName(String fileName);
}

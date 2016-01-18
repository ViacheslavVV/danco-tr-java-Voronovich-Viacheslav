package com.training.danco.facade.api;

import java.util.Date;

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
	
	public Course[] getAllCourses();
	
	public boolean setLecturerToCourse(int courseId, int lecturerId);
	
	public boolean addLectionToCourse(int courseId, int lectionId);
	
	public boolean addStudentToCourse(int courseId, int studentId);
	
	public boolean removeLectionFromCourse(int courseId, int lectionId);
	
	public boolean removeStudentFromCourse(int courseId, int studentId);
	
	public int getCoursesCount();
	
	public Lection[] getLectionsByCourse(int courseId);
	
	public Student[] getStudentsByCourse(int courseId);
	
	public Course[] getCoursesInInterval(Date dateFrom, Date dateTo);
	
	public Course[] getCoursesSortedByStartDate();
	
	public Course[] getCoursesSortedByStudentsCount();
	
	public Course[] getCoursesSortedByLecturer();
	
	public Course[] getCoursesSortedByName();
	
	public Course[] getCurrentCoursesSortedByStartDate();
	
	public Course[] getCurrentCoursesSortedByStudentsCount();
	
	public Course[] getCurrentCoursesSortedByLecturer();
	
	public Course[] getCurrentCoursesSortedByName();
	
	public Course[] getCoursesAfterDateSortedByStartDate(Date date);
	
	public Course[] getCoursesAfterDateSortedByStudentsCount(Date date);
	
	public Course[] getCoursesAfterDateSortedByLecturer(Date date);
	
	public Course[] getCoursesAfterDateSortedByName(Date date);
		
	public boolean setLection(Lection lection);
	
	public Lection getLection(int lectionId);
	
	public boolean updateLection(Lection lection);
	
	public boolean deleteLection(int lectionId);
	
	public Lection[] getAllLections();

	public Lection[] getLectionsSortedByDate();
	
	public Lection[] getLectionsSortedByName();
	
	public Lection[] getLectionsByDate(Date date);
	
	public int getLectionsCount();
	
	public boolean setLecturer(Lecturer lecturer);
	
	public Lecturer getLecturer(int lecturerId);
	
	public boolean updateLecturer(Lecturer lecturer);
	
	public boolean deleteLecturer(int lecturerId);
	
	public Lecturer[] getAllLecturers();

	public Lecturer[] getLecturersSortedByName();
	
	public Lecturer[] getLecturersSortedByCoursesCount();
	
	public int getLecturersCount();
	
	public boolean setStudent(Student student);
	
	public Student getStudent(int studentId);
	
	public boolean updateStudent(Student student);
	
	public boolean deleteStudent(int studentId);
	
	public Student[] getAllStudents();
	
	public int getStudentsCount();
	
	public void loadDataFromFIle();

	public void saveDataToFile();
	
	public boolean setFileName(String fileName);
}

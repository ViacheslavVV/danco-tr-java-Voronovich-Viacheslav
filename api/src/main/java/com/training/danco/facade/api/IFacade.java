package com.training.danco.facade.api;

import java.util.Date;
import java.util.List;

import com.training.danco.model.Audit;
import com.training.danco.model.Course;
import com.training.danco.model.Lection;
import com.training.danco.model.Lecturer;
import com.training.danco.model.Student;
import com.training.danco.model.User;
import com.training.danco.params.CourseDateParam;
import com.training.danco.params.SortingParam;

public interface IFacade {
	
	public Integer setUser(User user);
	
	public User getUser(Integer userId);
	
	public List<User> getAllUsers();
	
	public User getUserByLogin(String login);
	
	public Integer setAudit(Audit audit);
	
	public Integer setCourse(Object course);
	
	public Course getCourse(Object courseId);
	
	public Boolean updateCourse(Object course);
	
	public Boolean deleteCourse(Object courseId);
	
	public Boolean cloneCourse(Object courseId);
	
	public List<Course> getAllCourses();
	
	public Integer getCoursesCount();
	
	public List<Student> getStudentsExceptCourse(Integer courseId);
	
	public List<Lection> getFreeLections();
	
	public Boolean removeStudentFromCourse(Object courseAndStudentId);
	
	public Boolean removeLectionFromCourse(Object courseAndLectionId);
	
	public Boolean addStudentToCourse(Integer courseId, Integer studentId);
	
	public Boolean addLectionToCourse(Integer courseId, Integer lectionId);
	
	public Boolean setLecturerToCourse(Object courseAndLecturerId);
	
	public List<Student> getStudentsByCourse(Integer courseId);
	
	public List<Lection> getLectionsByCourse(Integer courseId);
	
	public List<Course> getCoursesByStudent(Integer studentId);
	
	public List<Course> getCoursesByLecturer(Integer lecturerId);
	
	public List<Course> getCoursesInInterval(Object dateFromAndTo);
	
	public List<Course> getSortedCourses(CourseDateParam courseDateParam, SortingParam sortingParam, Date date);
		
	public Integer setLection(Object lection);
	
	public Lection getLection(Object lectionId);
	
	public Boolean updateLection(Object lection);
	
	public Boolean deleteLection(Object lectionId);
	
	public List<Lection> getAllLections();

	public List<Lection> getSortedLections(SortingParam sortingParam);
	
	public List<Lection> getLectionsByDate(Object date);
	
	public Integer getLectionsCount();
	
	public Integer setLecturer(Object lecturer);
	
	public Lecturer getLecturer(Object lecturerId);
	
	public Boolean updateLecturer(Object lecturer);
	
	public Boolean deleteLecturer(Object lecturerId);
	
	public List<Lecturer> getAllLecturers();

	public List<Lecturer> getSortedLecturers(SortingParam sortingParam);
	
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

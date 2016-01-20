package com.danco.training.ui.menu.builder;

import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.ext.*;

public class MenuBuilder {

	public Menu generateMenu(){
		
		Menu mainMenu = new Menu("Main menu");
		Menu workWithDataMenu = new Menu("Work with Data menu");
		Menu courseMenu = new Menu("Course menu");
		Menu lecturerMenu = new Menu("Lecturer menu");
		Menu lectionMenu = new Menu("Lection menu");
		Menu studentMenu = new Menu("Student menu");
		Menu coursesMenu = new Menu("Courses menu");
		Menu lecturersMenu = new Menu("Lecturers menu");
		Menu lectionsMenu = new Menu("Lections menu");
		Menu studentsMenu = new Menu("Students menu");
		
		//Main
		mainMenu.addMenuItem(new NavigationItem(workWithDataMenu));
		mainMenu.addMenuItem(new LoadDataItem(mainMenu));
		mainMenu.addMenuItem(new SaveDataItem(mainMenu));
		mainMenu.addMenuItem(new NewFileNameItem(mainMenu));
		mainMenu.addMenuItem(new ExitMenuItem());
		
		//Work with data menu
		workWithDataMenu.addMenuItem(new NavigationItem(courseMenu));
		workWithDataMenu.addMenuItem(new NavigationItem(lecturerMenu));
		workWithDataMenu.addMenuItem(new NavigationItem(lectionMenu));
		workWithDataMenu.addMenuItem(new NavigationItem(studentMenu));
		workWithDataMenu.addMenuItem(new NavigationItem(mainMenu));
		
		//Course menu
		courseMenu.addMenuItem(new NewCourseItem(courseMenu));
		courseMenu.addMenuItem(new CourseItem(courseMenu));
		courseMenu.addMenuItem(new DeleteCourseItem(courseMenu));
		courseMenu.addMenuItem(new CourseCloneItem(courseMenu));
		courseMenu.addMenuItem(new SetLecturerToCourseItem(courseMenu));
		courseMenu.addMenuItem(new AddLectionToCourseItem(courseMenu));
		courseMenu.addMenuItem(new AddStudentToCourseItem(courseMenu));
		courseMenu.addMenuItem(new RemoveLectionFromCourseItem(courseMenu));
		courseMenu.addMenuItem(new RemoveStudentFromCourseItem(courseMenu));
		courseMenu.addMenuItem(new LectionsByCourseItem(courseMenu));
		courseMenu.addMenuItem(new StudentsByCourseItem(courseMenu));
		courseMenu.addMenuItem(new LecturerByCourseItem(courseMenu));
		courseMenu.addMenuItem(new NavigationItem(coursesMenu));
		courseMenu.addMenuItem(new NavigationItem(workWithDataMenu));
		
		//Courses menu
		coursesMenu.addMenuItem(new AllCoursesItem(coursesMenu));
		coursesMenu.addMenuItem(new CoursesCountItem(coursesMenu));
		coursesMenu.addMenuItem(new CoursesInIntervalItem(coursesMenu));
		coursesMenu.addMenuItem(new CoursesCountItem(coursesMenu));
		coursesMenu.addMenuItem(new CoursesSortedByLecturerItem(coursesMenu));
		coursesMenu.addMenuItem(new CoursesSortedByNameItem(coursesMenu));
		coursesMenu.addMenuItem(new CoursesSortedByStartDateItem(coursesMenu));
		coursesMenu.addMenuItem(new CoursesSortedByStudentsCountItem(coursesMenu));
		coursesMenu.addMenuItem(new CurrentCoursesSortedByLecturerItem(coursesMenu));
		coursesMenu.addMenuItem(new CurrentCoursesSortedByNameItem(coursesMenu));
		coursesMenu.addMenuItem(new CurrentCoursesSortedByStartDateItem(coursesMenu));
		coursesMenu.addMenuItem(new CurrentCoursesSortedByStudentsCountItem(coursesMenu));
		coursesMenu.addMenuItem(new CoursesAfterDateSortedByLecturerItem(coursesMenu));
		coursesMenu.addMenuItem(new CoursesAfterDateSortedByNameItem(coursesMenu));
		coursesMenu.addMenuItem(new CoursesAfterDateSortedByStartDateItem(coursesMenu));
		coursesMenu.addMenuItem(new CoursesAfterDateSortedByStudentsCountItem(coursesMenu));
		coursesMenu.addMenuItem(new NavigationItem(courseMenu));
		
		//Lecturer menu
		lecturerMenu.addMenuItem(new NewLecturerItem(lecturerMenu));
		lecturerMenu.addMenuItem(new LecturerItem(lecturerMenu));
		lecturerMenu.addMenuItem(new DeleteLecturerItem(lecturerMenu));
		lecturerMenu.addMenuItem(new NavigationItem(lecturersMenu));
		lecturerMenu.addMenuItem(new NavigationItem(workWithDataMenu));
		
		//Lecturers menu
		lecturersMenu.addMenuItem(new AllLecturersItem(lecturersMenu));
		lecturersMenu.addMenuItem(new LecturerCountItem(lecturersMenu));
		lecturersMenu.addMenuItem(new LecturersSortedByCoursesCountItem(lecturersMenu));
		lecturersMenu.addMenuItem(new LecturersSortedByNameItem(lecturersMenu));
		lecturersMenu.addMenuItem(new NavigationItem(lecturerMenu));
		
		//Lection menu
		lectionMenu.addMenuItem(new NewLectionItem(lectionMenu));
		lectionMenu.addMenuItem(new LectionItem(lectionMenu));
		lectionMenu.addMenuItem(new DeleteLectionItem(lectionMenu));
		lectionMenu.addMenuItem(new NavigationItem(lectionsMenu));
		lectionMenu.addMenuItem(new NavigationItem(workWithDataMenu));
		
		//Lections menu
		lectionsMenu.addMenuItem(new AllLectionsItem(lectionsMenu));
		lectionsMenu.addMenuItem(new LectionCountItem(lectionsMenu));
		lectionsMenu.addMenuItem(new LectionsSortedByNameItem(lectionsMenu));
		lectionsMenu.addMenuItem(new LectionsSortedByDateItem(lectionsMenu));
		lectionsMenu.addMenuItem(new NavigationItem(lectionMenu));
		
		//Student menu
		studentMenu.addMenuItem(new NewStudentItem(studentMenu));
		studentMenu.addMenuItem(new StudentItem(studentMenu));
		studentMenu.addMenuItem(new DeleteStudentItem(studentMenu));
		studentMenu.addMenuItem(new NavigationItem(studentsMenu));
		studentMenu.addMenuItem(new NavigationItem(workWithDataMenu));
		
		//Students menu
		studentsMenu.addMenuItem(new AllStudentsItem(studentsMenu));
		studentsMenu.addMenuItem(new StudentsCountItem(studentsMenu));
		studentsMenu.addMenuItem(new NavigationItem(studentMenu));
		
		return mainMenu;
	}
}

package com.danco.training.ui.menu.builder;

import com.danco.training.dim.DependencyInjectionManager;
import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.ext.*;
import com.training.danco.facade.api.IFacade;

public class MenuBuilder {

	public Menu generateMenu(){
		
		IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);
		
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
		mainMenu.addMenuItem(new LoadDataItem(mainMenu,facade));
		mainMenu.addMenuItem(new SaveDataItem(mainMenu,facade));
		mainMenu.addMenuItem(new NavigationItem("Exit",null));
		
		//Work with data menu
		workWithDataMenu.addMenuItem(new NavigationItem(courseMenu));
		workWithDataMenu.addMenuItem(new NavigationItem(lecturerMenu));
		workWithDataMenu.addMenuItem(new NavigationItem(lectionMenu));
		workWithDataMenu.addMenuItem(new NavigationItem(studentMenu));
		workWithDataMenu.addMenuItem(new NavigationItem(mainMenu));
		
		//Course menu
		courseMenu.addMenuItem(new NewCourseItem(courseMenu, facade));
		courseMenu.addMenuItem(new CourseItem(courseMenu, facade));
		courseMenu.addMenuItem(new DeleteCourseItem(courseMenu, facade));
		courseMenu.addMenuItem(new CourseCloneItem(courseMenu, facade));
		courseMenu.addMenuItem(new ImportCoursesItem(courseMenu, facade));
		courseMenu.addMenuItem(new ExportCoursesItem(courseMenu, facade));
		courseMenu.addMenuItem(new ExportAllCoursesItem(courseMenu, facade));
		courseMenu.addMenuItem(new SetLecturerToCourseItem(courseMenu, facade));
		courseMenu.addMenuItem(new AddLectionToCourseItem(courseMenu, facade));
		courseMenu.addMenuItem(new AddStudentToCourseItem(courseMenu, facade));
		courseMenu.addMenuItem(new RemoveLectionFromCourseItem(courseMenu, facade));
		courseMenu.addMenuItem(new RemoveStudentFromCourseItem(courseMenu, facade));
		courseMenu.addMenuItem(new LectionsByCourseItem(courseMenu, facade));
		courseMenu.addMenuItem(new StudentsByCourseItem(courseMenu, facade));
		courseMenu.addMenuItem(new LecturerByCourseItem(courseMenu, facade));
		courseMenu.addMenuItem(new NavigationItem(coursesMenu));
		courseMenu.addMenuItem(new NavigationItem(workWithDataMenu));
		
		//Courses menu
		coursesMenu.addMenuItem(new AllCoursesItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new CoursesCountItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new CoursesInIntervalItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new CoursesCountItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new CoursesSortedByLecturerItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new CoursesSortedByNameItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new CoursesSortedByStartDateItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new CoursesSortedByStudentsCountItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new CurrentCoursesSortedByLecturerItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new CurrentCoursesSortedByNameItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new CurrentCoursesSortedByStartDateItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new CurrentCoursesSortedByStudentsCountItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new CoursesAfterDateSortedByLecturerItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new CoursesAfterDateSortedByNameItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new CoursesAfterDateSortedByStartDateItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new CoursesAfterDateSortedByStudentsCountItem(coursesMenu, facade));
		coursesMenu.addMenuItem(new NavigationItem(courseMenu));
		
		//Lecturer menu
		lecturerMenu.addMenuItem(new NewLecturerItem(lecturerMenu, facade));
		lecturerMenu.addMenuItem(new LecturerItem(lecturerMenu, facade));
		lecturerMenu.addMenuItem(new DeleteLecturerItem(lecturerMenu, facade));
		lecturerMenu.addMenuItem(new ImportLecturersItem(lecturerMenu, facade));
		lecturerMenu.addMenuItem(new ExportLecturersItem(lecturerMenu, facade));
		lecturerMenu.addMenuItem(new ExportAllLecturersItem(lecturerMenu, facade));
		lecturerMenu.addMenuItem(new NavigationItem(lecturersMenu));
		lecturerMenu.addMenuItem(new NavigationItem(workWithDataMenu));
		
		//Lecturers menu
		lecturersMenu.addMenuItem(new AllLecturersItem(lecturersMenu, facade));
		lecturersMenu.addMenuItem(new LecturerCountItem(lecturersMenu, facade));
		lecturersMenu.addMenuItem(new LecturersSortedByCoursesCountItem(lecturersMenu, facade));
		lecturersMenu.addMenuItem(new LecturersSortedByNameItem(lecturersMenu, facade));
		lecturersMenu.addMenuItem(new NavigationItem(lecturerMenu));
		
		//Lection menu
		lectionMenu.addMenuItem(new NewLectionItem(lectionMenu, facade));
		lectionMenu.addMenuItem(new LectionItem(lectionMenu, facade));
		lectionMenu.addMenuItem(new DeleteLectionItem(lectionMenu, facade));
		lectionMenu.addMenuItem(new ImportLectionsItem(lectionMenu, facade));
		lectionMenu.addMenuItem(new ExportLectionsItem(lectionMenu, facade));
		lectionMenu.addMenuItem(new ExportAllLectionsItem(lectionMenu, facade));
		lectionMenu.addMenuItem(new NavigationItem(lectionsMenu));
		lectionMenu.addMenuItem(new NavigationItem(workWithDataMenu));
		
		//Lections menu
		lectionsMenu.addMenuItem(new AllLectionsItem(lectionsMenu, facade));
		lectionsMenu.addMenuItem(new LectionCountItem(lectionsMenu, facade));
		lectionsMenu.addMenuItem(new LectionsSortedByNameItem(lectionsMenu, facade));
		lectionsMenu.addMenuItem(new LectionsSortedByDateItem(lectionsMenu, facade));
		lectionsMenu.addMenuItem(new NavigationItem(lectionMenu));
		
		//Student menu
		studentMenu.addMenuItem(new NewStudentItem(studentMenu, facade));
		studentMenu.addMenuItem(new StudentItem(studentMenu, facade));
		studentMenu.addMenuItem(new DeleteStudentItem(studentMenu, facade));
		studentMenu.addMenuItem(new ImportStudentsItem(studentMenu, facade));
		studentMenu.addMenuItem(new ExportStudentsItem(studentMenu, facade));
		studentMenu.addMenuItem(new ExportAllStudentsItem(studentMenu, facade));
		studentMenu.addMenuItem(new NavigationItem(studentsMenu));
		studentMenu.addMenuItem(new NavigationItem(workWithDataMenu));
		
		//Students menu
		studentsMenu.addMenuItem(new AllStudentsItem(studentsMenu, facade));
		studentsMenu.addMenuItem(new StudentsCountItem(studentsMenu, facade));
		studentsMenu.addMenuItem(new NavigationItem(studentMenu));
		
		return mainMenu;
	}
}

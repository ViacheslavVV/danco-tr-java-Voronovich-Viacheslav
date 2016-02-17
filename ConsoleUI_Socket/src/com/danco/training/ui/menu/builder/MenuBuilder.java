package com.danco.training.ui.menu.builder;

import com.danco.training.ui.menu.Menu;
import com.danco.training.ui.menu.item.ext.*;
import com.training.danco.message.handler.IMessageHandler;

public class MenuBuilder {

	public Menu generateMenu(IMessageHandler messageHandler) {

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

		// Main
		mainMenu.addMenuItem(new NavigationItem(workWithDataMenu));
		mainMenu.addMenuItem(new LoadDataItem(mainMenu, messageHandler));
		mainMenu.addMenuItem(new SaveDataItem(mainMenu, messageHandler));
		mainMenu.addMenuItem(new NavigationItem("Exit", null));

		// Work with data menu
		workWithDataMenu.addMenuItem(new NavigationItem(courseMenu));
		workWithDataMenu.addMenuItem(new NavigationItem(lecturerMenu));
		workWithDataMenu.addMenuItem(new NavigationItem(lectionMenu));
		workWithDataMenu.addMenuItem(new NavigationItem(studentMenu));
		workWithDataMenu.addMenuItem(new NavigationItem(mainMenu));

		// Course menu
		courseMenu.addMenuItem(new NewCourseItem(courseMenu, messageHandler));
		courseMenu.addMenuItem(new CourseItem(courseMenu, messageHandler));
		courseMenu.addMenuItem(new DeleteCourseItem(courseMenu, messageHandler));
		courseMenu.addMenuItem(new CourseCloneItem(courseMenu, messageHandler));
		courseMenu.addMenuItem(new ImportCoursesItem(courseMenu, messageHandler));
		courseMenu.addMenuItem(new ExportCoursesItem(courseMenu, messageHandler));
		courseMenu.addMenuItem(new ExportAllCoursesItem(courseMenu, messageHandler));
		courseMenu.addMenuItem(new SetLecturerToCourseItem(courseMenu, messageHandler));
		courseMenu.addMenuItem(new AddLectionToCourseItem(courseMenu, messageHandler));
		courseMenu.addMenuItem(new AddStudentToCourseItem(courseMenu, messageHandler));
		courseMenu.addMenuItem(new RemoveLectionFromCourseItem(courseMenu, messageHandler));
		courseMenu.addMenuItem(new RemoveStudentFromCourseItem(courseMenu, messageHandler));
		courseMenu.addMenuItem(new LectionsByCourseItem(courseMenu, messageHandler));
		courseMenu.addMenuItem(new StudentsByCourseItem(courseMenu, messageHandler));
		courseMenu.addMenuItem(new LecturerByCourseItem(courseMenu, messageHandler));
		courseMenu.addMenuItem(new NavigationItem(coursesMenu));
		courseMenu.addMenuItem(new NavigationItem(workWithDataMenu));

		// Courses menu
		coursesMenu.addMenuItem(new AllCoursesItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new CoursesCountItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new CoursesInIntervalItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new CoursesCountItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new CoursesSortedByLecturerItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new CoursesSortedByNameItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new CoursesSortedByStartDateItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new CoursesSortedByStudentsCountItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new CurrentCoursesSortedByLecturerItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new CurrentCoursesSortedByNameItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new CurrentCoursesSortedByStartDateItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new CurrentCoursesSortedByStudentsCountItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new CoursesAfterDateSortedByLecturerItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new CoursesAfterDateSortedByNameItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new CoursesAfterDateSortedByStartDateItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new CoursesAfterDateSortedByStudentsCountItem(coursesMenu, messageHandler));
		coursesMenu.addMenuItem(new NavigationItem(courseMenu));

		// Lecturer menu
		lecturerMenu.addMenuItem(new NewLecturerItem(lecturerMenu, messageHandler));
		lecturerMenu.addMenuItem(new LecturerItem(lecturerMenu, messageHandler));
		lecturerMenu.addMenuItem(new DeleteLecturerItem(lecturerMenu, messageHandler));
		lecturerMenu.addMenuItem(new ImportLecturersItem(lecturerMenu, messageHandler));
		lecturerMenu.addMenuItem(new ExportLecturersItem(lecturerMenu, messageHandler));
		lecturerMenu.addMenuItem(new ExportAllLecturersItem(lecturerMenu, messageHandler));
		lecturerMenu.addMenuItem(new NavigationItem(lecturersMenu));
		lecturerMenu.addMenuItem(new NavigationItem(workWithDataMenu));

		// Lecturers menu
		lecturersMenu.addMenuItem(new AllLecturersItem(lecturersMenu, messageHandler));
		lecturersMenu.addMenuItem(new LecturerCountItem(lecturersMenu, messageHandler));
		lecturersMenu.addMenuItem(new LecturersSortedByCoursesCountItem(lecturersMenu, messageHandler));
		lecturersMenu.addMenuItem(new LecturersSortedByNameItem(lecturersMenu, messageHandler));
		lecturersMenu.addMenuItem(new NavigationItem(lecturerMenu));

		// Lection menu
		lectionMenu.addMenuItem(new NewLectionItem(lectionMenu, messageHandler));
		lectionMenu.addMenuItem(new LectionItem(lectionMenu, messageHandler));
		lectionMenu.addMenuItem(new DeleteLectionItem(lectionMenu, messageHandler));
		lectionMenu.addMenuItem(new ImportLectionsItem(lectionMenu, messageHandler));
		lectionMenu.addMenuItem(new ExportLectionsItem(lectionMenu, messageHandler));
		lectionMenu.addMenuItem(new ExportAllLectionsItem(lectionMenu, messageHandler));
		lectionMenu.addMenuItem(new NavigationItem(lectionsMenu));
		lectionMenu.addMenuItem(new NavigationItem(workWithDataMenu));

		// Lections menu
		lectionsMenu.addMenuItem(new AllLectionsItem(lectionsMenu, messageHandler));
		lectionsMenu.addMenuItem(new LectionCountItem(lectionsMenu, messageHandler));
		lectionsMenu.addMenuItem(new LectionsSortedByNameItem(lectionsMenu, messageHandler));
		lectionsMenu.addMenuItem(new LectionsSortedByDateItem(lectionsMenu, messageHandler));
		lectionsMenu.addMenuItem(new NavigationItem(lectionMenu));

		// Student menu
		studentMenu.addMenuItem(new NewStudentItem(studentMenu, messageHandler));
		studentMenu.addMenuItem(new StudentItem(studentMenu, messageHandler));
		studentMenu.addMenuItem(new DeleteStudentItem(studentMenu, messageHandler));
		studentMenu.addMenuItem(new ImportStudentsItem(studentMenu, messageHandler));
		studentMenu.addMenuItem(new ExportStudentsItem(studentMenu, messageHandler));
		studentMenu.addMenuItem(new ExportAllStudentsItem(studentMenu, messageHandler));
		studentMenu.addMenuItem(new NavigationItem(studentsMenu));
		studentMenu.addMenuItem(new NavigationItem(workWithDataMenu));

		// Students menu
		studentsMenu.addMenuItem(new AllStudentsItem(studentsMenu, messageHandler));
		studentsMenu.addMenuItem(new StudentsCountItem(studentsMenu, messageHandler));
		studentsMenu.addMenuItem(new NavigationItem(studentMenu));

		return mainMenu;
	}
}

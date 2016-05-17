package com.training.danco.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.danco.dim.DependencyInjectionManager;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;
import com.training.danco.params.CourseDateParam;
import com.training.danco.params.SortingParam;

public class CourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);

	public CourseServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getParameter("type");

		try {
			if (type.equals("GetOneCourse")) {
				Object result = facade.getCourse(Integer.parseInt(request.getParameter("courseId")));
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}
				request.getRequestDispatcher("/course/getOneCourse.jsp").forward(request, response);
			} else if (type.equals("GetManyCourses")) {
				CourseDateParam courseDateParam = CourseDateParam.valueOf(request.getParameter("courseDateParam"));
				SortingParam sortingParam = SortingParam.valueOf(request.getParameter("sortingParam"));
				Date date = null;
				if (courseDateParam == CourseDateParam.AFTER_DATE) {
					date = convertToDate(request.getParameter("date"));
				}
				List<Course> result = facade.getSortedCourses(courseDateParam, sortingParam, date);
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}

				request.getRequestDispatcher("/course/getManyCourses.jsp").forward(request, response);
			} else if (type.equals("GetCoursesInInterval")) {
				List<Object> list = new ArrayList<>();
				list.add(convertToDate(request.getParameter("dateFrom")));
				list.add(convertToDate(request.getParameter("dateTo")));
				List<Course> result = facade.getCoursesInInterval(list);
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}
				request.getRequestDispatcher("/course/getCoursesInInterval.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/course/courseMenu.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", true);
			request.getRequestDispatcher("/course/courseMenu.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getParameter("type");

		try {
			if (type.equals("AddLectionToCourse")) {
				List<Integer> list = new ArrayList<>();
				list.add(Integer.parseInt(request.getParameter("courseId")));
				list.add(Integer.parseInt(request.getParameter("lectionId")));
				Boolean result = facade.addLectionToCourse(list);
				request.setAttribute("result", result);
				request.getRequestDispatcher("/course/addLectionToCourse.jsp").forward(request, response);
			} else if (type.equals("AddStudentToCourse")) {
				List<Integer> list = new ArrayList<>();
				list.add(Integer.parseInt(request.getParameter("courseId")));
				list.add(Integer.parseInt(request.getParameter("studentId")));
				Boolean result = facade.addStudentToCourse(list);
				request.setAttribute("result", result);
				request.getRequestDispatcher("/course/addStudentToCourse.jsp").forward(request, response);
			} else if (type.equals("CreateCourse")) {
				List<Object> list = new ArrayList<>();
				list.add(request.getParameter("name"));
				list.add(convertToDate(request.getParameter("startDate")));
				list.add(convertToDate(request.getParameter("finalDate")));
				list.add(Integer.parseInt(request.getParameter("maxStudents")));
				list.add(Integer.parseInt(request.getParameter("maxLections")));
				Integer id = facade.setCourse(list);
				if (id == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", id);
				}
				request.getRequestDispatcher("/course/createCourse.jsp").forward(request, response);
			} else if (type.equals("SetLecturerToCourse")) {
				List<Integer> list = new ArrayList<>();
				list.add(Integer.parseInt(request.getParameter("courseId")));
				list.add(Integer.parseInt(request.getParameter("lecturerId")));
				Boolean result = facade.setLecturerToCourse(list);
				request.setAttribute("result", result);
				request.getRequestDispatcher("/course/setLecturerToCourse.jsp").forward(request, response);
			} else if (type.equals("DeleteCourse")) {
				Boolean result = facade.deleteCourse(Integer.parseInt(request.getParameter("courseId")));
				request.setAttribute("result", result);
				request.getRequestDispatcher("/course/deleteCourse.jsp").forward(request, response);
			} else if (type.equals("RemoveLectionFromCourse")) {
				List<Integer> list = new ArrayList<>();
				list.add(Integer.parseInt(request.getParameter("courseId")));
				list.add(Integer.parseInt(request.getParameter("lectionId")));
				Boolean result = facade.removeLectionFromCourse(list);
				request.setAttribute("result", result);
				request.getRequestDispatcher("/course/removeLectionFromCourse.jsp").forward(request,
						response);
			} else if (type.equals("RemoveStudentFromCourse")) {
				List<Integer> list = new ArrayList<>();
				list.add(Integer.parseInt(request.getParameter("courseId")));
				list.add(Integer.parseInt(request.getParameter("studentId")));
				Boolean result = facade.removeStudentFromCourse(list);
				request.setAttribute("result", result);
				request.getRequestDispatcher("/course/removeStudentFromCourse.jsp").forward(request,
						response);
			} else {
				request.getRequestDispatcher("/course/courseMenu.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", true);
			request.getRequestDispatcher("/course/courseMenu.jsp").forward(request, response);
		}
	}

	private Date convertToDate(String string) {
		String[] dateAndTime = string.split(" ");
		String[] startDate = dateAndTime[0].split("-");
		String[] startTime = dateAndTime[1].split(":");
		return new GregorianCalendar(Integer.parseInt(startDate[0]), Integer.parseInt(startDate[1]),
				Integer.parseInt(startDate[2]), Integer.parseInt(startTime[0]), Integer.parseInt(startTime[1]))
						.getTime();
	}
}

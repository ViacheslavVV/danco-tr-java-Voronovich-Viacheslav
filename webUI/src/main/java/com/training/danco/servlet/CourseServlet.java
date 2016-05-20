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
		
		String idParam = request.getParameter("id");

		try {
			if (idParam != null) {
				Integer id = Integer.parseInt(idParam);
				Course result = facade.getCourse(id);
				Object students = facade.getStudentsByCourse(id);
				Object lections = facade.getLectionsByCourse(id);
				request.setAttribute("course", result);
				request.setAttribute("lecturer", result.getLecturer());
				request.setAttribute("students", students);
				request.setAttribute("lections", lections);
				request.getRequestDispatcher("/course/course_detailed.jsp").forward(request, response);
			} else {
				CourseDateParam courseDateParam = null;
				SortingParam sortingParam = null;
				Date date = null;
				Date dateFrom = null;
				Date dateTo = null;

				boolean cont = true;
				Object resultList = null;
				try {
					dateFrom = convertToDate(request.getParameter("dateFrom"));
					dateTo = convertToDate(request.getParameter("dateTo"));
					cont = false;
					List<Date> dateFromAndTo = new ArrayList<Date>();
					dateFromAndTo.add(dateFrom);
					dateFromAndTo.add(dateTo);
					resultList = facade.getCoursesInInterval(dateFromAndTo);
				} catch (Exception c) {
				}

				if (cont) {
					try {
						courseDateParam = CourseDateParam.valueOf(request.getParameter("courseDateParam"));
						sortingParam = SortingParam.valueOf(request.getParameter("sortingParam"));
					} catch (Exception e) {
						courseDateParam = CourseDateParam.NONE;
						sortingParam = SortingParam.ID;
					}
					if (courseDateParam == CourseDateParam.AFTER_DATE) {
						date = convertToDate(request.getParameter("dateFrom"));
					}
					resultList = facade.getSortedCourses(courseDateParam, sortingParam, date);
					
				}
				request.setAttribute("courses", resultList);
				request.getRequestDispatcher("/course/course.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", "Something wrong!");
			request.getRequestDispatcher("/course/course.jsp").forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
		String name = request.getParameter("name");
		Date startDate = convertToDate(request.getParameter("startDate"));
		Date finalDate = convertToDate(request.getParameter("finalDate"));
		Integer maxLections = Integer.parseInt(request.getParameter("maxLections"));
		Integer maxStudents = Integer.parseInt(request.getParameter("maxStudents"));
		List<Object> list =  new ArrayList<>();
		list.add(name);
		list.add(startDate);
		list.add(finalDate);
		list.add(maxStudents);
		list.add(maxLections);
		facade.setCourse(list);
		} catch (Exception e){
		}
		response.sendRedirect("/Course");
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

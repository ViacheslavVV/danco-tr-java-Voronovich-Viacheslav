package com.training.danco.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.danco.dim.DependencyInjectionManager;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Course;

public class CourseEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);
	
    public CourseEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer courseId = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("course", facade.getCourse(courseId));
		request.getRequestDispatcher("/edit/edit_course.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Integer id = Integer.parseInt(request.getParameterValues("courseId")[0]);
			String name = request.getParameter("name");
			Date startDate = convertToDate(request.getParameter("startDate"));
			Date finalDate = convertToDate(request.getParameter("finalDate"));
			Integer maxLections = Integer.parseInt(request.getParameter("maxLections"));
			Integer maxStudents = Integer.parseInt(request.getParameter("maxStudents"));
			Course course = new Course(name, startDate, finalDate, maxStudents, maxLections);
			course.setId(id);
			facade.updateCourse(course);
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

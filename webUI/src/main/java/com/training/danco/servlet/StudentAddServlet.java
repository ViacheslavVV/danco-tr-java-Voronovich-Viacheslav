package com.training.danco.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.danco.dim.DependencyInjectionManager;
import com.training.danco.facade.api.IFacade;

/**
 * Servlet implementation class StudentAddServlet
 */
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);
	
    public StudentAddServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer courseId = Integer.parseInt(request.getParameter("courseId"));
		request.setAttribute("students", facade.getStudentsExceptCourse(courseId));
		request.setAttribute("courseId", courseId);
		request.getRequestDispatcher("/course/add_students.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer courseId = Integer.parseInt(request.getParameterValues("courseId")[0]);
		
		for (String studentId : request.getParameterValues("studentId")) {
			facade.addStudentToCourse(courseId, Integer.parseInt(studentId));
		}
		response.sendRedirect("/Course?id=" + courseId);
	}

}

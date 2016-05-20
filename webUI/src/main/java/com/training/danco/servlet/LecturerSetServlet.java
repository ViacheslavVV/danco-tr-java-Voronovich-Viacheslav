package com.training.danco.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.danco.dim.DependencyInjectionManager;
import com.training.danco.facade.api.IFacade;

public class LecturerSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);

	public LecturerSetServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("lecturers", facade.getAllLecturers());
		request.setAttribute("courseId", request.getParameter("courseId"));
		request.getRequestDispatcher("/course/set_lecturer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer courseId = Integer.parseInt(request.getParameterValues("courseId")[0]);
		Integer lecturerId = Integer.parseInt(request.getParameter("lecturerId"));
		List<Integer> courseAndLecturerId = new ArrayList<>();
		courseAndLecturerId.add(courseId);
		courseAndLecturerId.add(lecturerId);
		if (facade.setLecturerToCourse(courseAndLecturerId)) {
			request.setAttribute("message", "Ok");
		} else {
			request.setAttribute("message", "Something wrong!");
		}
		response.sendRedirect("/Course?id=" + courseId);
	}

}

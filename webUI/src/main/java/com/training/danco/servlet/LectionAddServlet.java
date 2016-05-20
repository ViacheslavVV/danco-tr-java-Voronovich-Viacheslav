package com.training.danco.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.danco.dim.DependencyInjectionManager;
import com.training.danco.facade.api.IFacade;

public class LectionAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);

	public LectionAddServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("lections", facade.getFreeLections());
		request.setAttribute("courseId", request.getParameter("courseId"));
		request.getRequestDispatcher("/course/add_lections.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer courseId = Integer.parseInt(request.getParameterValues("courseId")[0]);
		String[] lectionIds = request.getParameterValues("lectionId");
		if (lectionIds != null) {
			for (String lectionId : lectionIds) {
				facade.addLectionToCourse(courseId, Integer.parseInt(lectionId));
			}
		}
		response.sendRedirect("/Course?id=" + courseId);
	}

}

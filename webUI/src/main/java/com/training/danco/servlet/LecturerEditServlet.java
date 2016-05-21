package com.training.danco.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.danco.dim.DependencyInjectionManager;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lecturer;

public class LecturerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);

    public LecturerEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer lecturerId = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("lecturer", facade.getLecturer(lecturerId));
		request.getRequestDispatcher("/edit/edit_lecturer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameterValues("lecturerId")[0]);
			String name = request.getParameter("name");
			Integer age = Integer.parseInt(request.getParameter("age"));
			Lecturer lecturer = new Lecturer(name, age);
			lecturer.setId(id);
			facade.updateLecturer(lecturer);
		} catch (Exception e) {
		}
		response.sendRedirect("/Lecturer");
	}

}

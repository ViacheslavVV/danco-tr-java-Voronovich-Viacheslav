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
import com.training.danco.model.Lecturer;
import com.training.danco.params.SortingParam;

public class LecturerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);

	public LecturerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("id");

		try {
			if (idParam != null) {
				Integer id = Integer.parseInt(idParam);
				Lecturer result = facade.getLecturer(id);
				request.setAttribute("lecturer", result);
				request.setAttribute("courses", facade.getCoursesByLecturer(id));
				request.getRequestDispatcher("/lecturer/lecturer_detailed.jsp").forward(request, response);
			} else {
				SortingParam sortingParam = null;
				try {
					sortingParam = SortingParam.valueOf(request.getParameter("sortingParam"));
				} catch (Exception e) {
					sortingParam = SortingParam.ID;
				}

				Object lecturers = facade.getSortedLecturers(sortingParam);

				request.setAttribute("lecturers", lecturers);
				request.getRequestDispatcher("/lecturer/lecturer.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", "Something wrong!");
			request.getRequestDispatcher("/lecturer/lecturer.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String name = request.getParameter("name");
			Integer age = Integer.parseInt(request.getParameter("age"));
			List<Object> list = new ArrayList<>();
			list.add(name);
			list.add(age);
			facade.setLecturer(list);
		} catch (Exception e) {
		}
		response.sendRedirect("/Lecturer");
	}

}

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
import com.training.danco.model.Student;

public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);

	public StudentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");

		try {
			if (idParam != null) {
				Integer id = Integer.parseInt(idParam);
				Student result = facade.getStudent(id);
				request.setAttribute("student", result);
				request.setAttribute("course", result.getCourse());
				request.getRequestDispatcher("/student/student_detailed.jsp").forward(request, response);
			} else {

				Object resultList = facade.getAllStudents();
					
				request.setAttribute("students", resultList);
				request.getRequestDispatcher("/student/student.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", "Something wrong!");
			request.getRequestDispatcher("/student/student.jsp").forward(request, response);
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
			facade.setStudent(list);
		} catch (Exception e) {
		}
		response.sendRedirect("/Student");
	}

}

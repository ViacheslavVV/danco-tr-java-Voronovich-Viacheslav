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
		String type = request.getParameter("type");

		try {
			if (type.equals("GetStudent")) {
				Object result = facade.getStudent(Integer.parseInt(request.getParameter("studentId")));
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}
				request.getRequestDispatcher("/student/getStudent.jsp").forward(request, response);
			} else if (type.equals("GetAllStudents")) {
				List<Student> result = facade.getAllStudents();
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}

				request.getRequestDispatcher("/student/getAllStudents.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/student/studentMenu.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", true);
			request.getRequestDispatcher("/student/studentMenu.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");

		try {
			if (type.equals("CreateStudent")) {
				List<Object> list = new ArrayList<Object>();
				list.add(request.getParameter("name"));
				list.add(Integer.parseInt(request.getParameter("age")));
				Object result = facade.setStudent(list);
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}
				request.getRequestDispatcher("/student/createStudent.jsp").forward(request, response);
			} else if (type.equals("DeleteStudent")) {
				Integer id = Integer.parseInt(request.getParameter("studentId"));
				Boolean result = facade.deleteStudent(id);
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}

				request.getRequestDispatcher("/student/deleteStudent.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/student/studentMenu.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", true);
			request.getRequestDispatcher("/student/studentMenu.jsp").forward(request, response);
		}
	}

}

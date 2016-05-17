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

public class LecturerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);
	
    public LecturerServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");

		try {
			if (type.equals("GetLecturer")) {
				Object result = facade.getLecturer(Integer.parseInt(request.getParameter("lecturerId")));
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}
				request.getRequestDispatcher("/lecturer/getLecturer.jsp").forward(request, response);
			} else if (type.equals("GetAllLecturers")) {
				List<Lecturer> result = facade.getAllLecturers();
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}

				request.getRequestDispatcher("/lecturer/getAllLecturers.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/lecturer/lecturerMenu.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", true);
			request.getRequestDispatcher("/lecturer/lecturerMenu.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");

		try {
			if (type.equals("CreateLecturer")) {
				List<Object> list = new ArrayList<Object>();
				list.add(request.getParameter("name"));
				list.add(Integer.parseInt(request.getParameter("age")));
				Object result = facade.setLecturer(list);
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}
				request.getRequestDispatcher("/lecturer/createLecturer.jsp").forward(request, response);
			} else if (type.equals("DeleteLecturer")) {
				Integer id = Integer.parseInt(request.getParameter("lecturerId"));
				Boolean result = facade.deleteLecturer(id);
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}

				request.getRequestDispatcher("/lecturer/deleteLecturer.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/lecturer/lecturerMenu.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", true);
			request.getRequestDispatcher("/lecturer/lecturerMenu.jsp").forward(request, response);
		}
	}

}

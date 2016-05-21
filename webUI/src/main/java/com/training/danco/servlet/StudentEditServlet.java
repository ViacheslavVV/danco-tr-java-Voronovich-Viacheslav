package com.training.danco.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.danco.dim.DependencyInjectionManager;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Student;

public class StudentEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);

    public StudentEditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer studentId = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("student", facade.getStudent(studentId));
		request.getRequestDispatcher("/edit/edit_student.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameterValues("studentId")[0]);
			String name = request.getParameter("name");
			Integer age = Integer.parseInt(request.getParameter("age"));
			Student student = new Student(name, age);
			student.setId(id);
			facade.updateStudent(student);
		} catch (Exception e) {
		}
		response.sendRedirect("/Student");
	}

}

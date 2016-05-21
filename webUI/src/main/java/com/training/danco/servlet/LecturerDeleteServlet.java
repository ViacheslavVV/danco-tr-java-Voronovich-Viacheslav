package com.training.danco.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.danco.dim.DependencyInjectionManager;
import com.training.danco.facade.api.IFacade;

public class LecturerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);
	
    public LecturerDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Integer id = Integer.parseInt(request.getParameter("id"));
			facade.deleteLecturer(id);
		} catch(Exception e){
		}
		response.sendRedirect("/Lecturer");
	}

}

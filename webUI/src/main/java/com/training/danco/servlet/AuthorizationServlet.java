package com.training.danco.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.danco.dim.DependencyInjectionManager;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Audit;
import com.training.danco.model.User;

public class AuthorizationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);

	public AuthorizationServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/authorization.html").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = null;
		HttpSession session = request.getSession(true);
		String resourse = ((HttpServletRequest) request).getRequestURL().toString();

		String resultPage = null;
		user = facade.getUserByLogin(request.getParameter("login"));
		
		if (user != null
				&& request.getParameter("password").equals(user.getPassword())) {
			session.setAttribute("user", user);
			resultPage = "/welcome.jsp";
			Audit audit = new Audit(user, new Date(System.currentTimeMillis()), resourse);
			facade.setAudit(audit);
		} else {
			resultPage = "/authorization.html";
		}
		
		request.getRequestDispatcher(resultPage).forward(request, response);
	}

}

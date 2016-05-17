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

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);

    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = null;
		HttpSession session = request.getSession(true);
		StringBuffer resourse = ((HttpServletRequest) request).getRequestURL();

		String login = request.getParameter("login");
		String resultPage = null;
		for (User temp : facade.getAllUsers()){
			if (temp.getLogin().equals(login)){
				user = temp;
				break;
			}
		}
		
		if (user != null
				&& user.getPassword().equals(request.getParameter("password"))) {
			session.setAttribute("user", user);
			resultPage = "/index.html";
			Audit audit = new Audit(user, new Date(), resourse.toString());
			facade.setAudit(audit);
		} else {
			resultPage = "/login.html";
		}
		
		request.getRequestDispatcher(resultPage).forward(request, response);
	}

}

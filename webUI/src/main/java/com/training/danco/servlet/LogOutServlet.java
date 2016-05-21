package com.training.danco.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.training.danco.model.User;

public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LogOutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		User user = (User)session.getAttribute("user");
        if (user != null) {
            session.removeAttribute("user");
        }
        request.getRequestDispatcher("/leave_page.html").forward(request, response);
	}

}

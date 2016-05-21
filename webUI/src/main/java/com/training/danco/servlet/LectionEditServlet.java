package com.training.danco.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.danco.dim.DependencyInjectionManager;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Lection;

public class LectionEditServlet extends HttpServlet {

	private static final long serialVersionUID = -770261915654782212L;

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);

	public LectionEditServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer lectionId = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("lection", facade.getLection(lectionId));
		request.getRequestDispatcher("/edit/edit_lection.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer id = Integer.parseInt(request.getParameterValues("lectionId")[0]);
			String name = request.getParameter("name");
			Date date = convertToDate(request.getParameter("date"));
			Lection lection = new Lection(name, date);
			lection.setId(id);
			facade.updateLection(lection);
		} catch (Exception e) {
		}
		response.sendRedirect("/Lection");
	}

	private Date convertToDate(String string) {
		String[] dateAndTime = string.split(" ");
		String[] startDate = dateAndTime[0].split("-");
		String[] startTime = dateAndTime[1].split(":");
		return new GregorianCalendar(Integer.parseInt(startDate[0]), Integer.parseInt(startDate[1]),
				Integer.parseInt(startDate[2]), Integer.parseInt(startTime[0]), Integer.parseInt(startTime[1]))
						.getTime();
	}
}

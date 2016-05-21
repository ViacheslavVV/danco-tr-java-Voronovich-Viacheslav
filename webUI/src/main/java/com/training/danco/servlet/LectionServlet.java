package com.training.danco.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.training.danco.dim.DependencyInjectionManager;
import com.training.danco.facade.api.IFacade;
import com.training.danco.params.SortingParam;

public class LectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);

	public LectionServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			SortingParam sortingParam = null;
			Date date = null;

			boolean cont = true;
			Object resultList = null;
			try {
				date = convertToDate(request.getParameter("date"));
				cont = false;
				resultList = facade.getLectionsByDate(date);
			} catch (Exception c) {
			}

			if (cont) {
				try {
					sortingParam = SortingParam.valueOf(request.getParameter("sortingParam"));
				} catch (Exception e) {
					sortingParam = SortingParam.ID;
				}
				resultList = facade.getSortedLections(sortingParam);

			}
			request.setAttribute("lections", resultList);
			request.getRequestDispatcher("/lection/lection.jsp").forward(request, response);

		} catch (Exception e) {
			request.setAttribute("error", "Something wrong!");
			request.getRequestDispatcher("/lection/lection.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			String name = request.getParameter("name");
			Date date = convertToDate(request.getParameter("date"));
			List<Object> list =  new ArrayList<>();
			list.add(name);
			list.add(date);
			facade.setLection(list);
			} catch (Exception e){
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

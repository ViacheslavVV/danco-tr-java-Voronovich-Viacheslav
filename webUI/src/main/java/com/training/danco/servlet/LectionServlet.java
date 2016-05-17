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
import com.training.danco.model.Lection;
import com.training.danco.params.SortingParam;

public class LectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);

    public LectionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");

		try {
			if (type.equals("GetLection")) {
				Object result = facade.getLection(Integer.parseInt(request.getParameter("lectionId")));
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}
				request.getRequestDispatcher("/lection/getLection.jsp").forward(request, response);
			} else if (type.equals("GetAllLections")) {
				SortingParam sortingParam = SortingParam.valueOf(request.getParameter("sortingParam"));
				List<Lection> result = facade.getSortedLections(sortingParam);
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}

				request.getRequestDispatcher("/lection/getAllLections.jsp").forward(request, response);
			} else if (type.equals("GetLectionsByDate")) {
				Date date = convertToDate(request.getParameter("date")); 
				List<Lection> result = facade.getLectionsByDate(date);
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}

				request.getRequestDispatcher("/lection/getLectionsByDate.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/lection/lectionMenu.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", true);
			request.getRequestDispatcher("/lecturer/lecturerMenu.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");

		try {
			if (type.equals("CreateLection")) {
				List<Object> list = new ArrayList<Object>();
				list.add(request.getParameter("name"));
				list.add(convertToDate(request.getParameter("date")));
				Object result = facade.setLection(list);
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}
				request.getRequestDispatcher("/lection/createLection.jsp").forward(request, response);
			} else if (type.equals("DeleteLection")) {
				Integer id = Integer.parseInt(request.getParameter("lectionId"));
				Boolean result = facade.deleteLection(id);
				if (result == null) {
					request.setAttribute("result", -1);
				} else {
					request.setAttribute("result", result);
				}

				request.getRequestDispatcher("/lection/deleteLection.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/lection/lectionMenu.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", true);
			request.getRequestDispatcher("/lection/lectionMenu.jsp").forward(request, response);
		}
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

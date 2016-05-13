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

public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);

	public CourseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getRequestURL().toString().replaceFirst("(.*[\\/]course)", "");
		
		if (type.equals("")) {
			request.getRequestDispatcher("/pages/course.jsp").forward(request, response);
		} else if (type.equals("/create")) {
			request.getRequestDispatcher("/pages/courseAdd.jsp").forward(request, response);
		} else if (type.equals("/getById")) {

		} else if (type.equals("/delete")) {

		} else if (type.equals("/clone")) {

		} else if (type.equals("/all")) {

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = getQueryType(request.getRequestURL().toString());
		Boolean isOk = true;
		if (type.equals("/create")) {
			Integer resultId = null;
			try {
				List<Object> params = new ArrayList<>();
				params.add(request.getParameter("name"));
				params.add(convertToDate(request.getParameter("startDate")));
				params.add(convertToDate(request.getParameter("finalDate")));
				params.add(Integer.parseInt(request.getParameter("maxStudents")));
				params.add(Integer.parseInt(request.getParameter("maxLections")));
				resultId = facade.setCourse(params);
			} catch (Exception e) {
				isOk = false;
			}
			if (resultId == null) {
				isOk = false;
			}
			request.setAttribute("isOk", isOk);
			request.getRequestDispatcher("/pages/courseAdd.jsp").forward(request, response);
		} else if (type.equals("/getById")) {

		} else if (type.equals("/delete")) {

		} else if (type.equals("/clone")) {

		} else if (type.equals("/all")) {

		}
	}

	private String getQueryType(String url){
		String tempStr = url.replaceFirst(".*[\\/]course", "");
		if (tempStr.charAt(tempStr.length()-1) == '/'){
			tempStr = tempStr.substring(0,tempStr.length()-1);
		}
		return tempStr;
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

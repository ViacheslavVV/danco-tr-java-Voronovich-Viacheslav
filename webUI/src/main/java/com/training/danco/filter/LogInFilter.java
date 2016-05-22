package com.training.danco.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogInFilter implements Filter {

	public LogInFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		if (!"/LogIn".equals(httpRequest.getRequestURI())
				&& (session == null || session.getAttribute("user") == null)) {
			httpResponse.sendRedirect("/LogIn");
		} else {
		chain.doFilter(httpRequest, httpResponse);}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

package com.training.danco.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.training.danco.dim.DependencyInjectionManager;
import com.training.danco.facade.api.IFacade;
import com.training.danco.model.Audit;
import com.training.danco.model.User;

public class AuditFilter implements Filter {

	private IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);
	
	public AuditFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String resourse = ((HttpServletRequest) request).getRequestURL().toString();
		HttpSession session = ((HttpServletRequest) request).getSession(true);

		if(session != null && session.getAttribute("user") != null){
			Object user = session.getAttribute("user");
			if(user !=null){
				Audit audit = new Audit((User)user, new Date(System.currentTimeMillis()), resourse);
				facade.setAudit(audit);
			} 
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

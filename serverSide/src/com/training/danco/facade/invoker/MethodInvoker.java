package com.training.danco.facade.invoker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.danco.training.dim.DependencyInjectionManager;
import com.training.danco.facade.api.IFacade;

public class MethodInvoker {

	private static final Logger LOGGER = LogManager.getLogger(MethodInvoker.class);

	public static final Object invokeMethod(String methodName, Object params) {
		IFacade facade = (IFacade) DependencyInjectionManager.getClassInstance(IFacade.class);

		Object result = null;
		try {
			if (params == null) {
				result = facade.getClass().getMethod(methodName, (Class<?>) null).invoke(facade, (Class<?>) null);
			} else {
				result = facade.getClass().getMethod(methodName, Object.class).invoke(facade, params);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return result;
	}
}

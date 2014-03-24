package com.GD.interceptor;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class LoginRequiredInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		boolean isInvoke = true;
		// 判断该方法是否加了@LoginRequired 注解
		if (invocation.getMethod().isAnnotationPresent(LoginRequired.class)) {
			Object[] args = invocation.getArguments();
			HttpServletRequest request = null;
			HttpServletResponse response = null;
			for (Object o : args) {
				if (o instanceof HttpServletRequest) {
					request = (HttpServletRequest) o;
					continue;
				}
				if (o instanceof HttpServletResponse) {
					response = (HttpServletResponse) o;
					continue;
				}
			}
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			if (StringUtils.isNotEmpty(username)) {
				long lastAccess = session.getLastAccessedTime();
				int max = session.getMaxInactiveInterval();
				System.out.println("last:" + lastAccess + " max:" + max);
			}
			Map<String, String[]> map = request.getParameterMap();
			for (Entry<String, String[]> entry : map.entrySet()) {
				System.out.println("key:" + entry.getKey() + " value:" + entry.getValue()[0]);
			}
			response.sendRedirect("loginPage.do");
			isInvoke = false;
		}
		// 执行被拦截的方法，切记，如果此方法不调用，则被拦截的方法不会被执行。
		if (isInvoke) {
			return invocation.proceed();
		}
		return null;
	}

}

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
		// �жϸ÷����Ƿ����@LoginRequired ע��
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
		// ִ�б����صķ������мǣ�����˷��������ã������صķ������ᱻִ�С�
		if (isInvoke) {
			return invocation.proceed();
		}
		return null;
	}

}

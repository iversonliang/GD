package com.GD.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.GD.util.RequestUtil;

@Component
public class LoginRequiredInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println("methodName: " + invocation.getMethod().getName());
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
			System.out.println("interceptor ---- sessionId:" + session.getId() + " username:" + session.getAttribute("username"));
			if (StringUtils.isNotEmpty(username)) {
				long lastAccess = session.getLastAccessedTime();
				int max = session.getMaxInactiveInterval();
				System.out.println("last:" + lastAccess + " max:" + max);
			} else {
				String url = RequestUtil.getRequestURL(request);
				session.setAttribute("jumpPage", url);
				response.sendRedirect("loginPage.do");
				isInvoke = false;
			}
		}
		// ִ�б����صķ������мǣ�����˷��������ã������صķ������ᱻִ�С�
		if (isInvoke) {
			return invocation.proceed();
		}
		return null;
	}

}

package com.GD.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.GD.util.DateUtil;
import com.GD.util.RequestUtil;

@Component
public class LoginRequiredInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println(DateUtil.date2String(new Date()) + "  methodName: " + invocation.getMethod().getName());
		boolean isInvoke = true;

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
		
		String username = null;
		HttpSession session = null;
		boolean isLogin = false;
		if (request != null) {
			session = request.getSession();
			username = (String) session.getAttribute("username");
			isLogin = StringUtils.isNotEmpty(username);
			request.setAttribute("isLogin", isLogin);
		}
		// 判断该方法是否加了@LoginRequired 注解
		if (invocation.getMethod().isAnnotationPresent(LoginRequired.class) && !isLogin) {
			String url = RequestUtil.getRequestURL(request);
			session.setAttribute("jumpPage", url);
			response.sendRedirect("/page/login.jsp");
			isInvoke = false;
		}
		// 执行被拦截的方法，切记，如果此方法不调用，则被拦截的方法不会被执行。
		if (isInvoke) {
			return invocation.proceed();
		}
		return null;
	}

}

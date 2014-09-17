package com.GD.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.GD.model.LastReadMessage;
import com.GD.service.AnnouncementService;
import com.GD.service.CommentService;
import com.GD.service.LastReadMessageService;
import com.GD.service.NoticeService;
import com.GD.util.DateUtil;
import com.GD.util.RequestUtil;

@Component
public class LoginRequiredInterceptor implements MethodInterceptor {

	private Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private LastReadMessageService lastReadMessageService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private AnnouncementService announcementService;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		String methodName = invocation.getMethod().getName();
		logger.info(DateUtil.date2String(new Date()) + "  methodName: " + methodName);
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
		Integer userId = null;
		boolean isLogin = false;
		if (request != null) {
			session = request.getSession();
			username = (String) session.getAttribute("username");
			isLogin = StringUtils.isNotEmpty(username);
			request.setAttribute("isLogin", isLogin);
			if (isLogin) {
				userId = (Integer) session.getAttribute("userId");
			}
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
			Object result = invocation.proceed();
			this.handleMessageTipsNum(result, userId, isLogin);
			return result;
		}
		return null;
	}
	
	private void handleMessageTipsNum(Object result, Integer userId, boolean isLogin) {
		boolean isModelAndView = result instanceof ModelAndView;
		if (!isModelAndView) {
			return;
		}
		ModelAndView model = (ModelAndView) result;
		int toMyComments = 0;
		int toMyReply = 0;
		int notice = 0;
		int announcement = 0;
		if (isLogin) {
			LastReadMessage lastReadMessage = lastReadMessageService.get(userId);
			toMyComments = commentService.countToMe(userId) - lastReadMessage.getToMyComments();
			toMyReply = commentService.countReplyToMe(userId) - lastReadMessage.getReplyToMe();
			notice = noticeService.count(userId) - lastReadMessage.getNotice();
			announcement = announcementService.count() - lastReadMessage.getAnnouncement();
			model.addObject("toMyComments", toMyComments);
			model.addObject("toMyReply", toMyReply);
			model.addObject("notice", notice);
			model.addObject("announcement", announcement);
		}
	}

}

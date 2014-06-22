package com.GD.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.GD.handler.CommentHandler;
import com.GD.interceptor.LoginRequired;
import com.GD.model.Comment;
import com.GD.service.CommentService;
import com.GD.service.UserService;
import com.GD.type.ErrorTipsType;
import com.GD.util.ViewUtil;
import com.GD.web.form.CommentForm;
import com.GD.web.vo.CommentVO;

@Controller
@RequestMapping(value = CommentController.DIR)
public class CommentController {

	public static final String DIR = "/comment";
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CommentHandler commentHandler;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	
	@LoginRequired
	@RequestMapping( value = "/add.do", method = RequestMethod.POST)
	public ModelAndView add(HttpServletRequest request, HttpSession session, CommentForm form) {
		boolean result = false;
		String message = "";
		int errorCode = -1;
		int replyUserId = 0;
		String replyContent = StringUtils.defaultIfEmpty(form.getReplyContent(), "");
		if (form.getReplyUserId() != null && form.getReplyUserId() > 0) {
			replyUserId = form.getReplyUserId();
		} 
		String replyNickname = StringUtils.defaultIfEmpty(form.getReplyNickname(), "");
		int userId = (Integer) session.getAttribute("userId");
		try {
			result = commentService.add(userId, form.getVideoId(), form.getContent(), replyUserId, replyNickname, replyContent);
		} catch (Exception e) {
			String temp = e.getMessage();
			try {
				ErrorTipsType type = ErrorTipsType.toType(temp);
				message = type.getDesc();
				errorCode = type.getKey();
			} catch (Exception e1) {
				logger.error(e1.getMessage(), e1);
			}
		}
		List<Comment> commentList = commentService.list(form.getVideoId(), 0, 10);
		List<CommentVO> commentVoList = commentHandler.toVoList(commentList);
		ModelAndView model = ViewUtil.getSpecifiedView(DIR, "loadComment");
		model.addObject("result", result);
		model.addObject("message", message);
		model.addObject("errorCode", errorCode);
		System.out.println(commentVoList.size());
		model.addObject("commentList", commentVoList);
		return model;
	}
}

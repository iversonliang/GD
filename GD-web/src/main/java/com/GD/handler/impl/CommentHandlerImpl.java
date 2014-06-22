package com.GD.handler.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.GD.handler.CommentHandler;
import com.GD.model.Comment;
import com.GD.service.UserService;
import com.GD.util.DateUtil;
import com.GD.web.vo.CommentVO;

@Component
public class CommentHandlerImpl implements CommentHandler{
	
	@Autowired
	private UserService userService;
	

	@Override
	public List<CommentVO> toVoList(List<Comment> list) {
		List<CommentVO> voList = new ArrayList<CommentVO>();
		for (Comment comment : list) {
			CommentVO vo = new CommentVO();
			try {
				BeanUtils.copyProperties(vo, comment);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			vo.setDeployTimeTips(DateUtil.getDeployTimeTips(comment.getPosttime()));
			String headImg = userService.getHeadImg(comment.getUserId());
			vo.setHeadImg(headImg);
			voList.add(vo);
		}
		return voList;
	}

}

package com.GD.handler;

import java.util.List;

import com.GD.model.Comment;
import com.GD.web.vo.CommentVO;

public interface CommentHandler {

	public List<CommentVO> toVoList(List<Comment> list);
}

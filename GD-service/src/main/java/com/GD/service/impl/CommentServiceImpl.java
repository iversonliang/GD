package com.GD.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GD.dao.CommentDao;
import com.GD.model.Comment;
import com.GD.model.User;
import com.GD.service.CommentService;
import com.GD.service.UserService;
import com.GD.service.VideoService;
import com.GD.type.StatusType;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private VideoService videoService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentDao commentDao;

	@Override
	public boolean add(int userId, int videoId, String content, int replyUserId, String replyNickname, String replyContent) {
		videoService.checkVideo(videoId);
		User user = userService.get(userId);
		String nickname = user.getNickname();
		nickname = StringUtils.defaultIfEmpty(nickname, user.getUsername());
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setDel(false);
		comment.setNickname(nickname);
		comment.setPosttime(new Date());
		comment.setReplyNickname(replyNickname);
		comment.setReplyUserId(replyUserId);
		comment.setStatus(StatusType.NORMAL.getKey());
		comment.setUserId(userId);
		comment.setVideoId(videoId);
		comment.setReplyContent(replyContent);
		return commentDao.add(comment) > 0;
	}

	@Override
	public boolean del(int commentId) {
		return commentDao.del(commentId);
	}

	@Override
	public List<Comment> list(int videoId, int start, int size) {
		return commentDao.list(videoId, start, size);
	}

	@Override
	public int count(int videoId) {
		return commentDao.count(videoId);
	}

}

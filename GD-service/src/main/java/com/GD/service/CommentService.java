package com.GD.service;

import java.util.List;

import com.GD.model.Comment;

public interface CommentService {

	public boolean add(int userId, int videoId, String content, int replyUserId, String replyNickname, String replyContent);

	public boolean del(int commentId);

	public List<Comment> list(int videoId, int start, int size);

	public int count(int videoId);
}

package com.GD.dao;

import java.util.List;

import com.GD.model.Comment;

public interface CommentDao {
	
	public int add(Comment comment);
	
	public boolean del(int commentId);
	
	public int count(int videoId);
	
	public List<Comment> list(int videoId, int start, int size);

}

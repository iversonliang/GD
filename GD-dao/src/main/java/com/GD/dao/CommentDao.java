package com.GD.dao;

import java.util.List;

import com.GD.model.Comment;

public interface CommentDao {

	public int add(Comment comment);

	public boolean del(int commentId);
	
	public boolean delByVideo(int videoId);

	public int count(int videoId);

	public List<Comment> list(int videoId, int start, int size);

	/**
	 * 获取我的评论
	 * 
	 * @param userId
	 * @param start TODO
	 * @param size TODO
	 * @return
	 */
	public List<Comment> listMyComments(int userId, int start, int size);
	
	public int countMyComments(int userId);

	/**
	 * 获取给我的回复
	 * 
	 * @param userId
	 * @param start TODO
	 * @param size TODO
	 * @return
	 */
	public List<Comment> listReplyToMe(int userId, int start, int size);

	public int countReplyToMe(int userId);
	
	public List<Comment> listToMe(int userId, int start, int size);
	
	public int countToMe(int userId);
}

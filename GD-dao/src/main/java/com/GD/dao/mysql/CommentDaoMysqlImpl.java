package com.GD.dao.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.GD.dao.CommentDao;
import com.GD.model.Comment;
import com.GD.mysql.Jdbc;
import com.GD.mysql.StatementParameter;

@Repository
public class CommentDaoMysqlImpl implements CommentDao{
	
	@Autowired
	private Jdbc jdbc;

	@Override
	public int add(Comment comment) {
		String sql = "INSERT INTO comment(comment_id,video_id,content,user_id,nickname,reply_user_id,reply_nickname,del,status,posttime,reply_content) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		StatementParameter params = new StatementParameter();
		params.setInt(comment.getCommentId());
		params.setInt(comment.getVideoId());
		params.setString(comment.getContent());
		params.setInt(comment.getUserId());
		params.setString(comment.getNickname());
		params.setInt(comment.getReplyUserId());
		params.setString(comment.getReplyNickname());
		params.setBool(comment.isDel());
		params.setInt(comment.getStatus());
		params.setDate(comment.getPosttime());
		params.setString(comment.getReplyContent());
		return (int) jdbc.insertForLastId(sql, params);
	}

	@Override
	public boolean del(int commentId) {
		String sql = "UPDATE comment SET del=1 WHERE comment_id=?";
		return jdbc.updateForBoolean(sql, commentId);
	}

	@Override
	public List<Comment> list(int videoId, int start, int size) {
		String sql = "SELECT * FROM comment WHERE video_id=? AND del=0 LIMIT ?,?";
		return jdbc.queryForList(sql, Comment.class, videoId, start, size);
	}

	@Override
	public int count(int videoId) {
		String sql = "SELECT COUNT(*) FROM comment WHERE video_id=? AND del=0";
		return jdbc.queryForInt(sql, videoId);
	}

	
}

package com.GD.dao.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.GD.dao.LikeVideoDao;
import com.GD.model.LikeVideo;
import com.GD.util.ListUtil;

@Primary
@Repository
public class LikeVideoDaoCacheImpl implements LikeVideoDao {
	
	@Autowired
	private LikeVideoDao likeVideoDaoMysqlImpl;
	@Autowired
	private LikeVideoDao likeVideoDaoRedisImpl;

	@Override
	public boolean add(LikeVideo likeVideo) {
		boolean result = likeVideoDaoMysqlImpl.add(likeVideo);
		if (result) {
			likeVideoDaoRedisImpl.add(likeVideo);
		}
		return result;
	}

	@Override
	public List<LikeVideo> listByUser(int userId, int start, int size) {
		List<LikeVideo> list = likeVideoDaoRedisImpl.listByUser(userId, start, size);
		if (ListUtil.isEmpty(list)) {
			list = likeVideoDaoMysqlImpl.listByUser(userId, start, size);
		}
		return list;
	}

	@Override
	public int count(int userId) {
		int count = likeVideoDaoRedisImpl.count(userId);
		if (count == 0) {
			count = likeVideoDaoMysqlImpl.count(userId);
		}
		return count;
	}

	@Override
	public boolean isLike(int userId, int videoId) {
		return likeVideoDaoRedisImpl.isLike(userId, videoId);
	}

	@Override
	public boolean delete(int userId, int videoId) {
		boolean result = likeVideoDaoMysqlImpl.delete(userId, videoId);
		if (result) {
			likeVideoDaoRedisImpl.delete(userId, videoId);
		}
		return result;
	}

}

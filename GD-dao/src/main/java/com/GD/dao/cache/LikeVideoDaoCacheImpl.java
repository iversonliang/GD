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
	public List<LikeVideo> listByUser(int userId) {
		List<LikeVideo> list = likeVideoDaoRedisImpl.listByUser(userId);
		if (ListUtil.isEmpty(list)) {
			list = likeVideoDaoMysqlImpl.listByUser(userId);
		}
		return list;
	}

}

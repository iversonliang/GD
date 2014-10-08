package com.GD.dao.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.GD.dao.VideoDao;
import com.GD.model.Video;

@Primary
@Repository
public class VideoDaoCacheImpl implements VideoDao {

	@Autowired
	private VideoDao videoDaoMysqlImpl;
	@Autowired
	private VideoDao videoDaoMemoryImpl;
	
	@Override
	public boolean add(Video video) {
		boolean result = videoDaoMysqlImpl.add(video);
		if (result) {
			videoDaoMemoryImpl.add(video);
		}
		return result;
	}

	@Override
	public Video get(int videoId) {
		Video video = videoDaoMemoryImpl.get(videoId);
		if (video == null) {
			video = videoDaoMysqlImpl.get(videoId);
			if (video != null) {
				videoDaoMemoryImpl.add(video);
			}
		}
		return video;
	}

	@Override
	public boolean del(int videoId) {
		boolean result = videoDaoMysqlImpl.del(videoId);
		if (result) {
			videoDaoMemoryImpl.del(videoId);
		}
		return result;
	}

	@Override
	public boolean unDel(int videoId) {
		boolean result = videoDaoMysqlImpl.unDel(videoId);
		if (result) {
			Video video = videoDaoMysqlImpl.get(videoId);
			videoDaoMemoryImpl.add(video);
		}
		return result;
	}

	@Override
	public int countByUser(int userId, int videoSourceType) {
		return videoDaoMysqlImpl.countByUser(userId, videoSourceType);
	}

	@Override
	public int count(int status, int videoType, int homeType, int videoGradeType, int videoSourceType, int timeLimitType, String keyword, boolean showDel) {
		return videoDaoMysqlImpl.count(status, videoType, homeType, videoGradeType, videoSourceType, timeLimitType, keyword, showDel);
	}

	@Override
	public List<Video> list(int status, int videoType, int homeType, int videoGradeType, int videoSourceType, int sortType, int timeLimitType, String keyword, boolean showDel, int start, int size) {
		return videoDaoMysqlImpl.list(status, videoType, homeType, videoGradeType, videoSourceType, sortType, timeLimitType, keyword, showDel, start, size);
	}

	@Override
	public List<Video> list(int userId, int videoSourceType, int start, int size) {
		return videoDaoMysqlImpl.list(userId, videoSourceType, start, size);
	}

	@Override
	public boolean play(int videoId) {
		boolean result = videoDaoMysqlImpl.play(videoId);
		if (result) {
			videoDaoMemoryImpl.del(videoId);
		}
		return result;
	}

	@Override
	public boolean love(int videoId) {
		boolean result = videoDaoMysqlImpl.love(videoId);
		if (result) {
			videoDaoMemoryImpl.del(videoId);
		}
		return result;
	}

	@Override
	public boolean commont(int videoId) {
		boolean result = videoDaoMysqlImpl.commont(videoId);
		if (result) {
			videoDaoMemoryImpl.del(videoId);
		}
		return result;
	}

	@Override
	public int getMaxIndexNum(int homeType) {
		return videoDaoMysqlImpl.getMaxIndexNum(homeType);
	}

	@Override
	public Video getByIndexNum(int homeType, int indexNum) {
		return videoDaoMysqlImpl.getByIndexNum(homeType, indexNum);
	}

	@Override
	public boolean updateIndexBetween(int homeType, int start, int end, boolean isIncr) {
		boolean result = videoDaoMysqlImpl.updateIndexBetween(homeType, start, end, isIncr);
		if (result) {
			this.removeAll();
		}
		return result;
	}

	@Override
	public boolean updateHomeTypeIndex(int videoId, int homeType, int indexNum) {
		boolean result = videoDaoMysqlImpl.updateHomeTypeIndex(videoId, homeType, indexNum);
		if (result) {
			this.removeAll();
		}
		return result;
	}

	@Override
	public boolean updateVideoGradeType(int videoId, int videoGradeType) {
		boolean result = videoDaoMysqlImpl.updateVideoGradeType(videoId, videoGradeType);
		if (result) {
			this.removeAll();
		}
		return result;
	}

	@Override
	public boolean removeAll() {
		return videoDaoMemoryImpl.removeAll();
	}

	@Override
	public boolean update(Video video) {
		boolean result = videoDaoMysqlImpl.update(video);
		if (result) {
			videoDaoMemoryImpl.update(video);
		}
		return result;
	}

	@Override
	public List<Video> listAllHomeType() {
		return videoDaoMysqlImpl.listAllHomeType();
	}

	@Override
	public boolean updateHomeTypeIndex(int videoId, int index) {
		return videoDaoMysqlImpl.updateHomeTypeIndex(videoId, index);
	}

}

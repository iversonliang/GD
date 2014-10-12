package com.GD.dao.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.stereotype.Repository;

import com.GD.dao.VideoDao;
import com.GD.model.Video;

@Repository
public class VideoDaoMemoryImpl implements VideoDao {
	
	public static LRUMap VIDEO_MAP = new LRUMap(1000);

	@Override
	public boolean add(Video video) {
		VIDEO_MAP.put(video.getVideoId(), video);
		return true;
	}

	@Override
	public Video get(int videoId) {
		return (Video) VIDEO_MAP.get(videoId);
	}

	@Override
	public boolean del(int videoId) {
		VIDEO_MAP.remove(videoId);
		return true;
	}

	@Override
	public boolean unDel(int videoId) {
		throw new NotImplementedException();
	}

	@Override
	public int countByUser(int userId, int videoSourceType) {
		throw new NotImplementedException();
	}

	@Override
	public int count(int status, int videoType, int homeType, int videoGradeType, int videoSourceType, int timeLimitType, String keyword, boolean showDel) {
		throw new NotImplementedException();
	}

	@Override
	public List<Video> list(int status, int videoType, int homeType, int videoGradeType, int videoSourceType, int sortType, int timeLimitType, String keyword, boolean showDel, int start, int size) {
		throw new NotImplementedException();
	}

	@Override
	public List<Video> list(int userId, int videoSourceType, int start, int size) {
		throw new NotImplementedException();
	}

	@Override
	public boolean play(int videoId) {
		throw new NotImplementedException();
	}

	@Override
	public boolean love(int videoId) {
		throw new NotImplementedException();
	}

	@Override
	public boolean commont(int videoId) {
		throw new NotImplementedException();
	}

	@Override
	public int getMaxIndexNum(int homeType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Video getByIndexNum(int homeType, int indexNum) {
		throw new NotImplementedException();
	}

	@Override
	public boolean updateIndexBetween(int homeType, int start, int end, boolean isIncr) {
		throw new NotImplementedException();
	}

	@Override
	public boolean updateHomeTypeIndex(int videoId, int homeType, int indexNum) {
		throw new NotImplementedException();
	}

	@Override
	public boolean updateVideoGradeType(int videoId, int videoGradeType) {
		throw new NotImplementedException();
	}

	@Override
	public boolean removeAll() {
		VIDEO_MAP.clear();
		return true;
	}

	@Override
	public boolean update(Video video) {
		VIDEO_MAP.put(video.getVideoId(), video);
		return true;
	}

	@Override
	public List<Video> listAllHomeType() {
		throw new NotImplementedException();
	}

	@Override
	public boolean updateHomeTypeIndex(int videoId, int index) {
		throw new NotImplementedException();
	}

	@Override
	public boolean updateNicknameByUser(int userId, String nickname) {
		@SuppressWarnings("unchecked")
		Set<Entry<Integer, Video>> set = VIDEO_MAP.entrySet();
		List<Integer> list = new ArrayList<Integer>();
		for (Entry<Integer, Video> entry : set) {
			if (userId == entry.getValue().getUserId()) {
				list.add(entry.getKey());
			}
		}
		for (Integer key : list) {
			VIDEO_MAP.remove(key);
		}
		return true;
	}
	
}

package com.GD.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GD.dao.AdDao;
import com.GD.model.Ad;
import com.GD.service.AdService;
import com.GD.type.AdAreaType;
import com.GD.util.AssertUtil;

@Service
public class AdServiceImpl implements AdService {
	
	@Autowired
	private AdDao adDao;

	@Override
	public boolean add(int userId, Ad ad) {
		int indexNum = ad.getIndexNum();
		int maxIndexNum = adDao.getMaxIndexNum(ad.getAdAreaType());
		if (indexNum > maxIndexNum + 1 || indexNum < 1) {
			indexNum = maxIndexNum + 1;
			maxIndexNum++;
			ad.setIndexNum(indexNum);
		}
		if (indexNum >=1 && indexNum <= maxIndexNum) {
			adDao.updateIndexBetween(ad.getAdAreaType(), indexNum, Integer.MAX_VALUE, true);
		}
		return adDao.add(ad);
	}

	@Override
	public boolean delete(int adId) {
		Ad ad = adDao.get(adId);
		AssertUtil.assertNotNull(ad, "没有找到广告[" + adId + "]");
		boolean result = false;
		result = adDao.delete(adId);
		if (result) {
			adDao.updateIndexBetween(ad.getAdAreaType(), ad.getIndexNum(), Integer.MAX_VALUE, false);
		}
		return result;
	}

	@Override
	public boolean updateIndex(int adId, AdAreaType adAreaType, int indexNum) {
		Ad ad = adDao.get(adId);
		AssertUtil.assertNotNull(ad, "没有找到广告[" + adId + "]");
		int oldIndexNum = ad.getIndexNum();
		if (oldIndexNum != indexNum) {
			int maxIndexNum = adDao.getMaxIndexNum(adAreaType.getKey());
			if (indexNum > maxIndexNum || indexNum < 1) {
				throw new RuntimeException("更新的顺序[" + indexNum + "]不在范围内[1, " + maxIndexNum + "]");
			}
			adDao.updateAdAreaTypeIndex(adId, adAreaType.getKey(), Integer.MAX_VALUE);
			Ad exchangeObj = adDao.getByIndexNum(adAreaType.getKey(), indexNum);
			adDao.updateAdAreaTypeIndex(exchangeObj.getAdId(), adAreaType.getKey(), oldIndexNum);
		}
		return adDao.updateAdAreaTypeIndex(adId, adAreaType.getKey(), indexNum);
	}

	@Override
	public int count(AdAreaType type) {
		return adDao.count(type.getKey());
	}

	@Override
	public List<Ad> list(AdAreaType type, int start, int size) {
		return adDao.list(type.getKey(), start, size);
	}

}

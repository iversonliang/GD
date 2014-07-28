package com.GD.model;

import java.util.Date;

public class Ad {

	public int adId;
	public int adAreaType;
	public int indexNum;
	public String url;
	public String imgUrl;
	public Date posttime;
	public boolean del;

	public boolean isDel() {
		return del;
	}

	public void setDel(boolean del) {
		this.del = del;
	}

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public int getAdAreaType() {
		return adAreaType;
	}

	public void setAdAreaType(int adAreaType) {
		this.adAreaType = adAreaType;
	}

	public int getIndexNum() {
		return indexNum;
	}

	public void setIndexNum(int indexNum) {
		this.indexNum = indexNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getPosttime() {
		return posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

}

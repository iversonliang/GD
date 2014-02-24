package com.GD.model;

public class UserClock {
	private int userClockId;
	private long yyuid;
	private int gameTaskId;
	private String task;
	private String gameId;
	private String serverId;
	private String gameName;
	private String serverName;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private String remark;
	private int frequency;
	private boolean del;

	public long getYyuid() {
		return yyuid;
	}

	public void setYyuid(long yyuid) {
		this.yyuid = yyuid;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public boolean isDel() {
		return del;
	}

	public void setDel(boolean del) {
		this.del = del;
	}

	public int getUserClockId() {
		return userClockId;
	}

	public void setUserClockId(int userClockId) {
		this.userClockId = userClockId;
	}

	public int getGameTaskId() {
		return gameTaskId;
	}

	public void setGameTaskId(int gameTaskId) {
		this.gameTaskId = gameTaskId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

}

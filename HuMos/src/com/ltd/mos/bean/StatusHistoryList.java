package com.ltd.mos.bean;

public class StatusHistoryList {

	private String statusId; // 状态标识
	private String statusDescription; // 状态描述
	private String statusDatetime;// 状态变更时间

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getStatusDatetime() {
		return statusDatetime;
	}

	public void setStatusDatetime(String statusDatetime) {
		this.statusDatetime = statusDatetime;
	}

}

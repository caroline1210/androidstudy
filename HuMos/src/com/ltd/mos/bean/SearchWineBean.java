package com.ltd.mos.bean;

public class SearchWineBean {
	String desc;
	int num;
	String key;
	String type;
	int iconState; // 1��ͼ��0��ͼ��
	int searchState;// 1����0ͬ��

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getIconState() {
		return iconState;
	}

	public void setIconState(int iconState) {
		this.iconState = iconState;
	}

	public int getSearchState() {
		return searchState;
	}

	public void setSearchState(int searchState) {
		this.searchState = searchState;
	}

}

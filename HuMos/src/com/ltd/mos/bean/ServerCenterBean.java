package com.ltd.mos.bean;

import java.util.ArrayList;

public class ServerCenterBean {
	String type;
	String wineName;
	String keyWords;
	String desc;
	ArrayList<String> images;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWineName() {
		return wineName;
	}
	public void setWineName(String wineName) {
		this.wineName = wineName;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public ArrayList<String> getImages() {
		return images;
	}
	public void setImages(ArrayList<String> images) {
		this.images = images;
	}
	
}

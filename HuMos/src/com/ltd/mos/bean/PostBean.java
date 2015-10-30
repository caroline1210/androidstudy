package com.ltd.mos.bean;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PostBean {
	int code;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	String phoneNumber;// 手机号

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	TailorBean tailorBean;

	public TailorBean getTailorBean() {
		return tailorBean;
	}

	public void setTailorBean(TailorBean tailorBean) {
		this.tailorBean = tailorBean;
	}

	public String getXunJiuKeyWords() {
		return xunJiuKeyWords;
	}

	public void setXunJiuKeyWords(String xunJiuKeyWords) {
		this.xunJiuKeyWords = xunJiuKeyWords;
	}

	String password;
	String verify;
	String userName;
	String lastPassword;
	String imagePath;
	String url;
	String xunJiuTitle;
	String xunJiuContent;
	String xunJiuKeyWords;
	String xunJiuCreatDate;
	String xunJiuId;
	String contentId;
	String xunJiuNum;
	String version;
	ArrayList<String> xunJiuImages;

	public String getXunJiuNum() {
		return xunJiuNum;
	}

	public void setXunJiuNum(String xunJiuNum) {
		this.xunJiuNum = xunJiuNum;
	}

	public String getXunJiuCreatDate() {
		return xunJiuCreatDate;
	}

	public void setXunJiuCreatDate(String xunJiuCreatDate) {
		this.xunJiuCreatDate = xunJiuCreatDate;
	}

	public String getXunJiuId() {
		return xunJiuId;
	}

	public void setXunJiuId(String xunJiuId) {
		this.xunJiuId = xunJiuId;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public ArrayList<String> getXunJiuImages() {
		return xunJiuImages;
	}

	public void setXunJiuImages(ArrayList<String> xunJiuImages) {
		this.xunJiuImages = xunJiuImages;
	}

	public String getXunJiuTitle() {
		return xunJiuTitle;
	}

	public void setXunJiuTitle(String xunJiuTitle) {
		this.xunJiuTitle = xunJiuTitle;
	}

	public String getXunJiuContent() {
		return xunJiuContent;
	}

	public void setXunJiuContent(String xunJiuContent) {
		this.xunJiuContent = xunJiuContent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getLastPassword() {
		return lastPassword;
	}

	public void setLastPassword(String lastPassword) {
		this.lastPassword = lastPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	String key;
	String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	String productCategoryId; // 酒品分类标识
	String productId;// 酒品标示
	String internalName;// 酒品名称
	String barcode;// 条形码
	String isPromoProduct;// 是否促销（Y/N）
	String haveCoupon;// 是否返券（Y/N）
	String includeGift;// 是否赠送（Y/N）
	ArrayList<String> productFeatureIds; // 产品特征标示列表
	String viewSize;// 每页数
	String viewIndex;// 第几页（从0开始）
	String orderBy;// (productId/internalName/barcode/listPrice/defaultPrice);
	String quantity;// 数量
	ArrayList<String> seqIds;// 提交订单商品集合
	String shipDate;// 送货时间
	String note;// 备注
	String contactMechId;// 收货地址标识
	String attnName;// 收货人
	String stateProvinceGeoId;// 省份标识
	String cityGeoId;// 市标识
	String countyGeoId;// 区/县标识
	String address2;// 详细地址
	String phone;// 收货电话
	String geoId;// 国家/省/市标识（中国：CHN）
	String shoppingListItemSeqId;// 购物车商品标识
	String noteInfo;// 段子内容
	String noteName;// 段子标题
	String noteDateTime;// 段子时间
	String longitude;// 经度
	String latitude;// 纬度

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getNoteInfo() {
		return noteInfo;
	}

	public void setNoteInfo(String noteInfo) {
		this.noteInfo = noteInfo;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public String getNoteDateTime() {
		return noteDateTime;
	}

	public void setNoteDateTime(String noteDateTime) {
		this.noteDateTime = noteDateTime;
	}

	public ArrayList<String> getProductFeatureIds() {
		return productFeatureIds;
	}

	public void setProductFeatureIds(ArrayList<String> productFeatureIds) {
		this.productFeatureIds = productFeatureIds;
	}

	public String getShoppingListItemSeqId() {
		return shoppingListItemSeqId;
	}

	public void setShoppingListItemSeqId(String shoppingListItemSeqId) {
		this.shoppingListItemSeqId = shoppingListItemSeqId;
	}

	public String getGeoId() {
		return geoId;
	}

	public void setGeoId(String geoId) {
		this.geoId = geoId;
	}

	String statusId;// dingdanzhuangtai

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAttnName() {
		return attnName;
	}

	public void setAttnName(String attnName) {
		this.attnName = attnName;
	}

	public String getStateProvinceGeoId() {
		return stateProvinceGeoId;
	}

	public void setStateProvinceGeoId(String stateProvinceGeoId) {
		this.stateProvinceGeoId = stateProvinceGeoId;
	}

	public String getCityGeoId() {
		return cityGeoId;
	}

	public void setCityGeoId(String cityGeoId) {
		this.cityGeoId = cityGeoId;
	}

	public String getCountyGeoId() {
		return countyGeoId;
	}

	public void setCountyGeoId(String countyGeoId) {
		this.countyGeoId = countyGeoId;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getContactMechId() {
		return contactMechId;
	}

	public void setContactMechId(String contactMechId) {
		this.contactMechId = contactMechId;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ArrayList<String> getSeqIds() {
		return seqIds;
	}

	public void setSeqIds(ArrayList<String> seqIds) {
		this.seqIds = seqIds;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public String getInternalName() {
		return internalName;
	}

	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getIsPromoProduct() {
		return isPromoProduct;
	}

	public void setIsPromoProduct(String isPromoProduct) {
		this.isPromoProduct = isPromoProduct;
	}

	public String getHaveCoupon() {
		return haveCoupon;
	}

	public void setHaveCoupon(String haveCoupon) {
		this.haveCoupon = haveCoupon;
	}

	public String getIncludeGift() {
		return includeGift;
	}

	public void setIncludeGift(String includeGift) {
		this.includeGift = includeGift;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getViewSize() {
		return viewSize;
	}

	public void setViewSize(String viewSize) {
		this.viewSize = viewSize;
	}

	public String getViewIndex() {
		return viewIndex;
	}

	public void setViewIndex(String viewIndex) {
		this.viewIndex = viewIndex;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

}

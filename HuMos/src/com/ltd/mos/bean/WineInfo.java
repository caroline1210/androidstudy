package com.ltd.mos.bean;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class WineInfo implements Serializable {// Activity之间传递类对象 实现Serializable

	private String wineDese;// 商品名称
	private String wineevent;// 商品描述
	private String wineLoneDese;// 商品详细描述
	private String wineId;// 商品标识
	private String winePrice;// 酒斯基价格
	private String marketPrice;// 市场价格
	private String goods_id;//
	private String winNum;// 数量
	private String select; // 是否选定
	private String arrivatime;// 送达时间
	private String customTime;// 订制时间
	private String findwineState;// 寻酒状态
	private String sendMerchant;// 配送商家
	private String primaryProductCategoryId;// 酒品分类标识
	private String categoryName;// 酒品分类名称 eg:茅台、五粮液
	private String winePicUrl;// 图片地址
	private String smallImageUrl;// 小图
	private String mediumImageUrl;// 中图
	private String largeImageUrl;// 大图
	private String detailImageUrl;// 详细图片
	private String originalImageUrl;// 原始图片
	private String barcode;// 条形码
	private ArrayList<FeaturesBean> featuresList;// 酒品特征列表
	private String isPromoProduct;// 是否促销（Y/N）
	private String haveCoupon;// 是否返券（Y/N）
	private String includeGift;// 是否赠送（Y/N）
	private boolean straw;// 荐
	private boolean present;// 赠
	private boolean drop;// 降

	public String getWineLoneDese() {
		return wineLoneDese;
	}

	public void setWineLoneDese(String wineLoneDese) {
		this.wineLoneDese = wineLoneDese;
	}

	public ArrayList<FeaturesBean> getFeaturesList() {
		return featuresList;
	}

	public void setFeaturesList(ArrayList<FeaturesBean> featuresList) {
		this.featuresList = featuresList;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
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

	private String location;// 距离

	private ArrayList<String> products;

	public String getPrimaryProductCategoryId() {
		return primaryProductCategoryId;
	}

	public void setPrimaryProductCategoryId(String primaryProductCategoryId) {
		this.primaryProductCategoryId = primaryProductCategoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSmallImageUrl() {
		return smallImageUrl;
	}

	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}

	public String getMediumImageUrl() {
		return mediumImageUrl;
	}

	public void setMediumImageUrl(String mediumImageUrl) {
		this.mediumImageUrl = mediumImageUrl;
	}

	public String getLargeImageUrl() {
		return largeImageUrl;
	}

	public void setLargeImageUrl(String largeImageUrl) {
		this.largeImageUrl = largeImageUrl;
	}

	public String getDetailImageUrl() {
		return detailImageUrl;
	}

	public void setDetailImageUrl(String detailImageUrl) {
		this.detailImageUrl = detailImageUrl;
	}

	public String getOriginalImageUrl() {
		return originalImageUrl;
	}

	public void setOriginalImageUrl(String originalImageUrl) {
		this.originalImageUrl = originalImageUrl;
	}

	public ArrayList<String> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<String> products) {
		this.products = products;
	}

	public String getWineId() {
		return wineId;
	}

	public void setWineId(String wineId) {
		this.wineId = wineId;
	}

	public String getWineevent() {
		return wineevent;
	}

	public void setWineevent(String wineevent) {
		this.wineevent = wineevent;
	}

	public boolean isStraw() {
		return straw;
	}

	public void setStraw(boolean straw) {
		this.straw = straw;
	}

	public boolean isPresent() {
		return present;
	}

	public void setPresent(boolean present) {
		this.present = present;
	}

	public boolean isDrop() {
		return drop;
	}

	public void setDrop(boolean drop) {
		this.drop = drop;
	}

	public String getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(String marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getWinePicUrl() {
		return winePicUrl;
	}

	public void setWinePicUrl(String winePicUrl) {
		this.winePicUrl = winePicUrl;
	}

	public String getWineDese() {
		return wineDese;
	}

	public void setWineDese(String wineDese) {
		this.wineDese = wineDese;
	}

	public String getWinePrice() {
		return winePrice;
	}

	public void setWinePrice(String winePrice) {
		this.winePrice = winePrice;
	}

	public String getWinNum() {
		return winNum;
	}

	public void setWinNum(String winNum) {
		this.winNum = winNum;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getSendMerchant() {
		return sendMerchant;
	}

	public void setSendMerchant(String sendMerchant) {
		this.sendMerchant = sendMerchant;
	}

	public String getArrivatime() {
		return arrivatime;
	}

	public void setArrivatime(String arrivatime) {
		this.arrivatime = arrivatime;
	}

	public String getCustomTime() {
		return customTime;
	}

	public void setCustomTime(String customTime) {
		this.customTime = customTime;
	}

	public String getFindwineState() {
		return findwineState;
	}

	public void setFindwineState(String findwineState) {
		this.findwineState = findwineState;
	}

}

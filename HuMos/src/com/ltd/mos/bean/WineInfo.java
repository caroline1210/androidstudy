package com.ltd.mos.bean;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class WineInfo implements Serializable {// Activity֮�䴫������� ʵ��Serializable

	private String wineDese;// ��Ʒ����
	private String wineevent;// ��Ʒ����
	private String wineLoneDese;// ��Ʒ��ϸ����
	private String wineId;// ��Ʒ��ʶ
	private String winePrice;// ��˹���۸�
	private String marketPrice;// �г��۸�
	private String goods_id;//
	private String winNum;// ����
	private String select; // �Ƿ�ѡ��
	private String arrivatime;// �ʹ�ʱ��
	private String customTime;// ����ʱ��
	private String findwineState;// Ѱ��״̬
	private String sendMerchant;// �����̼�
	private String primaryProductCategoryId;// ��Ʒ�����ʶ
	private String categoryName;// ��Ʒ�������� eg:ę́������Һ
	private String winePicUrl;// ͼƬ��ַ
	private String smallImageUrl;// Сͼ
	private String mediumImageUrl;// ��ͼ
	private String largeImageUrl;// ��ͼ
	private String detailImageUrl;// ��ϸͼƬ
	private String originalImageUrl;// ԭʼͼƬ
	private String barcode;// ������
	private ArrayList<FeaturesBean> featuresList;// ��Ʒ�����б�
	private String isPromoProduct;// �Ƿ������Y/N��
	private String haveCoupon;// �Ƿ�ȯ��Y/N��
	private String includeGift;// �Ƿ����ͣ�Y/N��
	private boolean straw;// ��
	private boolean present;// ��
	private boolean drop;// ��

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

	private String location;// ����

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

package com.ltd.mos.bean;

/**
 * 购物车 对象
 * 
 * @ClassName: ShopCarBean
 * @Description: TODO
 * @author xuwu
 * @date 2014-9-3 下午05:12:38
 */
public class ShopCarBean {

	private String shoppingListItemSeqId;// 购物车明细标识
	private String internalName;// 酒品名称
	private String unitPrice;// 酒品单价
	private String quantity;// 酒品数量
	private String totalPrice;// 酒品总价
	private String productId;// 酒品标识
	private String smallImageUrl;// 酒品图片路径
	private String largeImageUrl;// 酒品图片路径
	private String mediumImageUrl;// 酒品图片路径
	private String detailImageUrl;//
	private String shippingListAddress;// 购物车收货地址
	private String shippingListTotalPrice;// 购物车总价
	private String select; // 是否选定

	private Address address;

	public String getLargeImageUrl() {
		return largeImageUrl;
	}

	public void setLargeImageUrl(String largeImageUrl) {
		this.largeImageUrl = largeImageUrl;
	}

	public String getMediumImageUrl() {
		return mediumImageUrl;
	}

	public void setMediumImageUrl(String mediumImageUrl) {
		this.mediumImageUrl = mediumImageUrl;
	}

	public String getDetailImageUrl() {
		return detailImageUrl;
	}

	public void setDetailImageUrl(String detailImageUrl) {
		this.detailImageUrl = detailImageUrl;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getShippingListAddress() {
		return shippingListAddress;
	}

	public void setShippingListAddress(String shippingListAddress) {
		this.shippingListAddress = shippingListAddress;
	}

	public String getShippingListTotalPrice() {
		return shippingListTotalPrice;
	}

	public void setShippingListTotalPrice(String shippingListTotalPrice) {
		this.shippingListTotalPrice = shippingListTotalPrice;
	}

	public String getShoppingListItemSeqId() {
		return shoppingListItemSeqId;
	}

	public void setShoppingListItemSeqId(String shoppingListItemSeqId) {
		this.shoppingListItemSeqId = shoppingListItemSeqId;
	}

	public String getInternalName() {
		return internalName;
	}

	public void setInternalName(String internalName) {
		this.internalName = internalName;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSmallImageUrl() {
		return smallImageUrl;
	}

	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}

}

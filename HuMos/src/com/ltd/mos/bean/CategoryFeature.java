package com.ltd.mos.bean;

import java.util.ArrayList;

/**
 * ��Ʒ�����ʶ
 * 
 * @ClassName: CategoryFeature
 * @Description: TODO
 * @author xuwu
 * @date 2014-9-11 ����03:44:17
 */
public class CategoryFeature {
	private String productFeatureCategoryId;// ���Է����ʶ
	private String description;// ���Է�������
	private ArrayList<FilterBean> featureList;// �����б�
	private int selectItem = -1;

	public int getSelectItem() {
		return selectItem;
	}

	public void setSelectItem(int selectItem) {
		this.selectItem = selectItem;
	}

	public ArrayList<FilterBean> getFeatureList() {
		return featureList;
	}

	public void setFeatureList(ArrayList<FilterBean> featureList) {
		this.featureList = featureList;
	}

	public String getProductFeatureCategoryId() {
		return productFeatureCategoryId;
	}

	public void setProductFeatureCategoryId(String productFeatureCategoryId) {
		this.productFeatureCategoryId = productFeatureCategoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

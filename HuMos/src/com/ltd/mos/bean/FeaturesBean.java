package com.ltd.mos.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: FeaturesBean
 * @Description: ��Ʒ�����б�
 * @author xuwu
 * @date 2014-8-26 ����02:23:34
 */
@SuppressWarnings("serial")
public class FeaturesBean implements Serializable {
	private String productFeatureCategoryId;// ���Է����ʶ
	private String productFeatureCategoryDescription;// ������������.eg:"Ʒ��"
	private String productFeatureId;// ��Ʒ������ʶ
	private String productFeatureDescription;// ��Ʒ��������. eg:"����Һ"

	public String getProductFeatureCategoryId() {
		return productFeatureCategoryId;
	}

	public void setProductFeatureCategoryId(String productFeatureCategoryId) {
		this.productFeatureCategoryId = productFeatureCategoryId;
	}

	public String getProductFeatureCategoryDescription() {
		return productFeatureCategoryDescription;
	}

	public void setProductFeatureCategoryDescription(
			String productFeatureCategoryDescription) {
		this.productFeatureCategoryDescription = productFeatureCategoryDescription;
	}

	public String getProductFeatureId() {
		return productFeatureId;
	}

	public void setProductFeatureId(String productFeatureId) {
		this.productFeatureId = productFeatureId;
	}

	public String getProductFeatureDescription() {
		return productFeatureDescription;
	}

	public void setProductFeatureDescription(String productFeatureDescription) {
		this.productFeatureDescription = productFeatureDescription;
	}

}

package com.ltd.mos.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: FeaturesBean
 * @Description: 酒品特征列表
 * @author xuwu
 * @date 2014-8-26 下午02:23:34
 */
@SuppressWarnings("serial")
public class FeaturesBean implements Serializable {
	private String productFeatureCategoryId;// 特性分类标识
	private String productFeatureCategoryDescription;// 特征分类描述.eg:"品牌"
	private String productFeatureId;// 酒品特征标识
	private String productFeatureDescription;// 酒品特征描述. eg:"五粮液"

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

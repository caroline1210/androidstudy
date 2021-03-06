package com.ltd.mos.task;

import android.content.Context;

import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.http.HttpUtil;
import com.ltd.mos.util.Const;

public class Task {
	PostBean postBean;
	HttpUtil httpUtil;
	ECallBack callBack;

	public Task(PostBean postBean, Context context, ECallBack callBack) {
		this.postBean = postBean;
		this.callBack = callBack;
		httpUtil = new HttpUtil(context);
	}

	public void postHttp() {
		switch (postBean.getCode()) {
		case Const.GETVERIFYCODE:
			httpUtil.getVerifyCode(postBean, callBack);
			break;
		case Const.REGISTER:
			httpUtil.register(postBean, callBack);
			break;
		case Const.LOGIN:
			httpUtil.login(postBean, callBack);
			break;
		case Const.RESETPASSWORD:
			httpUtil.resetPassword(postBean, callBack);
			break;
		case Const.GETCATEGORYFEATURES:
			httpUtil.getCategoryFeatures(postBean, callBack);
			break;
		case Const.FINDPRODUCT:
			httpUtil.findProduct(postBean, callBack);
			break;
		case Const.FINDSINGLEPRODUCT:
			httpUtil.findProductById(postBean, callBack);
			break;
		case Const.VERIFYCODE:
			httpUtil.verifyCode(postBean, callBack);
			break;
		case Const.CHANGEPWD:
			httpUtil.changePassword(postBean, callBack);
			break;
		case Const.CHANGEUSERNAME:
			httpUtil.changeUsername(postBean, callBack);
			break;
		case Const.ADDSHOPPINGLISTITEM:
			httpUtil.addShoppingListItem(postBean, callBack);
			break;
		case Const.LISTSHOPPINGLISTITEM:
			httpUtil.listShoppingListItem(postBean, callBack);
			break;
		case Const.CLEARSHOPPINGLISTITEM:
			httpUtil.clearShoppingListItem(postBean, callBack);
			break;
		case Const.SUBMITSHOPPINGLIST:
			httpUtil.submitShoppingList(postBean, callBack);
			break;
		case Const.CREATESHIPPINGADDRESS:
			httpUtil.createShippingAddress(postBean, callBack);
			break;
		case Const.LISTSHIPPINGADDRESS:
			httpUtil.listShippingAddress(postBean, callBack);
			break;
		case Const.SETSHOPPINGLISTADDRESS:
			httpUtil.setShoppingListAddress(postBean, callBack);
			break;
		case Const.GETGEOCHILDREN:
			httpUtil.getGeoChildren(postBean, callBack);
			break;
		case Const.PERSONORDER:
			httpUtil.getPersonOrder(postBean, callBack);
			break;
		case Const.UPDATESHIPPINGADDRESS:
			httpUtil.updateShippingAddress(postBean, callBack);
			break;
		case Const.DELETESHIPPINGADDRESS:
			httpUtil.deleteShippingAddress(postBean, callBack);
			break;
		case Const.DELETESHOPPINGLISTITEM:
			httpUtil.deleteShoppingListItem(postBean, callBack);
			break;
		case Const.CONFIRMERWEIMA:
			httpUtil.confirmErweima(postBean, callBack);
			break;
		case Const.IMMEDIATECONFIRME:
			httpUtil.immediateconfirm(postBean, callBack);
			break;
		case Const.SYSTEMMESSAGE://
			httpUtil.systemMessage(postBean, callBack);
			break;
		case Const.SENDMESSAGE://
			httpUtil.sendMessage(postBean, callBack);
			break;
		case Const.UPDATEVERSION://更新版本
			httpUtil.updateVersion(postBean, callBack);
			break;
		case Const.HEALTHDRINK://健康饮酒
			httpUtil.healthDrink(postBean, callBack);
			break;
		case Const.WINEJOKES://酒段子
			httpUtil.wineJokes(postBean, callBack);
			break;
		case Const.CUSTOMIZED://私人订制信息列表
			httpUtil.customized(postBean, callBack);
			break;
		case Const.SEARCHPRODUCT:
			httpUtil.searchProduct(postBean, callBack);
			break;
		case Const.CREATEPRODUCTREQUEST://新增寻酒
			httpUtil.createProductRequest(postBean, callBack);
			break;
		case Const.LISTPRODUCTREQUEST://寻酒列表
			httpUtil.listProductRequest(postBean, callBack);
			break;
		case Const.CREATECUSTOMIZEDREQUEST:
			httpUtil.createCustomizedRequest(postBean, callBack);
			break;
		default:
			break;
		}
	}
}

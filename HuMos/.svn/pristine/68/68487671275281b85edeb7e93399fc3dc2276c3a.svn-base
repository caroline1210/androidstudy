package com.ltd.mos.http;

import java.util.ArrayList;

import android.content.Context;

import com.lidroid.xutils.http.RequestParams;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.TailorBean;
import com.ltd.mos.util.Logic;

public class HttpUtil {

	private static String httpHead = "http://101.251.236.162:8080/user/control/";
	public static String httpHead_image = "http://101.251.236.162:8080";
	/**
	 * 登陆注册相关接口
	 */
	private static String getVerifyCode = httpHead + "getVerifyCode";// 获取验证码
	private static String register = httpHead + "registerUser";// 注册
	private static String resetPassword = httpHead + "resetPassword";// 重置密码
	private static String login = httpHead + "userLogin";// 登陆
	private static String verifyCode = httpHead + "verifyCaptchaCode";// 验证验证码
	private static String changePassword = httpHead + "changePassword";// 更改密码
	/**
	 * 个人中心接口
	 */
	private static String updateUserInfo = httpHead + "updateUserInfo";// 修改用户名
	private static String getUserInfo = httpHead + "getUserInfo";// 获取用户信息
	private static String getPersonOrder = httpHead + "listOrders";// 获取个人中心订单信息
	private static String sendMessage = httpHead + "listUserNote"; // 消息
	private static String updateVersion = httpHead + "getLatestAppRelease";// 更新版本
	private static String customized = httpHead + "listCustomizedRequest";// 私人订制信息列表
	/**
	 * 休闲中心
	 */
	private static String healthDrink = httpHead + "listHealthDrink";// 健康饮酒
	private static String wineJokes = httpHead + "listWineJoke";// 酒段子
	/**
	 * 寻酒，私人订制
	 */
	public static String uploadFile = httpHead + "uploadFile";// 上传图片
	private static String image = httpHead + "image";// 查看图片
	private static String listProductRequest = httpHead + "listProductRequest";// 寻酒信息列表
	private static String createProductRequest = httpHead
			+ "createProductRequest";// 新增寻酒信息
	private static String listCustomizedRequest = httpHead
			+ "listCustomizedRequest";// 私人定制信息列表
	private static String createCustomizedRequest = httpHead
			+ "createCustomizedRequest";// 新增私人定制信息

	/**
	 * 下单相关接口
	 */
	private static String getCategoryFeatures = httpHead
			+ "getCategoryFeatures";// 获取酒品分类特征
	private static String findProduct = httpHead + "findProduct";// 查询检索酒品全部信息
	private static String comfirmErweima = httpHead
			+ "confirmCompletedOrderItem";// 二维码扫描确认订单
	private static String immediateconfirm = httpHead + "confirmCompletedOrder";// 手动扫描确认订单
	private static String systemMessage = httpHead + "listSystemNotice"; // 系统消息
	private static String addShoppingListItem = httpHead
			+ "addShoppingListItem";// 添加商品到购物车
	private static String listShoppingListItem = httpHead
			+ "listShoppingListItem";// 获取购物车酒品列表
	private static String deleteShoppingListItem = httpHead
			+ "deleteShoppingListItem";// 从购物车删除商品
	private static String clearShoppingListItem = httpHead
			+ "clearShoppingListItem";// 清空购物车商品
	private static String setShoppingListAddress = httpHead
			+ "setShoppingListAddress";// 设置购物车收货地址
	private static String submitShoppingList = httpHead + "submitShoppingList";// 提交
	private static String createShippingAddress = httpHead
			+ "createShippingAddress";// 新增收货地址
	private static String listShippingAddress = httpHead
			+ "listShippingAddress";// 获取收货地址
	private static String getGeoChildren = httpHead + "getGeoChildren";// 获取省/市/区列表
	private static String updateShippingAddress = httpHead
			+ "updateShippingAddress";// 修改收货地址
	private static String deleteShippingAddress = httpHead
			+ "deleteShippingAddress";// 删除收货地址
	private Context mContext;

	public HttpUtil(Context context) {
		mContext = context;
	}

	/**
	 * 新增私人订制
	 * 
	 * @author xuwu
	 * @param postBean
	 * @param callBack
	 */
	public void createCustomizedRequest(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		TailorBean tailorBean = postBean.getTailorBean();
		if (tailorBean.getImages().size() == 0) {
			params.addQueryStringParameter("contentId", "");
		} else {
			for (String str : tailorBean.getImages()) {
				params.addQueryStringParameter("contentId",
						Logic.getString(str));
			}
		}
		params.addQueryStringParameter(
				"custRequestName",
				tailorBean.getTailorAdrress() + ","
						+ tailorBean.getTailorPrice() + ","
						+ tailorBean.getTailorNum() + ","
						+ tailorBean.getTailorTime() + ","
						+ tailorBean.getTailorDesc());
		params.addQueryStringParameter(
				"story",
				tailorBean.getTailorName() + "," + tailorBean.getTailorPhone()
						+ "," + tailorBean.getTailorEmail() + ","
						+ tailorBean.getTailorPath() + ","
						+ tailorBean.getTailorRemark());
		HttpsClientHelper.httpsPost(createCustomizedRequest, mContext, params,
				callBack);
	}

	/**
	 * 寻酒信息
	 * 
	 * @author xuwu
	 * @param postBean
	 * @param callBack
	 */
	public void listProductRequest(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		HttpsClientHelper.httpsPost(listProductRequest, mContext, params,
				callBack);
	}

	/**
	 * 新增寻酒信息
	 * 
	 * @author xuwu
	 * @param postBean
	 * @param callBack
	 */
	public void createProductRequest(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		if (postBean.getXunJiuImages().size() == 0) {
			params.addQueryStringParameter("contentId", "");
		} else {
			for (String str : postBean.getXunJiuImages()) {
				params.addQueryStringParameter("contentId",
						Logic.getString(str));
			}
		}
		params.addQueryStringParameter("custRequestName",
				Logic.getString(postBean.getXunJiuTitle()));
		params.addQueryStringParameter("story",
				Logic.getString(postBean.getXunJiuContent()));
		HttpsClientHelper.httpsPost(createProductRequest, mContext, params,
				callBack);
	}

	/**
	 * 删除收货地址
	 * 
	 * @author xuwu
	 * @param postBean
	 * @param callBack
	 */
	public void deleteShippingAddress(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		params.addQueryStringParameter("contactMechId",
				Logic.getString(postBean.getContactMechId()));
		HttpsClientHelper.httpsPost(deleteShippingAddress, mContext, params,
				callBack);
	}

	/**
	 * 修改收货地址
	 * 
	 * @author xuwu
	 * @param postBean
	 * @param callBack
	 */
	public void updateShippingAddress(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		params.addQueryStringParameter("attnName",
				Logic.getString(postBean.getAttnName()));
		params.addQueryStringParameter("contactMechId",
				Logic.getString(postBean.getContactMechId()));
		params.addQueryStringParameter("stateProvinceGeoId",
				Logic.getString(postBean.getStateProvinceGeoId()));
		params.addQueryStringParameter("cityGeoId",
				Logic.getString(postBean.getCityGeoId()));
		params.addQueryStringParameter("countyGeoId",
				Logic.getString(postBean.getCountyGeoId()));
		params.addQueryStringParameter("address2",
				Logic.getString(postBean.getAddress2()));
		params.addQueryStringParameter("phoneNumber",
				Logic.getString(postBean.getPhone()));
		HttpsClientHelper.httpsPost(updateShippingAddress, mContext, params,
				callBack);
	}

	/**
	 * 获取省/市/区列表
	 * 
	 * @author xuwu
	 * @param postBean
	 * @param callBack
	 */
	public void getGeoChildren(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		params.addQueryStringParameter("geoId",
				Logic.getString(postBean.getGeoId()));
		HttpsClientHelper.httpsPost(getGeoChildren, mContext, params, callBack);
	}

	/**
	 * 获取收货地址
	 * 
	 * @author xuwu
	 * @param postBean
	 * @param callBack
	 */
	public void listShippingAddress(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		HttpsClientHelper.httpsPost(listShippingAddress, mContext, params,
				callBack);
	}

	/**
	 * 新增收货地址
	 * 
	 * @author xuwu
	 * @param postBean
	 * @param callBack
	 */
	public void createShippingAddress(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		params.addQueryStringParameter("attnName",
				Logic.getString(postBean.getAttnName()));
		params.addQueryStringParameter("stateProvinceGeoId",
				Logic.getString(postBean.getStateProvinceGeoId()));
		params.addQueryStringParameter("cityGeoId",
				Logic.getString(postBean.getCityGeoId()));
		params.addQueryStringParameter("countyGeoId",
				Logic.getString(postBean.getCountyGeoId()));
		params.addQueryStringParameter("address2",
				Logic.getString(postBean.getAddress2()));
		params.addQueryStringParameter("phoneNumber",
				Logic.getString(postBean.getPhone()));
		params.addQueryStringParameter("longitude",
				Logic.getString("1215426516"));
		params.addQueryStringParameter("latitude",
				Logic.getString("1256752151"));
		HttpsClientHelper.httpsPost(createShippingAddress, mContext, params,
				callBack);
	}

	/**
	 * 设置收货地址
	 * 
	 * @author xuwu
	 * @param postBean
	 * @param callBack
	 */
	public void setShoppingListAddress(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		params.addQueryStringParameter("contactMechId",
				Logic.getString(postBean.getContactMechId()));
		HttpsClientHelper.httpsPost(setShoppingListAddress, mContext, params,
				callBack);
	}

	/**
	 * 提交订单
	 * 
	 * @author xuwu
	 * @param postBean
	 * @param callBack
	 */
	public void submitShoppingList(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		params.addQueryStringParameter("shipDate",
				Logic.getString(postBean.getShipDate()));
		params.addQueryStringParameter("note",
				Logic.getString(postBean.getNote()));
		params.addQueryStringParameter("shoppingListItemSeqIds",
				postBean.getShoppingListItemSeqId());
		HttpsClientHelper.httpsPost(submitShoppingList, mContext, params,
				callBack);
	}

	/**
	 * 清空购物车商品
	 * 
	 * @author xuwu
	 * @param postBean
	 * @param callBack
	 */
	public void clearShoppingListItem(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));

		HttpsClientHelper.httpsPost(clearShoppingListItem, mContext, params,
				callBack);
	}

	/**
	 * 获取购物车酒品列表
	 * 
	 * @author xuwu
	 * @param postBean
	 * @param callBack
	 */
	public void listShoppingListItem(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));

		HttpsClientHelper.httpsPost(listShoppingListItem, mContext, params,
				callBack);
	}

	/**
	 * 删除购物车商品
	 * 
	 * @author xuwu
	 * 
	 * @param callBack
	 */
	public void deleteShoppingListItem(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		for (int i = 0; i < postBean.getSeqIds().size(); i++) {
			params.addQueryStringParameter("shoppingListItemSeqId", postBean
					.getSeqIds().get(i));
		}
		HttpsClientHelper.httpsPost(deleteShoppingListItem, mContext, params,
				callBack);
	}

	/**
	 * 添加商品到购物车
	 * 
	 * @author xuwu
	 * 
	 * @param callBack
	 */
	public void addShoppingListItem(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		params.addQueryStringParameter("productId",
				Logic.getString(postBean.getProductId()));
		params.addQueryStringParameter("quantity",
				Logic.getString(postBean.getQuantity()));
		HttpsClientHelper.httpsPost(addShoppingListItem, mContext, params,
				callBack);
	}

	/**
	 * 查询检索酒品全部信息
	 * 
	 * @param postBean
	 * @return
	 */
	public void findProduct(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("productCategoryId",
				Logic.getString(postBean.getProductCategoryId()));
		params.addQueryStringParameter("productId",
				Logic.getString(postBean.getProductId()));
		params.addQueryStringParameter("internalName",
				Logic.getString(postBean.getInternalName()));
		params.addQueryStringParameter("barcode",
				Logic.getString(postBean.getBarcode()));
		params.addQueryStringParameter("isPromoProduct",
				Logic.getString(postBean.getIsPromoProduct()));
		params.addQueryStringParameter("haveCoupon",
				Logic.getString(postBean.getHaveCoupon()));
		params.addQueryStringParameter("includeGift",
				Logic.getString(postBean.getIncludeGift()));
		params.addQueryStringParameter("viewSize",
				Logic.getString(postBean.getViewSize()));
		params.addQueryStringParameter("viewIndex",
				Logic.getString(postBean.getViewIndex()));
		params.addQueryStringParameter("orderBy",
				Logic.getString(postBean.getOrderBy()));
		params.addQueryStringParameter("longitude",
				Logic.getString("1215426516"));
		params.addQueryStringParameter("latitude",
				Logic.getString("1256752151"));
		ArrayList<String> productFeatureIds = postBean.getProductFeatureIds();
		if (productFeatureIds != null && productFeatureIds.size() > 0) {
			for (int i = 0; i < productFeatureIds.size(); i++) {
				params.addQueryStringParameter("productFeatureIds",
						Logic.getString(productFeatureIds.get(i)));
			}
		}
		HttpsClientHelper.httpsPost(findProduct, mContext, params, callBack);
	}

	/**
	 * 查询单个酒品详细信息
	 * 
	 * @param productId
	 * @return
	 */
	public void findProductById(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("productId",
				Logic.getString(postBean.getProductId()));
		if (BaseActivity.currentLat == 0 || BaseActivity.currentLng == 0) {
			params.addQueryStringParameter("longitude",
					Logic.getString(BaseActivity.currentLng));
			params.addQueryStringParameter("latitude",
					Logic.getString(BaseActivity.currentLat));
		} else {
			params.addQueryStringParameter("longitude",
					Logic.getString("1215426516"));
			params.addQueryStringParameter("latitude",
					Logic.getString("1256752151"));
		}
		HttpsClientHelper.httpsPost(findProduct, mContext, params, callBack);
	}

	/**
	 * 搜索酒品
	 * 
	 * @param productId
	 * @return
	 */
	public void searchProduct(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("internalName",
				Logic.getString(postBean.getInternalName()));
		params.addQueryStringParameter("viewSize",
				Logic.getString(postBean.getViewSize()));
		params.addQueryStringParameter("viewIndex",
				Logic.getString(postBean.getViewIndex()));
		if (BaseActivity.currentLat == 0 || BaseActivity.currentLng == 0) {
			params.addQueryStringParameter("longitude",
					Logic.getString(BaseActivity.currentLng));
			params.addQueryStringParameter("latitude",
					Logic.getString(BaseActivity.currentLat));
		} else {
			params.addQueryStringParameter("longitude",
					Logic.getString("1215426516"));
			params.addQueryStringParameter("latitude",
					Logic.getString("1256752151"));
		}
		HttpsClientHelper.httpsPost(findProduct, mContext, params, callBack);
	}

	/**
	 * 获取酒品分类特征
	 * 
	 * @param postBean
	 * @return
	 */
	public void getCategoryFeatures(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("productCategoryId",
				Logic.getString(postBean.getProductCategoryId()));
		HttpsClientHelper.httpsPost(getCategoryFeatures, mContext, params,
				callBack);
	}

	/**
	 * 获取验证码
	 * 
	 * @param postBean
	 * @return
	 */
	public void getVerifyCode(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("phoneNumber",
				Logic.getString(postBean.getPhoneNumber()));
		HttpsClientHelper.httpsPost(getVerifyCode, mContext, params, callBack);
	}

	/**
	 * 注册
	 * 
	 * @param postBean
	 * @return
	 */
	public void register(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("phoneNumber",
				Logic.getString(postBean.getPhoneNumber()));
		params.addQueryStringParameter("captchaCode",
				Logic.getString(postBean.getVerify()));
		params.addQueryStringParameter("password",
				Logic.getString(postBean.getPassword()));
		HttpsClientHelper.httpsPost(register, mContext, params, callBack);
	}

	/**
	 * 登陆
	 * 
	 * @param postBean
	 * @return
	 */
	public void login(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("username",
				Logic.getString(postBean.getPhoneNumber()));
		params.addQueryStringParameter("password",
				Logic.getString(postBean.getPassword()));
		HttpsClientHelper.httpsPost(login, mContext, params, callBack);
	}

	/**
	 * 验证手机验证码
	 * 
	 * @param postBean
	 * @return
	 */
	public void verifyCode(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("phoneNumber",
				Logic.getString(postBean.getPhoneNumber()));
		params.addQueryStringParameter("captchaCode",
				Logic.getString(postBean.getVerify()));
		HttpsClientHelper.httpsPost(verifyCode, mContext, params, callBack);
	}

	/**
	 * 重置密码
	 * 
	 * @param postBean
	 * @return
	 */
	public void resetPassword(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addQueryStringParameter("phoneNumber",
				Logic.getString(postBean.getPhoneNumber()));
		params.addQueryStringParameter("captchaCode",
				Logic.getString(postBean.getVerify()));
		params.addQueryStringParameter("password",
				Logic.getString(postBean.getPassword()));
		HttpsClientHelper.httpsPost(resetPassword, mContext, params, callBack);
	}

	/**
	 * 更改密码
	 * 
	 * @param postBean
	 * @return
	 */
	public void changePassword(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD",
				Logic.getString(postBean.getLastPassword()));
		params.addQueryStringParameter("password",
				Logic.getString(postBean.getPassword()));
		HttpsClientHelper.httpsPost(changePassword, mContext, params, callBack);
	}

	/**
	 * 
	 * 更改用户名
	 * 
	 * @param postBean
	 * @param callBack
	 */

	public void changeUsername(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		params.addQueryStringParameter("firstName",
				Logic.getString(postBean.getUserName()));
		HttpsClientHelper.httpsPost(updateUserInfo, mContext, params, callBack);
	}

	/**
	 * 获取用户信息
	 * 
	 * @param postBean
	 * @param callBack
	 */
	public void getUserInfo(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		HttpsClientHelper.httpsPost(getUserInfo, mContext, params, callBack);
	}

	/**
	 * 获取个人订单的状态和信息
	 * 
	 * @param postBean
	 * @param callBack
	 */

	public void getPersonOrder(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		params.addQueryStringParameter("statusId",
				Logic.getString(postBean.getStatusId()));
		HttpsClientHelper.httpsPost(getPersonOrder, mContext, params, callBack);
	}

	/**
	 * 二维码扫描确认订单
	 * 
	 * @param postBean
	 * @param callBack
	 */
	public void confirmErweima(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		params.addQueryStringParameter("productBarcode",
				Logic.getString(postBean.getBarcode()));
		HttpsClientHelper.httpsPost(comfirmErweima, mContext, params, callBack);
	}

	/**
	 * 手动确认订单
	 * 
	 * @param postBean
	 * @param callBack
	 */
	public void immediateconfirm(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		params.addQueryStringParameter("orderId",
				Logic.getString(postBean.getProductId()));
		HttpsClientHelper.httpsPost(immediateconfirm, mContext, params,
				callBack);
	}

	/**
	 * 
	 * 
	 * @param postBean
	 * @param callBack
	 */
	public void systemMessage(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		HttpsClientHelper.httpsPost(systemMessage, mContext, params, callBack);
	}

	/**
	 * 
	 * 消息提醒
	 * 
	 * @param postBean
	 * @param callBack
	 */
	public void sendMessage(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		HttpsClientHelper.httpsPost(sendMessage, mContext, params, callBack);
	}

	/**
	 * 版本更新
	 * 
	 * @param postBean
	 * @param callBack
	 */
	public void updateVersion(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		params.addQueryStringParameter("systemNoteEnumId",
				Logic.getString(postBean.getVersion()));
		HttpsClientHelper.httpsPost(updateVersion, mContext, params, callBack);
	}

	/**
	 * 健康饮酒
	 * 
	 * @param postBean
	 * @param callBack
	 */
	public void healthDrink(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		HttpsClientHelper.httpsPost(healthDrink, mContext, params, callBack);
	}

	/**
	 * 酒段子
	 * 
	 * @param postBean
	 * @param callBack
	 */
	public void wineJokes(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		HttpsClientHelper.httpsPost(wineJokes, mContext, params, callBack);
	}

	/**
	 * 私人订制信息列表
	 * 
	 * @param postBean
	 * @param callBack
	 */
	public void customized(PostBean postBean, ECallBack callBack) {
		RequestParams params = new RequestParams();
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		HttpsClientHelper.httpsPost(customized, mContext, params, callBack);
	}
}

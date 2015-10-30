package com.ltd.mos.http;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.widget.Toast;

import com.baidu.platform.comapi.map.p;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.entity.FileUploadEntity;
import com.lidroid.xutils.http.client.multipart.HttpMultipartMode;
import com.lidroid.xutils.http.client.multipart.MultipartEntity;
import com.lidroid.xutils.http.client.multipart.content.FileBody;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.util.Logic;

public class HttpsClientHelper {

	public static void httpsPost(String surl, Context context,
			RequestParams params, ECallBack callBack) {
		postHttpsClient(surl, context, params, callBack);
	}

	/**
	 * 发送请求
	 * 
	 * @param surl
	 * @param context
	 * @param saveCookie
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	static ResultObject result = new ResultObject();

	public static void postHttpsClient(String surl, Context context,
			RequestParams params, final ECallBack callBack) {
		if (result == null) {
			result = new ResultObject();
		}
		HttpUtils http = new HttpUtils();
		http.configCurrentHttpCacheExpiry(1000 * 10);
		http.send(HttpRequest.HttpMethod.GET, surl, params,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						result.setmCode(responseInfo.statusCode);
						result.setmMessage(responseInfo.result);
						callBack.OnCreate(result);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						callBack.OnError("");
					}
				});

	}

	/**
	 * 上传图片
	 * 
	 * @param postBean
	 * @param context
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void uploadFile(PostBean postBean, final Context context,final ECallBack callBack){
		RequestParams params = new RequestParams(); // 默认编码UTF-8
		params.addHeader("USERNAME", Logic.getString(postBean.getPhoneNumber()));
		params.addHeader("PASSWORD", Logic.getString(postBean.getPassword()));
		params.addBodyParameter("uploadedFile",
				new File(postBean.getImagePath()));

		HttpUtils http = new HttpUtils();

		http.send(HttpRequest.HttpMethod.POST, postBean.getUrl(), params,
				new RequestCallBack<String>() {

					@Override
					public void onStart() {
					}

					@Override
					public void onLoading(long total, long current,
							boolean isUploading) {
						if (isUploading) {
							Toast.makeText(context,
									"upload: " + current + "/" + total, 1000)
									.show();
						} else {
							
						}
					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						callBack.OnCreate(responseInfo.result);
					}

					@Override
					public void onFailure(HttpException error, String msg) {
						
					}
				});
	}

}

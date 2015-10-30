package com.ltd.mos.logo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.main.HomeTabActivity;
import com.ltd.mos.main.WineHomeActivity;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.FileUtil;
import com.ltd.mos.util.Logic;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;

public class LogoActivity extends BaseActivity implements Runnable {

	private ImageView logoimage, logo_bottom, logo_imagetext;
	private double marginTop;// logo离屏幕顶部的距离
	private double imageWidth;// logo 宽
	private double imageHeight;// logo 高
	private double bottomWidth;// 底部图片的宽
	private double bottomHeight;// 底部图片的高
	// 定位相关
	LocationClient mLocClient;
	public boolean isFirstLoc = true;// 是否首次定位

	/**
	 * 构造广播监听类，监听 SDK key 验证以及网络异常广播
	 */
	public class SDKReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			String s = intent.getAction();
			if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
			} else if (s
					.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
			}
		}
	}

	private SDKReceiver mReceiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logo);
		getScreenInfo();// 初始化屏幕宽高
		setImageAttribute();
		regToWx();
		Thread thread = new Thread(this);
		thread.start();
		// 注册 SDK 广播监听者
		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
		iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
		mReceiver = new SDKReceiver();
		registerReceiver(mReceiver, iFilter);
		getCurrentLocation();
	}

	private void regToWx() {

		// 实例化微信api
		wxApi = WXAPIFactory.createWXAPI(this, Const.WX_APP_ID, true);

		// 将应用的appid注册到微信
		wxApi.registerApp(Const.WX_APP_ID);

	}

	// 設置logo图片和底部图片的属性
	private void setImageAttribute() {
		marginTop = 64 * SCREENHEIGHT / 800;
		imageWidth = 265 * SCREENWIDE / 480;
		imageHeight = 320 * SCREENHEIGHT / 800;
		logoimage = (ImageView) findViewById(R.id.logo_image);
		logo_bottom = (ImageView) findViewById(R.id.logo_bottom);
		logo_imagetext = (ImageView) findViewById(R.id.logo_imagetext);

		// logo 图片的属性
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.gravity = Gravity.CENTER_HORIZONTAL;
		lp.topMargin = (int) marginTop;
		lp.width = (int) imageWidth;
		lp.height = (int) imageHeight;
		logoimage.setLayoutParams(lp);

		// 底部文字属性
		bottomWidth = 363 * SCREENWIDE / 480;
		bottomHeight = 19 * SCREENHEIGHT / 800;
		LayoutParams params = logo_bottom.getLayoutParams();
		params.width = (int) bottomWidth;
		params.height = (int) bottomHeight;
		logo_bottom.setLayoutParams(params);

		// logo下面文字属性
		bottomWidth = 240 * SCREENWIDE / 480;
		bottomHeight = 81 * SCREENHEIGHT / 800;
		LayoutParams param = logo_imagetext.getLayoutParams();
		param.width = (int) bottomWidth;
		param.height = (int) bottomHeight;
		logo_imagetext.setLayoutParams(param);
	}

	public void run() {
		SystemClock.sleep(1000);

		FileUtil.copyDBFile(LogoActivity.this, "searchhistory.db");

		if (SaveApplicationParam.getGuideVal(LogoActivity.this)) {
			startActivity(new Intent(LogoActivity.this, HomeTabActivity.class));// 主界面
		} else {
			startActivity(new Intent(LogoActivity.this, GuideActivity.class));// 引导界面
		}

		this.finish();
	}
	@Override
	protected void onPause() {

		super.onPause();

		MobclickAgent.onPause(this);

	}

	@Override
	protected void onResume() {

		super.onResume();

		MobclickAgent.onResume(this);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 取消监听 SDK 广播
		unregisterReceiver(mReceiver);
	}

	/**
	 * 获取定位信息
	 */
	public void getCurrentLocation() {
		if (mLocClient != null) {
			mLocClient.stop();
		}
		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(new MyLocationListenner());
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(10000);
		mLocClient.setLocOption(option);
		mLocClient.start();
	}

	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null)
				return;
			if (isFirstLoc) {
				isFirstLoc = false;
				currentLat = (float) location.getLatitude();
				currentLng = (float) location.getLongitude();
				WineHomeActivity activity = (WineHomeActivity) Logic
						.getActivityByName(".WineHomeActivity");
				if (activity != null) {
					activity.onRefresh();
				}
				if (mLocClient != null) {
					mLocClient.stop();
				}
			}
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}
}

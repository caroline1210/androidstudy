package com.ltd.mos.main;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewDebug.IntToString;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.base.Refresh;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.erweima.ErcodeScanActivity;
import com.ltd.mos.game.GameActivity;
import com.ltd.mos.login.LoginActivity;
import com.ltd.mos.logo.LogoActivity;
import com.ltd.mos.personal.DGoodsAct;
import com.ltd.mos.personal.DaijinquanAct;
import com.ltd.mos.personal.PersonAct;
import com.ltd.mos.personal.QuestionAct;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

/**
 * 选酒中心
 * 
 * @author xuwu
 * 
 */
public class WineHomeActivity extends BaseActivity implements
		OnPageChangeListener, OnClickListener, Refresh {

	private ViewPager winehome_viewPager;
	private GridView winehome_gridview;
	private ArrayList<ImageView> imageViews;// 图片数组
	private ArrayList<View> dots;// 圆点数组
	private ImageView home_ad, home_search;// 广告图片的id
	private ViewGroup home_help;
	private int currentItem = 0;
	private int oldPosition = 0;
	// 图片资源id
	private int[] imageId = { R.drawable.ad_01, R.drawable.ad_02,
			R.drawable.ad_03, };
	private ScheduledExecutorService scheduledExecutorService;
	private TextView home_dols, home_location;
	private ViewGroup home_ll_location;
	private GeoCoder mSearch = null;
	public static boolean GETLOCATION;
	private ImageView home_dols_image;
	private ImageView unRead;
	// 切换当前显示的图片
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			winehome_viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
		};
	};

	private void initSearch() {
		// 初始化搜索模块，注册事件监听
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
			public void onGetGeoCodeResult(GeoCodeResult arg0) {
				// TODO Auto-generated method stub

			}

			public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
				// TODO Auto-generated method stub
				if (result == null
						|| result.error != SearchResult.ERRORNO.NO_ERROR) {
					return;
				}
				String city = result.getAddressDetail().city;
				String address = result.getAddress();
				Toast.makeText(WineHomeActivity.this, city + " : " + address,
						Toast.LENGTH_LONG).show();
				SaveApplicationParam.saveLandLocation(WineHomeActivity.this,
						address);
				home_location.setText(Logic.getInstance().getAddress(address));
			}
		});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.winehome);
		getScreenInfo();

		findViewById();
		startTime();
		// 根据屏幕大小设置viewpager的宽高
		LayoutParams params = winehome_viewPager.getLayoutParams();
		params.width = SCREENWIDE;
		// params.height = 350 * SCREENWIDE / 970;
		params.height = 200 * SCREENWIDE / 450;
		winehome_viewPager.setLayoutParams(params);

		// 设置广告语的高度
		LayoutParams layoutParams = home_ad.getLayoutParams();
		layoutParams.height = 112 * SCREENWIDE / 480;
		home_ad.setLayoutParams(layoutParams);

		initPagerImage();

		winehome_viewPager.setAdapter(new HomePagerAdapter());

		// 设置滑动监听器
		winehome_viewPager.setOnPageChangeListener(this);

		winehome_gridview.setAdapter(new WineHomeAdapter(this));

		winehome_gridview.setOnItemClickListener(new GridItemClick());

	}

	private boolean INTIME = false;

	private void startTime() {
		// TODO Auto-generated method stub
		if (INTIME) {

		} else {
			new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					INTIME = true;
					int num = 0;
					while (currentLat == 0 || currentLng == 0) {
						SystemClock.sleep(200);
						num++;
						if (num > 50) {
							INTIME = false;
							return;
						}
					}
					INTIME = false;
					handler2.sendMessage(new Message());

				}
			}).start();
		}

	}

	Handler handler2 = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (mSearch == null) {
				initSearch();
			}
			LatLng ptCenter = new LatLng(currentLat, currentLng);
			ReverseGeoCodeOption option = new ReverseGeoCodeOption();
			option.location(ptCenter);
			mSearch.reverseGeoCode(option);
			GETLOCATION = true;
		};
	};

	private void initPagerImage() {
		imageViews = new ArrayList<ImageView>();
		// 初始化图片资源
		for (int i = 0; i < imageId.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(imageId[i]);
			imageView.setScaleType(ScaleType.CENTER_CROP);
			imageViews.add(imageView);
		}
		// 装载圆点
		dots = new ArrayList<View>();
		dots.add(findViewById(R.id.home_dot0));
		dots.add(findViewById(R.id.home_dot1));
		dots.add(findViewById(R.id.home_dot2));
	}

	@Override
	protected void onStart() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		// 当Activity显示出来后，每两秒钟切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 4,
				TimeUnit.SECONDS);
		super.onStart();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mSearch != null) {
			mSearch.destroy();
		}
	}

	@Override
	protected void onStop() {
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
		Logic.allActivity.add(this);
		super.onStop();
	}

	/**
	 * 返回键响应
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click(); // 调用双击退出函数
		}
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Logic.allActivity.remove(this);
		String str = SaveApplicationParam.getLandLocation(this);
		if (Logic.getString(str).length() == 0) {
			return;
		}
		home_location.setText(Logic.getInstance().getAddress(str));
	}

	/**
	 * 换行切换任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (winehome_viewPager) {
				currentItem = (currentItem + 1) % imageViews.size();
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}

	private void findViewById() {
		winehome_viewPager = (ViewPager) findViewById(R.id.winehome_viewpager);
		winehome_gridview = (GridView) findViewById(R.id.winehome_gridview);
		home_ad = (ImageView) findViewById(R.id.home_ad);
		home_search = (ImageView) findViewById(R.id.home_search);
		home_help = (ViewGroup) findViewById(R.id.home_help);
		home_dols = (TextView) findViewById(R.id.home_dols);
		home_dols.setOnClickListener(this);
		home_dols_image = (ImageView) findViewById(R.id.home_dols_image);
		home_dols_image.setOnClickListener(this);
		home_ll_location = (ViewGroup) findViewById(R.id.home_ll_location);
		home_location = (TextView) findViewById(R.id.home_location);
		unRead = (ImageView) this.findViewById(R.id.unRead);
		home_search.setOnClickListener(this);
		home_help.setOnClickListener(this);
		home_ll_location.setOnClickListener(this);
	}

	class GridItemClick implements OnItemClickListener {

		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			switch (position) {
			case 0:// 葡萄酒
				startActivity(new Intent(WineHomeActivity.this,
						GrapeActivity.class).putExtra(Const.FLAG_WINE,
						Const.GRAPE_WINE));
				break;
			case 1:// 白酒
				startActivity(new Intent(WineHomeActivity.this,
						GrapeActivity.class).putExtra(Const.FLAG_WINE,
						Const.WHITE_SPIRIT));
				break;
			case 2:// 啤酒
				startActivity(new Intent(WineHomeActivity.this,
						GrapeActivity.class).putExtra(Const.FLAG_WINE,
						Const.BEER));
				break;
			case 3:// 老酒
				startActivity(new Intent(WineHomeActivity.this,
						GrapeActivity.class).putExtra(Const.FLAG_WINE,
						Const.OLD_WINE));
				break;
			case 4:// 周边
				startActivity(new Intent(WineHomeActivity.this,
						GameActivity.class));
				break;
			case 5:// 扫一扫
				startActivity(new Intent(WineHomeActivity.this, DGoodsAct.class));
				break;
			default:
				break;
			}
		}

	}

	/**
	 * viewpager 适配器
	 * 
	 * @author xuwu
	 * 
	 */
	class HomePagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(imageViews.get(arg1));
			return imageViews.get(arg1);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView((View) arg2);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

	}

	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public void onPageSelected(int position) {
		currentItem = position;
		dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
		dots.get(position).setBackgroundResource(R.drawable.dot_focused);
		oldPosition = position;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.home_search:
			startActivity(new Intent(WineHomeActivity.this,
					WineSearchActivity.class));
			break;
		case R.id.home_help:// 帮助
			unRead.setVisibility(View.GONE);
			startActivity(new Intent(this, QuestionAct.class));
			break;
		case R.id.home_ll_location:// 位置
			startActivity(new Intent(WineHomeActivity.this,
					LocationActivity.class));
			break;
		case R.id.home_dols:
		case R.id.home_dols_image:
			if (Logic.getLoginState(this)) {
				startActivity(new Intent(this, DaijinquanAct.class));
			} else {
				showMainPopWindow(home_dols_image, new ECallBack() {

					public void OnError(Object obj) {
						// TODO Auto-generated method stub

					}

					public void OnCreate(Object obj) {
						// TODO Auto-generated method stub
						startActivity(new Intent(WineHomeActivity.this,
								LoginActivity.class));
					}
				});
			}
			break;
		default:
			break;
		}

	}

	public void onRefresh() {
		// TODO Auto-generated method stub
		try {
			LatLng ptCenter = new LatLng(currentLat, currentLng);
			ReverseGeoCodeOption option = new ReverseGeoCodeOption();
			option.location(ptCenter);
			if (mSearch == null) {
				initSearch();
			}
			mSearch.reverseGeoCode(option);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

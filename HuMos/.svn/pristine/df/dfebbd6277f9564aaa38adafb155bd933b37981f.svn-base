package com.ltd.mos.sercenter;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.personal.QuestionAct;
import com.ltd.mos.util.Logic;

/**
 * 服务中心
 * 
 * @author CaoT。
 * 
 */
public class SerCenterActivity extends BaseActivity implements
		OnPageChangeListener {

	private ViewPager ser_viewPager;
	private GridView ser_gridview;
	private ArrayList<ImageView> imageViews;// 图片数组
	private ArrayList<View> dots;// 圆点数组
	private View view;
	private int currentItem = 0;
	private int oldPosition = 0;
	// 图片资源id
	private int[] imageId = { R.drawable.ser_ad_03, R.drawable.ser_ad_04 };
	private ScheduledExecutorService scheduledExecutorService;
	// 切换当前显示的图片
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			ser_viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sercenter);
		getScreenInfo();
		findViewById();

		initPagerImage();

		// 根据屏幕大小设置viewpager的宽高
		LayoutParams params = ser_viewPager.getLayoutParams();
		params.width = SCREENWIDE;
		params.height = 200 * SCREENWIDE / 450;
		ser_viewPager.setLayoutParams(params);

		ser_viewPager.setAdapter(new SerPagerAdapter());

		ser_viewPager.setOnPageChangeListener(this);

		ser_gridview.setAdapter(new SerCenterAdapter(this));
		ser_gridview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					startActivity(new Intent(SerCenterActivity.this,
							TastingActivity.class));
					break;
				case 1:
					startActivity(new Intent(SerCenterActivity.this,
							PersonalTailorActivity.class));
					break;
				case 2:
					startActivity(new Intent(SerCenterActivity.this,
							SearchWineActivity.class));
					break;
				case 3:
					startActivity(new Intent(SerCenterActivity.this,
							LighthouseActivity.class));
					break;
				case 4:
					startActivity(new Intent(SerCenterActivity.this,
							QuestionAct.class));
					break;
				case 5:
					break;
				case 6:

					break;
				default:
					break;
				}
			}
		});

	}

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
		dots.add(findViewById(R.id.ser_dot0));
		dots.add(findViewById(R.id.ser_dot1));
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
	protected void onStop() {
		// 当Activity不可见的时候停止切换
		scheduledExecutorService.shutdown();
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

	/**
	 * 换行切换任务
	 * 
	 * @author Administrator
	 * 
	 */
	private class ScrollTask implements Runnable {

		public void run() {
			synchronized (ser_viewPager) {
				currentItem = (currentItem + 1) % imageViews.size();
				handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
			}
		}

	}

	private void findViewById() {
		ser_viewPager = (ViewPager) findViewById(R.id.ser_viewpager);
		ser_gridview = (GridView) findViewById(R.id.ser_gridview);
		view = findViewById(R.id.view);
		Logic logic = Logic.getInstance();
		logic.initHeadView(view, "服务中心", false, "", null);
	}

	/**
	 * viewpager 适配器
	 * 
	 * @author xuwu
	 * 
	 */
	class SerPagerAdapter extends PagerAdapter {

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
}

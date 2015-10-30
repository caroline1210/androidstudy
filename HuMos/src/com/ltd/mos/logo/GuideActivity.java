package com.ltd.mos.logo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.main.HomeTabActivity;

public class GuideActivity extends BaseActivity implements OnPageChangeListener {

	private ViewPager guidePager;
	// 图片资源id
	private int[] imageId = { R.drawable.guide_01, R.drawable.guide_02,
			R.drawable.guide_03 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide);
		SaveApplicationParam.saveGuideVal(this, true);
		getScreenInfo();
		guidePager = (ViewPager) findViewById(R.id.guide_pager);
		guidePager.setAdapter(new GPagerAdapter());
		guidePager.setOnPageChangeListener(this);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			startActivity(new Intent(GuideActivity.this, HomeTabActivity.class));// 主界面
			finish();

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	class GPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imageId.length;
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			View imageLayout = LinearLayout.inflate(GuideActivity.this,
					R.layout.guide_pager_item, null);

			assert imageLayout != null;
			ImageView imageView = (ImageView) imageLayout
					.findViewById(R.id.image);
			ImageView inhome = (ImageView) imageLayout.findViewById(R.id.inhome);

			LayoutParams params = inhome.getLayoutParams();
			params.width = 130*BaseActivity.SCREENWIDE/450;
			params.height = 36*BaseActivity.SCREENHEIGHT/800;
			inhome.setLayoutParams(params);
			
			if (position == imageId.length - 1) {
				inhome.setVisibility(View.VISIBLE);
			} else {
				inhome.setVisibility(View.GONE);
			}

			inhome.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					startActivity(new Intent(GuideActivity.this,
							HomeTabActivity.class));// 主界面
					finish();
				}
			});

			imageView.setImageResource(imageId[position]);

			view.addView(imageLayout, 0);
			return imageLayout;
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

	}

	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	public void onPageSelected(int position) {

	}
}

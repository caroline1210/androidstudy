package com.ltd.mos.main;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.ltd.mos.R;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.leisure.LeisureActivity;
import com.ltd.mos.login.LoginActivity;
import com.ltd.mos.logo.LogoActivity;
import com.ltd.mos.personal.PersonAct;
import com.ltd.mos.sercenter.SerCenterActivity;
import com.ltd.mos.shopcar.ShopCarActivity;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;

public class HomeTabActivity extends TabActivity implements OnClickListener {

	public static TabHost tabHost;
	public static String WINECENTER = "WINECENTER";// 选酒中心
	public String SHOPCAR = "SHOPCAR";// 购物车
	public String PERSONAL = "PERSONAL";// 个人中心
	public String SERVERCENTER = "SERVERCENTER";// 服务中心
	public String LEISURECENTER = "LEISURECENTER";// 休闲中心
	private LinearLayout tab_winecenter, tab_shopcar, tab_personal, tab_server,
			tab_leisure;
	private static ImageView tab_image_wine;
	private static ImageView tab_image_cart;
	private static ImageView tab_image_personal;
	private static ImageView tab_image_servicecenter;
	private static ImageView tab_image_leisure;
	private static TextView tab_text_wine;
	private static TextView tab_text_cart;
	private static TextView tab_text_personal;
	private static TextView tab_text_servicecenter;
	private static TextView tab_text_leisure;

	static Context context;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.hometab);
		setTab();
		setCheckEd(R.id.tab_winecenter);
		UmengUpdateAgent.update(this);

		UmengUpdateAgent.setUpdateAutoPopup(false);

		UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {

			public void onUpdateReturned(int updateStatus,
					UpdateResponse updateInfo) {

				switch (updateStatus) {

				case 0: // has update

					UmengUpdateAgent.showUpdateDialog(HomeTabActivity.this,
							updateInfo);

					break;

				}

			}


		});

	}

	@SuppressWarnings("deprecation")
	private void setTab() {

		tabHost = getTabHost();

		iniTabSpec(WINECENTER, WineHomeActivity.class);
		iniTabSpec(SHOPCAR, ShopCarActivity.class);
		iniTabSpec(PERSONAL, PersonAct.class);
		iniTabSpec(SERVERCENTER, SerCenterActivity.class);
		iniTabSpec(LEISURECENTER, LeisureActivity.class);

		tab_winecenter = (LinearLayout) findViewById(R.id.tab_winecenter);
		tab_shopcar = (LinearLayout) findViewById(R.id.tab_shopcar);
		tab_personal = (LinearLayout) findViewById(R.id.tab_personal);
		tab_server = (LinearLayout) findViewById(R.id.tab_server);
		tab_leisure = (LinearLayout) findViewById(R.id.tab_leisure);

		tab_image_wine = (ImageView) findViewById(R.id.tab_image_wine);
		tab_image_cart = (ImageView) findViewById(R.id.tab_image_cart);
		tab_image_personal = (ImageView) findViewById(R.id.tab_image_personal);
		tab_image_servicecenter = (ImageView) findViewById(R.id.tab_image_servicecenter);
		tab_image_leisure = (ImageView) findViewById(R.id.tab_image_leisure);

		tab_text_wine = (TextView) findViewById(R.id.tab_text_wine);
		tab_text_cart = (TextView) findViewById(R.id.tab_text_cart);
		tab_text_personal = (TextView) findViewById(R.id.tab_text_personal);
		tab_text_servicecenter = (TextView) findViewById(R.id.tab_text_servicecenter);
		tab_text_leisure = (TextView) findViewById(R.id.tab_text_leisure);

		tab_winecenter.setOnClickListener(this);
		tab_shopcar.setOnClickListener(this);
		tab_personal.setOnClickListener(this);
		tab_server.setOnClickListener(this);
		tab_leisure.setOnClickListener(this);

	}

	private void iniTabSpec(String FLAG, Class<?> activity) {
		TabSpec assign = tabHost.newTabSpec(FLAG).setIndicator(FLAG);
		assign.setContent(new Intent(HomeTabActivity.this, activity));
		tabHost.addTab(assign);
	}

	/**
	 * 底部标签点击事件
	 */
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.tab_winecenter:// 选酒中心
			tabHost.setCurrentTabByTag(WINECENTER);
			setCheckEd(R.id.tab_winecenter);
			break;
		case R.id.tab_shopcar:// 购物车
			if (SaveApplicationParam.getLandState(HomeTabActivity.this))
				tabHost.setCurrentTabByTag(SHOPCAR);
			else
				startActivity(new Intent(HomeTabActivity.this,
						LoginActivity.class));
			setCheckEd(R.id.tab_shopcar);
			break;
		case R.id.tab_personal:// 个人中心
			tabHost.setCurrentTabByTag(PERSONAL);

			setCheckEd(R.id.tab_personal);
			break;
		case R.id.tab_server:// 服务中心
			tabHost.setCurrentTabByTag(SERVERCENTER);

			setCheckEd(R.id.tab_server);
			break;
		case R.id.tab_leisure:// 休闲中心
			tabHost.setCurrentTabByTag(LEISURECENTER);
			setCheckEd(R.id.tab_leisure);
			break;
		default:
			break;
		}

	}

	/**
	 * 设置选择状态
	 * 
	 * @param id
	 */
	public static void setCheckEd(int id) {

		clearBg();

		switch (id) {
		case R.id.tab_winecenter:
			tab_image_wine.setImageResource(R.drawable.wine_center_click);
			tab_text_wine.setTextColor(context.getResources().getColor(
					R.color.blue));
			break;
		case R.id.tab_shopcar:
			tab_image_cart.setImageResource(R.drawable.shopping_cart_click);
			tab_text_cart.setTextColor(context.getResources().getColor(
					R.color.blue));
			break;
		case R.id.tab_personal:
			tab_image_personal.setImageResource(R.drawable.personal_click);
			tab_text_personal.setTextColor(context.getResources().getColor(
					R.color.blue));
			break;
		case R.id.tab_server:
			tab_image_servicecenter
					.setImageResource(R.drawable.servicecenter_click);
			tab_text_servicecenter.setTextColor(context.getResources()
					.getColor(R.color.blue));
			break;
		case R.id.tab_leisure:
			tab_image_leisure.setImageResource(R.drawable.leisure_click);
			tab_text_leisure.setTextColor(context.getResources().getColor(
					R.color.blue));
			break;
		default:
			break;
		}
	}

	/**
	 * 清除选中状态
	 */
	private static void clearBg() {
		tab_image_wine.setImageResource(R.drawable.wine_center);
		tab_text_wine.setTextColor(context.getResources().getColor(
				R.color.black));

		tab_image_cart.setImageResource(R.drawable.shopping_cart);
		tab_text_cart.setTextColor(context.getResources().getColor(
				R.color.black));

		tab_image_personal.setImageResource(R.drawable.personal);
		tab_text_personal.setTextColor(context.getResources().getColor(
				R.color.black));

		tab_image_servicecenter.setImageResource(R.drawable.servicecenter);
		tab_text_servicecenter.setTextColor(context.getResources().getColor(
				R.color.black));

		tab_image_leisure.setImageResource(R.drawable.leisure);
		tab_text_leisure.setTextColor(context.getResources().getColor(
				R.color.black));
	}
}

package com.ltd.mos.main;

import java.util.ArrayList;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.CategoryFeature;
import com.ltd.mos.bean.FilterBean;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

@SuppressWarnings("deprecation")
public class GrapeActivity extends ActivityGroup {

	private Logic logic;
	private View grape_title, grapelable;
	private ViewGroup gv;
	public static final String HOTSELLACTIVITY = "HOTSELLACTIVITY";
	private int currentlable;
	private String FALG_WINE;
	private String title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grape);
		FALG_WINE = Logic.getString(this.getIntent().getStringExtra(
				Const.FLAG_WINE));
		title = getTitleName();
		findviewid();

	}

	private void findviewid() {
		logic = Logic.getInstance();
		grape_title = findViewById(R.id.grape_title);
		grapelable = findViewById(R.id.grapelable);
		gv = (ViewGroup) findViewById(R.id.grape_ll);

		if (FALG_WINE.equals(Const.OLD_WINE)) {
			logic.initGrapeTitle(grape_title, title, true, false,
					new GrapeEcallBack());
		} else {
			logic.initGrapeTitle(grape_title, title, false, false,
					new GrapeEcallBack());

		}
		logic.initGrapeLable(GrapeActivity.this, grapelable, FALG_WINE,
				new LableCallBack());
		gv.removeAllViews();
		View frontView = getLocalActivityManager().startActivity(
				"no1",
				new Intent(GrapeActivity.this, HotSellActivity.class).putExtra(
						"LABLE", Const.HOTSELL).putExtra(Const.FLAG_WINE,
						FALG_WINE)).getDecorView();
		gv.addView(frontView);

		currentlable = 0;
	}

	/**
	 * 选项卡点击事件
	 * 
	 * @author xuwu
	 * 
	 */
	class LableCallBack implements ECallBack {

		public void OnCreate(Object obj) {
			View curView = gv.getChildAt(0);
			if (Const.HOTSELL.equals(obj) || Const.ALL.equals(obj)) {// 热销 ,全部
				if (currentlable == 0) {
					return;
				}
				logic.AnimaRightOut(GrapeActivity.this, curView);
				gv.removeAllViews();
				View frontView = getLocalActivityManager()
						.startActivity(
								"no1",
								new Intent(GrapeActivity.this,
										HotSellActivity.class)
										.putExtra(
												"LABLE",
												Const.HOTSELL.equals(obj) ? Const.HOTSELL
														: Const.ALL).putExtra(
												Const.FLAG_WINE, FALG_WINE))
						.getDecorView();
				logic.AnimaRightIn(GrapeActivity.this, frontView);
				gv.addView(frontView);
				currentlable = 0;
			} else if (Const.PRICE.equals(obj)
					|| Const.WHITE_SPIRIT.equals(obj)) {// 价格，白酒
				if (currentlable == 1) {
					return;
				}
				if (currentlable == 0) {
					logic.AnimaLeftOut(GrapeActivity.this, curView);
				} else {
					logic.AnimaRightOut(GrapeActivity.this, curView);
				}
				gv.removeAllViews();
				View frontView = getLocalActivityManager().startActivity(
						"no2",
						new Intent(GrapeActivity.this, HotSellActivity.class)
								.putExtra(
										"LABLE",
										Const.PRICE.equals(obj) ? Const.PRICE
												: Const.WHITE_SPIRIT).putExtra(
										Const.FLAG_WINE, FALG_WINE))
						.getDecorView();
				if (currentlable == 0) {
					logic.AnimaLeftIn(GrapeActivity.this, frontView);
				} else {
					logic.AnimaRightIn(GrapeActivity.this, frontView);
				}
				gv.addView(frontView);

				currentlable = 1;
			} else if (Const.NEWPRODUCT.equals(obj)
					|| Const.WINE_RED.equals(obj)) {// 新品，红酒
				if (currentlable == 2) {

					return;
				}
				logic.AnimaLeftOut(GrapeActivity.this, curView);
				gv.removeAllViews();
				View frontView = getLocalActivityManager()
						.startActivity(
								"no3",
								new Intent(GrapeActivity.this,
										HotSellActivity.class)
										.putExtra(
												"LABLE",
												Const.NEWPRODUCT.equals(obj) ? Const.NEWPRODUCT
														: Const.WINE_RED)
										.putExtra(Const.FLAG_WINE, FALG_WINE))
						.getDecorView();
				logic.AnimaLeftIn(GrapeActivity.this, frontView);
				gv.addView(frontView);
				currentlable = 2;
			}

		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}

	}

	class GrapeEcallBack implements ECallBack {

		public void OnCreate(Object obj) {
			if (Const.LEFT.equals(obj)) {
				finish();
			}
			if (Const.RIGHT.equals(obj)) {
				// loadData();

				final BaseActivity activity = new BaseActivity();
				activity.showProgressDialog(GrapeActivity.this,
						getString(R.string.loading));

				PostBean post = new PostBean();
				post.setCode(Const.GETCATEGORYFEATURES);
				post.setProductCategoryId(FALG_WINE);
				new Task(post, GrapeActivity.this, new ECallBack() {

					public void OnError(Object obj) {
						Toast.makeText(getApplicationContext(),
								R.string.errcode_net, 1000).show();
					}

					public void OnCreate(Object obj) {

						ResultObject result = (ResultObject) obj;
						String msg = result.getmMessage();
						JsonPrase prase = new JsonPrase();
						if (prase.getState(msg)) {
							ArrayList<CategoryFeature> categoryFeaturesList = prase
									.getCategoryFeatures(msg);
							logic.showFilterPop(GrapeActivity.this,
									grape_title, categoryFeaturesList,
									new ECallBack() {

										public void OnError(Object obj) {
										}

										public void OnCreate(Object obj) {
											Intent intent = new Intent();
											intent.setAction("FILTER");
											sendBroadcast(intent);

										}
									});
							activity.dismissProgressDialog();
						} else {
							Toast.makeText(getApplicationContext(),
									R.string.errcode_request, 1000).show();
						}

					}
				}).postHttp();
			}
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		BaseActivity.categoryFeatureList.clear();
	}

	private String getTitleName() {

		if (FALG_WINE.equals(Const.GRAPE_WINE))
			return getString(R.string.putaojiu);
		if (FALG_WINE.equals(Const.WHITE_SPIRIT))
			return getString(R.string.baijiu);
		if (FALG_WINE.equals(Const.BEER))
			return getString(R.string.pijiu);
		if (FALG_WINE.equals(Const.OLD_WINE))
			return getString(R.string.laojiu);
		return "";
	}

}

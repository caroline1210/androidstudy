package com.ltd.mos.util;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.CategoryFeature;
import com.ltd.mos.bean.FilterBean;
import com.ltd.mos.bean.LeisureBean;
import com.ltd.mos.bean.SearchBean;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.main.FilterListAdapter;

public class Logic {
	public static ArrayList<Activity> allActivity = new ArrayList<Activity>();// 用于保存运行过的activity
	/* 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载 */
	private static Logic instance = null;
	private LeisureBean currentLeisure;// 休闲中心
	private LeisureBean currentLightHouse;// 休闲中心

	/* 私有构造方法，防止被实例化 */
	private Logic() {

	}

	/**
	 * 从集合中读取activity
	 * 
	 * @param name
	 *            activity名称
	 * @return
	 */
	public static Activity getActivityByName(String name) {
		for (Activity ac : allActivity) {
			if (ac.getClass().getName().indexOf(name) >= 0) {
				return ac;
			}
		}
		return null;
	}

	/**
	 * 检测登陆状态
	 * 
	 * @return
	 */
	public static boolean getLoginState(Context context) {
		return SaveApplicationParam.getLandState(context);
	}

	/**
	 * 用户聊天图片路径
	 * 
	 * @param userId
	 * @param friendUserId
	 * @param name
	 * @return
	 */
	public static File getImagePath(String name) {
		File file1 = new File(Environment.getExternalStorageDirectory(),
				"/jiusiji/file/msg/image/");
		if (!file1.exists()) {
			try {
				file1.mkdirs();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		File file = new File(Environment.getExternalStorageDirectory(),
				"/jiusiji/file/msg/image/" + name);
		return file;
	}

	/**
	 * 改变登陆状态
	 * 
	 * @param context
	 */
	public static void changeLoginState(Context context, boolean state) {
		SaveApplicationParam.saveLandState(context, false);
	}

	public static Logic getInstance() {
		if (instance == null) {
			synchronized (Logic.class) {
				if (instance == null) {
					instance = new Logic();
				}
			}
		}
		return instance;
	}

	/**
	 * 是否为手机号码
	 * 
	 * @param num
	 * @return
	 */
	public static boolean ifPhoneNum(String num) {

		return num.matches("^1[3458]\\d{9}$");
	}

	/**
	 * 设置评价星星显示的个数
	 * 
	 * @param view
	 * @param shownum
	 */
	public void setStarsShow(View view, int shownum) {
		ImageView start1 = (ImageView) view.findViewById(R.id.star_01);
		ImageView start2 = (ImageView) view.findViewById(R.id.star_02);
		ImageView start3 = (ImageView) view.findViewById(R.id.star_03);
		ImageView start4 = (ImageView) view.findViewById(R.id.star_04);
		ImageView start5 = (ImageView) view.findViewById(R.id.star_05);

		switch (shownum) {
		case 0:
			start1.setImageResource(R.drawable.gray_star);
			start2.setImageResource(R.drawable.gray_star);
			start3.setImageResource(R.drawable.gray_star);
			start4.setImageResource(R.drawable.gray_star);
			start5.setImageResource(R.drawable.gray_star);
			break;
		case 1:
			start1.setImageResource(R.drawable.yellow_star);
			start2.setImageResource(R.drawable.gray_star);
			start3.setImageResource(R.drawable.gray_star);
			start4.setImageResource(R.drawable.gray_star);
			start5.setImageResource(R.drawable.gray_star);
			break;
		case 2:
			start1.setImageResource(R.drawable.yellow_star);
			start2.setImageResource(R.drawable.yellow_star);
			start3.setImageResource(R.drawable.gray_star);
			start4.setImageResource(R.drawable.gray_star);
			start5.setImageResource(R.drawable.gray_star);
			break;
		case 3:
			start1.setImageResource(R.drawable.yellow_star);
			start2.setImageResource(R.drawable.yellow_star);
			start3.setImageResource(R.drawable.yellow_star);
			start4.setImageResource(R.drawable.gray_star);
			start5.setImageResource(R.drawable.gray_star);
			break;
		case 4:
			start1.setImageResource(R.drawable.yellow_star);
			start2.setImageResource(R.drawable.yellow_star);
			start3.setImageResource(R.drawable.yellow_star);
			start4.setImageResource(R.drawable.yellow_star);
			start5.setImageResource(R.drawable.gray_star);
			break;
		case 5:
			start1.setImageResource(R.drawable.yellow_star);
			start2.setImageResource(R.drawable.yellow_star);
			start3.setImageResource(R.drawable.yellow_star);
			start4.setImageResource(R.drawable.yellow_star);
			start5.setImageResource(R.drawable.yellow_star);
			break;

		default:
			break;
		}
	}

	public LeisureBean getCurrentLightHouse() {
		if (currentLightHouse == null) {
			return new LeisureBean();
		}
		return currentLightHouse;
	}

	public void setCurrentLightHouse(LeisureBean currentLightHouse) {
		this.currentLightHouse = currentLightHouse;
	}

	public LeisureBean getCurrentLeisure() {
		if (currentLeisure == null) {
			return new LeisureBean();
		}
		return currentLeisure;
	}

	public void setCurrentLeisure(LeisureBean currentLeisure) {
		this.currentLeisure = currentLeisure;
	}

	public BaseActivity currentActivity;

	/**
	 * 防止NullPointException
	 * 
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj) {
		if (obj == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(obj);
		return sb.toString();
	}

	/**
	 * 初始化当前activity
	 * 
	 * @param activity
	 */
	public void initCurrentActivity(BaseActivity activity) {
		currentActivity = activity;
	}

	/**
	 * 初始化图片显示逻辑
	 * 
	 * @param imageView
	 */
	public void initImageView(View view) {
		LinearLayout.LayoutParams layoutParams = (LayoutParams) view
				.getLayoutParams();
		layoutParams.width = BaseActivity.SCREENWIDE;
		layoutParams.height = BaseActivity.SCREENWIDE * Const.PICTURE_HEIGHT
				/ Const.PICTURE_WIDE;
		view.setLayoutParams(layoutParams);
	}

	/**
	 * 初始化酒游戏背景显示逻辑
	 * 
	 * @param imageView
	 */
	public void initWineGameView(View view) {
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
				.getLayoutParams();
		layoutParams.width = (int) 300;
		layoutParams.height = (int) 192;
		view.setLayoutParams(layoutParams);
	}

	/**
	 * 初始化色子游戏背景显示逻辑
	 * 
	 * @param imageView
	 */
	public void initDiceGameView(Context context, View view) {
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
				.getLayoutParams();
		layoutParams.width = (int) (BaseActivity.SCREENWIDE - BaseActivity
				.dip2px(context, 30)) * 3 / 4;
		layoutParams.height = (int) ((BaseActivity.SCREENWIDE - BaseActivity
				.dip2px(context, 30)) * 3 / 4 * 253 / 281);
		view.setLayoutParams(layoutParams);
	}

	/**
	 * 初始化listView的高度
	 * 
	 * @param listView
	 * @param listSize
	 */
	public void initListView(ListView listView, int listSize, Context context) {
		LinearLayout.LayoutParams layoutParams = (LayoutParams) listView
				.getLayoutParams();
		layoutParams.width = BaseActivity.SCREENWIDE;
		layoutParams.height = BaseActivity.dip2px(context, 40) * listSize;
		listView.setLayoutParams(layoutParams);
	}

	/**
	 * 初始化ImageView的高度
	 * 
	 * @param listView
	 * @param listSize
	 */
	public void initErWeiMaView(ImageView imageView, Context context) {
		FrameLayout.LayoutParams layoutParams = (android.widget.FrameLayout.LayoutParams) imageView
				.getLayoutParams();
		layoutParams.width = BaseActivity.SCREENWIDE;
		layoutParams.height = (int) (BaseActivity.SCREENWIDE * 0.8);
		imageView.setLayoutParams(layoutParams);
	}

	/**
	 * 显示set
	 * 
	 * @param view
	 */
	public void showSet(View view, boolean show, boolean showemp,
			final ECallBack callBack) {
		ImageButton set = (ImageButton) view.findViewById(R.id.set);
		if (show)
			set.setVisibility(View.VISIBLE);
		else
			set.setVisibility(View.GONE);
		set.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.OnCreate(Const.SET);
			}
		});

		TextView text = (TextView) view.findViewById(R.id.empty);
		if (showemp)
			text.setVisibility(View.VISIBLE);
		else
			text.setVisibility(View.GONE);
		text.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				callBack.OnCreate("EMPTY");
			}
		});
	}

	/**
	 * 
	 * @param view
	 * @param mainTitle
	 *            title
	 * @param leftState
	 *            左button是否显示
	 * @param rightContent
	 *            右button 是否显示
	 */
	public void initHeadView(View view, String mainTitle, boolean leftState,
			String rightContent, final ECallBack callBack) {
		TextView mainText = (TextView) view.findViewById(R.id.main_text);
		mainText.setText(getString(mainTitle));
		LinearLayout left = (LinearLayout) view.findViewById(R.id.head_back);
		if (!leftState) {
			left.setVisibility(View.GONE);
		} else {
			left.setVisibility(View.VISIBLE);
		}
		left.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (callBack == null) {
					return;
				}
				callBack.OnCreate(Const.LEFT);
			}
		});
		Button right = (Button) view.findViewById(R.id.right);
		if (Logic.getString(rightContent).length() == 0) {
			right.setVisibility(View.GONE);
		} else {
			right.setVisibility(View.VISIBLE);
			right.setText(rightContent);
		}
		right.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (callBack == null) {
					return;
				}
				callBack.OnCreate(Const.RIGHT);
			}
		});
		TextView textRight = (TextView) view.findViewById(R.id.empty);
		textRight.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (callBack == null) {
					return;
				}
				callBack.OnCreate(Const.CLEAR);
			}
		});
	}

	/**
	 * 葡萄酒界面 标题 控制
	 * 
	 * @param view
	 * @param title
	 * @param callback
	 */
	public void initGrapeTitle(View view, String title, boolean onlyShowLeft,
			boolean showShare, final ECallBack callBack) {
		LinearLayout title_return = (LinearLayout) view
				.findViewById(R.id.title_return);
		title_return.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (callBack == null) {
					return;
				}
				callBack.OnCreate(Const.LEFT);

			}
		});
		TextView head_title = (TextView) view.findViewById(R.id.head_title);
		head_title.setText(title);

		if (onlyShowLeft) {
			return;
		}

		LinearLayout filter_ll = (LinearLayout) view
				.findViewById(R.id.filter_ll);
		if (showShare)
			filter_ll.setVisibility(View.GONE);
		else
			filter_ll.setVisibility(View.VISIBLE);

		filter_ll.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (callBack == null) {
					return;
				}
				callBack.OnCreate(Const.RIGHT);

			}
		});

		LinearLayout share_collect = (LinearLayout) view
				.findViewById(R.id.share_collect);
		if (showShare)
			share_collect.setVisibility(View.VISIBLE);
		else {
			share_collect.setVisibility(View.GONE);
			return;
		}

		LinearLayout head_share = (LinearLayout) view
				.findViewById(R.id.head_share);
		head_share.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (callBack == null) {
					return;
				}
				callBack.OnCreate(Const.SHARE);

			}
		});

		LinearLayout head_collect = (LinearLayout) view
				.findViewById(R.id.head_collect);
		head_collect.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (callBack == null) {
					return;
				}
				callBack.OnCreate(Const.COLLECT);

			}
		});

	}

	/**
	 * 精确相加
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public double doubleAdd(String a, String b) {
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);
		return b1.add(b2).doubleValue();
	}

	/**
	 * 精确相乘
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public double doubleMultiply(String a, String b) {
		BigDecimal b1 = new BigDecimal(a);
		BigDecimal b2 = new BigDecimal(b);
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 葡萄酒界面 选项卡 控制
	 * 
	 * @param view
	 * @param callBack
	 */
	private RelativeLayout grape_hotsell, grape_price, grape_newproduct;
	private TextView grape_hotsell_text, grape_price_text,
			grape_newproduct_text, line_left, line_mid, line_right;

	@SuppressWarnings("unused")
	public void initGrapeLable(final Context context, View view,
			final String wineType, final ECallBack callBack) {

		line_left = (TextView) view.findViewById(R.id.line_left);
		line_mid = (TextView) view.findViewById(R.id.line_mid);
		line_right = (TextView) view.findViewById(R.id.line_right);

		grape_hotsell_text = (TextView) view
				.findViewById(R.id.grape_hotsell_text);
		grape_price_text = (TextView) view.findViewById(R.id.grape_price_text);
		grape_newproduct_text = (TextView) view
				.findViewById(R.id.grape_newproduct_text);

		if (wineType.equals(Const.OLD_WINE)) {// 老酒界面
			grape_hotsell_text.setText("全部");
			grape_price_text.setText("白酒");
			grape_newproduct_text.setText("红酒");
		}

		grape_hotsell = (RelativeLayout) view.findViewById(R.id.grape_hotsell);
		grape_price = (RelativeLayout) view.findViewById(R.id.grape_price);
		grape_newproduct = (RelativeLayout) view
				.findViewById(R.id.grape_newproduct);

		setCheckItem(context, grape_hotsell_text, line_left);

		grape_hotsell.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				setCheckItem(context, grape_hotsell_text, line_left);
				if (callBack == null) {
					return;
				}
				if (wineType.equals(Const.OLD_WINE)) {
					callBack.OnCreate(Const.ALL);
				} else {
					callBack.OnCreate(Const.HOTSELL);
				}

			}
		});

		grape_price.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				setCheckItem(context, grape_price_text, line_mid);
				if (callBack == null) {
					return;
				}
				if (wineType.equals(Const.OLD_WINE)) {
					callBack.OnCreate(Const.WHITE_SPIRIT);
				} else {
					callBack.OnCreate(Const.PRICE);
				}
			}
		});

		grape_newproduct.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				setCheckItem(context, grape_newproduct_text, line_right);
				if (callBack == null) {
					return;
				}
				if (wineType.equals(Const.OLD_WINE)) {
					callBack.OnCreate(Const.WINE_RED);
				} else {
					callBack.OnCreate(Const.NEWPRODUCT);
				}
			}
		});

	}

	public String getAddress(String address) {
		String str;
		if (address.contains("区")) {
			int index = address.indexOf("区");
			str = address.substring(index + 1);
			if (str.length() <= 2) {
				return address;
			}
		} else if (address.contains("市")) {
			int index = address.indexOf("市");
			str = address.substring(index + 1);
		} else if (address.contains("省")) {
			int index = address.indexOf("省");
			str = address.substring(index + 1);
		} else {
			str = address;
		}
		if (Logic.getString(str).length() == 0) {
			return address;
		} else if (Logic.getString(str).length() > 4) {
			if (str.contains("镇")) {
				int index1 = str.indexOf("镇");
				return getFourStr(str.substring(0, index1 + 1));
			} else if (str.contains("村")) {
				int index1 = str.indexOf("村");
				return getFourStr(str.substring(0, index1 + 1));
			} else if (str.contains("街")) {
				int index1 = str.indexOf("街");
				return getFourStr(str.substring(0, index1 + 1));
			} else if (str.contains("路")) {
				int index1 = str.indexOf("路");
				return getFourStr(str.substring(0, index1 + 1));
			} else if (str.contains("处")) {
				int index1 = str.indexOf("处");
				return getFourStr(str.substring(0, index1 + 1));
			} else if (str.contains("村")) {
				int index1 = str.indexOf("村");
				return getFourStr(str.substring(0, index1 + 1));
			}
		}
		return str;
	}

	private String getFourStr(String str) {
		if (Logic.getString(str).length() > 4) {
			return str.substring(0, 4) + "...";
		}
		return str;
	}

	private void setCheckItem(Context context, TextView text, View view) {
		grape_hotsell_text.setTextColor(context.getResources().getColor(
				R.color.black));
		grape_price_text.setTextColor(context.getResources().getColor(
				R.color.black));
		grape_newproduct_text.setTextColor(context.getResources().getColor(
				R.color.black));

		line_left.setBackgroundColor(context.getResources().getColor(
				R.color.white));
		line_mid.setBackgroundColor(context.getResources().getColor(
				R.color.white));
		line_right.setBackgroundColor(context.getResources().getColor(
				R.color.white));

		text.setTextColor(context.getResources().getColor(R.color.blue));

		view.setBackgroundColor(context.getResources().getColor(R.color.blue));

	}

	private String path = "/data/data/com.ltd.mos/files/searchhistory.db";

	/**
	 * 获取搜索酒默认显示数据
	 */
	public ArrayList<SearchBean> getSearchHistory(Context context) {

		ArrayList<SearchBean> list = new ArrayList<SearchBean>();

		SQLiteDatabase db = SQLiteDatabase.openDatabase(path, null,
				SQLiteDatabase.NO_LOCALIZED_COLLATORS);
		Cursor cursor = null;
		try {
			cursor = db.query(Const.SEARCHHISTORY, null, null, null, null,
					null, null);
			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				list.add(getSearchData(cursor));
				cursor.moveToNext();
			}
			cursor.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (cursor != null) {
				cursor.close();
			}
		}
		return list;
	}

	private SearchBean getSearchData(Cursor cursor) {
		SearchBean values = new SearchBean();
		int colIndex = cursor.getColumnIndex("name");
		if (colIndex != -1)
			values.setName(cursor.getString(colIndex));
		return values;

	}

	// animation 左进
	public void AnimaLeftIn(Context context, View view) {
		Animation animation_out = AnimationUtils.loadAnimation(context,
				R.anim.push_left_in);
		view.startAnimation(animation_out);
	}

	// animation 左出
	public void AnimaLeftOut(Context context, View view) {
		Animation animation_out = AnimationUtils.loadAnimation(context,
				R.anim.push_left_out);
		view.startAnimation(animation_out);
	}

	// animation 右进
	public void AnimaRightIn(Context context, View view) {
		Animation animation_out = AnimationUtils.loadAnimation(context,
				R.anim.push_right_in);
		view.startAnimation(animation_out);
	}

	// animation 左出
	public void AnimaRightOut(Context context, View view) {
		Animation animation_out = AnimationUtils.loadAnimation(context,
				R.anim.push_right_out);
		view.startAnimation(animation_out);
	}

	/**
	 * 显示筛选pop
	 * 
	 * @param context
	 */
	private PopupWindow searchPop;
	private FilterListAdapter filterListAdapter;

	public void showFilterPop(final Context context, View parent,
			ArrayList<CategoryFeature> list, final ECallBack callBack) {
		if (searchPop != null && searchPop.isShowing()) {
			return;
		}
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View filtView = layoutInflater.inflate(R.layout.pop_filter, null);

		ListView pop_listview = (ListView) filtView
				.findViewById(R.id.pop_listview);
		filterListAdapter = new FilterListAdapter(context, list);
		pop_listview.setAdapter(filterListAdapter);

		Button cancel = (Button) filtView.findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dismisPop();
			}
		});
		Button confirm = (Button) filtView.findViewById(R.id.confirm);
		confirm.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (BaseActivity.categoryFeatureList != null
						&& BaseActivity.categoryFeatureList.size() > 0) {
					callBack.OnCreate("confirm");
				}
				dismisPop();
			}
		});

		searchPop = new PopupWindow(filtView, LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT);
		searchPop.setFocusable(true);
		searchPop.setTouchable(true);
		searchPop.setOutsideTouchable(true);
		searchPop.setBackgroundDrawable(new BitmapDrawable());
		try {
			searchPop.showAsDropDown(parent, 0, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 隐藏pop
	 */
	public void dismisPop() {
		if (searchPop != null && searchPop.isShowing()) {
			searchPop.dismiss();
			searchPop = null;
		}
	}

	// /**
	// * 显示筛选dialog
	// *
	// * xuwu
	// *
	// * @param context
	// */
	// private BaseDialog filterDialog;
	// private FilterListAdapter adapter;
	// private ExpandableListView listview;
	//
	// public void showFilterDialog(final Context context,
	// ArrayList<FilterBean> arrayList) {
	// filterDialog = new BaseDialog(context, R.style.dialog);
	// View view = LinearLayout.inflate(context, R.layout.dialog_filter, null);
	// LinearLayout filter_bg = (LinearLayout) view
	// .findViewById(R.id.filter_bg);
	// listview = (ExpandableListView) view.findViewById(R.id.filter_exlv);
	// listview.setGroupIndicator(null);
	// adapter = new FilterListAdapter(context, arrayList);
	// listview.setAdapter(adapter);
	//
	// listview.expandGroup(0);
	//
	// listview.setOnGroupExpandListener(new OnGroupExpandListener() {
	// public void onGroupExpand(int groupPosition) {
	// for (int i = 0; i < adapter.getGroupCount(); i++) {
	// if (i != groupPosition
	// && listview.isGroupExpanded(groupPosition)) {
	// listview.collapseGroup(i);
	// }
	// }
	// }
	// });
	//
	// LayoutParams params = (LayoutParams) filter_bg.getLayoutParams();
	// params.width = BaseActivity.SCREENWIDE * 330 / 480;
	// params.height = BaseActivity.SCREENHEIGHT * 3 / 5;
	// filter_bg.setLayoutParams(params);
	//
	// Window win = filterDialog.getWindow();
	// WindowManager.LayoutParams lParams = win.getAttributes();
	// lParams.gravity = Gravity.RIGHT | Gravity.TOP;
	// lParams.x = BaseActivity.dip2px(context, 10);// 设置x坐标
	// lParams.y = BaseActivity.dip2px(context, 55);// 设置y坐标
	// win.setAttributes(lParams);
	//
	// filterDialog.setContentView(view);
	// filterDialog.setCanceledOnTouchOutside(true);
	// filterDialog.show();
	// }
}

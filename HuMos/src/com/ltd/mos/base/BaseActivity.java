package com.ltd.mos.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.ltd.mos.R;
import com.ltd.mos.bean.Address;
import com.ltd.mos.bean.CategoryFeature;
import com.ltd.mos.bean.GeoInfo;
import com.ltd.mos.bean.OrderItemList;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.bean.ShopCarBean;
import com.ltd.mos.bean.TailorBean;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;
import com.ltd.mos.view.BaseDialog;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.mm.sdk.openapi.IWXAPI;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.bean.Address;
import com.ltd.mos.bean.GeoInfo;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.bean.ShopCarBean;
import com.ltd.mos.bean.TailorBean;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.view.BaseDialog;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.tencent.mm.sdk.openapi.IWXAPI;

/**
 * 
 * @author xuwu
 * 
 */
public class BaseActivity extends Activity {

	// 屏幕宽
	public static int SCREENWIDE;
	// 屏幕高
	public static int SCREENHEIGHT;

	public static int DIPWIDE;
	public static float currentLat;
	public static float currentLng;
	public static ExecutorService FULL_TASK_EXECUTOR;

	public static boolean FRASHSHOPCAT = true;
	public static boolean FRASHADDRESS = true;
	public static ArrayList<Activity> allActivity = new ArrayList<Activity>();// 用于保存运行过的activity

	public static IWXAPI wxApi;
	public static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;// 分享到朋友圈支持的微信最低版本
	public static ArrayList<OrderItemList> dgOrderList = new ArrayList<OrderItemList>();
	static {
		FULL_TASK_EXECUTOR = (ExecutorService) Executors.newCachedThreadPool();
	};
	public static ImageLoader imageLoader = ImageLoader.getInstance();

	public static ArrayList<ShopCarBean> shopList = new ArrayList<ShopCarBean>();

	public static ArrayList<CategoryFeature> categoryFeatureList = new ArrayList<CategoryFeature>();

	// 添加订单商品到集合
	public static void setShopList(ShopCarBean shop) {
		if (shopList == null)
			shopList = new ArrayList<ShopCarBean>();
		shopList.add(shop);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

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
	 * start Activity
	 * 
	 * @param cls
	 */
	public void startActivity(Class<?> cls) {

		startActivity(new Intent(this, cls));
	}

	/**
	 * 获取屏幕宽高
	 * 
	 * @param activity
	 */
	public void getScreenInfo() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		SCREENHEIGHT = metrics.heightPixels;
		SCREENWIDE = metrics.widthPixels;
		DIPWIDE = px2dip(this, SCREENWIDE);
	}

	/**
	 * 设置酒详情 酒图片的宽高
	 * 
	 * @param view
	 */
	public void setWineDetailImageWH(View view) {

		if (SCREENWIDE == 0 || SCREENHEIGHT == 0) {
			getScreenInfo();
		}
		LayoutParams layoutParams = view.getLayoutParams();
		layoutParams.width = SCREENWIDE;
		layoutParams.height = 307 * SCREENWIDE / 480;
		view.setLayoutParams(layoutParams);

	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, double dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 双击退出
	 */
	private static Boolean isExit = false;

	public void exitBy2Click() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // 准备退出
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // 取消退出
				}
			}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

		} else {
			finish();
			System.exit(0);
		}
	}

	/**
	 * 生成数据
	 * 
	 * @param content
	 * @param hint
	 * @param title
	 * @return
	 */
	private ArrayList<TailorBean> getList(String[] content, String[] hint,
			String[] title) {
		ArrayList<TailorBean> list = new ArrayList<TailorBean>();
		for (int i = 0; i < title.length; i++) {
			TailorBean tailorBean = new TailorBean();
			tailorBean.setTitle(title[i]);
			if (hint != null) {
				tailorBean.setContentHint(hint[i]);
			}
			if (content != null) {
				tailorBean.setContent(content[i]);
			}
			list.add(tailorBean);

		}
		return list;

	}

	private BaseDialog areaDialog;

	/**
	 * 设置收货地址区域对话框
	 * 
	 * @author xuwu
	 * @param callBack
	 */

	private GeoInfo geoInfo_pro;// 省
	private GeoInfo geoInfo_city;// 市
	private GeoInfo geoInfo_cuntry;// 区

	public void showAreaDialog(final ECallBack callBack) {
		areaDialog = new BaseDialog(this, R.style.dialog);
		View view = LinearLayout.inflate(this, R.layout.select_area, null);
		final TextView area_omit = (TextView) view.findViewById(R.id.area_omit);
		final TextView area_city = (TextView) view.findViewById(R.id.area_city);
		final TextView area_country = (TextView) view
				.findViewById(R.id.area_country);
		area_omit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				showAreaList(AREATYPE1, "CHN", new ECallBack() {

					public void OnError(Object obj) {
					}

					public void OnCreate(Object obj) {

						if (geoInfo_pro != null) {
							String temp = geoInfo_pro.getGeoId();
							geoInfo_pro = (GeoInfo) obj;
							if (!temp.equals(geoInfo_pro.getGeoId())) {
								area_city.setText("");
								area_country.setText("");
								if (geoInfo_city != null)
									geoInfo_city = null;
								if (geoInfo_cuntry != null)
									geoInfo_cuntry = null;

							}
						}

						geoInfo_pro = (GeoInfo) obj;
						area_omit.setText(geoInfo_pro.getGeoName());

					}
				});
			}
		});

		area_city.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (geoInfo_pro != null)
					showAreaList(AREATYPE2, geoInfo_pro.getGeoId(),
							new ECallBack() {

								public void OnError(Object obj) {
								}

								public void OnCreate(Object obj) {

									if (geoInfo_city != null) {
										String temp = geoInfo_city.getGeoId();
										geoInfo_city = (GeoInfo) obj;
										if (!temp.equals(geoInfo_city
												.getGeoId())) {
											area_country.setText("");
											if (geoInfo_cuntry != null)
												geoInfo_cuntry = null;

										}
									}

									geoInfo_city = (GeoInfo) obj;
									area_city.setText(geoInfo_city.getGeoName());

								}
							});

			}
		});

		area_country.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (geoInfo_city != null)
					showAreaList(AREATYPE3, geoInfo_city.getGeoId(),
							new ECallBack() {

								public void OnError(Object obj) {
								}

								public void OnCreate(Object obj) {
									geoInfo_cuntry = (GeoInfo) obj;
									area_country.setText(geoInfo_cuntry
											.getGeoName());

								}
							});

			}
		});
		Button area_sure = (Button) view.findViewById(R.id.area_sure);
		area_sure.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Address address = new Address();
				if (geoInfo_pro == null) {
					Toast.makeText(BaseActivity.this, "请选择省份", 1000).show();
					return;
				} else if (geoInfo_city == null) {
					Toast.makeText(BaseActivity.this, "请选择城市", 1000).show();
					return;
				} else if (geoInfo_cuntry == null) {
					Toast.makeText(BaseActivity.this, "请选择区域", 1000).show();
					return;
				}
				address.setStateProvinceGeoId(geoInfo_pro.getGeoId());
				address.setProvince(geoInfo_pro.getGeoName());
				address.setCityGeoId(geoInfo_city.getGeoId());
				address.setCityName(geoInfo_city.getGeoName());

				address.setCountyGeoId(geoInfo_cuntry.getGeoId());
				address.setCountyName(geoInfo_cuntry.getGeoName());
				callBack.OnCreate(address);
				dismissDialog();

			}
		});
		Button area_cancel = (Button) view.findViewById(R.id.area_cancel);
		area_cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				dismissDialog();

			}
		});

		areaDialog.setContentView(view);
		areaDialog.setCanceledOnTouchOutside(false);
		areaDialog.show();
	}

	public static final int AREATYPE1 = 1; // 省份
	public static final int AREATYPE2 = 2; // 城市
	public static final int AREATYPE3 = 3; // 区域

	private BaseDialog areaListDialog;
	ArrayList<GeoInfo> geoInfos;

	public void showAreaList(int areaType, String geoId,
			final ECallBack callBack) {
		areaListDialog = new BaseDialog(this, R.style.dialog);
		final View view = LinearLayout.inflate(this, R.layout.dialog_arealist,
				null);
		TextView title = (TextView) view.findViewById(R.id.arealist_title);
		if (areaType == 1) {
			title.setText("选择省份");
		} else if (areaType == 2) {
			title.setText("选择城市");
		} else if (areaType == 3) {
			title.setText("选择区域");
		}

		final ListView arealist = (ListView) view.findViewById(R.id.arealist);

		Register register = SaveApplicationParam.getRegister(this);
		PostBean bean = new PostBean();
		bean.setCode(Const.GETGEOCHILDREN);
		bean.setPhoneNumber(register.getPhone());
		bean.setPassword(register.getPwd());
		bean.setGeoId(geoId);
		new Task(bean, this, new ECallBack() {

			public void OnError(Object obj) {
				Toast.makeText(BaseActivity.this, R.string.errcode_net, 1000)
						.show();
			}

			public void OnCreate(Object obj) {
				ResultObject result = (ResultObject) obj;
				String msg = result.getmMessage();
				JsonPrase jsonPrase = new JsonPrase();
				if (jsonPrase.getState(msg)) {
					geoInfos = jsonPrase.getGeoId(msg);
					AreaAdapter areaListAdapter = new AreaAdapter(geoInfos);
					arealist.setAdapter(areaListAdapter);
					areaListDialog.setContentView(view);
					areaListDialog.setCanceledOnTouchOutside(false);
					areaListDialog.show();
				} else {
					Toast.makeText(BaseActivity.this, R.string.errcode_request,
							1000).show();
				}
			}
		}).postHttp();

		arealist.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				GeoInfo geoInfo = geoInfos.get(pos);
				callBack.OnCreate(geoInfo);
				dismissAreaList();
			}
		});

	}

	class AreaAdapter extends BaseAdapter {

		private ArrayList<GeoInfo> geoInfos;

		public AreaAdapter(ArrayList<GeoInfo> geoInfos) {
			this.geoInfos = geoInfos;
		}

		public int getCount() {

			return geoInfos.size();
		}

		public Object getItem(int position) {

			return null;
		}

		public long getItemId(int position) {

			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LinearLayout.inflate(BaseActivity.this,
						R.layout.item_arealist, null);
				holder.text = (TextView) convertView
						.findViewById(R.id.item_area_text);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.text.setText(geoInfos.get(position).getGeoName());

			return convertView;
		}

		class ViewHolder {
			TextView text;
		}

	}

	private ProgressDialog progressDialog;

	/**
	 * 进度条对话框
	 * 
	 * @author xuwu
	 * @param context
	 * @param content
	 */
	public void showProgressDialog(Context context, String content) {
		progressDialog = new ProgressDialog(context);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setMessage(content);
		progressDialog.show();

	}

	/**
	 * 隐藏进度条对话框
	 * 
	 * @author xuwu
	 */
	public void dismissProgressDialog() {
		if (progressDialog != null && progressDialog.isShowing())
			progressDialog.dismiss();
	}

	private static BaseDialog takePhotoDialog;

	/**
	 * 拍照
	 * 
	 * @param callBack
	 */
	public void showTakePhotoDialog(final ECallBack callBack) {
		takePhotoDialog = new BaseDialog(this, R.style.dialog);
		View view = LinearLayout.inflate(this, R.layout.take_photo, null);
		ListView listView = (ListView) view.findViewById(R.id.listView);
		BaseAdapter adapter = new BaseDialogAdapter(this);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (takePhotoDialog != null && takePhotoDialog.isShowing()) {
					takePhotoDialog.dismiss();
					takePhotoDialog = null;
				}

				switch (arg2) {
				case 0:
					callBack.OnCreate(Const.PICTURE);
					break;
				case 1:
					callBack.OnCreate(Const.TAKEPHOTO);
					break;
				case 2:
					callBack.OnCreate(Const.CANCLE);
					break;
				default:
					break;

				}
			}
		});
		takePhotoDialog.setContentView(view);
		takePhotoDialog.setCanceledOnTouchOutside(true);
		takePhotoDialog.show();
	}

	private BaseDialog shareDialog;

	/**
	 * 分享对话框
	 * 
	 * @author xuwu
	 * @param context
	 */
	public void showShareDialog(final ECallBack callBack) {
		shareDialog = new BaseDialog(this, R.style.dialog);
		View view = LinearLayout.inflate(this, R.layout.dialog_share, null);
		ImageView share_wxfriends = (ImageView) view
				.findViewById(R.id.share_wxfriends);

		share_wxfriends.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (callBack != null) {
					callBack.OnCreate(Const.WXFRIEND);// 好友
				}

			}
		});
		ImageView share_wxcircle = (ImageView) view
				.findViewById(R.id.share_wxcircle);
		share_wxcircle.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (callBack != null) {
					callBack.OnCreate(Const.WXCIRCLE);// 朋友圈
				}
			}
		});

		shareDialog.setContentView(view);
		shareDialog.setCanceledOnTouchOutside(true);
		shareDialog.show();
	}

	/**
	 * final Activity activity ：调用该方法的Activity实例 long milliseconds ：震动的时长，单位是毫秒
	 * long[] pattern ：自定义震动模式 。数组中数字的含义依次是[静止时长，震动时长，静止时长，震动时长。。。]时长的单位是毫秒
	 * boolean isRepeat ： 是否反复震动，如果是true，反复震动，如果是false，只震动一次
	 */

	public static void Vibrate(final Activity activity, long milliseconds) {
		Vibrator vib = (Vibrator) activity
				.getSystemService(Service.VIBRATOR_SERVICE);
		vib.vibrate(milliseconds);
	}

	public static void Vibrate(final Activity activity, long[] pattern,
			boolean isRepeat) {
		Vibrator vib = (Vibrator) activity
				.getSystemService(Service.VIBRATOR_SERVICE);
		vib.vibrate(pattern, isRepeat ? 1 : -1);
	}

	public static PopupWindow unloginPop;

	/**
	 * recordPopWindow
	 * 
	 * @param context
	 */
	public void showMainPopWindow(View parentView, final ECallBack back) {
		if (unloginPop != null && unloginPop.isShowing()) {
			return;
		}
		View view = RelativeLayout.inflate(BaseActivity.this,
				R.layout.pop_login, null);
		TextView register = (TextView) view.findViewById(R.id.register);
		SpannableStringBuilder builder = new SpannableStringBuilder(register
				.getText().toString());

		// ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
		ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.RED);
		builder.setSpan(redSpan, 3, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		register.setText(builder);
		Button jianchi = (Button) view.findViewById(R.id.login);
		jianchi.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (unloginPop != null && unloginPop.isShowing()) {
					unloginPop.dismiss();
					unloginPop = null;
				}
				back.OnCreate("");
			}
		});
		unloginPop = new PopupWindow(view, dip2px(this, 120), dip2px(this, 95));
		unloginPop.showAsDropDown(parentView, 0, 0);
		view.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (unloginPop != null && unloginPop.isShowing()) {
					unloginPop.dismiss();
					unloginPop = null;
				}
				return false;
			}
		});
		// 重写onKeyListener
		view.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					if (unloginPop != null && unloginPop.isShowing()) {
						unloginPop.dismiss();
						unloginPop = null;
					}
				}
				return false;
			}
		});

	}

	/**
	 * 隐藏弹出框
	 * 
	 * @author xuwu
	 */
	public void dismissDialog() {
		if (shareDialog != null && shareDialog.isShowing()) {
			shareDialog.dismiss();
			shareDialog = null;
		}

		if (areaDialog != null && areaDialog.isShowing()) {
			areaDialog.dismiss();
			areaDialog = null;
		}
	}

	public void dismissAreaList() {

		if (areaListDialog != null && areaListDialog.isShowing()) {
			areaListDialog.dismiss();
			areaListDialog = null;
		}
	}

	BaseDialog pd;

	/**
	 * 转圈 注册
	 */
	public void startProgressLoading(String content) {
		if (pd != null && pd.isShowing()) {// 快速操作 不重复显示
			return;
		}
		pd = new BaseDialog(this, R.style.MyDialog);
		View view = LinearLayout.inflate(this, R.layout.progress_loading, null);
		TextView text = (TextView) view.findViewById(R.id.autoregist);
		text.setText(content);
		pd.setCanceledOnTouchOutside(false);
		pd.setContentView(view);
		pd.show();
	}

	public void stopProgressLoading() {
		if (pd != null && pd.isShowing()) {
			pd.dismiss();
			pd = null;
		}
	}
}

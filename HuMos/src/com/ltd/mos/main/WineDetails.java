package com.ltd.mos.main;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.FeaturesBean;
import com.ltd.mos.bean.GeoInfo;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.bean.ShopCarBean;
import com.ltd.mos.bean.WineInfo;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.image.AsyncImageLoader;
import com.ltd.mos.login.LoginActivity;
import com.ltd.mos.shopcar.OrderActivity;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;

/**
 * 酒详情
 * 
 * @author xuwu
 * 
 */
public class WineDetails extends BaseActivity implements OnClickListener {

	private View view;
	private Logic logic;
	private ImageView image_title, image_share, image_collect;
	private ListView product_listview;
	private TextView details_marketprice, details_price, details_winename,
			details_wineevent, load_text, product_name;
	private RelativeLayout ll_evaluate;
	private Button details_joingwc, details_order;
	private WineInfo wineInfo = new WineInfo();
	private ScrollView detail_scroll;
	private ArrayList<FeaturesBean> featuresList;
	private WebView details_longdes;
	private LinearLayout layout_load;
	private String productId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.winedetails);
		wineInfo = (WineInfo) this.getIntent().getSerializableExtra(
				Const.WINEINFO);
		productId = Logic.getString(this.getIntent()
				.getStringExtra("PRODUCTID"));

		findviewid();

		if (productId.length() > 0) {// 从购物车跳转进来

			PostBean postBean = new PostBean();
			postBean.setCode(Const.FINDSINGLEPRODUCT);
			postBean.setProductId(productId);

			new Task(postBean, WineDetails.this, new CheckSingleProduct())
					.postHttp();

			layout_load.setVisibility(View.VISIBLE);

		} else {

			initProductsData(wineInfo);
		}

		image_title.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startActivity(new Intent(WineDetails.this,
						ImageShowActivity.class).putExtra("IMAGEURL",
						detailImageUrl));
			}
		});

	}

	private String detailImageUrl;

	private void initProductsData(WineInfo wine) {

		featuresList = wine.getFeaturesList();

		details_marketprice.setText(Logic.getString(wine.getMarketPrice()));
		details_price.setText(Logic.getString(wine.getWinePrice()));
		details_winename.setText(Logic.getString(wine.getWineDese()));
		details_wineevent.setText(Logic.getString(wine.getWineevent()));
		details_longdes.loadDataWithBaseURL(null,
				Logic.getString(wine.getWineLoneDese()), "text/html", "utf-8",
				null);
		product_name.setText(Logic.getString(wine.getWineDese()));
		AsyncImageLoader.initOption();
		AsyncImageLoader.displayImage(Logic.getString(wine.getLargeImageUrl()),
				image_title);

		detailImageUrl = Logic.getString(wine.getDetailImageUrl());
		// // 根据商品信息条目的个数设置ListView的高度

		detail_scroll.smoothScrollTo(0, 55);

		if (featuresList != null && featuresList.size() > 0) {
			final int lines = featuresList.size();
			final int height = dip2px(this, lines * 35);
			LayoutParams params = product_listview.getLayoutParams();
			params.height = height;
			product_listview.setLayoutParams(params);
		}
		product_listview.setFocusable(false);

		// 商品信息适配器
		product_listview.setAdapter(new ProductAdapter());
	}

	private void findviewid() {
		logic = Logic.getInstance();
		view = findViewById(R.id.view);
		layout_load = (LinearLayout) findViewById(R.id.layout_load);
		load_text = (TextView) findViewById(R.id.load_text);
		detail_scroll = (ScrollView) findViewById(R.id.detail_scroll);
		logic.initGrapeTitle(view, getString(R.string.details), false, true,
				new EcallBack());
		image_share = (ImageView) view.findViewById(R.id.image_share);
		image_collect = (ImageView) view.findViewById(R.id.image_collect);
		image_title = (ImageView) findViewById(R.id.image_title);
		details_marketprice = (TextView) findViewById(R.id.details_marketprice);
		details_price = (TextView) findViewById(R.id.details_price);
		details_winename = (TextView) findViewById(R.id.details_winename);
		details_wineevent = (TextView) findViewById(R.id.details_wineevent);
		details_longdes = (WebView) findViewById(R.id.details_longdes);
		ll_evaluate = (RelativeLayout) findViewById(R.id.ll_evaluate);
		details_joingwc = (Button) findViewById(R.id.details_joingwc);
		details_order = (Button) findViewById(R.id.details_order);
		product_name = (TextView) findViewById(R.id.product_name);
		// 字体加斜线
		details_marketprice.getPaint().setFlags(
				Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

		product_listview = (ListView) findViewById(R.id.product_listview);

		setWineDetailImageWH(image_title);// 设置标题图片的大小

		ll_evaluate.setOnClickListener(this);
		details_joingwc.setOnClickListener(this);
		details_order.setOnClickListener(this);
	}

	class EcallBack implements ECallBack {

		public void OnCreate(Object obj) {
			if (Const.SHARE.equals(obj)) {
				showShareDialog(new ShareCallBack());
			} else if (Const.LEFT.equals(obj)) {
				finish();
			} else {
				Toast.makeText(getApplicationContext(), "收藏", 1000).show();
			}
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}

	}

	class ShareCallBack implements ECallBack {

		public void OnCreate(Object obj) {

			if (Logic.getString((String) obj).equals(Const.WXFRIEND)) {
				onClickShare(0);
			} else if (Logic.getString(obj).equals(Const.WXCIRCLE)) {

				int wxSdkVersion = wxApi.getWXAppSupportAPI();
				if (wxSdkVersion >= TIMELINE_SUPPORTED_VERSION) {
					onClickShare(1);

				} else {
					Toast.makeText(
							WineDetails.this,
							"wxSdkVersion = "
									+ Integer.toHexString(wxSdkVersion)
									+ "\ntimeline not supported",
							Toast.LENGTH_LONG).show();
				}

			}

		}

		public void OnError(Object obj) {
		}

	}

	// 对接微信 分享
	public void onClickShare(int flag) {
		try {
			WXWebpageObject webpage = new WXWebpageObject();
			webpage.webpageUrl = "http://www.baidu.com";
			WXMediaMessage msg = new WXMediaMessage(webpage);
			msg.title = "分享个宝贝给你";
			msg.description = Logic.getString(wineInfo.getWineDese()) + "\n"
					+ Logic.getString(wineInfo.getWineevent());
			Bitmap thumb = BitmapFactory.decodeResource(getResources(),
					R.drawable.ceshi);
			msg.setThumbImage(thumb);
			SendMessageToWX.Req req = new SendMessageToWX.Req();
			req.transaction = String.valueOf(System.currentTimeMillis());
			req.message = msg;
			req.scene = (flag == 0 ? SendMessageToWX.Req.WXSceneSession
					: SendMessageToWX.Req.WXSceneTimeline);
			wxApi.sendReq(req);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dismissDialog();
	}

	// 检索单个酒品信息
	class CheckSingleProduct implements ECallBack {

		public void OnCreate(Object obj) {
			ResultObject result = (ResultObject) obj;
			String message = result.getmMessage();
			JsonPrase prase = new JsonPrase();

			if (prase.getState(message)) {
				ArrayList<WineInfo> products = prase.findProduct(message);
				if (products != null && products.size() > 0) {
					wineInfo = products.get(0);
					initProductsData(wineInfo);
					layout_load.setVisibility(View.GONE);
				}

			} else {
				load_text.setText("获取商品信息失败");
			}

		}

		public void OnError(Object obj) {
			load_text.setText("获取商品信息失败");
		}

	}

	class ProductAdapter extends BaseAdapter {

		public int getCount() {
			// TODO Auto-generated method stub
			return featuresList.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			convertView = LinearLayout.inflate(WineDetails.this,
					R.layout.item_productdetails, null);

			TextView text = (TextView) convertView
					.findViewById(R.id.item_detail_text);
			TextView title = (TextView) convertView
					.findViewById(R.id.item_detail_title);
			text.setText(Logic.getString(featuresList.get(position)
					.getProductFeatureDescription()));
			title.setText(Logic.getString(featuresList.get(position)
					.getProductFeatureCategoryDescription()) + " : ");
			return convertView;
		}

	}

	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.ll_evaluate:

			startActivity(AvaluateActivity.class);

			break;
		case R.id.details_joingwc:

			if (SaveApplicationParam.getLandState(WineDetails.this)) {
				Register register = SaveApplicationParam
						.getRegister(WineDetails.this);
				PostBean postBean = new PostBean();
				postBean.setCode(Const.ADDSHOPPINGLISTITEM);
				postBean.setPhoneNumber(register.getPhone());
				postBean.setPassword(register.getPwd());
				postBean.setProductId(wineInfo.getWineId());
				postBean.setQuantity("1");
				new Task(postBean, WineDetails.this, new ECallBack() {

					public void OnError(Object obj) {

						Toast.makeText(WineDetails.this, R.string.errcode_net,
								1000).show();
					}

					public void OnCreate(Object obj) {
						ResultObject result = (ResultObject) obj;
						System.out.println("addShoppingListItem result = "
								+ result.getmMessage());
						JsonPrase jsonPrase = new JsonPrase();
						if (jsonPrase.getState(result.getmMessage())) {
							FRASHSHOPCAT = true;
							Toast.makeText(WineDetails.this, "已加入购物车", 1000)
									.show();
							finish();
						} else {
							Toast.makeText(WineDetails.this, "添加失败，请重试", 1000)
									.show();
						}

					}
				}).postHttp();

			} else {
				startActivity(new Intent(WineDetails.this, LoginActivity.class));
			}
			break;

		case R.id.details_order:
			if (SaveApplicationParam.getLandState(WineDetails.this)) {
				if (shopList != null)
					shopList.clear();
				ShopCarBean shopCar = new ShopCarBean();
				shopCar.setUnitPrice(Logic.getString(wineInfo.getWinePrice()));
				shopCar.setQuantity("1");
				shopCar.setInternalName(Logic.getString(wineInfo.getWineDese()));
				shopCar.setTotalPrice(Logic.getString(wineInfo.getWinePrice()));
				shopCar.setProductId(Logic.getString(wineInfo.getWineId()));
				setShopList(shopCar);
				startActivityForResult(new Intent(WineDetails.this,
						OrderActivity.class).putExtra("PRICE",
						Logic.getString(wineInfo.getWinePrice())), 100);
			} else {
				startActivity(new Intent(WineDetails.this, LoginActivity.class));
			}
			break;
		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			finish();
		}

	}
}

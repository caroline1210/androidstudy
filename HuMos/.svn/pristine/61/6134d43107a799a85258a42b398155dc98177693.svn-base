package com.ltd.mos.main;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.bean.WineInfo;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.image.AnimateFirstDisplayListener;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.FileUtil;
import com.ltd.mos.util.Logic;

public class HotSellActivity extends BaseActivity {

	private ListView listview;
	private String lable;
	private String flag_wine;
	private ArrayList<WineInfo> wineList_hotsell, wineList_price,
			wineList_newProduct;

	private BaseAdapter hotSellAdapter;// 热销
	private BaseAdapter priceAdapter;// 价格
	private BaseAdapter newProductAdapter;// 新品
	private int viewIndex_hotsell = 0;// 页码
	private int viewIndex_price = 0;
	private int viewIndex_newproduct = 0;
	public static final String viewSize = "10";// 每页显示个数
	private MyBroadcast broadcastReceiver;

	private boolean hotsell_filter;// 显示的是否为筛选数据
	private boolean price_filter;
	private boolean newproduct_filter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotsell);
		hotsell_filter = false;
		price_filter = false;
		newproduct_filter = false;
		listview = (ListView) findViewById(R.id.hotsell_list);

		lable = Logic.getString(getIntent().getStringExtra("LABLE"));
		flag_wine = Logic
				.getString(getIntent().getStringExtra(Const.FLAG_WINE));
		if (lable.equals(Const.HOTSELL)) {

			showProgressDialog(this, getString(R.string.searching));
			requstHotSellList(viewIndex_hotsell + "", viewSize, true);

		} else if (lable.equals(Const.PRICE)) {
			showProgressDialog(this, getString(R.string.searching));
			requstPriceList(viewIndex_price + "", viewSize, true);

		} else if (lable.equals(Const.NEWPRODUCT)) {
			showProgressDialog(this, getString(R.string.searching));
			requstNewProductList(viewIndex_newproduct + "", viewSize, true);

		}

		broadcastReceiver = new MyBroadcast();
		IntentFilter filter = new IntentFilter("FILTER");
		// 注册广播接收器
		registerReceiver(broadcastReceiver, filter);

	}

	// 新品，全部
	private void requstNewProductList(String viewIndex, String viewSize,
			boolean firstload) {
		PostBean postBean = new PostBean();
		postBean.setCode(Const.FINDPRODUCT);
		postBean.setViewSize(viewSize);
		postBean.setViewIndex(viewIndex);
		postBean.setOrderBy("internalName");
		initCategoryId(postBean);

		new Task(postBean, HotSellActivity.this, new FindProductCallBack(
				Const.NEWPRODUCT, firstload)).postHttp();
	}

	// 价格，白酒
	private void requstPriceList(String viewIndex, String viewSize,
			boolean firstload) {
		PostBean postBean = new PostBean();
		postBean.setCode(Const.FINDPRODUCT);
		postBean.setViewSize(viewSize);
		postBean.setViewIndex(viewIndex);
		postBean.setOrderBy("defaultPrice");
		initCategoryId(postBean);

		new Task(postBean, HotSellActivity.this, new FindProductCallBack(
				Const.PRICE, firstload)).postHttp();
	}

	// 热销，红酒
	private void requstHotSellList(String viewIndex, String viewSize,
			boolean firstload) {
		PostBean postBean = new PostBean();
		initCategoryId(postBean);
		postBean.setCode(Const.FINDPRODUCT);
		postBean.setViewSize(viewSize);
		postBean.setViewIndex(viewIndex);
		postBean.setOrderBy("productId");
		new Task(postBean, HotSellActivity.this, new FindProductCallBack(
				Const.HOTSELL, firstload)).postHttp();
	}

	@Override
	protected void onResume() {

		super.onResume();

	}

	@Override
	protected void onDestroy() {

		unregisterReceiver(broadcastReceiver);

		super.onDestroy();
	}

	@Override
	public void onBackPressed() {
		AnimateFirstDisplayListener.displayedImages.clear();
		super.onBackPressed();
	}

	/**
	 * 实例化 酒类型
	 * 
	 * @author xuwu
	 * @param post
	 */
	private void initCategoryId(PostBean post) {
		if (flag_wine.equals(Const.GRAPE_WINE)) {
			post.setProductCategoryId(Const.GRAPE_WINE);
		} else if (flag_wine.equals(Const.WHITE_SPIRIT)) {
			post.setProductCategoryId(Const.WHITE_SPIRIT);
		} else if (flag_wine.equals(Const.BEER)) {
			post.setProductCategoryId(Const.BEER);
		} else if (flag_wine.equals(Const.OLD_WINE)) {
			post.setProductCategoryId(Const.OLD_WINE);
		}
	}

	class FindProductCallBack implements ECallBack {

		private String flag;
		private boolean firstload;

		public FindProductCallBack(String flag, boolean firstload) {
			this.flag = flag;
			this.firstload = firstload;
		}

		public void OnCreate(Object obj) {

			ResultObject result = (ResultObject) obj;
			Log.d("LOG", result.getmMessage());
			JsonPrase prase = new JsonPrase();
			dismissProgressDialog();
			if (firstload) {
				if (flag.equals(Const.HOTSELL)) {
					wineList_hotsell = prase.findProduct(result.getmMessage());
					hotSellAdapter = new GrapeListAdapter(HotSellActivity.this,
							wineList_hotsell);
					listview.setOnItemClickListener(new WineItemClick(flag));
					listview.setAdapter(hotSellAdapter);
					if (wineList_hotsell != null && wineList_hotsell.size() > 0)
						listview.setOnScrollListener(new OnScrollListenerImple(
								flag));
				} else if (flag.equals(Const.PRICE)) {
					wineList_price = prase.findProduct(result.getmMessage());
					priceAdapter = new GrapeListAdapter(HotSellActivity.this,
							wineList_price);
					listview.setOnItemClickListener(new WineItemClick(flag));
					listview.setAdapter(priceAdapter);
					if (wineList_price != null && wineList_price.size() > 0)
						listview.setOnScrollListener(new OnScrollListenerImple(
								flag));
				} else if (flag.equals(Const.NEWPRODUCT)) {
					wineList_newProduct = prase.findProduct(result
							.getmMessage());
					newProductAdapter = new GrapeListAdapter(
							HotSellActivity.this, wineList_newProduct);
					listview.setOnItemClickListener(new WineItemClick(flag));
					listview.setAdapter(newProductAdapter);
					if (wineList_newProduct != null
							&& wineList_newProduct.size() > 0)
						listview.setOnScrollListener(new OnScrollListenerImple(
								flag));
				}
			} else {
				ArrayList<WineInfo> addProducts = prase.findProduct(result
						.getmMessage());
				if (flag.equals(Const.HOTSELL)) {
					if (addProducts != null && addProducts.size() > 0) {
						wineList_hotsell.addAll(addProducts);
						hotSellAdapter.notifyDataSetChanged();
						listview.setSelection(selectItem);
					}

				} else if (flag.equals(Const.PRICE)) {
					if (addProducts != null && addProducts.size() > 0) {
						wineList_price.addAll(addProducts);
						priceAdapter.notifyDataSetChanged();
						listview.setSelection(selectItem);
					}
				} else if (flag.equals(Const.NEWPRODUCT)) {
					if (addProducts != null && addProducts.size() > 0) {
						wineList_newProduct.addAll(addProducts);
						newProductAdapter.notifyDataSetChanged();
						listview.setSelection(selectItem);
					}
				}

			}
		}

		public void OnError(Object obj) {

			Toast.makeText(getApplicationContext(), R.string.errcode_net, 1000)
					.show();

		}

	}

	int selectItem;

	private class OnScrollListenerImple implements OnScrollListener {

		private String tab_Selected;

		public OnScrollListenerImple(String tab_Selected) {
			this.tab_Selected = tab_Selected;
		}

		public void onScroll(AbsListView listView, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			int lastItem = firstVisibleItem + visibleItemCount;
			if (lastItem == totalItemCount) {
				try {
					System.out.println("Scroll to the listview last item");
					View lastItemView = (View) listView.getChildAt(listView
							.getChildCount() - 1);
					int bottom_list = listView.getBottom();
					int bottom_last = lastItemView.getBottom();
					if (bottom_list == bottom_last) {
						System.out
								.println("========Scroll to the listview bottom =============");
						selectItem = firstVisibleItem;
						addDataToListView(tab_Selected);

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public void onScrollStateChanged(AbsListView listview, int scrollState) {

		}

	}

	private void addDataToListView(String flag) {
		if (flag.equals(Const.HOTSELL)) {
			viewIndex_hotsell += 1;
			if (hotsell_filter) {
				PostBean postBean = new PostBean();
				initCategoryId(postBean);
				postBean.setCode(Const.FINDPRODUCT);
				postBean.setViewSize(viewSize);
				postBean.setViewIndex(viewIndex_hotsell + "");
				postBean.setOrderBy("productId");
				postBean.setProductFeatureIds(featureIds_hotsell);
				new Task(postBean, HotSellActivity.this,
						new FindProductCallBack(Const.HOTSELL, false))
						.postHttp();
			} else
				requstHotSellList(viewIndex_hotsell + "", viewSize, false);

		} else if (flag.equals(Const.PRICE)) {
			viewIndex_price += 1;

			if (price_filter) {
				PostBean postBean = new PostBean();
				initCategoryId(postBean);
				postBean.setCode(Const.FINDPRODUCT);
				postBean.setViewSize(viewSize);
				postBean.setViewIndex(viewIndex_price + "");
				postBean.setOrderBy("defaultPrice");
				postBean.setProductFeatureIds(featureIds_price);
				new Task(postBean, HotSellActivity.this,
						new FindProductCallBack(Const.PRICE, false)).postHttp();
			} else

				requstPriceList(viewIndex_price + "", viewSize, false);
		} else if (flag.equals(Const.NEWPRODUCT)) {
			viewIndex_newproduct += 1;
			if (newproduct_filter) {
				PostBean postBean = new PostBean();
				initCategoryId(postBean);
				postBean.setCode(Const.FINDPRODUCT);
				postBean.setViewSize(viewSize);
				postBean.setViewIndex(viewIndex_newproduct + "");
				postBean.setOrderBy("internalName");
				postBean.setProductFeatureIds(featureIds_newProduct);
				new Task(postBean, HotSellActivity.this,
						new FindProductCallBack(Const.NEWPRODUCT, false))
						.postHttp();
			} else
				requstNewProductList(viewIndex_newproduct + "", viewSize, false);
		}
	}

	private class WineItemClick implements OnItemClickListener {
		private String tab_Selected;

		public WineItemClick(String tab_Selected) {
			this.tab_Selected = tab_Selected;
		}

		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			if (tab_Selected.equals("HOTSELL")) {
				startActivity(new Intent(HotSellActivity.this,
						WineDetails.class).putExtra(Const.WINEINFO,
						wineList_hotsell.get(position)));
			} else if (tab_Selected.equals("PRICE")) {
				startActivity(new Intent(HotSellActivity.this,
						WineDetails.class).putExtra(Const.WINEINFO,
						wineList_price.get(position)));
			} else if (tab_Selected.equals("NEWPRODUCT")) {
				startActivity(new Intent(HotSellActivity.this,
						WineDetails.class).putExtra(Const.WINEINFO,
						wineList_newProduct.get(position)));
			}

		}

	}

	ArrayList<String> featureIds_hotsell;
	ArrayList<String> featureIds_price;
	ArrayList<String> featureIds_newProduct;

	public class MyBroadcast extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {

			if (lable.equals(Const.HOTSELL)) {

				// 处理筛选事件

				featureIds_hotsell = new ArrayList<String>();
				for (int i = 0; i < BaseActivity.categoryFeatureList.size(); i++) {
					if (BaseActivity.categoryFeatureList.get(i).getSelectItem() != -1) {
						featureIds_hotsell
								.add(BaseActivity.categoryFeatureList
										.get(i)
										.getFeatureList()
										.get(BaseActivity.categoryFeatureList
												.get(i).getSelectItem())
										.getProductFeatureId());
					}
					// else {
					// featureIds_hotsell.add("");
					// }
				}

				hotsell_filter = true;
				viewIndex_hotsell = 0;
				PostBean postBean = new PostBean();
				initCategoryId(postBean);
				postBean.setCode(Const.FINDPRODUCT);
				postBean.setViewSize(viewSize);
				postBean.setViewIndex(viewIndex_hotsell + "");
				postBean.setOrderBy("productId");
				postBean.setProductFeatureIds(featureIds_hotsell);
				new Task(postBean, HotSellActivity.this,
						new FindProductCallBack(Const.HOTSELL, true))
						.postHttp();
				showProgressDialog(HotSellActivity.this,
						getString(R.string.searching));
			} else if (lable.equals(Const.PRICE)) {

				if (lable.equals(Const.HOTSELL)) {

					// 处理筛选事件

					featureIds_price = new ArrayList<String>();
					for (int i = 0; i < BaseActivity.categoryFeatureList.size(); i++) {
						if (BaseActivity.categoryFeatureList.get(i)
								.getSelectItem() != -1) {
							featureIds_price
									.add(BaseActivity.categoryFeatureList
											.get(i)
											.getFeatureList()
											.get(BaseActivity.categoryFeatureList
													.get(i).getSelectItem())
											.getProductFeatureId());
						}
						// else {
						// featureIds_price.add("");
						// }
					}

					price_filter = true;
					viewIndex_price = 0;
					PostBean postBean = new PostBean();
					initCategoryId(postBean);
					postBean.setCode(Const.FINDPRODUCT);
					postBean.setViewSize(viewSize);
					postBean.setViewIndex(viewIndex_price + "");
					postBean.setOrderBy("defaultPrice");
					postBean.setProductFeatureIds(featureIds_price);
					new Task(postBean, HotSellActivity.this,
							new FindProductCallBack(Const.PRICE, true))
							.postHttp();
					showProgressDialog(HotSellActivity.this,
							getString(R.string.searching));
				} else if (lable.equals(Const.NEWPRODUCT)) {

					if (lable.equals(Const.HOTSELL)) {

						// 处理筛选事件

						featureIds_newProduct = new ArrayList<String>();
						for (int i = 0; i < BaseActivity.categoryFeatureList
								.size(); i++) {
							if (BaseActivity.categoryFeatureList.get(i)
									.getSelectItem() != -1) {
								featureIds_newProduct
										.add(BaseActivity.categoryFeatureList
												.get(i)
												.getFeatureList()
												.get(BaseActivity.categoryFeatureList
														.get(i).getSelectItem())
												.getProductFeatureId());
							}
							// else {
							// featureIds_newProduct.add("");
							// }
						}

					}
					newproduct_filter = true;
					viewIndex_newproduct = 0;
					PostBean postBean = new PostBean();
					initCategoryId(postBean);
					postBean.setCode(Const.FINDPRODUCT);
					postBean.setViewSize(viewSize);
					postBean.setViewIndex(viewIndex_newproduct + "");
					postBean.setOrderBy("internalName");
					postBean.setProductFeatureIds(featureIds_newProduct);
					new Task(postBean, HotSellActivity.this,
							new FindProductCallBack(Const.NEWPRODUCT, true))
							.postHttp();
					showProgressDialog(HotSellActivity.this,
							getString(R.string.searching));
				}
			}

		}
	}
}

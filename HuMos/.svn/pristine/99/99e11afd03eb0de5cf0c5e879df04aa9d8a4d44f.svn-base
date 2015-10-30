package com.ltd.mos.shopcar;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.bean.ShopCarBean;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.main.GrapeActivity;
import com.ltd.mos.main.HomeTabActivity;
import com.ltd.mos.main.WineDetails;
import com.ltd.mos.main.WineHomeActivity;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

/**
 * 购物车
 * 
 * @author xuwu
 * 
 */
public class ShopCarActivity extends BaseActivity {
	private Button walk;
	private ListView listView;
	private WineAdapter adapter;
	private ViewGroup layout;
	private ArrayList<ShopCarBean> list = new ArrayList<ShopCarBean>();
	private TextView price, desc;
	private Button buy;
	private View view;
	private Button checkBox;
	private Button delete, right;
	public static boolean SELECTSTATE;
	private Register register;
	private ArrayList<String> itemSeqIdList = new ArrayList<String>();
	private LinearLayout shopcar_bottom, login;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shopcar);
		register = SaveApplicationParam.getRegister(this);
		layout = (ViewGroup) this.findViewById(R.id.layout);
		walk = (Button) this.findViewById(R.id.walk);
		walk.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (Logic.getString(walk.getText()).equals("设置网络")) {
					if (android.os.Build.VERSION.SDK_INT > 10) {
						// 3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
						startActivity(new Intent(
								android.provider.Settings.ACTION_SETTINGS));
					} else {
						startActivity(new Intent(
								android.provider.Settings.ACTION_WIRELESS_SETTINGS));
					}
				} else if (Logic.getString(walk.getText()).equals("重新获取")) {
					listShoppingListItem();
					startProgressLoading("");

				} else if (Logic.getString(walk.getText()).equals("继续逛逛")) {

					startActivity(new Intent(ShopCarActivity.this,
							GrapeActivity.class).putExtra(Const.FLAG_WINE,
							Const.GRAPE_WINE).setFlags(
							Intent.FLAG_ACTIVITY_CLEAR_TOP));

				}

			}
		});
		listView = (ListView) this.findViewById(R.id.listView);
		shopcar_bottom = (LinearLayout) findViewById(R.id.shopcar_bottom);

		price = (TextView) this.findViewById(R.id.price);
		buy = (Button) this.findViewById(R.id.buy);
		buy.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				if (list != null && list.size() > 0) {
					if (shopList != null && shopList.size() > 0)
						shopList.clear();
					for (int i = 0; i < list.size(); i++) {
						if (Logic.getString(list.get(i).getSelect())
								.equals("1")) {
							setShopList(list.get(i));
						}
					}
					if (shopList != null && shopList.size() > 0)
						startActivity(new Intent(ShopCarActivity.this,
								OrderActivity.class).putExtra("PRICE",
								price.getText()));
					else
						Toast.makeText(getApplicationContext(), "请选择要购买的商品",
								1000).show();

				}

			}
		});
		view = this.findViewById(R.id.view);
		Logic.getInstance().initHeadView(view, "购物车", false, "删除",
				new ECallBack() {

					public void OnError(Object obj) {
						// TODO Auto-generated method stub

					}

					public void OnCreate(Object obj) {

						if (SELECTSTATE == true) {// 清空
							PostBean postBean = new PostBean();
							postBean.setCode(Const.CLEARSHOPPINGLISTITEM);
							postBean.setPhoneNumber(register.getPhone());
							postBean.setPassword(register.getPwd());
							new Task(postBean, ShopCarActivity.this,
									new ECallBack() {

										public void OnError(Object obj) {
											Toast.makeText(
													getApplicationContext(),
													R.string.errcode_net, 1000)
													.show();
										}

										public void OnCreate(Object obj) {
											ResultObject result = (ResultObject) obj;
											String message = result
													.getmMessage();
											JsonPrase prase = new JsonPrase();
											if (prase.getState(message)) {
												list.clear();
												adapter.notifyDataSetChanged();

												layout.setVisibility(View.VISIBLE);
												delete.setVisibility(View.GONE);
												right.setVisibility(View.GONE);
												desc.setText("您的购物车暂时没有商品");
												walk.setText("继续逛逛");
											} else {
												Toast.makeText(
														getApplicationContext(),
														"删除失败,请重试", 1000)
														.show();
											}

										}
									}).postHttp();

						} else {
							if (list != null && list.size() > 0) {
								itemSeqIdList.clear();
								for (int i = 0; i < list.size(); i++) {
									if (Logic
											.getString(list.get(i).getSelect())
											.equals("1")) {
										itemSeqIdList.add(list.get(i)
												.getShoppingListItemSeqId());
									}
								}

								if (itemSeqIdList != null
										&& itemSeqIdList.size() == 0) {
									Toast.makeText(getApplicationContext(),
											"请选择要删除的商品", 1000).show();
									return;
								}

								PostBean postBean = new PostBean();
								postBean.setCode(Const.DELETESHOPPINGLISTITEM);
								postBean.setPassword(register.getPwd());
								postBean.setPhoneNumber(register.getPhone());
								postBean.setSeqIds(itemSeqIdList);
								new Task(postBean, ShopCarActivity.this,
										new ECallBack() {

											public void OnError(Object obj) {
												Toast.makeText(
														getApplicationContext(),
														R.string.errcode_net,
														1000).show();

											}

											public void OnCreate(Object obj) {
												ResultObject result = (ResultObject) obj;
												String msg = result
														.getmMessage();
												JsonPrase prase = new JsonPrase();
												if (prase.getState(msg)) {
													listShoppingListItem();
												} else {
													Toast.makeText(
															getApplicationContext(),
															R.string.errcode_request,
															1000).show();
												}

											}
										}).postHttp();
							}
						}

					}
				});
		delete = (Button) view.findViewById(R.id.delete);
		right = (Button) view.findViewById(R.id.right);
		delete.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		delete.setVisibility(View.GONE);
		right.setVisibility(View.GONE);
		desc = (TextView) findViewById(R.id.desc);
		checkBox = (Button) this.findViewById(R.id.checkBox1);
		checkBox.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!SELECTSTATE) {
					SELECTSTATE = true;
					checkBox.setBackgroundResource(R.drawable.shop_select);
					for (int i = 0; i < list.size(); i++) {
						list.get(i).setSelect("1");
					}
					setMoeny();
					adapter.notifyDataSetChanged();
				} else {
					SELECTSTATE = false;
					checkBox.setBackgroundResource(R.drawable.shop_unselect);
					for (int i = 0; i < list.size(); i++) {
						list.get(i).setSelect("0");
					}
					price.setText("0元");
					adapter.notifyDataSetChanged();
				}
			}
		});

		login = (LinearLayout) findViewById(R.id.login);

		FRASHSHOPCAT = true;
		if (SaveApplicationParam.getLandState(this)) {
			login.setVisibility(View.GONE);
		} else {
			login.setVisibility(View.VISIBLE);
		}
		listView.setOnItemClickListener(new ShoppingItemClick());
	}

	/**
	 * 获取购物车商品
	 * 
	 * @author xuwu
	 */
	private void listShoppingListItem() {

		Register register = SaveApplicationParam.getRegister(this);

		PostBean postBean = new PostBean();
		postBean.setCode(Const.LISTSHOPPINGLISTITEM);
		postBean.setPhoneNumber(register.getPhone());
		postBean.setPassword(register.getPwd());
		new Task(postBean, this, new ListShopCallBack()).postHttp();
	}

	private class ListShopCallBack implements ECallBack {

		public void OnCreate(Object obj) {
			ResultObject result = (ResultObject) obj;
			JsonPrase jsonPrase = new JsonPrase();
			String msg = result.getmMessage();
			dismissProgressDialog();
			if (jsonPrase.getState(msg)) {// 成功
				ArrayList<ShopCarBean> shoppingList = jsonPrase
						.getShoppingList(msg);
				list.clear();
				list.addAll(shoppingList);

				if (adapter == null) {
					adapter = new WineAdapter(ShopCarActivity.this, list,
							new CallBack(), new CheckCallBack());
					listView.setAdapter(adapter);
				} else {
					adapter.notifyDataSetChanged();
				}
				if (list == null || list.size() == 0) {
					layout.setVisibility(View.VISIBLE);
					delete.setVisibility(View.GONE);
					right.setVisibility(View.GONE);
					desc.setText("您的购物车暂时没有商品");
					walk.setText("继续逛逛");
				} else {
					setMoeny();
					layout.setVisibility(View.GONE);
					shopcar_bottom.setVisibility(View.VISIBLE);
					delete.setVisibility(View.VISIBLE);
					right.setVisibility(View.VISIBLE);
				}

			} else {
				desc.setText("获取购物车商品失败");
				layout.setVisibility(View.VISIBLE);
				delete.setVisibility(View.GONE);
				right.setVisibility(View.GONE);
				walk.setText("重新获取");
			}
		}

		public void OnError(Object obj) {
			layout.setVisibility(View.VISIBLE);
			delete.setVisibility(View.GONE);
			right.setVisibility(View.GONE);
			desc.setText(R.string.errcode_net);
			walk.setText("设置网络");
		}

	}

	@Override
	protected void onResume() {

		super.onResume();

		if (SaveApplicationParam.getLandState(this)) {
			login.setVisibility(View.GONE);
			if (FRASHSHOPCAT) {
				showProgressDialog(this, getString(R.string.searching));
				listShoppingListItem();
				FRASHSHOPCAT = false;
			}
		} else {
			login.setVisibility(View.VISIBLE);
		}

	}

	private int SELECTNUM;

	private void setMoeny() {
		BigDecimal money = new BigDecimal("0");
		SELECTNUM = 0;
		for (ShopCarBean wineInfo : list) {
			if (Logic.getString(wineInfo.getSelect()).equals("1")) {
				BigDecimal num = new BigDecimal(Logic.getString(wineInfo
						.getQuantity()));
				BigDecimal price = new BigDecimal(wineInfo.getUnitPrice());
				BigDecimal tempPrice = price.multiply(num);
				money = money.add(tempPrice);
				SELECTNUM++;
			}
		}
		if (SELECTNUM == list.size()) {
			SELECTSTATE = true;
			checkBox.setBackgroundResource(R.drawable.shop_select);
		} else {
			SELECTSTATE = false;
			checkBox.setBackgroundResource(R.drawable.shop_unselect);
		}
		if (money == null) {
			price.setText("0元");
			return;
		}
		price.setText(money.floatValue() + "元");

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

	class CallBack implements ECallBack {

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			String flag = Logic.getString(obj);
			int position = Integer.parseInt(flag);
			int winNum = Integer.parseInt(list.get(position).getQuantity());
			list.get(position).setQuantity(Logic.getString(winNum + 1));
			setMoeny();
			adapter.notifyDataSetChanged();
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub
			int position = Integer.parseInt(Logic.getString(obj));
			int winNum = Integer.parseInt(list.get(position).getQuantity());

			if (winNum <= 0) {
				SELECTSTATE = false;
				checkBox.setBackgroundResource(R.drawable.shop_unselect);
				return;
			}
			setMoeny();
			list.get(position).setQuantity(Logic.getString(winNum - 1));
			adapter.notifyDataSetChanged();
		}
	}

	class CheckCallBack implements ECallBack {

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			String flag = Logic.getString(obj);
			int position = Integer.parseInt(flag);
			list.get(position).setSelect("1");
			setMoeny();
			adapter.notifyDataSetChanged();

		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub
			int position = Integer.parseInt(Logic.getString(obj));
			list.get(position).setSelect("0");
			setMoeny();
			adapter.notifyDataSetChanged();
		}
	}

	class ShoppingItemClick implements OnItemClickListener {

		public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
				long arg3) {
			ShopCarBean shopping = list.get(pos);

			startActivity(new Intent(ShopCarActivity.this, WineDetails.class)
					.putExtra("PRODUCTID", shopping.getProductId()));
		}

	}

}

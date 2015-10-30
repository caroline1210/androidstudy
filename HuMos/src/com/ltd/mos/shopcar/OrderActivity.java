package com.ltd.mos.shopcar;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.Address;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.bean.ShopCarBean;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.main.WineDetails;
import com.ltd.mos.shopcar.ConsigActivity.AddressAdapter;
import com.ltd.mos.shopcar.ConsigActivity.ListAddressCallBack;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

/**
 * 下单
 * 
 * @author xuwu
 * 
 */
public class OrderActivity extends BaseActivity implements OnClickListener {

	private View view;
	private BaseAdapter orderAdapter;
	private static final int WHAT_ADD = 100;
	private static final int WHAT_REDUCE = 200;
	Register register;
	String price;

	private int selPostion = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order);
		register = SaveApplicationParam.getRegister(this);
		view = findViewById(R.id.view);
		price = Logic.getString(getIntent().getStringExtra("PRICE"));
		Logic.getInstance().initGrapeTitle(view, getString(R.string.order),
				true, false, new ECallBack() {

					public void OnError(Object obj) {
						// TODO Auto-generated method stub

					}

					public void OnCreate(Object obj) {
						finish();

					}
				});

		findViewId();

		orderAdapter = new OrderAdapter(shopList);
		View headView = LinearLayout.inflate(this, R.layout.address, null);
		findHeadView(headView);
		View footView = LinearLayout.inflate(this, R.layout.order_note, null);
		findFootView(footView);
		order_list.addHeaderView(headView);
		order_list.addFooterView(footView);
		order_list.setAdapter(orderAdapter);

		order_price.setText(price);

	}

	private EditText note_edit;

	private void findFootView(View footView) {
		note_edit = (EditText) footView.findViewById(R.id.note_edit);
		note_edit.addTextChangedListener(new TextWatcher() {

			private CharSequence temp;
			private int selectStart;
			private int selectEnd;

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				temp = s;
			}

			public void afterTextChanged(Editable s) {
				selectStart = note_edit.getSelectionStart();
				selectEnd = note_edit.getSelectionEnd();
				if (temp.length() > 100) {
					Toast.makeText(OrderActivity.this, "输入字数达到上限!",
							Toast.LENGTH_SHORT).show();
					s.delete(selectStart - 1, selectEnd);
					int tempSelect = selectEnd;
					note_edit.setText(temp);
					note_edit.setSelection(tempSelect);
				}

			}
		});

	}

	private TextView order_useraddress, order_username, order_userphone,
			location_data, order_geo;
	private LinearLayout userinfo_ll;
	Address address;

	private void findHeadView(View headView) {

		order_username = (TextView) headView.findViewById(R.id.order_username);
		order_useraddress = (TextView) headView
				.findViewById(R.id.order_useraddress);
		order_userphone = (TextView) headView
				.findViewById(R.id.order_userphone);
		order_geo = (TextView) headView.findViewById(R.id.order_geo);
		location_data = (TextView) headView.findViewById(R.id.location_data);
		userinfo_ll = (LinearLayout) headView.findViewById(R.id.userinfo_ll);

		try {
			address = shopList.get(0).getAddress();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (address != null) {
			order_userphone.setText(Logic.getString(address.getPhoneNumber()));
			order_username.setText(Logic.getString(address.getAttnName()));
			order_geo.setText(Logic.getString(address.getAddress1()));
			order_useraddress.setText(address.getAddress2());
			contactMechId = Logic.getString(address.getContactMechId());
		} else {

			PostBean bean = new PostBean();
			bean.setCode(Const.LISTSHIPPINGADDRESS);
			bean.setPhoneNumber(register.getPhone());
			bean.setPassword(register.getPwd());
			new Task(bean, this, new ListAddressCallBack()).postHttp();
			showProgressDialog(this, "加载中,请稍等...");

		}

		userinfo_ll.setOnClickListener(this);

	}

	class ListAddressCallBack implements ECallBack {

		public void OnCreate(Object obj) {
			dismissProgressDialog();
			ResultObject result = (ResultObject) obj;
			String msg = result.getmMessage();
			JsonPrase jsonPrase = new JsonPrase();
			if (jsonPrase.getState(msg)) {
				ArrayList<Address> listShippingAddress = jsonPrase
						.listShippingAddress(msg);
				if (listShippingAddress != null
						&& listShippingAddress.size() > 0) {
					Address address2 = listShippingAddress
							.get(listShippingAddress.size() - 1);
					order_userphone.setText(Logic.getString(address2
							.getPhoneNumber()));
					order_username.setText(Logic.getString(address2
							.getAttnName()));
					order_geo.setText(Logic.getString(address2.getAddress1()));
					order_useraddress.setText(address2.getAddress2());
					contactMechId = Logic
							.getString(address2.getContactMechId());
				} else {
					order_userphone.setText(register.getPhone());
					order_username.setText(register.getName());
					String str = SaveApplicationParam
							.getLandLocation(OrderActivity.this);
					order_useraddress.setText(str);
				}
			} else {
				order_userphone.setText(register.getPhone());
				order_username.setText(register.getName());
				String str = SaveApplicationParam
						.getLandLocation(OrderActivity.this);
				order_useraddress.setText(str);
			}
		}

		public void OnError(Object obj) {
			dismissProgressDialog();
			order_userphone.setText(register.getPhone());
			order_username.setText(register.getName());
			String str = SaveApplicationParam
					.getLandLocation(OrderActivity.this);
			order_useraddress.setText(str);
		}

	}

	private class OrderAdapter extends BaseAdapter {

		ArrayList<ShopCarBean> list;

		public OrderAdapter(ArrayList<ShopCarBean> list) {
			this.list = list;
		}

		public int getCount() {

			return list.size();
		}

		public Object getItem(int position) {

			return null;
		}

		public long getItemId(int position) {

			return 0;
		}

		public View getView(final int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = LinearLayout.inflate(OrderActivity.this,
						R.layout.item_order, null);
				holder.internalName = (TextView) convertView
						.findViewById(R.id.order_name);
				holder.unitPrice = (TextView) convertView
						.findViewById(R.id.order_unitprice);
				holder.quantity = (TextView) convertView
						.findViewById(R.id.order_num);
				holder.order_add = (ImageView) convertView
						.findViewById(R.id.order_add);
				holder.order_reduce = (ImageView) convertView
						.findViewById(R.id.order_reduce);
				holder.totalPrice = (TextView) convertView
						.findViewById(R.id.order_totalprice);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			ShopCarBean shopping = list.get(position);

			holder.internalName.setText(shopping.getInternalName());
			holder.unitPrice.setText(shopping.getUnitPrice());
			holder.totalPrice.setText(shopping.getTotalPrice());
			holder.quantity.setText(shopping.getQuantity() + " 瓶");

			holder.order_reduce.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {

					Message msg = new Message();
					msg.what = WHAT_ADD;
					msg.obj = position;
					handle.sendMessage(msg);

				}
			});

			holder.order_add.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {

					Message msg = new Message();
					msg.what = WHAT_REDUCE;
					msg.obj = position;
					handle.sendMessage(msg);

				}
			});

			return convertView;
		}

		class ViewHolder {
			TextView internalName;
			TextView unitPrice;
			TextView quantity;
			TextView totalPrice;
			ImageView order_reduce;
			ImageView order_add;
		}

	}

	private TextView order_submitorder;
	private TextView order_price;
	private ListView order_list;

	private void findViewId() {
		order_list = (ListView) findViewById(R.id.order_list);

		order_username = (TextView) findViewById(R.id.order_username);
		order_userphone = (TextView) findViewById(R.id.order_userphone);
		order_useraddress = (TextView) findViewById(R.id.order_useraddress);
		order_submitorder = (TextView) findViewById(R.id.order_submitorder);
		order_price = (TextView) findViewById(R.id.order_price);
		order_submitorder.setOnClickListener(this);

	}

	Handler handle = new Handler() {
		public void handleMessage(android.os.Message msg) {
			int position;
			switch (msg.what) {
			case WHAT_ADD:
				position = (Integer) msg.obj;

				break;
			case WHAT_REDUCE:
				position = (Integer) msg.obj;
				break;
			default:
				break;
			}

		};
	};

	// (float) (Double.parseDouble(wineInfo.getWinePrice()) * checkedNum)
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.userinfo_ll:

			Address address = new Address();
			address.setAddress1(Logic.getString(order_geo.getText()));
			address.setAddress2(Logic.getString(order_useraddress.getText()));
			address.setPhoneNumber(Logic.getString(order_userphone.getText()));
			address.setAttnName(Logic.getString(order_username.getText()));
			address.setContactMechId(contactMechId);
			address.setSelPostion(selPostion);
			startActivityForResult(new Intent(OrderActivity.this,
					ConsigActivity.class).putExtra("ADDRESS", address), 0);

			break;
		case R.id.order_submitorder:

			if (Logic.getString(order_username.getText()).length() == 0) {
				Toast.makeText(getApplicationContext(), "请填写收货人姓名", 1000)
						.show();
				break;
			}
			if (Logic.getString(order_userphone.getText()).length() == 0) {
				Toast.makeText(getApplicationContext(), "请填写手机号码", 1000).show();
				break;
			}

			if (Logic.getString(order_geo.getText()).length() == 0) {
				Toast.makeText(getApplicationContext(), "请填写所在区域", 1000).show();
				break;
			}

			if (Logic.getString(order_useraddress.getText()).length() == 0) {
				Toast.makeText(getApplicationContext(), "请填写详细地址", 1000).show();
				break;
			}

			showProgressDialog(OrderActivity.this, "订单处理中,请稍等...");

			if (Logic.getString(shopList.get(0).getShoppingListItemSeqId())
					.length() == 0) {// 立即下单

				// 把商品加入到购物车

				PostBean postBean = new PostBean();
				postBean.setCode(Const.ADDSHOPPINGLISTITEM);
				postBean.setPhoneNumber(register.getPhone());
				postBean.setPassword(register.getPwd());
				postBean.setProductId(shopList.get(0).getProductId());
				postBean.setQuantity("1");
				new Task(postBean, OrderActivity.this, new ECallBack() {

					public void OnError(Object obj) {

						Toast.makeText(OrderActivity.this,
								R.string.errcode_net, 1000).show();
					}

					public void OnCreate(Object obj) {
						// {"responseMessage":"success","shoppingListItemSeqId":"00002"}
						ResultObject result = (ResultObject) obj;
						System.out.println("addShoppingListItem result = "
								+ result.getmMessage());
						JsonPrase jsonPrase = new JsonPrase();
						if (jsonPrase.getState(result.getmMessage())) {
							final String shoppingListItemSeqId = jsonPrase
									.getShoppingListItemSeqId(result
											.getmMessage());

							if ("".equals(contactMechId)) {// 新地址

								// 创建新的地址
								PostBean post = new PostBean();
								post.setCode(Const.CREATESHIPPINGADDRESS);
								post.setAttnName(Logic.getString(order_username
										.getText()));
								post.setAddress2(Logic
										.getString(order_useraddress.getText()));
								post.setPhoneNumber(Logic.getString(register
										.getPhone()));
								post.setStateProvinceGeoId(address_order
										.getStateProvinceGeoId());
								post.setCityGeoId(address_order.getCityGeoId());
								post.setCountyGeoId(address_order
										.getCountyGeoId());
								post.setPhone(Logic.getString(order_userphone
										.getText()));
								post.setPassword(Logic.getString(register
										.getPwd()));

								new Task(post, OrderActivity.this,
										new ECallBack() {

											public void OnError(Object obj) {
												dismissProgressDialog();
												Toast.makeText(
														getApplicationContext(),
														"请求服务器失败", 1000).show();
											}

											public void OnCreate(Object obj) {
												ResultObject result = (ResultObject) obj;
												String msg = result
														.getmMessage();
												JsonPrase prase = new JsonPrase();
												if (prase.getState(msg)) {
													contactMechId = prase
															.getContactMechId(msg);
													// 设置收货地址
													PostBean postBean = new PostBean();
													postBean.setPhoneNumber(register
															.getPhone());
													postBean.setPassword(register
															.getPwd());
													postBean.setCode(Const.SETSHOPPINGLISTADDRESS);
													postBean.setContactMechId(contactMechId);

													new Task(postBean,
															OrderActivity.this,
															new ECallBack() {

																public void OnError(
																		Object obj) {
																	dismissProgressDialog();
																	Toast.makeText(
																			getApplicationContext(),
																			"请求服务器失败",
																			1000)
																			.show();
																}

																public void OnCreate(
																		Object obj) {
																	ResultObject result = (ResultObject) obj;
																	String msg = result
																			.getmMessage();
																	JsonPrase jsonPrase = new JsonPrase();
																	if (jsonPrase
																			.getState(msg)) {
																		PostBean postBean = new PostBean();
																		postBean.setCode(Const.SUBMITSHOPPINGLIST);
																		postBean.setPhoneNumber(register
																				.getPhone());
																		postBean.setPassword(register
																				.getPwd());
																		postBean.setShoppingListItemSeqId(shoppingListItemSeqId);
																		postBean.setShipDate("2014-10-1");
																		postBean.setNote("备注：测试测试");
																		new Task(
																				postBean,
																				OrderActivity.this,
																				new ECallBack() {

																					public void OnError(
																							Object obj) {
																						dismissProgressDialog();
																						Toast.makeText(
																								getApplicationContext(),
																								"请求服务器失败",
																								1000)
																								.show();

																					}

																					public void OnCreate(
																							Object obj) {
																						ResultObject result = (ResultObject) obj;
																						String msg = result
																								.getmMessage();
																						JsonPrase jsonPrase = new JsonPrase();
																						if (jsonPrase
																								.getState(msg)) {
																							Toast.makeText(
																									getApplicationContext(),
																									"订单提交成功!",
																									1000)
																									.show();
																							Intent intent = new Intent(
																									OrderActivity.this,
																									WineDetails.class);
																							setResult(
																									RESULT_OK,
																									intent);
																							finish();
																						} else {
																							dismissProgressDialog();
																							Toast.makeText(
																									getApplicationContext(),
																									"订单提交失败,请重试",
																									1000)
																									.show();
																						}

																					}
																				})
																				.postHttp();

																	} else {
																		dismissProgressDialog();
																		Toast.makeText(
																				getApplicationContext(),
																				"请求服务器失败",
																				1000)
																				.show();
																	}

																}
															}).postHttp();

												} else {
													dismissProgressDialog();
													Toast.makeText(
															getApplicationContext(),
															"请求服务器失败", 1000)
															.show();
												}

											}
										}).postHttp();
							} else {// 历史地址

								// 设置收货地址
								PostBean postBean = new PostBean();
								postBean.setPhoneNumber(register.getPhone());
								postBean.setPassword(register.getPwd());
								postBean.setCode(Const.SETSHOPPINGLISTADDRESS);
								postBean.setContactMechId(contactMechId);

								new Task(postBean, OrderActivity.this,
										new ECallBack() {

											public void OnError(Object obj) {
												dismissProgressDialog();
												Toast.makeText(
														getApplicationContext(),
														"请求服务器失败", 1000).show();
											}

											public void OnCreate(Object obj) {
												ResultObject result = (ResultObject) obj;
												String msg = result
														.getmMessage();
												JsonPrase jsonPrase = new JsonPrase();
												if (jsonPrase.getState(msg)) {
													PostBean postBean = new PostBean();
													postBean.setCode(Const.SUBMITSHOPPINGLIST);
													postBean.setPhoneNumber(register
															.getPhone());
													postBean.setPassword(register
															.getPwd());
													postBean.setShoppingListItemSeqId(shoppingListItemSeqId);
													postBean.setShipDate("2014-10-1");
													postBean.setNote("备注：测试测试");
													new Task(postBean,
															OrderActivity.this,
															new ECallBack() {

																public void OnError(
																		Object obj) {
																	dismissProgressDialog();
																	Toast.makeText(
																			getApplicationContext(),
																			"请求服务器失败",
																			1000)
																			.show();

																}

																public void OnCreate(
																		Object obj) {
																	ResultObject result = (ResultObject) obj;
																	String msg = result
																			.getmMessage();
																	JsonPrase jsonPrase = new JsonPrase();
																	if (jsonPrase
																			.getState(msg)) {
																		Toast.makeText(
																				getApplicationContext(),
																				"订单提交成功!",
																				1000)
																				.show();
																		Intent intent = new Intent(
																				OrderActivity.this,
																				WineDetails.class);
																		setResult(
																				RESULT_OK,
																				intent);
																		finish();
																	} else {
																		dismissProgressDialog();
																		Toast.makeText(
																				getApplicationContext(),
																				"订单提交失败,请重试",
																				1000)
																				.show();
																	}

																}
															}).postHttp();

												} else {
													dismissProgressDialog();
													Toast.makeText(
															getApplicationContext(),
															"请求服务器失败", 1000)
															.show();
												}

											}
										}).postHttp();

							}
						}

					}
				}).postHttp();

				break;
			}

			if ("".equals(contactMechId)) {
				// 创建新的地址
				PostBean post = new PostBean();
				post.setCode(Const.CREATESHIPPINGADDRESS);
				post.setAttnName(Logic.getString(order_username.getText()));
				post.setAddress2(Logic.getString(order_useraddress.getText()));
				post.setPhoneNumber(Logic.getString(register.getPhone()));
				post.setStateProvinceGeoId(address_order
						.getStateProvinceGeoId());
				post.setCityGeoId(address_order.getCityGeoId());
				post.setCountyGeoId(address_order.getCityGeoId());
				post.setPhone(Logic.getString(order_userphone.getText()));
				post.setPassword(Logic.getString(register.getPwd()));

				new Task(post, OrderActivity.this, new ECallBack() {

					public void OnError(Object obj) {
						Toast.makeText(OrderActivity.this,
								R.string.errcode_net, 1000).show();
					}

					public void OnCreate(Object obj) {
						ResultObject result = (ResultObject) obj;
						String msg = result.getmMessage();
						JsonPrase prase = new JsonPrase();
						if (prase.getState(msg)) {
							contactMechId = prase.getContactMechId(msg);
							commitOrder();

						} else {
							dismissProgressDialog();
							Toast.makeText(getApplicationContext(), "请求服务器失败",
									1000).show();
						}

					}
				}).postHttp();

			} else {

				commitOrder();
			}
			break;
		default:
			break;
		}

	}

	private void commitOrder() {
		PostBean postBean = new PostBean();
		postBean.setPhoneNumber(register.getPhone());
		postBean.setPassword(register.getPwd());
		postBean.setCode(Const.SETSHOPPINGLISTADDRESS);
		postBean.setContactMechId(contactMechId);
		new Task(postBean, OrderActivity.this, new ECallBack() {

			public void OnError(Object obj) {
				Toast.makeText(OrderActivity.this, R.string.errcode_net, 1000)
						.show();
			}

			public void OnCreate(Object obj) {
				ResultObject result = (ResultObject) obj;
				String msg = result.getmMessage();
				JsonPrase jsonPrase = new JsonPrase();
				if (jsonPrase.getState(msg)) {

					for (int i = 0; i < shopList.size(); i++) {
						final int temp = i;
						PostBean postBean = new PostBean();
						postBean.setCode(Const.SUBMITSHOPPINGLIST);
						postBean.setPhoneNumber(register.getPhone());
						postBean.setPassword(register.getPwd());
						postBean.setShoppingListItemSeqId(shopList.get(i)
								.getShoppingListItemSeqId());
						postBean.setShipDate("2014-10-1");
						postBean.setNote(Logic.getString(note_edit.getText()));
						new Task(postBean, OrderActivity.this, new ECallBack() {

							public void OnError(Object obj) {
								dismissProgressDialog();
								Toast.makeText(getApplicationContext(),
										"请求服务器失败", 1000).show();

							}

							public void OnCreate(Object obj) {
								ResultObject result = (ResultObject) obj;
								String msg = result.getmMessage();
								JsonPrase jsonPrase = new JsonPrase();
								if (jsonPrase.getState(msg)) {

									FRASHSHOPCAT = true;

									if (temp == shopList.size() - 1) {// 最后一个订单提交成功，关闭activity
										Toast.makeText(getApplicationContext(),
												"订单提交成功!", 1000).show();
										finish();
									}
								} else {
									dismissProgressDialog();
									Toast.makeText(getApplicationContext(),
											"订单提交失败,请重试", 1000).show();
								}

							}
						}).postHttp();

					}

				} else {
					dismissProgressDialog();
					Toast.makeText(getApplicationContext(), "请求服务器失败", 1000)
							.show();
				}

			}
		}).postHttp();
	}

	String contactMechId = "";

	Address address_order;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (resultCode) {

		case RESULT_OK:
			address_order = (Address) data.getSerializableExtra("ADDRESS");
			if (address_order == null) {
				order_userphone.setText(register.getPhone());
				order_username.setText(register.getName());
				String str = SaveApplicationParam.getLandLocation(this);
				order_useraddress.setText(str);
				order_geo.setText("");
				contactMechId = "";
				selPostion = 0;
			} else {

				order_username.setText(Logic.getString(address_order
						.getAttnName()));
				order_useraddress.setText(Logic.getString(address_order
						.getAddress2()));
				order_userphone.setText(Logic.getString(address_order
						.getPhoneNumber()));
				order_geo.setText(Logic.getString(address_order.getAddress1()));
				selPostion = address_order.getSelPostion();
				contactMechId = Logic.getString(address_order
						.getContactMechId());
			}

			break;

		default:
			break;
		}

	}
}

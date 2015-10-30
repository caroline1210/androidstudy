package com.ltd.mos.shopcar;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
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
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

/**
 * 编辑收货信息界面
 * 
 * @author xuwu
 * 
 */
public class ConsigActivity extends BaseActivity {

	private LinearLayout title_return;
	private LinearLayout addAddress;
	Register register;
	private ListView consig_listview;
	private ArrayList<Address> addressList;
	private AddressAdapter adapter;
	private Address address;
	private int selectPos = 0;
	private boolean tempAd;
	private boolean beExist;
	private String selectContactMechId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consig);
		register = SaveApplicationParam.getRegister(this);
		Intent intent = getIntent();
		address = (Address) intent.getSerializableExtra("ADDRESS");
		selectContactMechId = address.getContactMechId();
		selectPos = address.getSelPostion();
		tempAd = true;
		findviewid();
		initAddress();

	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	/**
	 * 获取收货地址
	 * 
	 * @author xuwu
	 */
	public void initAddress() {

		PostBean bean = new PostBean();
		bean.setCode(Const.LISTSHIPPINGADDRESS);
		bean.setPhoneNumber(register.getPhone());
		bean.setPassword(register.getPwd());
		new Task(bean, ConsigActivity.this, new ListAddressCallBack())
				.postHttp();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Address addres = null;
			Intent inent = new Intent(ConsigActivity.this, OrderActivity.class);
			if (addressList != null && addressList.size() > 0) {
				addres = addressList.get(selectPos);
				if (beExist && selectPos > 0)
					selectPos--;
				addres.setSelPostion(selectPos);
			}
			inent.putExtra("ADDRESS", addres);

			setResult(RESULT_OK, inent);
		}

		return super.onKeyDown(keyCode, event);
	}

	private void findviewid() {
		title_return = (LinearLayout) findViewById(R.id.title_return);
		addAddress = (LinearLayout) findViewById(R.id.addAddress);

		title_return.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Address addres = null;
				Intent inent = new Intent(ConsigActivity.this,
						OrderActivity.class);
				if (addressList != null && addressList.size() > 0) {
					addres = addressList.get(selectPos);
					if (beExist && selectPos > 0)
						selectPos--;
					addres.setSelPostion(selectPos);
				}
				inent.putExtra("ADDRESS", addres);

				setResult(RESULT_OK, inent);
				finish();
			}
		});

		addAddress.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startActivityForResult(new Intent(ConsigActivity.this,
						EditAddressActivity.class), 1000);

			}
		});
		addressList = new ArrayList<Address>();
		consig_listview = (ListView) findViewById(R.id.consig_listview);
		consig_listview
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					public boolean onItemLongClick(AdapterView<?> arg0,
							View arg1, final int position, long arg3) {
						final Address address = addressList.get(position);
						new AlertDialog.Builder(ConsigActivity.this)
								.setTitle("编辑地址")
								.setItems(new String[] { "修改", "删除" },
										new DialogInterface.OnClickListener() {

											public void onClick(
													DialogInterface dialog,
													int which) {
												if (which == 0) {
													startActivityForResult(
															new Intent(
																	ConsigActivity.this,
																	EditAddressActivity.class)
																	.putExtra(
																			"ADDRESS",
																			address),
															1001);
													dialog.dismiss();
												} else if (which == 1) {

													if (Logic
															.getString(
																	address.getContactMechId())
															.length() == 0) {
														addressList
																.remove(address);
														beExist = false;
														adapter.notifyDataSetChanged();
														return;
													}

													PostBean postBean = new PostBean();
													postBean.setCode(Const.DELETESHIPPINGADDRESS);
													postBean.setPhoneNumber(register
															.getPhone());
													postBean.setPassword(register
															.getPwd());
													postBean.setContactMechId(address
															.getContactMechId());
													new Task(
															postBean,
															ConsigActivity.this,
															new ECallBack() {

																public void OnError(
																		Object obj) {
																	Toast.makeText(
																			getApplicationContext(),
																			R.string.errcode_net,
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
																				"已删除",
																				1000)
																				.show();

																		if (position < selectPos) {
																			selectPos--;
																		}

																		if (position == selectPos)
																			selectPos = 0;
																		if (beExist)
																			tempAd = true;
																		initAddress();
																	} else {
																		Toast.makeText(
																				getApplicationContext(),
																				R.string.errcode_request,
																				1000)
																				.show();
																	}
																}
															}).postHttp();

												}

											}

										}

								).show();

						return false;
					}
				});

		consig_listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				selectPos = position;

				adapter.notifyDataSetChanged();

				Address addres = null;
				Intent inent = new Intent(ConsigActivity.this,
						OrderActivity.class);
				if (addressList != null && addressList.size() > 0) {
					addres = addressList.get(selectPos);
					if (beExist && selectPos > 0)
						selectPos--;
					addres.setSelPostion(selectPos);
				}
				inent.putExtra("ADDRESS", addres);

				setResult(RESULT_OK, inent);

				finish();

			}
		});

	}

	String CONTACTMECHID_NEW = "";

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode == RESULT_OK) {// 增加收货地址
			CONTACTMECHID_NEW = data.getExtras().getString("CONTACTMECHID");
			if (beExist)
				tempAd = true;
			initAddress();
		}

		if (resultCode == 500) {// 修改历史收货地址
			if (beExist)
				tempAd = true;

			initAddress();
		}
		if (resultCode == 600) {// 修改新收货地址
			Address ads = (Address) data.getExtras().getSerializable("ADS");

			addressList.set(0, ads);

			adapter.notifyDataSetChanged();
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	class ListAddressCallBack implements ECallBack {

		public void OnCreate(Object obj) {
			ResultObject result = (ResultObject) obj;
			String msg = result.getmMessage();
			JsonPrase jsonPrase = new JsonPrase();
			if (jsonPrase.getState(msg)) {
				addressList.clear();
				addressList.addAll(jsonPrase.listShippingAddress(msg));
				if (address.getContactMechId().length() == 0) {
					if (tempAd) {

						if (addressList.size() == 0) {
							addressList.add(0, address);
							beExist = true;
							tempAd = false;
						}
					}
				}
				if (adapter == null) {
					adapter = new AddressAdapter(addressList);
					consig_listview.setAdapter(adapter);
				} else {
					adapter.notifyDataSetChanged();
				}

			} else {
				Toast.makeText(getApplicationContext(), "请求服务器失败", 1000).show();
			}

		}

		public void OnError(Object obj) {
			Toast.makeText(ConsigActivity.this, R.string.errcode_net, 1000)
					.show();

		}

	}

	class AddressAdapter extends BaseAdapter {
		private ArrayList<Address> addressList;

		public AddressAdapter(ArrayList<Address> addressList) {
			this.addressList = addressList;
		}

		public int getCount() {

			return addressList.size();
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
				convertView = LinearLayout.inflate(ConsigActivity.this,
						R.layout.item_consig, null);

				holder.consig_address1 = (TextView) convertView
						.findViewById(R.id.consig_address1);
				holder.consig_address2 = (TextView) convertView
						.findViewById(R.id.consig_address2);
				holder.consig_name = (TextView) convertView
						.findViewById(R.id.consig_name);
				holder.consig_phone = (TextView) convertView
						.findViewById(R.id.consig_phone);
				holder.pretermit = (ImageView) convertView
						.findViewById(R.id.pretermit);

				holder.layout_ll = (LinearLayout) convertView
						.findViewById(R.id.layout_ll);

				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			Address address = addressList.get(position);
			holder.consig_address1.setText(Logic.getString(address
					.getAddress1()));
			holder.consig_address2.setText(Logic.getString(address
					.getAddress2()));
			holder.consig_name.setText(Logic.getString(address.getAttnName()));
			holder.consig_phone.setText(Logic.getString(address
					.getPhoneNumber()));

			if ("".equals(selectContactMechId)) {
				if (position == 0) {
					holder.layout_ll.setBackgroundDrawable(getResources()
							.getDrawable(R.drawable.address_bg));
					holder.pretermit.setVisibility(View.VISIBLE);
				} else {
					holder.layout_ll.setBackgroundDrawable(getResources()
							.getDrawable(R.drawable.rectangle_shade));
					holder.pretermit.setVisibility(View.GONE);
				}
			}
			if (selectContactMechId.equals(address.getContactMechId())) {
				holder.layout_ll.setBackgroundDrawable(getResources()
						.getDrawable(R.drawable.address_bg));
				holder.pretermit.setVisibility(View.VISIBLE);
			} else {
				holder.layout_ll.setBackgroundDrawable(getResources()
						.getDrawable(R.drawable.rectangle_shade));
				holder.pretermit.setVisibility(View.GONE);
			}

			return convertView;
		}

		class ViewHolder {
			TextView consig_address1;
			TextView consig_address2;
			TextView consig_name;
			TextView consig_phone;
			ImageView pretermit;
			LinearLayout layout_ll;
		}

	}

}

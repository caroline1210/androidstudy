package com.ltd.mos.personal;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.OrderItemList;
import com.ltd.mos.bean.PersonOrderBean;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.bean.ShippingAddress;
import com.ltd.mos.bean.StatusHistoryList;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.image.AsyncImageLoader;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

//历史订单详情
public class HistoryOrderDetailsAct extends BaseActivity {

	private View view;
	private TextView tv_number, tv_dese, tv_time, tv_price, tv_adress,
			tv_merchant, tv_state,tv_part;
	private ImageView iv_content;
	PostBean postBean = new PostBean();
	private ArrayList<OrderItemList> list = new ArrayList<OrderItemList>();
	private ArrayList<StatusHistoryList> StateList = new ArrayList<StatusHistoryList>();
	private ArrayList<ShippingAddress> ShipList = new ArrayList<ShippingAddress>();
	OrderItemList order = new OrderItemList();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_title);

		view = findViewById(R.id.order_details_view);
		initView();
		Logic logic = Logic.getInstance();
		logic.initHeadView(view, "订单详情", true, "", new CallBack());

		Register register = SaveApplicationParam.getRegister(this);
		postBean.setCode(Const.PERSONORDER);
		// postBean.setCode(Const.CONFIRMERWEIMA);
		postBean.setPhoneNumber(register.getPhone());
		postBean.setPassword(register.getPwd());
		postBean.setStatusId("ORDER_COMPLETED");
		// postBean.setBarcode(Const.BARCODE);
		Task task = new Task(postBean, HistoryOrderDetailsAct.this, new Back());
		task.postHttp();

	}

	class CallBack implements ECallBack {

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			finish();
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}
	}

	class Back implements ECallBack {

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			ResultObject resultObject = (ResultObject) obj;
			String str = resultObject.getmMessage();

			JsonPrase jsonPrase = new JsonPrase();
			ArrayList<PersonOrderBean> mList = jsonPrase.getOrderState(str);//
			for (int i = 0; i < mList.size(); i++) {
				for (int iu = 0; iu < mList.get(i).getOrderItemList().size(); iu++) {
					mList.get(i).getOrderItemList().get(iu)
							.setDate(mList.get(i).getOrderDate());
					mList.get(i).getOrderItemList().get(iu)
							.setOrderItemSeqId(mList.get(i).getGrandTotal());
				}
			}
			ArrayList<OrderItemList> pList = new ArrayList<OrderItemList>();
			for (PersonOrderBean personOrderBean : mList) {
				pList.addAll(personOrderBean.getOrderItemList());
			}
			list.addAll(pList);
			if (list == null || list.size() <= 0) {

			} else {
				tv_number.setText(list.get(0).getQuantity()+"瓶");
				tv_dese.setText(list.get(0).getInternalName());
				tv_price.setText(list.get(0).getItemTotal()+"元");
				tv_time.setText(list.get(0).getDate());
				tv_part.setText(list.get(0).getOrderItemSeqId());
				
				AsyncImageLoader.initOption();
				OrderItemList orderItemList=list.get(0);
				String url = Logic.getString(orderItemList.getSmallImageUrl());
				AsyncImageLoader.displayImage(url,iv_content);
			}

			//
			ArrayList<ShippingAddress> shList = new ArrayList<ShippingAddress>();
			for (PersonOrderBean personOrderBean : mList) {
				shList.addAll(personOrderBean.getShippingAddress());
			}
			ShipList.addAll(shList);
			if (ShipList == null || ShipList.size() <= 0) {

			} else {
				tv_adress.setText(ShipList.get(0).getAddress1());
				
			}
			//
			ArrayList<StatusHistoryList> stList = new ArrayList<StatusHistoryList>();
			for (PersonOrderBean personOrderBean : mList) {
				stList.addAll(personOrderBean.getStatusHistoryList());
			}
			StateList.addAll(stList);
			if (StateList == null || StateList.size() <= 0) {

			} else {
				tv_state.setText(StateList.get(0).getStatusDescription());
				tv_merchant.setText(StateList.get(0).getStatusDatetime());
			}
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		tv_number = (TextView) findViewById(R.id.tv_person_title_num);
		tv_dese = (TextView) findViewById(R.id.tv_person_title_winedese);
		tv_time = (TextView) findViewById(R.id.tv_person_title_time);
		tv_price = (TextView) findViewById(R.id.tv_person_title_price);
		tv_merchant = (TextView) findViewById(R.id.tv_person_title_merchant);
		tv_adress = (TextView) findViewById(R.id.tv_person_title_address);
		tv_state = (TextView) findViewById(R.id.tv_person_title_orderstate);
		iv_content = (ImageView) findViewById(R.id.iv_person_title_content);
		tv_part = (TextView) findViewById(R.id.tv_person_title_number);
	}
}

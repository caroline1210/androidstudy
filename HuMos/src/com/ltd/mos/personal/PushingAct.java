package com.ltd.mos.personal;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.OrderItemList;
import com.ltd.mos.bean.PersonOrderBean;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

/**
 * 推送中
 * 
 * @author Administrator
 * 
 */
public class PushingAct extends BaseActivity {

	private View view;
	private ListView listview;
	private PushingAdapter adapter;
	// private ArrayList<PushingBean> list = new ArrayList<PushingBean>();
	private ArrayList<OrderItemList> list = new ArrayList<OrderItemList>();
	PostBean postBean = new PostBean();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_pushing);

		listview = (ListView) this.findViewById(R.id.listView);
		// for (int i = 0; i < 8; i++) {
		// PushingBean info = new PushingBean();
		// info.setPicUrl(R.drawable.ad);
		// info.setWineDese("西班牙原装进口的莫斯卡托泡酒+750ml香槟" + i);
		// info.setWinePrice("总价：200元     ");
		// info.setWineNum("数量：" + i);
		// info.setTime("05:00");
		// list.add(info);
		// }
		adapter = new PushingAdapter(this, list, new CallBack());
		listview.setAdapter(adapter);
		Register register = SaveApplicationParam.getRegister(this);
		postBean.setCode(Const.PERSONORDER);
//		postBean.setCode(Const.HEALTHDRINK);
		postBean.setPhoneNumber(register.getPhone());
		postBean.setPassword(register.getPwd());
		postBean.setStatusId("ORDER_CREATED");
//		postBean.setBarcode(Const.BARCODE);
		Task task = new Task(postBean, PushingAct.this, new Back());
		task.postHttp();
		/**
		 * 标题栏的设置
		 */
		view = findViewById(R.id.person_pushing_view);
		Logic logic = Logic.getInstance();
		logic.initHeadView(view, "推送中", true, "", new ECallBack() {

			public void OnCreate(Object obj) {
				// TODO Auto-generated method stub
				finish();
			}

			public void OnError(Object obj) {
				// TODO Auto-generated method stub

			}
		});
	}

	class CallBack implements ECallBack {

		public void OnCreate(Object obj) {
			int position = (Integer) obj;
			OrderItemList bean = list.get(position);
			Intent intent = new Intent(PushingAct.this, CancelOrderAct.class);
			startActivity(intent);

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
//			ArrayList<OrderItemList> pList = new ArrayList<OrderItemList>();
			for (int i = 0; i < mList.size(); i++) {
				for (int iu = 0; iu < mList.get(i).getOrderItemList().size(); iu++) {
					mList.get(i).getOrderItemList().get(iu).setDate(mList.get(i).getOrderDate());
//					mList.get(i).getOrderItemList().get(iu).setOrderItemSeqId(mList.get(i).getGrandTotal());
				}
			}
			ArrayList<OrderItemList> pList = new ArrayList<OrderItemList>();
			for (PersonOrderBean personOrderBean : mList) {
				pList.addAll(personOrderBean.getOrderItemList());
			}
			list.addAll(pList);
			adapter.notifyDataSetChanged();//传递给适配器去改变内容
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}
	}
}

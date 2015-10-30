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

//待收货
public class DGoodsAct extends BaseActivity {

	private View view;
	private ListView listView;
	private DGoodsAdapter adapter;

	JsonPrase jsonPrase = new JsonPrase();

	// ArrayList<PersonOrderBean> mList = jsonPrase.getOrderState(str);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dai_shouhuo);

		view = findViewById(R.id.dsh_view);
		PostBean postBean = new PostBean();
		// for (int i = 0; i < 6; i++) {
		// WineInfo info = new WineInfo();
		// info.setSendMerchant("长阳奥莱斯特红酒屋");
		// info.setWinePrice("总价：1000元      ");
		// info.setWineDese("德国原装进口黑啤酒" + i);
		// info.setWinNum("数量：" + i);
		// info.setArrivatime("预计送达时间：11:50");
		// list.add(info);
		// }
		adapter = new DGoodsAdapter(this, dgOrderList, new CallBack());
		listView = (ListView) findViewById(R.id.lv_dsh);
		listView.setAdapter(adapter);
		Register register = SaveApplicationParam.getRegister(this);
		postBean.setCode(Const.PERSONORDER);
		postBean.setPhoneNumber(register.getPhone());
		postBean.setPassword(register.getPwd());
		postBean.setStatusId("ORDER_SENT");
//		postBean.setStatusId("ORDER_COMPLETED");
		Task task = new Task(postBean, DGoodsAct.this, new Back());
		task.postHttp();

		Logic logic = Logic.getInstance();
		logic.initHeadView(view, "待收货", true, "", new ECallBack() {

			public void OnCreate(Object obj) {
				// TODO Auto-generated method stub
				finish();
			}

			public void OnError(Object obj) {
				// TODO Auto-generated method stub

			}

		});
//		BroadcastReceiver myReceiver = new BroadcastReceiver() { 
//			 
//	        @Override 
//	        public void onReceive(Context context, Intent intent) { 
//	        	dgOrderList.remove(intent);
//				adapter.notifyDataSetChanged();
//	        }
//	    }; 
//		IntentFilter filter = new IntentFilter(); 
//		filter.addAction("delete");
//		registerReceiver(myReceiver, filter);
		
	}
	class CallBack implements ECallBack {

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			final int position = (Integer) obj;
			Intent intent = new Intent(DGoodsAct.this, CancelOrderAct.class);
			intent.putExtra("position", position);
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

			// JsonPrase jsonPrase = new JsonPrase();
			ArrayList<PersonOrderBean> mList = jsonPrase.getOrderState(str);
			for (int i = 0; i < mList.size(); i++) {
				for (int iu = 0; iu < mList.get(i).getOrderItemList().size(); iu++) {
					mList.get(i).getOrderItemList().get(iu)
							.setDate(mList.get(i).getOrderDate());
					mList.get(i).getOrderItemList().get(iu).setOrderId(mList.get(i).getOrderId());
				}
			}

			ArrayList<OrderItemList> pList = new ArrayList<OrderItemList>();
			for (PersonOrderBean personOrderBean : mList) {
				pList.addAll(personOrderBean.getOrderItemList());
			}
			dgOrderList.addAll(pList);
			adapter.notifyDataSetChanged();// 传递给适配器去改变内容
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(adapter!=null){
			adapter.notifyDataSetChanged();
		}
	}
}

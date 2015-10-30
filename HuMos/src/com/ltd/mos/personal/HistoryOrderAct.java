package com.ltd.mos.personal;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class HistoryOrderAct extends BaseActivity {

	private View view;
	private TextView tv_clear;
	private HistoryOrderAdapter adapter;
	private ArrayList<OrderItemList> list;
	private ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history_order);

		list = new ArrayList<OrderItemList>();
		PostBean postBean = new PostBean();
//		for (int i = 0; i < 5; i++) {
//			PushingBean info = new PushingBean();
//			info.setWinePrice("总价：118");
//			info.setWineDese("德国原装莫斯卡托香槟750ml" + i);
//			info.setTime("2014.08.22");
//			info.setWineNum("数量："+i+"瓶");
//
//			list.add(info);
//		}
		adapter = new HistoryOrderAdapter(this,list);
		listView = (ListView) findViewById(R.id.lv_history_order);
		listView.setAdapter(adapter);
		Register register = SaveApplicationParam.getRegister(this);
		postBean.setCode(Const.PERSONORDER);
//		postBean.setCode(Const.HEALTHDRINK);
		postBean.setPhoneNumber(register.getPhone());
		postBean.setPassword(register.getPwd());
		postBean.setStatusId("ORDER_COMPLETED");
		Task task = new Task(postBean, HistoryOrderAct.this, new Back());
		task.postHttp();
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(HistoryOrderAct.this,HistoryOrderDetailsAct.class);
				startActivity(intent);
			}
		});
		//长按
//		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
//
//			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
//					int position, long arg3) {
//				// TODO Auto-generated method stub
//				String item = (String) listView.getItemAtPosition(position); 
//				  
//                SharedPreferences Addresses = getSharedPreferences(FileListenerActivity.PREFS_NAME, 0); 
//                SharedPreferences.Editor editor = Addresses.edit(); 
//                editor.remove(item); 
//                editor.commit(); 
//                  
//                Toast.makeText(getBaseContext(), item + "被删除了", 
//                        Toast.LENGTH_SHORT).show(); 
//                  
//                 list.remove(item); 
//                 adapter.notifyDataSetChanged(); 
//  
//                return true; 
//
//			}
//		});

		view = findViewById(R.id.history_order_view);
		tv_clear = (TextView) findViewById(R.id.empty);
		tv_clear.setText(R.string.person_qk);

		Logic logic = Logic.getInstance();

		logic.showSet(view, false, true, new CallBack());
		logic.initHeadView(view, "历史订单", true, "", new CallBack());
	}

	class CallBack implements ECallBack {

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			if (obj.equals(Const.LEFT)) {
				// 返回上一级界面
				finish();
			} else {
				// 清空
				list.clear();
				adapter.notifyDataSetChanged();
			}
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}
	}
	class Back implements ECallBack{

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			ResultObject resultObject = (ResultObject) obj;
			String str = Logic.getString(resultObject.getmMessage());
			
			JsonPrase jsonPrase = new JsonPrase();
			ArrayList<PersonOrderBean> mList = jsonPrase.getOrderState(str);
			for (int i = 0; i < mList.size(); i++) {
				for (int iu = 0; iu < mList.get(i).getOrderItemList().size(); iu++) {
					mList.get(i).getOrderItemList().get(iu).setDate(mList.get(i).getOrderDate());
				}
			}
			
			ArrayList<OrderItemList> pList= new ArrayList<OrderItemList>();
			for (PersonOrderBean personOrderBean:mList) {
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

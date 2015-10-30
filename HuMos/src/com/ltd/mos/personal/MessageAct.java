package com.ltd.mos.personal;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.base.HuMosActivity;
import com.ltd.mos.bean.MessageBean;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

public class MessageAct extends BaseActivity implements HuMosActivity {

	private View view;
	private ListView listView;
	private MessageAdapter adapter;
	private ArrayList<MessageBean> list = new ArrayList<MessageBean>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message);

		// for (int i = 0; i < 5; i++) {
		// MessageBean info = new MessageBean();
		// info.setContent("您还有几个订单没有完成，您希望现在去立即完成这些订单吗？" + i);
		// list.add(info);
		// }
		adapter = new MessageAdapter(this, list);
		listView = (ListView) findViewById(R.id.lv_message);
		listView.setAdapter(adapter);
		invokePart();

		view = findViewById(R.id.message_view);
		Logic logic = Logic.getInstance();
		logic.showSet(view, false, true, new CallBack());
		logic.initHeadView(view, "消息", true, "清空", new CallBack());

	}

	private void invokePart() {
		// TODO Auto-generated method stub
		PostBean postBean = new PostBean();
		Register register = SaveApplicationParam.getRegister(this);
		// postBean.setCode(Const.SYSTEMMESSAGE);
		postBean.setCode(Const.SENDMESSAGE);
		postBean.setPhoneNumber(register.getPhone());
		postBean.setPassword(register.getPwd());
		Task task = new Task(postBean, MessageAct.this, new Back());
		task.postHttp();
	}

	class CallBack implements ECallBack {

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			if (obj.equals(Const.LEFT)) {
				finish();
			} else if (obj.equals(Const.RIGHT)) {
				// 清空
				list.clear();
				adapter.notifyDataSetChanged();
			}
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}
	}

	class Back implements ECallBack {
		// PostBean postBean;
		//
		// public Back(PostBean postBean) {
		// // TODO Auto-generated constructor stub
		// this.postBean = postBean;
		// }
		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			ResultObject resultObject = (ResultObject) obj;
			String str = Logic.getString(resultObject.getmMessage());

			JsonPrase jsonPrase = new JsonPrase();
			MessageBean message = jsonPrase.getsendMessage(str);
			list.add(message);
			adapter.notifyDataSetChanged();
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		allActivity.add(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		allActivity.remove(this);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		allActivity.remove(this);
	}

	// 消息数据处理
	public void refresh(Object... param) {
		Toast.makeText(getApplicationContext(), "收到广播", 1000).show();
		invokePart();
	}
}

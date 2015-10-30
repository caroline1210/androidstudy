package com.ltd.mos.personal;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.sercenter.SearchWineAdapter;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

//定制详情
public class CustomizeAct extends BaseActivity {

	private View view;
	private ListView listView;
	private SearchWineAdapter adapter;
	private ArrayList<PostBean> list;
//	private String[] wineinfo={ "使用场合：", "单价要求：", "数量/瓶：", "使用时间：","添加附件：","详细描述："};
//	private String[] winehint={"婚宴","300","50","2014-08-11","","请准时，如有变动请随时联系"};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customize);

		list = new ArrayList<PostBean>();

//		list=getList(null, winehint, wineinfo);
		listView = (ListView) findViewById(R.id.lv_customize);
		adapter = new SearchWineAdapter(list, this, new CallBack());
		listView.setAdapter(adapter);
		PostBean postBean=new PostBean();
		Register register = SaveApplicationParam.getRegister(this);
		postBean.setCode(Const.CUSTOMIZED);
		postBean.setPhoneNumber(register.getPhone());
		postBean.setPassword(register.getPwd());
		Task task = new Task(postBean, CustomizeAct.this, new Back());
		task.postHttp();

		view = findViewById(R.id.customize_view);
		Logic logic = Logic.getInstance();
		logic.showSet(view, false, true, new CallBack());
		logic.initHeadView(view, "定制详情", true, "", new CallBack());
	}

	class CallBack implements ECallBack {

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			if (obj.equals(Const.LEFT)) {
				// 返回上一级界面
				finish();
			} else {
				// 清空
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
			String str = resultObject.getmMessage();
			
			JsonPrase jsonPrase = new JsonPrase();
			list.addAll(jsonPrase.getXunjiuList(str));
			adapter.notifyDataSetChanged();
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub
			
		}
	}
//	private ArrayList<TailorBean> getList(String[] content, String[] hint,
//			String[] title) {
//		ArrayList<TailorBean> list = new ArrayList<TailorBean>();
//		for (int i = 0; i < title.length; i++) {
//			TailorBean tailorBean = new TailorBean();
//			tailorBean.setTitle(title[i]);
//			if (hint != null) {
//				tailorBean.setContentHint(hint[i]);
//			}
//			if (content != null) {
//				tailorBean.setContent(content[i]);
//			}
//			list.add(tailorBean);
//		}
//		return list;
//
//	}
}

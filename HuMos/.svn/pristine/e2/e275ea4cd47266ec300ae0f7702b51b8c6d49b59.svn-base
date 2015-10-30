package com.ltd.mos.sercenter;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.bean.SearchWineBean;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

/**
 * Ñ°¾Æ
 * 
 * @author CaoT¡£
 * 
 */
public class SearchWineActivity extends BaseActivity {
	private ListView listView;
	private ArrayList<PostBean> list = new ArrayList<PostBean>();
	private SearchWineAdapter adapter;
	private View view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_wine);
		listView = (ListView) this.findViewById(R.id.listView);
		view = this.findViewById(R.id.view);
		Logic.getInstance().initHeadView(view, "Ñ°¾Æ", true, "ÎÒÒªÑ°",
				new HeadCallBack());
		adapter = new SearchWineAdapter(list, this, new CallBack());
		listView.setAdapter(adapter);
		getList();
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void getList() {
		PostBean postBean = new PostBean();
		postBean.setCode(Const.LISTPRODUCTREQUEST);
		Register register = SaveApplicationParam.getRegister(this);
		postBean.setPhoneNumber(register.getPhone());
		postBean.setPassword(register.getPwd());
		Task task = new Task(postBean, this, new ECallBack() {

			public void OnError(Object obj) {
				// TODO Auto-generated method stub

			}

			public void OnCreate(Object obj) {
				// TODO Auto-generated method stub
				ResultObject resultObject = (ResultObject) obj;
				JsonPrase jsonPrase = new JsonPrase();
				list.addAll(jsonPrase.getXunjiuList(resultObject.getmMessage()));
				adapter.notifyDataSetChanged();

			}
		});
		task.postHttp();
	}

	class CallBack implements ECallBack {

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			try {
				int position = (Integer) obj;
				// if (list.get(position).getSearchState() == 1) {
				// list.get(position).setSearchState(0);
				// list.get(position).setNum(list.get(position).getNum() + 1);
				// } else {
				// list.get(position).setSearchState(1);
				// list.get(position).setNum(list.get(position).getNum() - 1);
				// }
				adapter.notifyDataSetChanged();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}

	}

	class HeadCallBack implements ECallBack {

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			String flag = Logic.getString(obj);
			if (flag.equals(Const.LEFT)) {
				SearchWineActivity.this.finish();
			} else if (flag.equals(Const.RIGHT)) {
				startActivity(new Intent(SearchWineActivity.this,
						MySearchActivity.class));
			}
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}

	}
}

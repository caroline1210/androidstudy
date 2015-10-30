package com.ltd.mos.personal;

import java.util.ArrayList;

import android.os.Bundle;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.DjqBean;
import com.ltd.mos.util.Logic;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

//代金券
public class DaijinquanAct extends BaseActivity {

	View view;
	Button backbutton;
	private ListView listView;
	private DaijinquanAdapter adapter;
	private ArrayList<DjqBean> list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.daijinquan);

		list = new ArrayList<DjqBean>();

		for (int i = 0; i < 5; i++) {
			DjqBean info = new DjqBean();
			info.setDjqdese("新用户专项10元代金券");
			info.setGeneral("全场通用！");
			info.setValidaty("有效期至2014-08-30");

			list.add(info);
		}
		adapter = new DaijinquanAdapter(this, list);
		listView = (ListView) findViewById(R.id.lv_djq);
		listView.setAdapter(adapter);

		view = findViewById(R.id.djq_view);

		Logic logic = Logic.getInstance();
		logic.initHeadView(view, "代金券", true, "", new ECallBack() {

			public void OnError(Object obj) {
				// TODO Auto-generated method stub

			}

			public void OnCreate(Object obj) {
				// TODO Auto-generated method stub
				// Intent backIntent = new Intent(DaijinquanAct.this,
				// HomeTabActivity.class);
				// startActivity(backIntent);
				finish();
			}
		});
	}
}

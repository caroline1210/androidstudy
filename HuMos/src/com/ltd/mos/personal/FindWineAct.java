package com.ltd.mos.personal;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.WineInfo;
import com.ltd.mos.util.Logic;

public class FindWineAct extends BaseActivity {

	private View view;
	private FindWineAdapter adapter;
	private ListView listview;
	private ArrayList<WineInfo> list = new ArrayList<WineInfo>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findwine);

		listview = (ListView) findViewById(R.id.lv_findview);
		for (int i = 0; i < 10; i++) {
			WineInfo info = new WineInfo();
			if (i%2==0) {
				info.setFindwineState("待反馈");
			} else {
				info.setFindwineState("已完成");
			}
			info.setWineevent("【白酒】弄本崇明老白酒500ml世博酒 清甜型");
			info.setCustomTime("2014-09-06");
			list.add(info);
		}
		adapter = new FindWineAdapter(this, list);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
			}
		});

		view = findViewById(R.id.findwine_view);
		Logic logic = Logic.getInstance();
		logic.initHeadView(view, "我的寻酒", true, "", new CallBack());
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
}

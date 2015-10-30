package com.ltd.mos.sercenter;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.LeisureBean;
import com.ltd.mos.leisure.ArticleDetailActivity;
import com.ltd.mos.leisure.LeisureAdapter;
import com.ltd.mos.util.Logic;

public class LighthouseActivity extends BaseActivity {
	private View view;
	private Logic logic = Logic.getInstance();
	private ArrayList<LeisureBean> list = new ArrayList<LeisureBean>();
	private ViewGroup leftView, rightView;
	private TextView titleContent;
	private ListView listView;
	private LeisureAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.light_house);
		view = this.findViewById(R.id.view);
		logic.initHeadView(view, "∆ÿπ‚Ã®", true, "", new ECallBack() {

			public void OnError(Object obj) {
				// TODO Auto-generated method stub

			}

			public void OnCreate(Object obj) {
				// TODO Auto-generated method stub
				LighthouseActivity.this.finish();
			}
		});
		titleContent = (TextView) this.findViewById(R.id.title_content);
		titleContent.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Logic.getInstance().setCurrentLightHouse(list.get(1));
				startActivity(new Intent(LighthouseActivity.this,
						LighthouseDetailActivity.class));
			}
		});
		rightView = (ViewGroup) this.findViewById(R.id.rightView);
		rightView.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Logic.getInstance().setCurrentLightHouse(list.get(1));
				startActivity(new Intent(LighthouseActivity.this,
						LighthouseDetailActivity.class));
			}
		});
		leftView = (ViewGroup) this.findViewById(R.id.leftView);
		leftView.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Logic.getInstance().setCurrentLightHouse(list.get(1));
				startActivity(new Intent(LighthouseActivity.this,
						LighthouseDetailActivity.class));
			}
		});
		listView = (ListView) this.findViewById(R.id.listView);
		list = getList(5);
		adapter = new LeisureAdapter(this, list, null);
		listView.setAdapter(adapter);
		logic.initListView(listView, 15, this);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Logic.getInstance().setCurrentLightHouse(list.get(arg2));
				startActivity(new Intent(LighthouseActivity.this,
						LighthouseDetailActivity.class));
			}
		});
	}

	private ArrayList<LeisureBean> getList(int num) {
		ArrayList<LeisureBean> list = new ArrayList<LeisureBean>();
		for (int i = 0; i < num; i++) {
			LeisureBean leisureBean = new LeisureBean();
			leisureBean.setNoteInfo("æ∞—Ù∏‘æ∆µ¿π›÷Æ ¢ ¿æ∆¥º" + i);
			list.add(leisureBean);
		}
		return list;

	}
}

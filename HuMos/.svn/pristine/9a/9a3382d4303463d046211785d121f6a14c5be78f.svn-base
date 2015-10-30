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
import com.ltd.mos.bean.Tasting;
import com.ltd.mos.util.Logic;
/**
 * 品酒会
 * @author CaoT。
 *
 */
public class TastingActivity extends BaseActivity {
	private View view;
	private ListView listView;
	private ArrayList<Tasting> list = new ArrayList<Tasting>();
	private TastingAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tasting);
		view = this.findViewById(R.id.view);
		Logic.getInstance().initHeadView(view, "品酒会", true, "",
				new ECallBack() {

					public void OnError(Object obj) {
						// TODO Auto-generated method stub

					}

					public void OnCreate(Object obj) {
						// TODO Auto-generated method stub
						TastingActivity.this.finish();
					}
				});
		list = getList();
		listView = (ListView) this.findViewById(R.id.listView);
		adapter = new TastingAdapter(this, list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				startActivity(new Intent(TastingActivity.this,
						TastingDetailActivity.class));
			}
		});
	}

	private ArrayList<Tasting> getList() {
		ArrayList<Tasting> list = new ArrayList<Tasting>();
		for (int i = 0; i < 12; i++) {
			Tasting tasting = new Tasting();
			tasting.setTitle("安东尼世家葡萄酒晚宴");
			tasting.setAddress("朝阳三里屯路南 36-1楼 2门 101");
			tasting.setTime("下周三  晚上6开始");
			tasting.setUser("红樽坊");
			list.add(tasting);
		}
		return list;
	}
}

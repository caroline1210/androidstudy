package com.ltd.mos.personal;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.WineInfo;
import com.ltd.mos.util.Logic;

public class MyCollectionAct extends BaseActivity {

	Button backButton;
	View view;
	private ListView listView;
	private MycollectAdapter adapter;
	private ArrayList<WineInfo> list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mycollection);
		
list = new ArrayList<WineInfo>();
		
		for (int i = 0; i < 7; i++) {
			WineInfo info = new WineInfo();
			info.setWinePrice("258.0");
			info.setWineDese("法国原装进口麦洛红酒750ml" + i);
			info.setMarketPrice("市场价：125");
			info.setLocation("2.5km");
			info.setWineevent("国际葡萄酒挑战获奖酒");
			
			list.add(info);
		}
		adapter = new MycollectAdapter(this,list);
		listView = (ListView) findViewById(R.id.lv_mycollect);
		listView.setAdapter(adapter);

		view = findViewById(R.id.mycollection_view);
		Logic logic = Logic.getInstance();
		
//		logic.initHeadView(view, "", true, false, new Ecallback());
		logic.initHeadView(view, "我的收藏", true, "", new ECallBack() {

			public void OnError(Object obj) {
				// TODO Auto-generated method stub

			}

			public void OnCreate(Object obj) {
				// TODO Auto-generated method stub
//				Intent backIntent = new Intent(MyCollectionAct.this,
//						HomeTabActivity.class);
//				startActivity(backIntent);
				finish();
			}
		});
	}
}

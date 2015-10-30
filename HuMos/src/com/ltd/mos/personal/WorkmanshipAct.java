package com.ltd.mos.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.util.Logic;

//商品评价
public class WorkmanshipAct extends BaseActivity {

	private View view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workmanship);

		init();

		view = findViewById(R.id.workmanship_view);

		Logic logic = Logic.getInstance();
		logic.initHeadView(view, "商品评价", true, "", new ECallBack() {

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

	private void init() {
		// TODO Auto-generated method stub
	}
}

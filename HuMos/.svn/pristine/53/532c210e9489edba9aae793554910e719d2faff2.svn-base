package com.ltd.mos.personal;

import android.os.Bundle;
import android.view.View;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.util.Logic;

public class OrderDetailsAct extends BaseActivity {

	private View view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_details);

		view = findViewById(R.id.order_details_view);

		Logic logic = Logic.getInstance();
		logic.initHeadView(view, "∂©µ•œÍ«È", true, "", new ECallBack() {

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

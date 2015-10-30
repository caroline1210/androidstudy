package com.ltd.mos.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.util.Logic;

//更换绑定手机
public class BindMobileAct extends BaseActivity {

	private View view;
	@SuppressWarnings("unused")
	private LinearLayout ll_back;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bind_mobile);

		view = findViewById(R.id.bind_view);
		ll_back = (LinearLayout) findViewById(R.id.head_back);

		Logic logic = Logic.getInstance();
		logic.initHeadView(view, "更换绑定手机", true, "", new ECallBack() {

			public void OnError(Object obj) {
				// TODO Auto-generated method stub

			}

			public void OnCreate(Object obj) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}

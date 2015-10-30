package com.ltd.mos.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.util.Logic;

//取消订单
public class CancelOrderAct extends BaseActivity {

	private View view;
	private Button cancelButton;
	private EditText et_cause;
	private CheckBox cb_order, cb_error, cb_speed, cb_other;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cancel_order);
		initView();
		initListener();
		cancelButton = (Button) findViewById(R.id.b_cancel_order);
		cancelButton.setOnClickListener(new OnClickListener() {
			// 取消订单
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!cb_order.isChecked() && !cb_error.isChecked()
						&& !cb_speed.isChecked() && !cb_other.isChecked()) {
					Toast.makeText(getApplicationContext(), "请选择一个理由", 1000)
							.show();
				} else if (cb_other.isChecked()&&et_cause.length()==0) {
					Toast.makeText(getApplicationContext(), "请说明其他原因", 1000)
					.show();
				} else {
					Toast.makeText(getApplicationContext(), "已取消该订单", 1000)
							.show();
//					startActivity(new Intent(CancelOrderAct.this,PushingAct.class));
					finish();
				}
			}
		});

		view = findViewById(R.id.cancel_view);
		Logic logic = Logic.getInstance();
		logic.initHeadView(view, "取消订单", true, "", new ECallBack() {

			public void OnCreate(Object obj) {
				// TODO Auto-generated method stub
				finish();
			}

			public void OnError(Object obj) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initListener() {
		// TODO Auto-generated method stub
		cb_order.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (cb_order.isChecked()) {
					cb_error.setChecked(false);
					cb_speed.setChecked(false);
				}
			}
		});
		cb_error.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (cb_error.isChecked()) {
					cb_order.setChecked(false);
					cb_speed.setChecked(false);
				}
			}
		});
		cb_speed.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (cb_speed.isChecked()) {
					cb_error.setChecked(false);
					cb_order.setChecked(false);
				}
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		cb_order = (CheckBox) findViewById(R.id.cb_order);
		cb_error = (CheckBox) findViewById(R.id.cb_error);
		cb_speed = (CheckBox) findViewById(R.id.cb_speed);
		cb_other = (CheckBox) findViewById(R.id.cb_other);
		et_cause = (EditText) findViewById(R.id.et_cancel_order_cause);
	}
}

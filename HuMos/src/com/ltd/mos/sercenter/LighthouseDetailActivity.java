package com.ltd.mos.sercenter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.util.Logic;

public class LighthouseDetailActivity extends BaseActivity {
	TextView content;
	View view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.light_house_detail);
		content = (TextView) this.findViewById(R.id.content);
		view = this.findViewById(R.id.view);
		Logic.getInstance().initHeadView(view, "∆ÿπ‚Ã®", true, "", new ECallBack() {
			
			public void OnError(Object obj) {
				// TODO Auto-generated method stub
				
			}
			
			public void OnCreate(Object obj) {
				// TODO Auto-generated method stub
				LighthouseDetailActivity.this.finish();
			}
		});
		content.setText(1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  " + 1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  " + 1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  " + 1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  " + 1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  " + 1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  " + 1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  " + 1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  " + 1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  " + 1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  " + 1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  " + 1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  " + 1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  " + 1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  " + 1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  " + 1 + "  " + 1 + "  " + 1 + "  " + 1
				+ "  ");
	}
}

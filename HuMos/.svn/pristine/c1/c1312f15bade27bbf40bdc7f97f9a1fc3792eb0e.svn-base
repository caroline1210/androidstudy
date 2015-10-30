package com.ltd.mos.leisure;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.util.Logic;

public class ArticleDetailActivity extends BaseActivity {
	TextView content;
	View view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.article);
		content = (TextView) this.findViewById(R.id.content);
		view = this.findViewById(R.id.view);
		
		String value = Logic.getString(Logic.getInstance().getCurrentLeisure().getNoteInfo());
		Logic.getInstance().initHeadView(view, value, true, "", new ECallBack() {
			
			public void OnError(Object obj) {
				// TODO Auto-generated method stub
				
			}
			
			public void OnCreate(Object obj) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		content.setText(value + "  " + value + "  " + value + "  " + value
				+ "  " + value + "  " + value + "  " + value + "  " + value
				+ "  " + value + "  " + value + "  " + value + "  " + value
				+ "  " + value + "  " + value + "  " + value + "  " + value
				+ "  " + value + "  " + value + "  " + value + "  " + value
				+ "  " + value + "  " + value + "  " + value + "  " + value
				+ "  " + value + "  " + value + "  " + value + "  " + value
				+ "  " + value + "  " + value + "  " + value + "  " + value
				+ "  " + value + "  " + value + "  " + value + "  " + value
				+ "  " + value + "  " + value + "  " + value + "  " + value
				+ "  " + value + "  " + value + "  " + value + "  " + value
				+ "  " + value + "  " + value + "  " + value + "  " + value
				+ "  " + value + "  " + value + "  " + value + "  " + value
				+ "  " + value + "  " + value + "  " + value + "  " + value
				+ "  " + value + "  " + value + "  " + value + "  " + value
				+ "  " + value + "  " + value + "  " + value + "  " + value
				+ "  ");
	}
}

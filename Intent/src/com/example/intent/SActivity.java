package com.example.intent;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SActivity extends Activity {
private Button bt;
private String content="���";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sactivity);
		//�ڶ������ʲ�N�r��o��һ�����؂�����,�؂���һ�����Č��H��һ��intent����
		bt=(Button) findViewById(R.id.button1);
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent data=new Intent();
				data.putExtra("data", content);
				setResult(2, data);
				finish();
				
				
			}
		});
	}
}

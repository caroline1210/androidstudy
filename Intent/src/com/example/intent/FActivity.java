package com.example.intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class FActivity extends Activity {
	private Button bt1;
	private Button bt2;
	private Context mContext;
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.factivity);
		//通過點擊bt1實現頁面之間的跳轉，通過startActivity方式實現
		//初始化intent
		mContext=this;
		tv=(TextView) findViewById(R.id.textView1);
		bt1=(Button) findViewById(R.id.button1_first);
		bt2=(Button) findViewById(R.id.button2_second);
		//註冊點擊使用
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(mContext,SActivity.class);
				//第一個參數是INTENT對象，第二個參數是請求的一個標識
				startActivityForResult(intent, 1);
				
			}
		});
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//第一個參數上下文對象，第二個參數目標文件
				Intent intent=new Intent(mContext, SActivity.class);
				startActivity(intent);
				
			}
		});
	}
	
	//requestcode：請求的標識
	//resultcode:第二個頁面返回的標識，data是第二個頁面回傳的數據
	
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
	super.onActivityResult(requestCode, resultCode, data);
	if(requestCode==1&&resultCode==2){
		String content=data.getStringExtra("data");
		tv.setText(content);
	}
}
}

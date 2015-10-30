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
		//ͨ�^�c��bt1���F���֮�g�����D��ͨ�^startActivity��ʽ���F
		//��ʼ��intent
		mContext=this;
		tv=(TextView) findViewById(R.id.textView1);
		bt1=(Button) findViewById(R.id.button1_first);
		bt2=(Button) findViewById(R.id.button2_second);
		//�]���c��ʹ��
		bt2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(mContext,SActivity.class);
				//��һ��������INTENT���󣬵ڶ���������Ո���һ�����R
				startActivityForResult(intent, 1);
				
			}
		});
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//��һ�����������Č��󣬵ڶ�������Ŀ���ļ�
				Intent intent=new Intent(mContext, SActivity.class);
				startActivity(intent);
				
			}
		});
	}
	
	//requestcode��Ո��Ę��R
	//resultcode:�ڶ�����淵�صĘ��R��data�ǵڶ������؂��Ĕ���
	
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
	super.onActivityResult(requestCode, resultCode, data);
	if(requestCode==1&&resultCode==2){
		String content=data.getStringExtra("data");
		tv.setText(content);
	}
}
}

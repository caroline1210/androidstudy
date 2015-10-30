package com.example.checkbox;


import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class MainActivity extends Activity {
private CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //��ʼ��checkbox
        checkBox=(CheckBox) findViewById(R.id.checkBox1);
        //ͨ������checkbox�ļ����¼�����checkbox�ǲ��Ǳ�ѡ��
        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){
        	@Override
        	public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
        	//ͨ��oncheckedchanged������checkbox�Ƿ�ѡ��
        	if(isChecked){
        		String text=checkBox.getText().toString();
        		Log.i("tag", text);
        	}}
        });
    }


   
}

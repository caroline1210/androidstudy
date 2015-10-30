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
        //初始化checkbox
        checkBox=(CheckBox) findViewById(R.id.checkBox1);
        //通过设置checkbox的监听事件来对checkbox是不是被选中
        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){
        	@Override
        	public void onCheckedChanged(CompoundButton buttonView,boolean isChecked){
        	//通过oncheckedchanged来监听checkbox是否被选中
        	if(isChecked){
        		String text=checkBox.getText().toString();
        		Log.i("tag", text);
        	}}
        });
    }


   
}

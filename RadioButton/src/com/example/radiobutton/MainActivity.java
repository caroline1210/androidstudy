package com.example.radiobutton;


import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;



public class MainActivity extends Activity implements OnCheckedChangeListener{
private RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg=(RadioGroup) findViewById(R.id.radioGroup1);
        
        //实现Radiogroup的一个监听 事件
        rg.setOnCheckedChangeListener(this);
    }
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
	switch(checkedId){
	case R.id.radio0:
		Log.i("tag", "你当前是一个男孩");
		break;
	case R.id.radio1:
		Log.i("tag", "你当前是一个女孩");
		break;
	}
		
	}
	


   
}

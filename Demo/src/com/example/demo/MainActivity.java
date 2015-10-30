package com.example.demo;


import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ToggleButton;



public class MainActivity extends Activity implements OnCheckedChangeListener{
	private ToggleButton tb;
	private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //初始化控件
        tb=(ToggleButton) findViewById(R.id.toggleButton1);
        img=(ImageView) findViewById(R.id.imageView1);
        //给当前的tb设置监听器
        tb.setOnCheckedChangeListener(this);
    }
	
    
    @Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// 当tb被点击的时候，当前的方法会执行
    	//buttonview 代表被点击控件的本身
    	// ischecked 代表被点击的控件的状态
    	//当点击tb的时候，更换img的背景
    	img.setBackgroundResource(isChecked?R.drawable.on:R.drawable.off );
		
	}


   
}

package com.example.autocom;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;


public class MainActivity extends Activity {
private AutoCompleteTextView acTextView;
private String[] res={"beijing1","beijing2","beijing3",
		"shanghai1","shanghai2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //第一步：初始化控件    第二步：需要一个适配器  第三步：初始化数据源
        //---去匹配文本框中输入的内容   第四步：将adpter与当前autocompletetextview绑定
        acTextView=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
    		android.R.layout.simple_list_item_1, res);
    acTextView.setAdapter(adapter);
    }


   
}

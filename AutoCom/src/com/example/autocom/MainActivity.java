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
        
        //��һ������ʼ���ؼ�    �ڶ�������Ҫһ��������  ����������ʼ������Դ
        //---ȥƥ���ı��������������   ���Ĳ�����adpter�뵱ǰautocompletetextview��
        acTextView=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,
    		android.R.layout.simple_list_item_1, res);
    acTextView.setAdapter(adapter);
    }


   
}

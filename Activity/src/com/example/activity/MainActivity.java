package com.example.activity;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;



public class MainActivity extends Activity {
final String TAG="tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "MainActivity-->onCreate");
    }
    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
    	 Log.i(TAG, "MainActivity-->onStart");
    }
@Override
protected void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	 Log.i(TAG, "MainActivity-->onResume");
}
@Override
protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	 Log.i(TAG, "MainActivity-->onPause");
}
@Override
protected void onStop() {
	// TODO Auto-generated method stub
	super.onStop();
	 Log.i(TAG, "MainActivity-->onStop");
}
@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
	 Log.i(TAG, "MainActivity-->onDestroy");
}

   
}

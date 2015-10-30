package com.ltd.mos.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.util.Logic;

public class InviteFriendAct extends BaseActivity{

	private View view;
	private ImageView iv_wx,iv_wxcircle;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.invitefriend);
		
		view=findViewById(R.id.invite_view);
		Logic logic=Logic.getInstance();
		logic.initHeadView(view, "ÑûÇëºÃÓÑ", true, "", new CallBack());
	}
	class CallBack implements ECallBack{

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			finish();
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub
			
		}}
}

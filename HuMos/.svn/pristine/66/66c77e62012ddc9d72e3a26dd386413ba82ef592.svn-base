package com.ltd.mos.base;

import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.personal.MessageAct;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	Context context;

	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		if (SaveApplicationParam.getLandState(context))// 登陆状态
			dataDispose();

	}

	// 数据处理
	public void dataDispose() {

		try {
			MessageAct messageAct = (MessageAct) BaseActivity
					.getActivityByName(".MessageAct");

			if (messageAct != null) {
				messageAct.refresh();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

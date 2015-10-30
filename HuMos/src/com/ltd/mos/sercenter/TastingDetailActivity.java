package com.ltd.mos.sercenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

public class TastingDetailActivity extends BaseActivity {
	private View view;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tasting_detail);
		view = findViewById(R.id.view);
		Logic.getInstance().initGrapeTitle(view, getString(R.string.details),
				false, true, new EcallBack());
	}

	class EcallBack implements ECallBack {

		public void OnCreate(Object obj) {
			if (Const.SHARE.equals(obj)) {
				onClickShare();
			} else if (Const.LEFT.equals(obj)) {
				finish();
			} else {
				Toast.makeText(getApplicationContext(), "收藏", 1000).show();
			}
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}

	}

	// 测试
	public void onClickShare() {

		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("image/*");
		intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
		intent.putExtra(Intent.EXTRA_TEXT, "亲们！这款酒不错哦~~~~~");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(Intent.createChooser(intent, getTitle()));

	}
}

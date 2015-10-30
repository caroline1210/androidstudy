package com.ltd.mos.main;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.util.Logic;

/**
 * 查看全部评价界面
 * 
 * @author xuwu
 * 
 */
public class AvaluateActivity extends BaseActivity {

	private ListView avaluate_listview;
	private TextView avaluate_title;
	private ImageView synthesize_score;
	private View view;
	private ProgressBar progressBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avaluates);

		view = findViewById(R.id.view);
		avaluate_listview = (ListView) findViewById(R.id.avaluate_listview);
		avaluate_title = (TextView) findViewById(R.id.avaluate_title);
		synthesize_score = (ImageView) findViewById(R.id.synthesize_score);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);

		Logic.getInstance().initGrapeTitle(view, "全部评价", true, false,
				new ECallBack() {

					public void OnError(Object obj) {
						// TODO Auto-generated method stub

					}

					public void OnCreate(Object obj) {
						finish();

					}
				});
	}
}

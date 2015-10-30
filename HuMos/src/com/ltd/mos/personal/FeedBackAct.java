package com.ltd.mos.personal;

import java.util.ArrayList;
import java.util.Calendar;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.FeedbackBean;
import com.ltd.mos.util.Logic;

//意见反馈
public class FeedBackAct extends BaseActivity implements OnClickListener {

	private View view;
	private FeedbackAdapter adapter;
	private Button sendButton;
	private EditText et_content;
	private ListView listView;
	private ArrayList<FeedbackBean> list = new ArrayList<FeedbackBean>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_feedback);

		view = findViewById(R.id.feedback_view);
		initView();
		initData();

		Logic logic = Logic.getInstance();
		logic.initHeadView(view, "意见反馈", true, "", new ECallBack() {

			public void OnError(Object obj) {
				// TODO Auto-generated method stub

			}

			public void OnCreate(Object obj) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private String[] msgArray = new String[] { "需要更新", "怎么更新", "个人设置中有", "哦" };

	private String[] dataArray = new String[] { "2014-08-26 18:00",
			"2014-08-26 18:00", "2014-08-26 18:00", "2014-08-26 18:00" };

	/**
	 * 填充数据
	 * 
	 */
	private void initData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 4; i++) {
			FeedbackBean info = new FeedbackBean();
			info.setDate(dataArray[i]);
			if (i % 2 == 0) {
				info.setName("客服");
				info.setComMeg(true);
			} else {
				info.setName("客户");
				info.setComMeg(false);
			}
			info.setText(msgArray[i]);
			list.add(info);
		}
		adapter = new FeedbackAdapter(this, list);
		listView.setAdapter(adapter);
	}

	private void initView() {
		// TODO Auto-generated method stub
		sendButton = (Button) findViewById(R.id.b_feedback_send);
		sendButton.setOnClickListener(this);
		listView = (ListView) findViewById(R.id.lv_feedback);
		et_content = (EditText) findViewById(R.id.et_feedback);
		//监听editview中字符个数的方法，类似微博的输入字符个数限制
		et_content.addTextChangedListener(mtextwatch);
	}
	
	TextWatcher mtextwatch = new TextWatcher() {
		private CharSequence temp;
		private int editStart;
		private int editEnd;

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub
			temp = s;
		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			editStart = et_content.getSelectionStart();
			editEnd = et_content.getSelectionEnd();
			if (temp.length() != 0) {
				sendButton.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.click_black_gray));
				sendButton.setTextColor(Color.WHITE);
			} else {
				sendButton.setBackgroundDrawable(getResources().getDrawable(
						R.drawable.click_send_bg));
				sendButton.setTextColor(Color.BLACK);
			}
		}
	};

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.b_feedback_send) {
			if (et_content.length() == 0) {
				Toast.makeText(FeedBackAct.this, "此处不能为空", Toast.LENGTH_SHORT)
						.show();
			} else {
				send();
			}
		}
	}

	private void send() {
		String contString = et_content.getText().toString();
		if (contString.length() > 0) {
			FeedbackBean info = new FeedbackBean();
			info.setDate(getDate());
			info.setName("客户");
			info.setComMeg(false);
			info.setText(contString);

			list.add(info);
			adapter.notifyDataSetChanged();// 刷新信息

			et_content.setText("");
			// 使发的信息总是出现在屏幕最下方
			listView.setSelection(listView.getCount() - 1);
		}
	}

	/**
	 * 得到日期格式
	 * 
	 * @return
	 */
	public String getDate() {
		Calendar calendar = Calendar.getInstance();
		String year = String.valueOf(calendar.get(Calendar.YEAR));
		String month = String.valueOf(calendar.get(Calendar.MONTH));
		String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH) + 1);
		String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		String mins = String.valueOf(calendar.get(Calendar.MINUTE));

		StringBuffer sb = new StringBuffer();
		sb.append(year + "-" + month + "-" + day + " " + hour + ":" + mins);

		return sb.toString();
	}
}

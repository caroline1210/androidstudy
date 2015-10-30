package com.ltd.mos.personal;

import java.util.ArrayList;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.bean.TailorBean;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.login.LoginActivity;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

public class SettingAct extends BaseActivity implements OnClickListener {

	private View view;
	private ListView lv_user, lv_setting, lv_other;
	private SettingAdapter userAdapter, settingAdapter, otherAdapter;
	private Button b_exit;
	private String[] userInfo = { "用户名", "修改密码" };
	private String[] user = { "修改", "" };
	private String[] setting = { "消息推送", "声音提醒", "2G/3G下使用无图模式" };
	private String[] other = { "意见反馈", "常见问题", "检查更新" };
	private int[] setUser = { R.drawable.person_setting,
			R.drawable.person_setting };
	private int[] setOther = { R.drawable.person_setting,
			R.drawable.person_setting, R.drawable.person_setting };
	private ArrayList<TailorBean> contentList = new ArrayList<TailorBean>();
	private ArrayList<TailorBean> titleList = new ArrayList<TailorBean>();
	private ArrayList<TailorBean> otherList = new ArrayList<TailorBean>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		initView();
		/**
		 * 
		 * 填充表数据
		 */
		contentList = getList(user, userInfo, setUser);
		contentList = getList(userInfo, user, setUser);
		userAdapter = new SettingAdapter(this, contentList, false);
		lv_user.setAdapter(userAdapter);
		//
		int[] resource = new int[3];
		if (SettingAdapter.msgState1) {
			resource[0] = R.drawable.person_open;
		} else {
			resource[0] = R.drawable.person_close;
		}

		if (SettingAdapter.msgState2) {
			resource[1] = R.drawable.person_open;
		} else {
			resource[1] = R.drawable.person_close;
		}
		if (SettingAdapter.msgState3) {
			resource[2] = R.drawable.person_open;
		} else {
			resource[2] = R.drawable.person_close;
		}
		titleList = getList(setting, null, resource);
		settingAdapter = new SettingAdapter(this, titleList, false);
		lv_setting.setAdapter(settingAdapter);
		//
		otherList = getList(other, null, setOther);
		otherAdapter = new SettingAdapter(this, otherList, true);
		lv_other.setAdapter(otherAdapter);
		// 获取当前的状态
		// getSharedPreferencesValues();
		// getSharedPreferencesVoice();
		// getSharedPreferencesNoPaint();

		registListener();
		view = findViewById(R.id.setting_view);

		Logic logic = Logic.getInstance();
		logic.initHeadView(view, "设置", true, "", new ECallBack() {

			public void OnError(Object obj) {
				// TODO Auto-generated method stub

			}

			public void OnCreate(Object obj) {
				// TODO Auto-generated method stub

				finish();
			}
		});
	}

	/**
	 * 
	 * 设定消息推送,声音提醒，2G/3G下是否无图状态， 并在进去界面是就判断按钮的状态
	 */
	// private boolean msgState;
	// private boolean voiceState;
	// private boolean nopaintState;

	// read state
	// private void getSharedPreferencesValues() {
	// msgState = SaveApplicationParam.getMsgSendVal(this);
	//
	// }
	//
	// private void getSharedPreferencesVoice() {
	// // TODO Auto-generated method stub
	// voiceState = SaveApplicationParam.getVoiceRemind(this);
	// //
	// System.out.println("**************voiceState**************"+voiceState);
	// }
	//
	// private void getSharedPreferencesNoPaint() {
	// // TODO Auto-generated method stub
	// nopaintState = SaveApplicationParam.getNoPaint(this);
	//
	// }

	// 控件初始化
	private void initView() {
		// TODO Auto-generated method stub
		lv_user = (ListView) findViewById(R.id.lv_user_setting);
		lv_setting = (ListView) findViewById(R.id.lv_setting);
		lv_other = (ListView) findViewById(R.id.lv_setting_other);
		b_exit = (Button) findViewById(R.id.b_setting_exit);
		b_exit.setOnClickListener(this);

		/**
		 * 
		 * ******************
		 */
		// if (msgState)
		// iv_message_pushing.setImageResource(R.drawable.person_open);
		// else
		// iv_message_pushing.setImageResource(R.drawable.person_close);
		// if (voiceState)
		// iv_voice_remind.setImageResource(R.drawable.person_open);
		// else
		// iv_voice_remind.setImageResource(R.drawable.person_close);
		// if (nopaintState)
		// iv_nopaint.setImageResource(R.drawable.person_open);
		// else
		// iv_nopaint.setImageResource(R.drawable.person_close);

	}

	private void registListener() {

		// listView用户基本信息部分
		lv_user.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (Logic.getLoginState(SettingAct.this)) {
					switch (position) {
					case 0:
						startActivityForResult(new Intent(SettingAct.this,
								ChangeUserNameAct.class), 200);
						break;
					case 1:
						startActivity(new Intent(SettingAct.this,
								ChangePWDAct.class));
						break;
					case 2:
						startActivity(new Intent(SettingAct.this,
								BindMobileAct.class));
						break;
					}
				} else {
					startActivity(new Intent(SettingAct.this,
							LoginActivity.class));
				}

			}
		});
		// 其他
		lv_other.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				switch (position) {
				case 0:
					if (Logic.getLoginState(SettingAct.this)) {
						startActivity(new Intent(SettingAct.this,
								FeedBackAct.class));
					} else {
						startActivity(new Intent(SettingAct.this,
								LoginActivity.class));
					}
					break;
				case 1:
					startActivity(new Intent(SettingAct.this, QuestionAct.class));
					break;
				case 2:
					Toast.makeText(getApplicationContext(), "建设中",
							Toast.LENGTH_SHORT).show();
					Register register = SaveApplicationParam
							.getRegister(SettingAct.this);
					PostBean postBean = new PostBean();
					postBean.setCode(Const.UPDATEVERSION);
					postBean.setPhoneNumber(register.getPhone());
					postBean.setPassword(register.getPwd());
					postBean.setVersion(getVersion());//
					Task task = new Task(postBean, SettingAct.this, new Back());
					task.postHttp();
					break;
				}
			}
		});
	}

	class Back implements ECallBack {

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			ResultObject result = (ResultObject) obj;
			String str = Logic.getString(result.getmMessage());
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}
	}

	// 获取当前版本号
	private String getVersion() {
		try {
			PackageManager manager = this.getPackageManager();
			PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
			return info.packageName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}

	// public void getValue(){
	// Register register = SaveApplicationParam.getRegister(this);//
	// PostBean postBean = new PostBean();
	// postBean.setCode(Const.UPDATEVERSION);
	// postBean.setPhoneNumber(register.getPhone());
	// postBean.setPassword(register.getPwd());
	// return;
	// }
	/**
	 * 生成数据方法 默认顺序，便于维护
	 */
	private ArrayList<TailorBean> getList(String[] title, String[] content,
			int[] resource) {
		ArrayList<TailorBean> list = new ArrayList<TailorBean>();
		for (int i = 0; i < resource.length; i++) {
			TailorBean tailorBean = new TailorBean();
			if (content != null) {
				tailorBean.setContent(content[i]);
			}
			if (title != null) {
				tailorBean.setTitle(title[i]);
			}
			if (resource != null) {
				tailorBean.setResource(resource[i]);
			}
			list.add(tailorBean);
		}
		return list;

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.b_setting_exit:
			Logic.changeLoginState(SettingAct.this, false);
			finish();
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			finish();
		}

	}
}

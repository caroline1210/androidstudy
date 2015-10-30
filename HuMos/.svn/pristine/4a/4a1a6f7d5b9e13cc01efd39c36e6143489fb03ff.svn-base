package com.ltd.mos.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.HumosBean;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.http.HttpUtil;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.login.LoginActivity.CallBack;
import com.ltd.mos.main.HomeTabActivity;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

public class SetPassword extends BaseActivity {
	private EditText password;
	private Button register;
	private View view;
	private ViewGroup head_back;
	private TextView main_text;
	String phoneNum;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_password);
		Intent intent = getIntent();
		phoneNum = intent.getStringExtra("PHONENUM");
		view = this.findViewById(R.id.view);
		head_back = (ViewGroup) view.findViewById(R.id.head_back);
		head_back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				SetPassword.this.finish();
			}
		});
		main_text = (TextView) view.findViewById(R.id.main_text);
		main_text.setText("÷ÿ÷√√‹¬Î");
		head_back.setVisibility(View.VISIBLE);
		main_text.setVisibility(View.VISIBLE);
		password = (EditText) this.findViewById(R.id.password);
		register = (Button) this.findViewById(R.id.register);
		register.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				PostBean postBean = getValue();
				if (postBean == null) {
					return;
				}
				postBean.setCode(Const.RESETPASSWORD);
				Task task = new Task(postBean, SetPassword.this, new CallBack(
						postBean));
				task.postHttp();
			}
		});
	}

	private PostBean getValue() {
		String passwordContent = Logic.getString(password.getText());
		if (passwordContent.length() == 0) {
			Toast.makeText(SetPassword.this, "«Î ‰»Î—È÷§¬Î", 1000).show();
			return null;
		}
		PostBean postBean = new PostBean();
		postBean.setPhoneNumber(phoneNum);
		postBean.setPassword(passwordContent);
		return postBean;
	}

	class CallBack implements ECallBack {
		PostBean postBean;

		public CallBack(PostBean postBean) {
			// TODO Auto-generated constructor stub
			this.postBean = postBean;
		}

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			try {
				ResultObject result = (ResultObject) obj;
				String str = Logic.getString(result.getmMessage());
				JsonPrase jsonParser = new JsonPrase();
				if (jsonParser.getState(str)) {
					if (postBean.getCode() == Const.RESETPASSWORD
							&& jsonParser.getState(str)) {
						HttpUtil httpUtil = new HttpUtil(SetPassword.this);
						postBean.setCode(Const.GETUSERINFO);
						httpUtil.getUserInfo(postBean, new CallBack(postBean));
					} else if (postBean.getCode() == Const.GETUSERINFO
							&& jsonParser.getState(str)) {
						Toast.makeText(SetPassword.this, "√‹¬Î“—–ﬁ∏ƒ", 1000).show();
						startActivity(new Intent(SetPassword.this,
								HomeTabActivity.class)
								.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
						SaveApplicationParam
								.saveLandState(SetPassword.this, true);
						Register register = new Register();
						register.setPhone(postBean.getPhoneNumber());
						register.setPwd(postBean.getPassword());
						SaveApplicationParam.saveRegister(SetPassword.this,
								register);
					} else {
						SaveApplicationParam.saveLandState(SetPassword.this,
								false);
					}
				
				} else {
					Toast.makeText(SetPassword.this, "√‹¬Î÷ÿ÷√ ß∞‹£¨«Î÷ÿ ‘", 1000).show();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}
	}
}

package com.ltd.mos.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
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
import com.ltd.mos.main.HomeTabActivity;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

public class LoginActivity extends BaseActivity {
	private TextView verify;
	private EditText account;
	private EditText password;
	private Button login;
	private View view;
	private ViewGroup head_back;
	private Button right;
	private TextView main_text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		view = this.findViewById(R.id.view);
		head_back = (ViewGroup) view.findViewById(R.id.head_back);
		head_back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				LoginActivity.this.finish();
			}
		});
		right = (Button) view.findViewById(R.id.right);
		right.setBackgroundDrawable(null);
		right.setText("注册");
		right.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LoginActivity.this,
						RegisterActivity.class));
			}
		});
		main_text = (TextView) view.findViewById(R.id.main_text);
		main_text.setText("登陆");
		head_back.setVisibility(View.VISIBLE);
		right.setVisibility(View.VISIBLE);
		main_text.setVisibility(View.VISIBLE);
		verify = (TextView) this.findViewById(R.id.verify);
		verify.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LoginActivity.this,
						ForgetPassword.class));
			}
		});
		account = (EditText) this.findViewById(R.id.account);
		password = (EditText) this.findViewById(R.id.password);
		login = (Button) this.findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				PostBean postBean = getValue();
				if (postBean == null) {
					return;
				}
				postBean.setCode(Const.LOGIN);
				Task task = new Task(postBean, LoginActivity.this,
						new CallBack(postBean));
				task.postHttp();
			}
		});
	}

	private PostBean getValue() {
		String accountContent = Logic.getString(account.getText());
		String passwordContent = Logic.getString(password.getText());
		if (accountContent.length() == 0) {
			Toast.makeText(LoginActivity.this, "请输入手机号", 1000).show();
			return null;
		}
		if (!Logic.ifPhoneNum(accountContent)) {
			Toast.makeText(LoginActivity.this, "请输入正确的手机号", 1000).show();
			return null;
		}
		if (passwordContent.length() == 0) {
			Toast.makeText(LoginActivity.this, "请输入密码", 1000).show();
			return null;
		}
		PostBean postBean = new PostBean();
		postBean.setPhoneNumber(accountContent);
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
				if (postBean.getCode() == Const.LOGIN
						&& jsonParser.getState(str)) {
					HttpUtil httpUtil = new HttpUtil(LoginActivity.this);
					postBean.setCode(Const.GETUSERINFO);
					httpUtil.getUserInfo(postBean, new CallBack(postBean));
				} else if (postBean.getCode() == Const.GETUSERINFO
						&& jsonParser.getState(str)) {
					Toast.makeText(LoginActivity.this, "登陆成功", 1000).show();
					// startActivity(new Intent(LoginActivity.this,
					// HomeTabActivity.class)
					// .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
					SaveApplicationParam
							.saveLandState(LoginActivity.this, true);
					Register register = new Register();
					register.setPhone(postBean.getPhoneNumber());
					register.setPwd(postBean.getPassword());
					SaveApplicationParam.saveRegister(LoginActivity.this,
							register);
					finish();
				} else {
					SaveApplicationParam.saveLandState(LoginActivity.this,
							false);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				SaveApplicationParam.saveLandState(LoginActivity.this, false);
				e.printStackTrace();
			}

		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub
			SaveApplicationParam.saveLandState(LoginActivity.this, false);
		}
	}
}

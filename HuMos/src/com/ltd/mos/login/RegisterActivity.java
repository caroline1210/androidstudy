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
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

public class RegisterActivity extends BaseActivity {
	private Button verify;
	private EditText account;
	private EditText password;
	private Button register;
	private View view;
	private ViewGroup head_back;
	private TextView main_text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		view = this.findViewById(R.id.view);
		head_back = (ViewGroup) view.findViewById(R.id.head_back);
		head_back.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				RegisterActivity.this.finish();
			}
		});
		main_text = (TextView) view.findViewById(R.id.main_text);
		main_text.setText("注册");
		head_back.setVisibility(View.VISIBLE);
		main_text.setVisibility(View.VISIBLE);
		verify = (Button) this.findViewById(R.id.verify);
		verify.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				String phoneNum = Logic.getString(account.getText());
				if (phoneNum.length() == 0) {
					Toast.makeText(RegisterActivity.this, "请输入手机号", 1000)
							.show();
					return;
				} else if (!Logic.ifPhoneNum(phoneNum)) {
					Toast.makeText(RegisterActivity.this, "请输入正确的手机号", 1000)
							.show();
					return;
				}
				PostBean postBean = new PostBean();
				postBean.setCode(Const.GETVERIFYCODE);
				postBean.setPhoneNumber(phoneNum);
				Task task = new Task(postBean, RegisterActivity.this,
						new CallBack(postBean));
				task.postHttp();
				startTime();
			}
		});
		account = (EditText) this.findViewById(R.id.account);
		password = (EditText) this.findViewById(R.id.password);
		register = (Button) this.findViewById(R.id.register);
		register.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				PostBean postBean = getValue();
				if (postBean == null) {
					return;
				}
				postBean.setCode(Const.REGISTER);
				Task task = new Task(postBean, RegisterActivity.this,
						new CallBack(postBean));
				task.postHttp();
			}
		});
	}

	private PostBean getValue() {
		String accountContent = Logic.getString(account.getText());
		String passwordContent = Logic.getString(password.getText());
		if (accountContent.length() == 0) {
			Toast.makeText(RegisterActivity.this, "请输入手机号", 1000).show();
			return null;
		}
		if (!Logic.ifPhoneNum(accountContent)) {
			Toast.makeText(RegisterActivity.this, "请输入正确的手机号", 1000).show();
			return null;
		}
		if (passwordContent.length() == 0) {
			Toast.makeText(RegisterActivity.this, "请输入验证码", 1000).show();
			return null;
		}
		PostBean postBean = new PostBean();
		postBean.setPhoneNumber(accountContent);
		postBean.setVerify(passwordContent);
		return postBean;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what <= 0) {
				verify.setTextColor(getResources().getColor(R.color.black));
				verify.setText("获取验证码");
			} else {
				verify.setTextColor(getResources().getColor(R.color.black));
				verify.setText(msg.what + "秒之后重新发送");
			}
		};
	};
	boolean INTIME;
	private int CYCLE = 60;

	private void startTime() {
		if (INTIME) {

		} else {
			new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					INTIME = true;
					while (CYCLE >= 0) {
						Message msg = new Message();
						msg.what = CYCLE;
						handler.sendMessage(msg);
						CYCLE--;
						SystemClock.sleep(1000);
					}
					INTIME = false;
				}
			}).start();
		}
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
				if (postBean.getCode() == Const.GETVERIFYCODE) {
					HumosBean humosBean = jsonParser.getVerify(str);
					Toast.makeText(RegisterActivity.this,
							humosBean.getVerify(), 1000).show();
//					password.setText(Logic.getString(humosBean.getVerify()));
				} else {
					startActivity(new Intent(RegisterActivity.this,
							SetPassword.class).putExtra("PHONENUM",
							postBean.getPhoneNumber()));
					RegisterActivity.this.finish();
					Toast.makeText(RegisterActivity.this, "注册成功", 1000).show();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}
	}
}

package com.ltd.mos.personal;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

//修改密码
public class ChangePWDAct extends BaseActivity {
	private Button b_change_pwd_confirm;
	private View view;
	private EditText et_pwd, et_newpwd, et_surepwd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.change_password);

		view = findViewById(R.id.pwd_view);
		b_change_pwd_confirm = (Button) this
				.findViewById(R.id.b_change_pwd_confirm);
		et_pwd = (EditText) findViewById(R.id.et_pwd);
		et_newpwd = (EditText) findViewById(R.id.et_newpwd);
		et_surepwd = (EditText) findViewById(R.id.et_surepwd);
		b_change_pwd_confirm.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				// PostBean postBean = new PostBean();
				// postBean.setLastPassword("123456");
				// postBean.setPassword("123456");
				// postBean.setCode(Const.CHANGEPASSWORD);
				// postBean.setUserName("15901037642");
				// postBean.setPhoneNumber("15901037642");
				PostBean postBean = getValue();
				postBean.setCode(Const.CHANGEPWD);
				Task task = new Task(postBean, ChangePWDAct.this, new CallBack(
						postBean));
				task.postHttp();
			}
		});
		Logic logic = Logic.getInstance();
		logic.initHeadView(view, "修改密码", true, "", new ECallBack() {

			public void OnError(Object obj) {
				// TODO Auto-generated method stub

			}

			public void OnCreate(Object obj) {
				// TODO Auto-generated method stub
				finish();
			}
		});
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
				if (postBean.getCode() == Const.CHANGEPWD
						&& jsonParser.getChangeUserPwd(str) != null) {
					Toast.makeText(ChangePWDAct.this, "修改成功", 1000).show();
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

	private PostBean getValue() {
		Register register = SaveApplicationParam.getRegister(this);//
		String oldPwd = Logic.getString(et_pwd.getText());
		String newPwd = Logic.getString(et_newpwd.getText());
		String surePwd = Logic.getString(et_surepwd.getText());
		if (oldPwd.length() == 0) {
			Toast.makeText(ChangePWDAct.this, "请输入原密码", Toast.LENGTH_SHORT)
					.show();
		} else if (newPwd.length() == 0) {
			Toast.makeText(ChangePWDAct.this, "请输入新密码", Toast.LENGTH_SHORT)
					.show();
		} else if (surePwd.length() == 0) {
			Toast.makeText(ChangePWDAct.this, "请再次输入新密码", Toast.LENGTH_SHORT)
					.show();
		} else if (!newPwd.equals(surePwd)) {
			Toast.makeText(ChangePWDAct.this, "两次输入的密码不相同", Toast.LENGTH_SHORT)
					.show();
		} else if (oldPwd.equals(register.getPwd())) {
			Toast.makeText(ChangePWDAct.this, "原密码与以前的不匹配", Toast.LENGTH_SHORT)
					.show();
		}
		PostBean postBean = new PostBean();
		postBean.setLastPassword(oldPwd);
		postBean.setPhoneNumber(register.getPhone());//
		postBean.setPassword(newPwd);
		return postBean;
	}
}

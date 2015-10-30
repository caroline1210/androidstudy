package com.ltd.mos.personal;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.TailorBean;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.erweima.ErweimaActivity;
import com.ltd.mos.login.LoginActivity;
import com.ltd.mos.sercenter.SearchWineActivity;
import com.ltd.mos.util.Logic;

//��������
public class PersonAct extends BaseActivity implements OnClickListener {

	// private static final String PersonAct = null;
	private View view;
	private TextView tv_name, tv_djq, tv_collect, tv_nologin;
	private ImageView iv_message, iv_head;
	private Button b_login;
	private LinearLayout ll_djq, ll_PresentExp;
	private ListView lv_order, lv_customize, lv_xj, lv_friends;
	private String[] order = { "������", "���ջ�", "��ʷ����" };
	private String[] customize = { "��Ϣ����", "�ҵ��ղ�" };
	private String[] xj = { "˽�˶���", "�ҵ�Ѱ��" };
	private String[] friends = { "�������" };
	private int[] personList1 = { R.drawable.person_setting,
			R.drawable.person_setting, R.drawable.person_setting };
	private ArrayList<TailorBean> orderList = new ArrayList<TailorBean>();
	private ArrayList<TailorBean> customizeList = new ArrayList<TailorBean>();
	private ArrayList<TailorBean> xjList = new ArrayList<TailorBean>();
	private ArrayList<TailorBean> friendList = new ArrayList<TailorBean>();
	private PersonAdapter orderAdapter, customizeAdapter, xjAdapter,
			friendAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_main);

		initView();
		// loginState();
		view = findViewById(R.id.person_view);
		/**
		 * person�����������䲿��
		 * 
		 */
		// ����
		orderList = getList(order, null, personList1);
		orderAdapter = new PersonAdapter(this, orderList);
		lv_order.setAdapter(orderAdapter);
		// ˽�˶���
		customizeList = getList(customize, null, personList1);
		customizeAdapter = new PersonAdapter(this, customizeList);
		lv_customize.setAdapter(customizeAdapter);
		// Ѱ��
		xjList = getList(xj, null, personList1);
		xjAdapter = new PersonAdapter(this, xjList);
		lv_xj.setAdapter(xjAdapter);
		// �������
		friendList = getList(friends, null, personList1);
		friendAdapter = new PersonAdapter(this, friendList);
		lv_friends.setAdapter(friendAdapter);

		registListener();
		Logic logic = Logic.getInstance();
		logic.showSet(view, true, true, new CallBack());// ����logic�����÷���
		logic.initHeadView(view, "��������", false, "", null);
	}

	/**
	 * ���ؼ���Ӧ
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitBy2Click(); // ����˫���˳�����
		}
		return true;
	}

	private void initView() {
		// TODO Auto-generated method stub
		lv_order = (ListView) findViewById(R.id.lv_person_order);
		lv_customize = (ListView) findViewById(R.id.lv_person_customize);
		lv_xj = (ListView) findViewById(R.id.lv_person_xj);
		lv_friends = (ListView) findViewById(R.id.lv_person_friends);
		
		tv_name = (TextView) findViewById(R.id.tv_person_name);
		
		
		tv_djq = (TextView) findViewById(R.id.tv_person_djq_count);
		tv_collect = (TextView) findViewById(R.id.tv_person_collect_count);
		tv_nologin = (TextView) findViewById(R.id.tv_nologin);
		b_login = (Button) findViewById(R.id.b_person_login);
		iv_message = (ImageView) findViewById(R.id.iv_person_message);
		iv_head = (ImageView) findViewById(R.id.iv_person_head);
		ll_djq = (LinearLayout) findViewById(R.id.ll_person_djq);
		ll_PresentExp = (LinearLayout) findViewById(R.id.ll_person_PresentExp);
	}

	private void registListener() {
		// TODO Auto-generated method stub
		tv_collect.setOnClickListener(this);
		tv_djq.setOnClickListener(this);
		tv_name.setOnClickListener(this);
		b_login.setOnClickListener(this);
		iv_message.setOnClickListener(this);
		ll_djq.setOnClickListener(this);
		ll_PresentExp.setOnClickListener(this);

		// ��������
		lv_order.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (Logic.getLoginState(PersonAct.this)) {
					switch (arg2) {
					case 0:
						startActivity(new Intent(PersonAct.this,
								PushingAct.class));
						break;
					case 1:
						startActivity(new Intent(PersonAct.this,
								DGoodsAct.class));
						break;
					case 2:
						startActivity(new Intent(PersonAct.this,
								HistoryOrderAct.class));
						break;
					}
				} else {
					startActivity(new Intent(PersonAct.this,
							LoginActivity.class));
				}

			}
		});
		// ��Ϣ���ղ�
		lv_customize.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (Logic.getLoginState(PersonAct.this)) {
					switch (arg2) {
					case 0:
						startActivity(new Intent(PersonAct.this,
								MessageAct.class));
						break;
					case 1:
						startActivity(new Intent(PersonAct.this,
								MyCollectionAct.class));
						break;
					}
				} else {
					startActivity(new Intent(PersonAct.this,
							LoginActivity.class));
				}

			}
		});
		// Ѱ�ƺͶ���
		lv_xj.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if (Logic.getLoginState(PersonAct.this)) {
					switch (arg2) {
					case 0:
						startActivity(new Intent(PersonAct.this,
								CustomizeAct.class));
						break;
					case 1:
						startActivity(new Intent(PersonAct.this,
								SearchWineActivity.class));
						break;
					}
				} else {
					startActivity(new Intent(PersonAct.this,
							LoginActivity.class));
				}
			}
		});
		// �������
		lv_friends.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					startActivity(new Intent(PersonAct.this,
							ErweimaActivity.class));
					break;
				}
			}
		});
	}

	class CallBack implements ECallBack {

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			Intent setIntent = new Intent(PersonAct.this, SettingAct.class);
			startActivity(setIntent);
		}

		public void OnError(Object obj) {
		}

	}

	/**
	 * ��������
	 * 
	 */
	private ArrayList<TailorBean> getList(String[] content, String[] count,
			int[] resource) {
		ArrayList<TailorBean> list = new ArrayList<TailorBean>();
		for (int i = 0; i < content.length; i++) {
			TailorBean tailorInfo = new TailorBean();
			tailorInfo.setContent(content[i]);
			if (count != null) {
				tailorInfo.setTitle(count[i]);
			}
			if (resource != null) {
				tailorInfo.setResource(resource[i]);
			}
			list.add(tailorInfo);
		}
		return list;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (Logic.getLoginState(PersonAct.this)) {
			switch (v.getId()) {
			case R.id.ll_person_djq:
				startActivity(new Intent(PersonAct.this, DaijinquanAct.class));
				break;
			case R.id.ll_person_PresentExp:
				Toast.makeText(PersonAct.this, "���ڽ�����", Toast.LENGTH_SHORT)
						.show();
				break;
			case R.id.b_person_login:
				startActivity(new Intent(PersonAct.this, LoginActivity.class));
				break;
			case R.id.iv_person_message:
				startActivity(new Intent(PersonAct.this, MessageAct.class));
				break;
			}
		} else {
			startActivity(new Intent(PersonAct.this, LoginActivity.class));
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		if (Logic.getLoginState(PersonAct.this)) {
			b_login.setVisibility(View.GONE);
			tv_nologin.setVisibility(View.GONE);
			tv_name.setVisibility(View.VISIBLE);
			Register register = SaveApplicationParam.getRegister(this);
			tv_name.setText(register.getName());//��ȡ�����û���
			iv_message.setVisibility(View.VISIBLE);
			iv_head.setVisibility(View.VISIBLE);
		} else {
			b_login.setVisibility(View.VISIBLE);
			tv_nologin.setVisibility(View.VISIBLE);
			tv_name.setVisibility(View.GONE);
			iv_message.setVisibility(View.GONE);
			iv_head.setVisibility(View.GONE);
		}

	}
}
package com.ltd.mos.leisure;

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
import android.widget.ListView;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.LeisureBean;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.game.GameActivity;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

/**
 * ��������
 * 
 * @author xuwu
 * 
 */
public class LeisureActivity extends BaseActivity {
	private View view;
	private ImageView imageView;
	Logic logic = Logic.getInstance();
	private ArrayList<LeisureBean> list_head = new ArrayList<LeisureBean>();
	private ArrayList<LeisureBean> list_body = new ArrayList<LeisureBean>();
	private ArrayList<LeisureBean> list_foot = new ArrayList<LeisureBean>();
	// ͼƬ��Դid
	private int[] imageId = { R.drawable.ser_ad_01, R.drawable.ser_ad_02,
			R.drawable.ser_ad_05 };
	private ListView listView_head, listView_body, listview_foot;

	private LeisureAdapter adapter_head, adapter_body, adapter_foot;
	Button layout_foot_more;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leisure);
		view = this.findViewById(R.id.view);
		logic.initHeadView(view, "��������", false, "", new CallBack());
		imageView = (ImageView) this.findViewById(R.id.image);
		if (SCREENHEIGHT == 0 || SCREENWIDE == 0) {
			getScreenInfo();
		}
		logic.initImageView(imageView);
		listView_body = (ListView) this.findViewById(R.id.listView_body1);
		listView_head = (ListView) this.findViewById(R.id.listView_head);
		listview_foot = (ListView) this.findViewById(R.id.listView_foot);
//		list_head = getList(5);
		adapter_head = new LeisureAdapter(this, list_head, new CallBack());
		listView_head.setAdapter(adapter_head);
		logic.initListView(listView_head, 5, this);
		listView_head.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Logic.getInstance().setCurrentLeisure(list_head.get(arg2));
				startActivity(new Intent(LeisureActivity.this,
						ArticleDetailActivity.class));
			}
		});

//		list_body = getList(3);
		adapter_body = new LeisureAdapter(this, list_body, new CallBack());
		listView_body.setAdapter(adapter_body);
		
		/**
		 * ������������
		 * 
		 */
		Register register = SaveApplicationParam.getRegister(this);
		PostBean postBean = new PostBean();
		postBean.setCode(Const.HEALTHDRINK);
		postBean.setPhoneNumber(register.getPhone());
		postBean.setPassword(register.getPwd());
		Task task = new Task(postBean, LeisureActivity.this, new Back());
		task.postHttp();
		
		logic.initListView(listView_body, 3, this);
		listView_body.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Logic.getInstance().setCurrentLeisure(list_body.get(arg2));
				startActivity(new Intent(LeisureActivity.this,
						ArticleDetailActivity.class));
			}
		});

//		list_foot = getList(3);
		adapter_foot = new LeisureAdapter(this, list_foot, new CallBack());
		listview_foot.setAdapter(adapter_foot);
		/**
		 * �����ƶ���
		 * 
		 */
//		Register register = SaveApplicationParam.getRegister(this);
		PostBean postBeanJokes = new PostBean();
		postBeanJokes.setCode(Const.WINEJOKES);
		postBeanJokes.setPhoneNumber(register.getPhone());
		postBeanJokes.setPassword(register.getPwd());
		Task taskJokes = new Task(postBean, LeisureActivity.this, new BackWineJokes());
		taskJokes.postHttp();

		logic.initListView(listview_foot, 3, this);
		listview_foot.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Logic.getInstance().setCurrentLeisure(list_foot.get(arg2));
				startActivity(new Intent(LeisureActivity.this,
						ArticleDetailActivity.class));
			}
		});
		layout_foot_more = (Button) this.findViewById(R.id.layout_foot_more);
		layout_foot_more.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LeisureActivity.this,
						GameActivity.class));
			}
		});
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

//	private ArrayList<LeisureBean> getList(int num) {
//		ArrayList<LeisureBean> list = new ArrayList<LeisureBean>();
//		for (int i = 0; i < num; i++) {
//			LeisureBean leisureBean = new LeisureBean();
//			leisureBean.setNoteInfo("�й����Ѿƽ���ս�Ե�����" + i);
//			list.add(leisureBean);
//		}
//		return list;
//
//	}
	//��������
	class Back implements ECallBack {

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			ResultObject resultObject=(ResultObject) obj;
			String str = Logic.getString(resultObject);
			JsonPrase jsonPrase = new JsonPrase();
			LeisureBean leisureBean=jsonPrase.getheadthDrink(str);
			list_body.add(leisureBean);
			adapter_body.notifyDataSetChanged();
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}
	}
	//�ƶ���
	class BackWineJokes implements ECallBack {

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			ResultObject resultObject=(ResultObject) obj;
			String str = Logic.getString(resultObject);
			JsonPrase jsonPrase = new JsonPrase();
			LeisureBean leisureBean=jsonPrase.getwineDrink(str);
			list_foot.add(leisureBean);
			adapter_foot.notifyDataSetChanged();
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}
	}


	class CallBack implements ECallBack {

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			String flag = Logic.getString(obj);
			if (flag.equals(Const.LEFT)) {
				Toast.makeText(LeisureActivity.this, "left", 1000).show();
			} else if (flag.equals(Const.RIGHT)) {
				Toast.makeText(LeisureActivity.this, "right", 1000).show();
			} else {

			}
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub

		}

	}
}

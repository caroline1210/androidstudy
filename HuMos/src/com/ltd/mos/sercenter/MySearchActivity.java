package com.ltd.mos.sercenter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.baidu.platform.comapi.map.p;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.bean.TailorBean;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.http.HttpUtil;
import com.ltd.mos.http.HttpsClientHelper;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.FileUtil;
import com.ltd.mos.util.ImageUtil;
import com.ltd.mos.util.Logic;

/**
 * 我要寻
 * 
 * @author CaoT。
 * 
 */
public class MySearchActivity extends BaseActivity {
	private View view;
	private CheckBox baiJiu, hongJiu, otherJiu;
	private ListView listView, listView2;
	private Button submit, imageAdd;
	private ImageView image1, image2, image3;
	private String title[] = { "酒品名称：", "关键词：" };
	private String hint[] = { "品牌、类型、度数等", "请输入关键字" };
	private String[] userInfo = { "联系人：", "电话：" };
	private String[] userInfoHint = { "请输入联系人姓名", "请输入电话" };
	private TailorAdapter adapter, userAdapter;
	private ArrayList<TailorBean> list = new ArrayList<TailorBean>();
	private ArrayList<TailorBean> userList = new ArrayList<TailorBean>();
	private boolean FLAG_IMAGE1, FLAG_IMAGE2, FLAG_IMAGE3;// 是否设置第一,二，三张图
	private ArrayList<String> images = new ArrayList<String>();
	private EditText detailInfo;
	private boolean BAIJIU, HONGJIU, OTHER;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_search);
		initView();
		list = getList(null, hint, title);
		adapter = new TailorAdapter(this, list);
		listView.setAdapter(adapter);
		userList = getList(null, userInfoHint, userInfo);
		userAdapter = new TailorAdapter(this, userList);
		listView2.setAdapter(userAdapter);
		listView2.setVisibility(View.GONE);
	}

	/**
	 * 生成数据
	 * 
	 * @param content
	 * @param hint
	 * @param title
	 * @return
	 */
	private ArrayList<TailorBean> getList(String[] content, String[] hint,
			String[] title) {
		ArrayList<TailorBean> list = new ArrayList<TailorBean>();
		for (int i = 0; i < title.length; i++) {
			TailorBean tailorBean = new TailorBean();
			tailorBean.setTitle(title[i]);
			if (hint != null) {
				tailorBean.setContentHint(hint[i]);
			}
			if (content != null) {
				tailorBean.setContent(content[i]);
			}
			list.add(tailorBean);

		}
		return list;

	}

	EditText xunjiuName;
	EditText xunjiuKeyWords;

	private void getListContent() {
		LinearLayout layout0 = (LinearLayout) listView.getChildAt(0);
		xunjiuName = (EditText) layout0.findViewById(R.id.editContent);
		LinearLayout layout1 = (LinearLayout) listView.getChildAt(1);
		xunjiuKeyWords = (EditText) layout1.findViewById(R.id.editContent);
	}

	String picName;
	public final int REQUEST_IMAGE = 111;
	public final int REQUEST_PHOTO = 222;

	private void initView() {
		// TODO Auto-generated method stub
		detailInfo = (EditText) this.findViewById(R.id.detailInfo);
		view = this.findViewById(R.id.view);
		Logic.getInstance().initHeadView(view, "我要寻", true, "",
				new ECallBack() {

					public void OnError(Object obj) {
						// TODO Auto-generated method stub

					}

					public void OnCreate(Object obj) {
						// TODO Auto-generated method stub
						MySearchActivity.this.finish();
					}
				});
		submit = (Button) this.findViewById(R.id.submit);
		submit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				getListContent();
				PostBean postBean = new PostBean();
				postBean.setCode(Const.CREATEPRODUCTREQUEST);
				String type = "";
				if (BAIJIU) {
					type += "baijiu";
				}
				if (HONGJIU) {
					type += "hongjiu";
				}
				if (OTHER) {
					type += "other";
				}
				Register register = SaveApplicationParam
						.getRegister(MySearchActivity.this);
				postBean.setPhoneNumber(register.getPhone());
				postBean.setPassword(register.getPwd());
				postBean.setXunJiuTitle(Logic.getString(xunjiuName.getText()));
				postBean.setXunJiuContent(type + ","
						+ Logic.getString(detailInfo.getText()) + ","
						+ Logic.getString(xunjiuKeyWords.getText()));
				postBean.setXunJiuImages(images);
				Task task = new Task(postBean, MySearchActivity.this,
						new ECallBack() {

							public void OnCreate(Object obj) {
								// TODO Auto-generated method stub
								ResultObject resultObject = (ResultObject) obj;
								JsonPrase jsonPrase = new JsonPrase();
								if (jsonPrase.getState(resultObject
										.getmMessage())) {
									Toast.makeText(MySearchActivity.this,
											"提交成功", 1000).show();
									MySearchActivity.this.finish();
								}
							}

							public void OnError(Object obj) {
								// TODO Auto-generated method stub

							}
						});
				task.postHttp();
			}
		});
		baiJiu = (CheckBox) this.findViewById(R.id.baiJiu);
		baiJiu.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				BAIJIU = isChecked;
			}
		});
		hongJiu = (CheckBox) this.findViewById(R.id.hongJiu);
		hongJiu.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				HONGJIU = isChecked;
			}
		});
		otherJiu = (CheckBox) this.findViewById(R.id.otherJiu);
		otherJiu.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				OTHER = isChecked;
			}
		});
		listView = (ListView) this.findViewById(R.id.listView);
		listView2 = (ListView) this.findViewById(R.id.listView_foot);
		image1 = (ImageView) this.findViewById(R.id.image1);
		image2 = (ImageView) this.findViewById(R.id.image2);
		image3 = (ImageView) this.findViewById(R.id.image3);
		image1.setVisibility(View.GONE);
		image2.setVisibility(View.GONE);
		image3.setVisibility(View.GONE);
		imageAdd = (Button) this.findViewById(R.id.imageAdd);
		imageAdd.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (FLAG_IMAGE1 && FLAG_IMAGE2 && FLAG_IMAGE3) {
					return;
				}
				showTakePhotoDialog(new ECallBack() {

					public void OnError(Object obj) {
						// TODO Auto-generated method stub

					}

					public void OnCreate(Object obj) {
						// TODO Auto-generated method stub
						int i = -1;
						try {
							i = Integer.parseInt(Logic.getString(obj));
						} catch (Exception e) {
							// TODO: handle exception
						}

						String state = Environment.getExternalStorageState();
						switch (i) {
						case Const.CANCLE:

							break;
						case Const.PICTURE:

							if (state.equals(Environment.MEDIA_MOUNTED)) {// 是否插入内存卡
								Intent intent = new Intent();
								intent.setAction(Intent.ACTION_PICK);
								intent.setDataAndType(
										MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
										"image/*");
								/* 取得图片后返回本画面 */
								startActivityForResult(intent, REQUEST_IMAGE);
							}
							break;
						case Const.TAKEPHOTO:
							picName = System.currentTimeMillis() + ".jpg";
							if (FileUtil.getAvailaleSize() < 50) {
								Toast.makeText(MySearchActivity.this,
										"sdcard内存不足", 1000).show();
							}
							if (state.equals(Environment.MEDIA_MOUNTED)) {// 是否插入内存卡
								Intent intent1 = new Intent();
								intent1.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
								intent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri
										.fromFile(Logic.getImagePath(picName)));
								startActivityForResult(intent1, REQUEST_PHOTO);
							} else {
								Toast.makeText(MySearchActivity.this, "请插入内存卡",
										1000).show();
							}
							break;
						default:
							break;
						}

					}
				});
			}
		});
	}

	HashMap<String, String> hp = new HashMap<String, String>();// 存储图片

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		try {
			if (resultCode != RESULT_OK) {
				return;
			}
			Register register = SaveApplicationParam.getRegister(this);
			final PostBean postBean = new PostBean();
			switch (requestCode) {
			case REQUEST_PHOTO:
				File file = Logic.getImagePath(picName);
				String path1 = file.getAbsolutePath();
				postBean.setPassword(register.getPwd());
				postBean.setPhoneNumber(register.getPhone());
				postBean.setImagePath(path1);
				postBean.setUrl(HttpUtil.uploadFile);
				HttpsClientHelper.uploadFile(postBean, MySearchActivity.this,
						new ECallBack() {

							public void OnError(Object obj) {
								// TODO Auto-generated method stub

							}

							public void OnCreate(Object obj) {
								// TODO Auto-generated method stub
								images.add(Logic.getString(obj));
							}
						});
				if (!FLAG_IMAGE1) {
					FLAG_IMAGE1 = true;
					hp.put("image1", path1);
					image1.setImageBitmap(ImageUtil.resizeBitmap(path1, 400,
							400));
					image1.setVisibility(View.VISIBLE);
				} else if (!FLAG_IMAGE2) {
					FLAG_IMAGE2 = true;
					hp.put("image2", path1);
					image2.setImageBitmap(ImageUtil.resizeBitmap(path1, 400,
							400));
					image2.setVisibility(View.VISIBLE);
				} else if (!FLAG_IMAGE3) {
					FLAG_IMAGE3 = true;
					hp.put("image3", path1);
					image3.setImageBitmap(ImageUtil.resizeBitmap(path1, 400,
							400));
					image3.setVisibility(View.VISIBLE);
					imageAdd.setVisibility(View.GONE);
				}
				break;
			case REQUEST_IMAGE:
				try {
					if (data != null) {
						Uri uri = data.getData();
						// 这里开始的第二部分，获取图片的路径：
						String[] proj = { MediaStore.Images.Media.DATA };
						Cursor cursor = managedQuery(uri, proj, null, null,
								null);
						int column_index = cursor
								.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
						cursor.moveToFirst();
						String path = cursor.getString(column_index);

						postBean.setPassword(register.getPwd());
						postBean.setPhoneNumber(register.getPhone());
						postBean.setImagePath(path);
						postBean.setUrl(HttpUtil.uploadFile);
						HttpsClientHelper.uploadFile(postBean,
								MySearchActivity.this, new ECallBack() {

									public void OnError(Object obj) {
										// TODO Auto-generated method stub

									}

									public void OnCreate(Object obj) {
										// TODO Auto-generated method stub
										JsonPrase jsonPrase = new JsonPrase();
										images.add(jsonPrase
												.getXunjiuCreate(Logic
														.getString(obj)));
									}
								});
						if (!FLAG_IMAGE1) {
							FLAG_IMAGE1 = true;
							hp.put("image1", path);
							image1.setImageBitmap(ImageUtil.resizeBitmap(path,
									400, 400));
							image1.setVisibility(View.VISIBLE);
						} else if (!FLAG_IMAGE2) {
							FLAG_IMAGE2 = true;
							hp.put("image2", path);
							image2.setImageBitmap(ImageUtil.resizeBitmap(path,
									400, 400));
							image2.setVisibility(View.VISIBLE);
						} else if (!FLAG_IMAGE3) {
							FLAG_IMAGE3 = true;
							hp.put("image3", path);
							image3.setImageBitmap(ImageUtil.resizeBitmap(path,
									400, 400));
							image3.setVisibility(View.VISIBLE);
							imageAdd.setVisibility(View.GONE);
						}
					}
				} catch (OutOfMemoryError e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

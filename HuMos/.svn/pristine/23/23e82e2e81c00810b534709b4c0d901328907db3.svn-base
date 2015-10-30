package com.ltd.mos.sercenter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.ltd.mos.http.HttpUtil;
import com.ltd.mos.http.HttpsClientHelper;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.FileUtil;
import com.ltd.mos.util.ImageUtil;
import com.ltd.mos.util.Logic;

/**
 * 私人订制
 * 
 * @author CaoT。
 * 
 */
public class PersonalTailorActivity extends BaseActivity {
	private View view;
	private ListView wineInfo_listView, userInfo_listView;
	private EditText editContent, detailInfo;
	private Button submit, imageAdd;
	private ImageView image1, image2, image3;
	private String[] wineInfo = { "使用场合：", "单价要求：", "数量(瓶)：", "使用时间：" };
	private String[] wineHint = { "婚宴、满月", "例300-500", "默认单位为瓶", "" };
	private String[] userInfo = { "联系人：", "电话：", "邮箱：", "收货地址：" };
	private TailorAdapter wineInfoAdapter, userInfoAdapter;
	private ArrayList<TailorBean> wineInfoList = new ArrayList<TailorBean>();
	private ArrayList<TailorBean> userInfoList = new ArrayList<TailorBean>();
	private boolean FLAG_IMAGE1, FLAG_IMAGE2, FLAG_IMAGE3;// 是否设置第一,二，三张图
	private ArrayList<String> images = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_tailor);
		initView();
		wineInfoList = getList(null, wineHint, wineInfo);
		wineInfoAdapter = new TailorAdapter(this, wineInfoList);
		wineInfo_listView.setAdapter(wineInfoAdapter);
		userInfoList = getList(null, null, userInfo);
		userInfoAdapter = new TailorAdapter(this, userInfoList);
		userInfo_listView.setAdapter(userInfoAdapter);
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

	EditText userOccasion, price, wineNum, time;
	EditText userName, userPhone, userEmail, userPath;

	private void getListContent() {
		LinearLayout layout0 = (LinearLayout) wineInfo_listView.getChildAt(0);
		userOccasion = (EditText) layout0.findViewById(R.id.editContent);
		LinearLayout layout1 = (LinearLayout) wineInfo_listView.getChildAt(1);
		price = (EditText) layout1.findViewById(R.id.editContent);
		LinearLayout layout2 = (LinearLayout) wineInfo_listView.getChildAt(2);
		wineNum = (EditText) layout2.findViewById(R.id.editContent);
		LinearLayout layout3 = (LinearLayout) wineInfo_listView.getChildAt(3);
		time = (EditText) layout3.findViewById(R.id.editContent);
		LinearLayout layout10 = (LinearLayout) wineInfo_listView.getChildAt(0);
		userName = (EditText) layout10.findViewById(R.id.editContent);
		LinearLayout layout11 = (LinearLayout) wineInfo_listView.getChildAt(1);
		userPhone = (EditText) layout11.findViewById(R.id.editContent);
		LinearLayout layout12 = (LinearLayout) wineInfo_listView.getChildAt(2);
		userEmail = (EditText) layout12.findViewById(R.id.editContent);
		LinearLayout layout13 = (LinearLayout) wineInfo_listView.getChildAt(3);
		userPath = (EditText) layout13.findViewById(R.id.editContent);
	}

	private TailorBean getData() {
		TailorBean tailorBean = new TailorBean();
		tailorBean.setTailorAdrress(Logic.getString(userOccasion.getText()));
		tailorBean.setTailorDesc(Logic.getString(editContent.getText()));
		tailorBean.setTailorEmail(Logic.getString(userEmail.getText()));
		tailorBean.setTailorName(Logic.getString(userName.getText()));
		tailorBean.setTailorNum(Logic.getString(wineNum.getText()));
		tailorBean.setTailorPath(Logic.getString(userPath.getText()));
		tailorBean.setTailorPhone(Logic.getString(userPhone.getText()));
		tailorBean.setTailorPrice(Logic.getString(price.getText()));
		tailorBean.setTailorRemark(Logic.getString(detailInfo.getText()));
		tailorBean.setTailorTime(Logic.getString(time.getText()));
		return tailorBean;
	}

	String picName;
	public final int REQUEST_IMAGE = 111;
	public final int REQUEST_PHOTO = 222;
	HashMap<String, String> hp = new HashMap<String, String>();// 存储图片

	private void initView() {
		wineInfo_listView = (ListView) this
				.findViewById(R.id.wineInfo_listView);
		userInfo_listView = (ListView) this
				.findViewById(R.id.userInfo_listView);
		detailInfo = (EditText) this.findViewById(R.id.detailInfo);
		editContent = (EditText) this.findViewById(R.id.editContent);
		submit = (Button) this.findViewById(R.id.submit);
		submit.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				getListContent();
				TailorBean tailorBean = getData();
				tailorBean.setImages(images);
				Register register = SaveApplicationParam.getRegister(PersonalTailorActivity.this);
				PostBean postBean = new PostBean();
				postBean.setPassword(register.getPwd());
				postBean.setPhoneNumber(register.getPhone());
				postBean.setTailorBean(tailorBean);
				postBean.setCode(Const.CREATECUSTOMIZEDREQUEST);
				new Task(postBean, PersonalTailorActivity.this, new ECallBack() {
					
					public void OnError(Object obj) {
						// TODO Auto-generated method stub
						
					}
					
					public void OnCreate(Object obj) {
						// TODO Auto-generated method stub
						ResultObject resultObject = (ResultObject) obj;
						JsonPrase jsonPrase= new JsonPrase();
						if(jsonPrase.getState(resultObject.getmMessage())){
							Toast.makeText(PersonalTailorActivity.this, "提交成功", 1000).show();
							return;
						}
						Toast.makeText(PersonalTailorActivity.this, "提交失败", 1000).show();
					}
				}).postHttp();
			}
		});
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
								Toast.makeText(PersonalTailorActivity.this,
										"sdcard内存不足", 1000).show();
							}
							if (state.equals(Environment.MEDIA_MOUNTED)) {// 是否插入内存卡
								Intent intent1 = new Intent();
								intent1.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
								intent1.putExtra(MediaStore.EXTRA_OUTPUT, Uri
										.fromFile(Logic.getImagePath(picName)));
								startActivityForResult(intent1, REQUEST_PHOTO);
							} else {
								Toast.makeText(PersonalTailorActivity.this,
										"请插入内存卡", 1000).show();
							}
							break;
						default:
							break;
						}

					}
				});
			}
		});
		view = this.findViewById(R.id.view);
		Logic.getInstance().initHeadView(view, "私人订制", true, "",
				new ECallBack() {

					public void OnError(Object obj) {
						// TODO Auto-generated method stub

					}

					public void OnCreate(Object obj) {
						// TODO Auto-generated method stub
						String flag = Logic.getString(obj);
						if (flag.equals(Const.LEFT)) {
							PersonalTailorActivity.this.finish();
						}
					}
				});
	}

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
				HttpsClientHelper.uploadFile(postBean,
						PersonalTailorActivity.this, new ECallBack() {

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
								PersonalTailorActivity.this, new ECallBack() {

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

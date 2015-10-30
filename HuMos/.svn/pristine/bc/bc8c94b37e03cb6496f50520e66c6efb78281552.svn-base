package com.ltd.mos.erweima;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.OrderItemList;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.Register;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.bean.WineInfo;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.http.HttpUtil;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.personal.DGoodsAct;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

/**
 * 二维码扫描
 * 
 * @author CaoT。
 * 
 */
public class ErcodeScanActivity extends BaseActivity implements Callback {

	private CaptureActivityHandler handler;
	private ErcodeScanView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;
	private SurfaceView surfaceView;
	Button my_erweima;
	Button scan_pic;
	PostBean postBean = new PostBean();

	private String resultString = "";
	String photo_path = "";
	Button btn_deng;
	ImageView image_erweima;
	public static boolean isOpen = false;
	CloudLed m_led;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_news_code_scan);
		CameraManager.init(getApplication());
		initControl();

		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
	}

	private void initControl() {
		viewfinderView = (ErcodeScanView) findViewById(R.id.viewfinder_view);
		surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		View view = this.findViewById(R.id.view);
		Logic.getInstance().initHeadView(view, "扫一扫", true, "灯",
				new ECallBack() {

					public void OnError(Object obj) {
						// TODO Auto-generated method stub

					}

					public void OnCreate(Object obj) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		my_erweima = (Button) findViewById(R.id.my_erweima);
		image_erweima = (ImageView) findViewById(R.id.image_erweima);
		btn_deng = (Button) findViewById(R.id.right);
		btn_deng.setVisibility(View.VISIBLE);
		btn_deng.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// isOpenLight(isOpen);
				if (!isOpen) {
					CameraManager.get().openLight();
				} else {
					CameraManager.get().offLight();
				}
				isOpen = !isOpen;

			}
		});
		//手动直接确认订单
		my_erweima.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				getValue();
			}
		});

	}
	
	public PostBean getValue(){
		Register register = SaveApplicationParam.getRegister(this);
		Intent intent=getIntent();
		String orderId=intent.getStringExtra("id");
		postBean.setCode(Const.IMMEDIATECONFIRME);
		postBean.setPhoneNumber(register.getPhone());
		postBean.setPassword(register.getPwd());
		postBean.setProductId(orderId);//订单标识
		Toast.makeText(ErcodeScanActivity.this, orderId, 4000).show();
		Task task=new Task(postBean, ErcodeScanActivity.this, new ImmediateConfirm(orderId));
		task.postHttp();
		return postBean;
	}
	class ImmediateConfirm implements ECallBack{
		String mOrderId;
		public ImmediateConfirm(String orderId) {
			// TODO Auto-generated constructor stub
			this.mOrderId =orderId;
		}
		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			ResultObject resultObject = (ResultObject) obj;
			String str=resultObject.getmMessage();
			JsonPrase jsonPrase=new JsonPrase();
			if(jsonPrase.getState(str)){
				Toast.makeText(ErcodeScanActivity.this, "收货成功", 1000).show();
				for(int i =0;i<dgOrderList.size();i++){
					if(dgOrderList.get(i).getOrderId().equals(mOrderId)){
						dgOrderList.remove(i);
						i--;
					}
				}
				finish();
//				sendBroadcast(new Intent("delete"));
			}else{
				Toast.makeText(ErcodeScanActivity.this, "请重新确认收货！", 1000).show();
			}
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub
			
		}}
	
	protected void isOpenLight(boolean isOpenLight) {
		if (isOpenLight) {
			m_led.turnOff();
			isOpen = false;
		} else {
			m_led.turnOn();
			isOpen = true;

		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	public void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	/**
	 * @param result
	 * @param barcode
	 */
	public void handleDecode(Result result, Bitmap barcode) {
		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();

		resultString = result.getText();
		Intent resultIntent = new Intent();
		Bundle bundle = new Bundle();
		bundle.putString("result", resultString);
		resultIntent.putExtras(bundle);
		this.setResult(RESULT_OK, resultIntent);
		System.out.println("-----------------" + getUrl(resultString));
		if (getUrl(resultString) != true) {
			Toast.makeText(ErcodeScanActivity.this, result.getText(), 1000)
					.show();
		} else {
			Intent intent = new Intent();
			intent.setAction("android.intent.action.VIEW");
			Uri content_url = Uri.parse(resultString);
			intent.setData(content_url);
			startActivity(intent);
			finish();
		}

		verify(resultString);
	}
	private void verify(String resultString){
		Register register = SaveApplicationParam.getRegister(this);
		postBean.setCode(Const.CONFIRMERWEIMA);
		postBean.setPhoneNumber(register.getPhone());
		postBean.setPassword(register.getPwd());
		postBean.setBarcode(resultString);//
		Task task = new Task(postBean, ErcodeScanActivity.this, new Back());
		task.postHttp();
	}
	class Back implements ECallBack{

		public void OnCreate(Object obj) {
			// TODO Auto-generated method stub
			ResultObject resultObject = (ResultObject) obj;
			String str=resultObject.getmMessage();
			JsonPrase jsonPrase=new JsonPrase();
			if(jsonPrase.getState(str)){
				Toast.makeText(ErcodeScanActivity.this, "验证成功", 1000).show();
			}else{
				Toast.makeText(ErcodeScanActivity.this, "未找到此酒品订单，鉴别为假酒！", 1000).show();
			}
			
		}

		public void OnError(Object obj) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private void start() {
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
	}

	public void stop() {
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats,
					characterSet);
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;
	}

	public ErcodeScanView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

	public static boolean getUrl(String url) {
		String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
		Pattern patt = Pattern.compile(regex);
		Matcher matcher = patt.matcher(url);
		boolean isMatch = matcher.matches();
		return isMatch;

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 1 && resultCode == RESULT_OK && null != data) {

			image_erweima.setVisibility(View.VISIBLE);
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();

			image_erweima.setImageBitmap(BitmapFactory.decodeFile(picturePath));
			scanningImage();

		}

	}

	private void scanningImage() {

		Map<DecodeHintType, String> hints = new HashMap<DecodeHintType, String>();
		hints.put(DecodeHintType.CHARACTER_SET, "utf-8");

		Bitmap bitmap = ((BitmapDrawable) image_erweima.getDrawable())
				.getBitmap();
		RGBLuminanceSource source = new RGBLuminanceSource(bitmap);
		BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
		QRCodeReader reader = new QRCodeReader();
		Result result;
		try {
			result = reader.decode(bitmap1, hints);
			Toast.makeText(ErcodeScanActivity.this, result.getText(),
					Toast.LENGTH_LONG).show();
			verify(resultString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		image_erweima.setVisibility(View.GONE);
		return super.onTouchEvent(event);
	}
}

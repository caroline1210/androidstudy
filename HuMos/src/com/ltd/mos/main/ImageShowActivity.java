package com.ltd.mos.main;

import java.util.Timer;
import java.util.TimerTask;

import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.image.AsyncImageLoader;
import com.ltd.mos.image.PhotoViewAttacher;
import com.ltd.mos.image.PhotoViewAttacher.OnMatrixChangedListener;
import com.ltd.mos.image.PhotoViewAttacher.OnPhotoTapListener;

public class ImageShowActivity extends BaseActivity {

	static final String PHOTO_TAP_TOAST_STRING = "Photo Tap! X: %.2f %% Y:%.2f %%";

	private ImageView mImageView;

	private PhotoViewAttacher mAttacher;
	String imageUri;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageshow);
		mImageView = (ImageView) findViewById(R.id.iv_photo);
		imageUri = this.getIntent().getStringExtra("IMAGEURL");
		AsyncImageLoader.initOption();
		AsyncImageLoader.displayImage(imageUri, mImageView, new ECallBack() {

			public void OnError(Object obj) {
			}

			public void OnCreate(Object obj) {
//				AsyncImageLoader.displayImage(imageUri, mImageView);
				mAttacher = new PhotoViewAttacher(mImageView);

				mAttacher.setOnMatrixChangeListener(new MatrixChangeListener());

				mAttacher.setOnPhotoTapListener(new OnPhotoTapListener() {

					public void onPhotoTap(View view, float x, float y) {
						Log.d("LOG", "-------click-----------");
						// exitByClick();
					}
				});
			}
		});

		isExit = false;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		try {
			mAttacher.cleanup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ˫���˳�
	 */
	private Boolean isExit;

	public void exitByClick() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // ׼���˳�
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {

					while (isExit) {
						isExit = false;
						finish();
					}

				}
			}, 700); // ���0.5������û�а��£���������ʱ��ȡ�����ղ�ִ�е�����

		} else {
			isExit = false; // ȡ���˳�
		}
	}

	private class MatrixChangeListener implements OnMatrixChangedListener {

		public void onMatrixChanged(RectF rect) {
		}
	}

}

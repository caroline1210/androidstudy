package com.ltd.mos.erweima;

import java.io.IOException;
import java.util.Hashtable;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.Register;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;

public class ErweimaActivity extends BaseActivity {

	int QR_WIDTH = 400;
	int QR_HEIGHT = 400;
	ImageView image_erweima;
	ImageView share_wxfriends, share_wxcircle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_erweima);
		image_erweima = (ImageView) findViewById(R.id.image_erweima);
		share_wxfriends = (ImageView) this.findViewById(R.id.share_wxfriends);

		share_wxfriends.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				onClickShare(0);

			}
		});
		share_wxcircle = (ImageView) this.findViewById(R.id.share_wxcircle);
		share_wxcircle.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				onClickShare(1);
			}
		});
		View view = this.findViewById(R.id.view);
		Logic.getInstance().initHeadView(view, "邀请好友", true, "",
				new ECallBack() {

					public void OnError(Object obj) {
						// TODO Auto-generated method stub

					}

					public void OnCreate(Object obj) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		try {
			Register register = SaveApplicationParam.getRegister(this);
			Bitmap bmTou = initProtrait();
			Bitmap bmEr = createImage(register.getPhone());
			createQRCodeBitmapWithPortrait(bmEr, bmTou);
			image_erweima.setImageBitmap(bmEr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 对接微信 分享
	public void onClickShare(int flag) {
		WXWebpageObject webpage = new WXWebpageObject();
		webpage.webpageUrl = "http://www.baidu.com";
		WXMediaMessage msg = new WXMediaMessage(webpage);
		msg.title = "分享个宝贝给你";
		msg.description = Logic.getString("分享测试");
		Bitmap thumb = BitmapFactory.decodeResource(getResources(),
				R.drawable.ceshi);
		msg.setThumbImage(thumb);
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = String.valueOf(System.currentTimeMillis());
		req.message = msg;
		req.scene = (flag == 0 ? SendMessageToWX.Req.WXSceneSession
				: SendMessageToWX.Req.WXSceneTimeline);
		wxApi.sendReq(req);
	}

	private Bitmap createImage(String text) {
		try {
			QRCodeWriter writer = new QRCodeWriter();

			if (text == null || "".equals(text) || text.length() < 1) {
				return null;
			}

			BitMatrix martix = writer.encode(text, BarcodeFormat.QR_CODE,
					QR_WIDTH, QR_HEIGHT);

			System.out.println("w:" + martix.getWidth() + "h:"
					+ martix.getHeight());

			Hashtable<EncodeHintType, Object> qrParam = new Hashtable<EncodeHintType, Object>();
			qrParam.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
			qrParam.put(EncodeHintType.CHARACTER_SET, "UTF-8");

			BitMatrix bitMatrix = new QRCodeWriter().encode(text,
					BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, qrParam);
			int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
			for (int y = 0; y < QR_HEIGHT; y++) {
				for (int x = 0; x < QR_WIDTH; x++) {
					if (bitMatrix.get(x, y)) {
						pixels[y * QR_WIDTH + x] = 0xff000000;
					} else {
						pixels[y * QR_WIDTH + x] = 0xffffffff;
					}

				}
			}

			Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
					Bitmap.Config.ARGB_8888);

			bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
			return bitmap;

		} catch (WriterException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void createQRCodeBitmapWithPortrait(Bitmap qr, Bitmap portrait) {
		int portrait_W = portrait.getWidth();
		int portrait_H = portrait.getHeight();

		int left = (400 - portrait_W) / 2;
		int top = (400 - portrait_H) / 2;
		int right = left + portrait_W;
		int bottom = top + portrait_H;
		Rect rect1 = new Rect(left, top, right, bottom);

		Canvas canvas = new Canvas(qr);

		Rect rect2 = new Rect(0, 0, portrait_W, portrait_H);
		canvas.drawBitmap(portrait, rect2, rect1, null);

	}

	private Bitmap initProtrait() throws IOException {
		Bitmap portrait = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		Matrix mMatrix = new Matrix();
		float width = portrait.getWidth();
		float height = portrait.getHeight();
		mMatrix.setScale(70 / width, 70 / height);
		return Bitmap.createBitmap(portrait, 0, 0, (int) width, (int) height,
				mMatrix, true);
	}

}

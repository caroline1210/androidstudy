package com.ltd.mos.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.Log;

public class ImageUtil {
	/**
	 * 
	 * 调整图片大小，调整图像到指定后的大小，如果长宽比例不符合原图，则居中平铺
	 * 
	 * @param inputFile
	 *            ：图片路径
	 * @param dstWidth
	 *            ：调整后宽度
	 * @param dstHeight
	 *            ：调整后高度
	 * @return
	 * @param
	 * @return Bitmap
	 * @throws
	 */

	public static Bitmap resizeBitmap(String inputFile, int dstWidth,
			int dstHeight) {
		if (FileUtil.getFileSizes(new File(inputFile)) == 0) {
			return null;
		}
		Bitmap roughBitmap;
		Bitmap resizedBitmap;
		try {
			int inWidth = 0;
			int inHeight = 0;
			InputStream in = new FileInputStream(inputFile);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(in, null, options);
			in.close();
			in = null;

			// save width and height
			inWidth = options.outWidth;
			inHeight = options.outHeight;

			// decode full image pre-resized
			in = new FileInputStream(inputFile);
			options = new BitmapFactory.Options();
			// calc rought re-size (this is no exact resize)
			options.inSampleSize = Math.max(inWidth / dstWidth, inHeight
					/ dstHeight);
			// decode full image
			roughBitmap = BitmapFactory.decodeStream(in, null, options);

			// calc exact destination size
			Matrix m = new Matrix();
			RectF inRect = new RectF(0, 0, roughBitmap.getWidth(),
					roughBitmap.getHeight());
			RectF outRect = new RectF(0, 0, dstWidth, dstHeight);
			m.setRectToRect(inRect, outRect, Matrix.ScaleToFit.CENTER);
			float[] values = new float[9];
			m.getValues(values);
			// resize bitmap
			resizedBitmap = Bitmap.createScaledBitmap(roughBitmap,
					(int) (roughBitmap.getWidth() * values[0]),
					(int) (roughBitmap.getHeight() * values[4]), true);
			in.close();
			in = null;
			if (roughBitmap != resizedBitmap) {// 生成的bitmap是同一个bitmap
				roughBitmap.recycle();
			}
			return resizedBitmap;
		} catch (OutOfMemoryError e) {
			System.gc();
			Log.e("Image", e.getMessage(), e);
		} catch (IOException e) {
			Log.e("Image", e.getMessage(), e);
		}
		return null;
	}
}

package com.ltd.mos.image;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * 网络图片异步加载
 * 
 * @ClassName: AsyncImageLoader
 * @Description: TODO
 * @author xuwu
 * @date 2014-9-2 下午01:36:24
 */
public class AsyncImageLoader {

	static DisplayImageOptions options;

	public static void initOption() {
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_stub)
				.showImageForEmptyUri(R.drawable.ic_empty)
				.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new SimpleBitmapDisplayer())// default
														// 可以设置动画，比如圆角或者渐变
				.build();
	}

	public static void displayImage(String imageUri, ImageView view) {

		/*
		 * String imageUri = "http://site.com/image.png"; // from Web String
		 * imageUri = "file:///mnt/sdcard/image.png"; // from SD card String
		 * imageUri = "content://media/external/audio/albumart/13"; // from
		 * content provider String imageUri = "assets://image.png"; // from
		 * assets
		 */

		BaseActivity.imageLoader.displayImage(imageUri, view, options,
				new AnimateFirstDisplayListener());
	}

	public static void displayImage(String imageUri, ImageView view,
			ECallBack callBack) {

		/*
		 * String imageUri = "http://site.com/image.png"; // from Web String
		 * imageUri = "file:///mnt/sdcard/image.png"; // from SD card String
		 * imageUri = "content://media/external/audio/albumart/13"; // from
		 * content provider String imageUri = "assets://image.png"; // from
		 * assets
		 */

		BaseActivity.imageLoader.displayImage(imageUri, view, options,
				new AnimateFirstListener(callBack));
	}

	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener {

		public static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}

	private static class AnimateFirstListener extends
			SimpleImageLoadingListener {
		ECallBack callBack;

		public AnimateFirstListener(ECallBack callBack) {
			// TODO Auto-generated constructor stub
			this.callBack = callBack;
		}

		public static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
				callBack.OnCreate("LOADINGCOMPLETE");
			}
		}
	}

}

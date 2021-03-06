package com.ltd.mos.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

public class FileUtil {

	/**
	 * 数据库文件复制
	 * 
	 * @param context
	 */
	public static void copyDBFile(Context context, String dbName) {
		File destFile = new File(context.getFilesDir(), dbName);
		if (destFile.exists() && destFile.length() > 0) {
			Log.d("LOG", "exist");
			return;
		}
		try {
			InputStream is = context.getAssets().open(dbName);
			File file = copyFile(is, destFile.getAbsolutePath());
			if (file == null) {
				Log.d("LOG", "copy failed");
			} else {
				Log.d("LOG", "copy success");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 文件拷贝
	public static File copyFile(InputStream is, String path) {
		try {
			File file = new File(path);
			FileOutputStream fos = new FileOutputStream(file);

			byte[] buffer = new byte[1024];
			int len = 0;

			while ((len = is.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
			fos.flush();
			fos.close();
			is.close();
			return file;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static long getAvailaleSize() {

		File path = Environment.getExternalStorageDirectory(); // 取得sdcard文件路径

		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();// 获取block的SIZE
		long availableBlocks = stat.getAvailableBlocks();// 空闲的Block的数量;
		return (availableBlocks * blockSize) / 1024 / 1024;// MIB单位

	}
	/**
	 * 写文件
	 */
	public static void write2SDcard(String str, String name) {

		String sDStateString = android.os.Environment.getExternalStorageState();
		File myFile = null;

		if (sDStateString.equals(android.os.Environment.MEDIA_MOUNTED)) {

			try {
				File SDFile = android.os.Environment
						.getExternalStorageDirectory();

				File destDir = new File(SDFile.getAbsolutePath() + "/humos");

				if (!destDir.exists()) {// 判断目录是否存在，不存在创建
					destDir.mkdir();// 创建目录
				}

				// 打开文件
				myFile = new File(destDir.getAbsolutePath() + File.separator
						+ name);

				// 判断文件是否存在,不存在则创建
				if (!myFile.exists()) {
					myFile.createNewFile();// 创建文件
				}
				// 写数据 注意这里，两个参数，第一个是写入的文件，第二个是指是覆盖还是追加，
				// 默认是覆盖的，就是不写第二个参数，这里设置为true就是说不覆盖，是在后面追加。
				FileOutputStream outputStream = new FileOutputStream(myFile,
						true);
				outputStream.write(str.getBytes());// 写入内容
				outputStream.close();// 关闭流

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	/**
	 * 文件大小
	 */
	public static long getFileSizes(File f) {// 取得文件大小
		long s = 0;
		if (f.exists()) {
			try {
				FileInputStream fis = null;
				fis = new FileInputStream(f);
				s = fis.available();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
		} else {
			return 0;
		}
		return s;
	}
}

/**
 * Dinglan eFamily
 *
 * Copyright (c) Beijing Dinglan Tech Co.,Ltd. (http://www.idinglan.cn)
 *
 *
 * @copyright     Copyright (c) Beijing Dinglan Tech Co.,Ltd. (http://www.idinglan.cn)
 * @link          http://www.idinglan.cn eFamily Project
 * @package       app
 * @since         eFamily 1.0
 */
package com.ltd.mos.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import android.util.Log;

/**
 * @author Van Wang
 * 
 */
public class IoUtils {

	private static final int BUFFER_SIZE = 1024 * 2;

	private IoUtils() {
		// Utility class.
	}

	public static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		StringBuilder sb = new StringBuilder();

		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				is.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

		return sb.toString();

	}

	public static String inputStreamToString(InputStream input)
			throws IOException {
		String ret = "";
		if (input != null) {
			Writer writer = new StringWriter();
			char[] buffer = new char[BUFFER_SIZE];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(input,
						"UTF-8"), BUFFER_SIZE);
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
				ret = writer.toString();
			} finally {
				input.close();
				writer.close();
			}
		}
		return ret;
	}

	public static int copyInputStreamToOutputStream(InputStream input,
			OutputStream output) throws Exception, IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		BufferedInputStream in = new BufferedInputStream(input, BUFFER_SIZE);
		BufferedOutputStream out = new BufferedOutputStream(output, BUFFER_SIZE);
		int count = 0, n = 0;
		try {

			while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
				out.write(buffer, 0, n);
				count += n;
			}
			out.flush();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				Log.e("IoUtils", e.getMessage());
			}
			try {
				in.close();
			} catch (IOException e) {
				Log.e("IoUtils", e.getMessage());
			}
		}
		return count;
	}
}

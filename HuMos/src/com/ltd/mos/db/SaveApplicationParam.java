package com.ltd.mos.db;

import com.ltd.mos.bean.Register;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SaveApplicationParam {

	/**
	 * ��ȡ�û���Ϣ
	 * 
	 * @author xuwu
	 * @param context
	 * @param regist
	 */
	public static Register getRegister(Context context) {
		Register regist = new Register();
		SharedPreferences sp = context.getSharedPreferences(Const.USERINFO,
				Activity.MODE_PRIVATE);
		regist.setName(sp.getString(Const.USERNAME, ""));
		regist.setPhone(sp.getString(Const.PHONENUMBER, ""));
		regist.setPwd(sp.getString(Const.PASSWORD, ""));
		return regist;

	}

	/**
	 * �����û���Ϣ
	 * 
	 * @author xuwu
	 * @param context
	 * @param regist
	 */
	public static void saveRegister(Context context, Register regist) {
		context.getSharedPreferences(Const.USERINFO, Activity.MODE_PRIVATE)
				.edit()
				.putString(Const.PHONENUMBER,
						Logic.getString(regist.getPhone()))
				.putString(Const.USERNAME, Logic.getString(regist.getName()))
				.putString(Const.PASSWORD, Logic.getString(regist.getPwd()))
				.commit();

	}

	/**
	 * �����û���½״̬
	 * 
	 * @param context
	 * @param val
	 */
	public static void saveLandState(Context context, boolean val) {
		context.getSharedPreferences(Const.USERINFO, Activity.MODE_PRIVATE)
				.edit().putBoolean(Const.LANDSTATE, val).commit();

	}

	/**
	 * ��ȡ�û���½״̬
	 * 
	 * @param context
	 * @param val
	 */
	public static boolean getLandState(Context context) {
		return context.getSharedPreferences(Const.USERINFO,
				Activity.MODE_PRIVATE).getBoolean(Const.LANDSTATE, false);

	}
	/**
	 * �����û����ε�½��ַ
	 * 
	 * @param context
	 * @param val
	 */
	public static void saveLandLocation(Context context, String val) {
		context.getSharedPreferences("location", Activity.MODE_PRIVATE)
				.edit().putString(Const.LANDSTATE, val).commit();

	}

	/**
	 * ��ȡ�û��ϴε�½��ַ
	 * 
	 * @param context
	 * @param val
	 */
	public static String getLandLocation(Context context) {
		return context.getSharedPreferences("location",
				Activity.MODE_PRIVATE).getString(Const.LANDSTATE, "");

	}
	
	/**
	 * �����û��Ƿ�������������
	 * 
	 * @param context
	 * @param val
	 */
	public static void saveGuideVal(Context context, boolean val) {
		context.getSharedPreferences(Const.USERINFO, Activity.MODE_PRIVATE)
				.edit().putBoolean(Const.INGUIDE, val).commit();

	}

	/**
	 * ��ȡ�û��Ƿ�������������
	 * 
	 * @param context
	 * @param val
	 */
	public static boolean getGuideVal(Context context) {
		return context.getSharedPreferences(Const.USERINFO,
				Activity.MODE_PRIVATE).getBoolean(Const.INGUIDE, false);

	}

	/**
	 * ������Ϣ���͵Ŀ���״̬
	 * 
	 * @param context
	 * @param key
	 */
	public static void saveMsgSendVal(Context context, boolean key) {
		context.getSharedPreferences(Const.PERSONAL, Activity.MODE_PRIVATE)
				.edit().putBoolean(Const.MSGSEND, key).commit();

	}

	/**
	 * ��ȡ��Ϣ���͵Ŀ���״̬
	 * 
	 * @param context
	 * @param key
	 */
	public static boolean getMsgSendVal(Context context) {
		return context.getSharedPreferences(Const.PERSONAL,
				Activity.MODE_PRIVATE).getBoolean(Const.MSGSEND, false);

	}

	/**
	 * �����������ѵĿ���״̬
	 * 
	 * @param context
	 * @param key
	 */
	public static void savaVoiceRemind(Context context, boolean key) {
		context.getSharedPreferences(Const.PERSONAL, Activity.MODE_PRIVATE)
				.edit().putBoolean(Const.VOICEREMIND, key).commit();
	}

	/**
	 * ��ȡ�������ѵĿ���״̬
	 * 
	 * @param context
	 * @param key
	 */
	public static boolean getVoiceRemind(Context context) {
		return context.getSharedPreferences(Const.PERSONAL,
				Activity.MODE_PRIVATE).getBoolean(Const.VOICEREMIND, false);
	}

	/**
	 * �����Ƿ�����ͼģʽ��
	 * 
	 * @param context
	 * @param key
	 */
	public static void saveNoPaint(Context context, boolean key) {
		context.getSharedPreferences(Const.PERSONAL, Activity.MODE_PRIVATE)
				.edit().putBoolean(Const.NOPAINT, key).commit();
	}

	/**
	 * ��ȡ�Ƿ�����ͼģʽ��
	 * 
	 * @param context
	 * @return
	 */
	public static boolean getNoPaint(Context context) {
		return context.getSharedPreferences(Const.PERSONAL,
				Activity.MODE_PRIVATE).getBoolean(Const.NOPAINT, false);
	}

}

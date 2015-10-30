package com.ltd.mos.db;

import java.util.ArrayList;

import com.ltd.mos.bean.WineInfo;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.Logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * @ClassName: DBHandle
 * @Description: 数据库操作类
 * @author xuwu
 * @date 2014-8-22 上午10:45:40
 */
public class DBHandle {

	private SQLiteDatabase db;
	private MyDatabaseHepler dbHelper;
	private final Context context;

	public DBHandle(Context ctx) {
		this.context = ctx.getApplicationContext();
		if (dbHelper == null) {
			dbHelper = new MyDatabaseHepler(context);
		}
		db = dbHelper.getWritableDatabase();
	}

	/**
	 * 添加商品到购物车 xuwu
	 * 
	 * @param info
	 * @return
	 */
	public boolean addWineInfo2Car(WineInfo info) {
		try {
			db.insert(Const.TABLE_SHOPCAR, null, wineInfo2CValue(info));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 删除购物车商品 xuwu
	 * 
	 * @param info
	 * @return
	 */
	public boolean deleteCarInfo(String productId) {
		try {
			db.delete(Const.TABLE_SHOPCAR, "goods_id = ?",
					new String[] { productId });
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 获取购物车商品
	 * 
	 * @author xuwu
	 * @return
	 */
	public ArrayList<WineInfo> getWineInfoByCar() {
		ArrayList<WineInfo> list = new ArrayList<WineInfo>();
		try {
			Cursor cursor = db.query(Const.TABLE_SHOPCAR, null, null, null,
					null, null, null);

			cursor.moveToFirst();
			while (!cursor.isAfterLast()) {
				list.add(getWineInfoData(cursor));
				cursor.moveToNext();
			}
			cursor.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * xuwu
	 * 
	 * @param info
	 * @return
	 */
	public ContentValues wineInfo2CValue(WineInfo info) {
		ContentValues value = new ContentValues();
		value.put("goods_number", Logic.getString(info.getWineId()));
		value.put("goods_title", info.getWineDese());
		value.put("goods_price", info.getWinePrice());
		value.put("goods_url", Logic.getString(info.getWinePicUrl()));
		value.put("goods_num", info.getWinNum());

		return value;
	}

	/**
	 * xuwu
	 * 
	 * @param cursor
	 * @return
	 */
	public WineInfo getWineInfoData(Cursor cursor) {

		WineInfo info = new WineInfo();

		int columnIndex = cursor.getColumnIndex("goods_number");
		if (columnIndex != -1)
			info.setWineId(cursor.getString(columnIndex));

		columnIndex = cursor.getColumnIndex("goods_id");
		if (columnIndex != -1)
			info.setGoods_id(cursor.getString(columnIndex));

		columnIndex = cursor.getColumnIndex("goods_title");
		if (columnIndex != -1)
			info.setWineDese(cursor.getString(columnIndex));

		columnIndex = cursor.getColumnIndex("goods_price");
		if (columnIndex != -1)
			info.setWinePrice(cursor.getString(columnIndex));

		columnIndex = cursor.getColumnIndex("goods_url");
		if (columnIndex != -1)
			info.setWinePicUrl(cursor.getString(columnIndex));

		columnIndex = cursor.getColumnIndex("goods_num");
		if (columnIndex != -1)
			info.setWinNum(cursor.getString(columnIndex));

		return info;
	}

	public void close() {

		if (db != null) {
			db.close();
		}
	}

}
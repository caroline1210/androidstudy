package com.ltd.mos.main;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;

/**
 * 主页功能按钮适配器
 * 
 * @author xuwu
 * 
 */
public class WineHomeAdapter extends BaseAdapter {

	private String[] itemName = { "葡萄酒", "白酒", "啤酒", "老酒", "其它", "收货扫" };

	private double imageWidth;// 图片的宽 高

	private double itemHeight;// item的高
	private int[] imageId = { R.drawable.ptj, R.drawable.bj, R.drawable.pj,
			R.drawable.lj, R.drawable.qt, R.drawable.sys, };

	private LayoutInflater inflater;

	public WineHomeAdapter(Context context) {
		inflater = LayoutInflater.from(context);
		imageWidth = (BaseActivity.SCREENWIDE / 3) * 3 / 5;
		itemHeight = BaseActivity.SCREENWIDE / 3;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return itemName.length;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.winehome_item, null);
			holder = new ViewHolder();

			holder.home_item_image = (ImageView) convertView
					.findViewById(R.id.home_item_image);
			holder.home_item_text = (TextView) convertView
					.findViewById(R.id.home_item_text);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.topMargin = (int) ((itemHeight - imageWidth - 20) / 3);
		lp.width = (int) imageWidth;
		lp.height = (int) imageWidth;
		holder.home_item_image.setLayoutParams(lp);

		LinearLayout.LayoutParams lp_text = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp_text.topMargin = (int) ((itemHeight - imageWidth - 20) / 3) - 10;
		lp_text.bottomMargin = (int) ((itemHeight - imageWidth - 20) / 3) - 10;
		holder.home_item_text.setLayoutParams(lp_text);

		holder.home_item_image.setImageResource(imageId[position]);
		holder.home_item_text.setText(itemName[position]);

		return convertView;
	}

	class ViewHolder {
		ImageView home_item_image;
		TextView home_item_text;
	}

}

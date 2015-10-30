package com.ltd.mos.personal;

import java.util.ArrayList;

import com.ltd.mos.R;
import com.ltd.mos.bean.WineInfo;
import com.ltd.mos.util.Logic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MycollectAdapter extends BaseAdapter{

	Context context;
	ArrayList<WineInfo> list;

	public MycollectAdapter(Context c,
			ArrayList<WineInfo> list) {
		// TODO Auto-generated constructor stub
		this.context=c;
		this.list=list;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.mycollection_item, null);
			
			holder.iv_image = (ImageView) convertView.findViewById(R.id.iv_collect_content);
			holder.iv_location = (ImageView) convertView.findViewById(R.id.iv_collect_location);
			holder.tv_desc = (TextView) convertView.findViewById(R.id.tv_collect_des);
			holder.tv_price = (TextView) convertView.findViewById(R.id.iv_collect_price);
			holder.tv_locaton=(TextView) convertView.findViewById(R.id.tv_collect_location);
			holder.tv_winedese=(TextView) convertView.findViewById(R.id.tv_collect_winedese);
			holder.tv_marketprice=(TextView) convertView.findViewById(R.id.tv_collect_marketprice);
//			holder.num = (TextView) convertView.findViewById(R.id.num);
//			holder.add = (Button) convertView.findViewById(R.id.add);
//			holder.reduce = (Button) convertView.findViewById(R.id.reduce);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		WineInfo wineInfo = list.get(position);
		
		holder.iv_image.setBackgroundDrawable(context.getResources()
				.getDrawable(R.drawable.jiu1));
		holder.tv_price.setText(Logic.getString(wineInfo.getWinePrice()));
		holder.tv_winedese.setText(Logic.getString(wineInfo.getWineDese()));
		holder.tv_desc.setText(Logic.getString(wineInfo.getWineevent()));
		holder.tv_locaton.setText(Logic.getString(wineInfo.getLocation()));
		holder.tv_marketprice.setText(Logic.getString(wineInfo.getMarketPrice()));
		
		return convertView;
	}

	class ViewHolder {
		
		private ImageView iv_image,iv_location;
		private TextView tv_desc;
//		private TextView price;
//		private TextView num;
		private TextView tv_locaton,tv_marketprice,tv_price,tv_winedese;
//		private Button add;
//		private Button reduce;
	}
}
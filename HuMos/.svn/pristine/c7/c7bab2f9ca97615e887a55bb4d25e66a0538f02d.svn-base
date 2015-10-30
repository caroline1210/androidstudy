package com.ltd.mos.sercenter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ltd.mos.R;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.SearchWineBean;
import com.ltd.mos.util.Logic;

public class SearchWineAdapter extends BaseAdapter {
	ArrayList<PostBean> list;
	Context context;
	ECallBack callBack;

	public SearchWineAdapter(ArrayList<PostBean> list, Context context,
			ECallBack callBack) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = list;
		this.callBack = callBack;
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

	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = FrameLayout.inflate(context,
					R.layout.search_wine_item, null);
			viewHolder = new ViewHolder();
			viewHolder.type = (TextView) convertView.findViewById(R.id.type);
			viewHolder.desc = (TextView) convertView.findViewById(R.id.desc);
			viewHolder.num = (TextView) convertView.findViewById(R.id.num);
			viewHolder.key = (TextView) convertView.findViewById(R.id.key);
			viewHolder.imageIcon = (ImageView) convertView
					.findViewById(R.id.searchIcon);
			viewHolder.searchIcon = (Button) convertView
					.findViewById(R.id.serachButton);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		PostBean searchWineBean = list.get(position);
//		 viewHolder.type.setText(Logic.getString(searchWineBean.getType()));
		viewHolder.type.setVisibility(View.GONE);
		viewHolder.desc
				.setText(Logic.getString(searchWineBean.getXunJiuTitle()));
		// viewHolder.num.setText("Õ¨«Û" + searchWineBean.getNum() + "»À");
		viewHolder.num.setVisibility(View.GONE);
		viewHolder.key.setText(Logic.getString(searchWineBean
				.getXunJiuContent()));
		if (searchWineBean.getXunJiuImages() != null
				&& searchWineBean.getXunJiuImages().size() == 1) {
			viewHolder.imageIcon.setVisibility(View.VISIBLE);
		} else {
			viewHolder.imageIcon.setVisibility(View.GONE);
		}
		// if (searchWineBean.getSearchState() == 1) {
		// viewHolder.searchIcon.setBackgroundDrawable(context.getResources()
		// .getDrawable(R.drawable.select_true));
		// } else {
		// viewHolder.searchIcon.setBackgroundDrawable(context.getResources()
		// .getDrawable(R.drawable.select_false));
		// }
		// viewHolder.searchIcon.setOnClickListener(new OnClickListener() {
		//
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		// callBack.OnCreate(position);
		// }
		// });
		return convertView;
	}

	class ViewHolder {
		TextView type;
		TextView desc;
		TextView num;
		TextView key;
		ImageView imageIcon;
		Button searchIcon;
	}
}

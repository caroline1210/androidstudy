package com.ltd.mos.personal;

import java.util.ArrayList;

import com.ltd.mos.R;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.OrderItemList;
import com.ltd.mos.erweima.ErcodeScanActivity;
import com.ltd.mos.image.AsyncImageLoader;
import com.ltd.mos.util.Logic;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DGoodsAdapter extends BaseAdapter {

	Context context;
	ArrayList<OrderItemList> list;
	ECallBack callBack;

	public DGoodsAdapter(Context c, ArrayList<OrderItemList> list,
			ECallBack callBack) {
		// TODO Auto-generated constructor stub
		this.context = c;
		this.list = list;
		this.callBack = callBack;
		AsyncImageLoader.initOption();
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
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.dgoods_item, null);
			holder.tv_merchant = (TextView) convertView
					.findViewById(R.id.tv_merchant);
			holder.tv_winedese = (TextView) convertView
					.findViewById(R.id.tv_dgoods_winedese);
			holder.tv_price = (TextView) convertView
					.findViewById(R.id.tv_dgoods_price);
			holder.tv_num = (TextView) convertView
					.findViewById(R.id.tv_dgoods_count);
			holder.tv_time = (TextView) convertView
					.findViewById(R.id.tv_dgoods_time);
			holder.tv_cancel = (TextView) convertView
					.findViewById(R.id.tv_dgoods_cancel);
			holder.tv_call = (TextView) convertView
					.findViewById(R.id.tv_dgoods_phone);
			holder.iv_goods = (ImageView) convertView
					.findViewById(R.id.iv_dgoods_goods);
			holder.iv_cancel = (ImageView) convertView
					.findViewById(R.id.iv_dgoods_cancel);
			holder.iv_call = (ImageView) convertView
					.findViewById(R.id.iv_dgoods_phone);
			holder.b_confirm = (Button) convertView
					.findViewById(R.id.b_dgoods_confirm_order);
			holder.rl_cancel = (RelativeLayout) convertView
					.findViewById(R.id.rl_dgoods_cancel);
			holder.ll_call = (LinearLayout) convertView
					.findViewById(R.id.ll_dgoods_call);
			holder.ll_call.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(context, "致电商家", Toast.LENGTH_SHORT).show();
				}
			});
			holder.rl_cancel.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					callBack.OnCreate(position);
				}
			});
			holder.b_confirm.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					OrderItemList orderItemList = list.get(position);
					context.startActivity(new Intent(context,
							ErcodeScanActivity.class).putExtra("id",
							orderItemList.getOrderId()));
					

				}
			});
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		OrderItemList orderItemList = list.get(position);
		String url = Logic.getString(orderItemList.getSmallImageUrl());
		AsyncImageLoader.displayImage(url, holder.iv_goods);
		// holder.tv_merchant.setText(orderItemList.getSendMerchant());
		holder.tv_winedese.setText(orderItemList.getInternalName());
		holder.tv_price.setText(orderItemList.getItemTotal() + "元");
		holder.tv_num.setText(orderItemList.getQuantity() + "瓶");
		holder.tv_time.setText(orderItemList.getDate());

		return convertView;
	}

	class ViewHolder {
		private TextView tv_merchant, tv_winedese, tv_price, tv_num, tv_time,
				tv_cancel, tv_call;
		private ImageView iv_goods, iv_cancel, iv_call;
		private Button b_confirm;
		private RelativeLayout rl_cancel;
		LinearLayout ll_call;
	}
}

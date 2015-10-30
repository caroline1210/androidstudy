package com.ltd.mos.main;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.bean.CategoryFeature;
import com.ltd.mos.bean.FilterBean;

/**
 * É¸Ñ¡µ¯³ö¿òÊÊÅäÆ÷
 * 
 * @author xuwu
 * 
 */
public class FilterListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private ArrayList<CategoryFeature> list;
	private Context cxt;
	ArrayList<String> featureIds;

	public FilterListAdapter(Context context, ArrayList<CategoryFeature> list) {
		this.list = list;
		this.cxt = context;
		inflater = LayoutInflater.from(context);
		featureIds = new ArrayList<String>();
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	private GridAdapter gridAdapter;

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.grid_filter, null);
			holder = new ViewHolder();
			holder.filter_title = (TextView) convertView
					.findViewById(R.id.filter_title);
			holder.filter_grid = (GridView) convertView
					.findViewById(R.id.filter_grid);
			convertView.setTag(holder);

		}

		else {
			holder = (ViewHolder) convertView.getTag();
		}
		CategoryFeature categoryFeature = list.get(position);

		holder.filter_title.setText(categoryFeature.getDescription());
		gridAdapter = new GridAdapter(categoryFeature);
		holder.filter_grid.setAdapter(gridAdapter);
		holder.filter_grid.setOnItemClickListener(new ItemClickLison(position,
				gridAdapter));

		return convertView;
	}

	static class ViewHolder {
		TextView filter_title;
		GridView filter_grid;
	}

	class GridAdapter extends BaseAdapter {

		private CategoryFeature categoryFeature;

		public GridAdapter(CategoryFeature categoryFeature) {
			this.categoryFeature = categoryFeature;
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return categoryFeature.getFeatureList().size();
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

				convertView = inflater.inflate(R.layout.item_grid_filter, null);
				holder = new ViewHolder();

				holder.grid_item_text = (TextView) convertView
						.findViewById(R.id.grid_item_text);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			FilterBean filterBean = categoryFeature.getFeatureList().get(
					position);
			if (position == categoryFeature.getSelectItem()) {
				holder.grid_item_text.setTextColor(cxt.getResources().getColor(
						R.color.blue));
			} else {
				holder.grid_item_text.setTextColor(cxt.getResources().getColor(
						R.color.gray002));
			}
			holder.grid_item_text.setText(filterBean.getDescription());

			return convertView;
		}

		class ViewHolder {
			TextView grid_item_text;
		}

	}

	class ItemClickLison implements OnItemClickListener {

		private int pos;
		private GridAdapter gridAdapter;

		public ItemClickLison(int pos, GridAdapter gridAdapter) {
			this.pos = pos;
			this.gridAdapter = gridAdapter;
		}

		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			list.get(pos).setSelectItem(position);
			if (BaseActivity.categoryFeatureList == null)
				BaseActivity.categoryFeatureList = new ArrayList<CategoryFeature>();
			BaseActivity.categoryFeatureList.clear();
			BaseActivity.categoryFeatureList.addAll(list);
			gridAdapter.notifyDataSetChanged();
		}

	}
}

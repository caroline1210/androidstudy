package com.ltd.mos.main;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.PostBean;
import com.ltd.mos.bean.ResultObject;
import com.ltd.mos.bean.SearchBean;
import com.ltd.mos.bean.WineInfo;
import com.ltd.mos.http.JsonPrase;
import com.ltd.mos.task.Task;
import com.ltd.mos.util.Const;
import com.ltd.mos.util.FileUtil;
import com.ltd.mos.util.Logic;

public class WineSearchActivity extends BaseActivity implements OnClickListener {

	private LinearLayout title_return, history_ll;
	private EditText search_edit;
	private ImageView search_delete;
	private ArrayList<SearchBean> searchHistory;
	private GridView history_grid;
	private TextView title_search, search_num;
	private ListView listview;
	private int viewsize = 10;
	private int viewIndex = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.winesearch);

		findViewById();

		Logic logic = Logic.getInstance();

		searchHistory = logic.getSearchHistory(this);

		history_grid.setAdapter(new GridAdapter());

		history_grid.setOnItemClickListener(new GridItemClick());
	}

	private void findViewById() {
		title_return = (LinearLayout) findViewById(R.id.title_return);
		search_delete = (ImageView) findViewById(R.id.search_delete);
		title_search = (TextView) findViewById(R.id.title_search);
		search_edit = (EditText) findViewById(R.id.search_edit);
		history_ll = (LinearLayout) findViewById(R.id.history_ll);
		history_grid = (GridView) findViewById(R.id.history_grid);
		listview = (ListView) findViewById(R.id.winesearch_list);
		search_num = (TextView) findViewById(R.id.search_num);
		title_return.setOnClickListener(this);
		search_delete.setOnClickListener(this);
		title_search.setOnClickListener(this);
		search_edit.addTextChangedListener(new EditTextWatcher());

	}

	int selectItem;

	private class OnScrollListenerImple implements OnScrollListener {

		public void onScroll(AbsListView absListView, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			// int lastItem = firstVisibleItem + visibleItemCount;
			// if (lastItem == totalItemCount) {
			// try {
			// System.out.println("Scroll to the listview last item");
			// View lastItemView = (View) absListView
			// .getChildAt(absListView.getChildCount() - 1);
			// int bottom_list = absListView.getBottom();
			// int bottom_last = lastItemView.getBottom();
			// if (bottom_list == bottom_last) {
			// System.out
			// .println("========Scroll to the listview bottom =============");
			// selectItem = firstVisibleItem;
			// viewIndex++;
			// PostBean post = new PostBean();
			// post.setCode(Const.SEARCHPRODUCT);
			// post.setInternalName(Logic.getString(
			// search_edit.getText()).trim());
			// post.setViewSize(viewsize + "");
			// post.setViewIndex(viewIndex + "");
			//
			// new Task(post, WineSearchActivity.this,
			// new SearchCallBack(false)).postHttp();
			//
			// }
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// }
		}

		public void onScrollStateChanged(AbsListView listview, int scrollState) {

			// 当不滚动时
			if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
				// 判断是否滚动到底部
				if (listview.getLastVisiblePosition() == listview.getCount() - 1) {
					selectItem = listview.getFirstVisiblePosition() + 1;
					viewIndex++;
					PostBean post = new PostBean();
					post.setCode(Const.SEARCHPRODUCT);
					post.setInternalName(Logic.getString(search_edit.getText())
							.trim());
					post.setViewSize(viewsize + "");
					post.setViewIndex(viewIndex + "");

					new Task(post, WineSearchActivity.this, new SearchCallBack(
							false)).postHttp();

				}
			}

		}

	}

	class GridAdapter extends BaseAdapter {

		public int getCount() {
			// TODO Auto-generated method stub
			return searchHistory.size();
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
				convertView = LinearLayout.inflate(WineSearchActivity.this,
						R.layout.searchhistory_item, null);
				holder = new ViewHolder();
				holder.history_text = (TextView) convertView
						.findViewById(R.id.history_item_text);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			SearchBean searchBean = searchHistory.get(position);
			holder.history_text.setText(searchBean.getName());

			return convertView;
		}

		class ViewHolder {
			TextView history_text;

		}
	}

	class GridItemClick implements OnItemClickListener {

		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			SearchBean searchBean = searchHistory.get(position);
			search_edit.setText(searchBean.getName());
			search_edit.setSelection(search_edit.getText().length());
		}

	}

	class EditTextWatcher implements TextWatcher {

		private CharSequence temp;

		public void afterTextChanged(Editable s) {

			if (temp.toString().trim().length() > 0) {
				search_delete.setVisibility(View.VISIBLE);
			} else {
				search_delete.setVisibility(View.GONE);
				history_ll.setVisibility(View.VISIBLE);
			}

		}

		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			temp = s;

		}

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_return:

			this.finish();

			break;
		case R.id.title_search:
			viewIndex = 0;
			String text = Logic.getString(search_edit.getText()).trim();
			if (text.length() == 0) {
				search_edit.setText(search_edit.getHint());
				search_edit.setSelection(search_edit.getText().length());
			}
			PostBean post = new PostBean();
			post.setCode(Const.SEARCHPRODUCT);
			post.setInternalName(Logic.getString(search_edit.getText()).trim());
			post.setViewSize(viewsize + "");
			post.setViewIndex(viewIndex + "");

			new Task(post, WineSearchActivity.this, new SearchCallBack(true))
					.postHttp();

			showProgressDialog(WineSearchActivity.this,
					getString(R.string.searching));

			break;
		case R.id.search_delete:
			search_edit.setText("");
			break;
		default:
			break;
		}

	}

	ArrayList<WineInfo> products;
	GrapeListAdapter adapter;

	class SearchCallBack implements ECallBack {

		boolean firstload;

		public SearchCallBack(boolean firstload) {
			this.firstload = firstload;
		}

		public void OnCreate(Object obj) {
			dismissProgressDialog();
			ResultObject result = (ResultObject) obj;
			String msg = result.getmMessage();
			FileUtil.write2SDcard(msg, "wineInfo.txt");
			JsonPrase prase = new JsonPrase();
			if (prase.getState(msg)) {
				if (firstload) {
					products = prase.findProduct(msg);
					adapter = new GrapeListAdapter(WineSearchActivity.this,
							products);
					listview.setOnItemClickListener(new WineItemClick());
					listview.setAdapter(adapter);
					if (products != null && products.size() > 0) {
						listview.setOnScrollListener(new OnScrollListenerImple());
						String listSize = prase.getProductListSize(msg);
						search_num.setText(listSize + "");
					} else {
						search_num.setText("0");
					}
					history_ll.setVisibility(View.GONE);
				} else {
					ArrayList<WineInfo> list = prase.findProduct(msg);

					if (list != null && list.size() > 0) {
						products.addAll(list);
						adapter.notifyDataSetChanged();
						listview.setSelection(selectItem);
					}
				}

			} else {
				Toast.makeText(getApplicationContext(),
						R.string.errcode_request, 1000).show();
			}

		}

		public void OnError(Object obj) {
			dismissProgressDialog();
			Toast.makeText(getApplicationContext(), R.string.errcode_net, 1000)
					.show();
		}

	}

	private class WineItemClick implements OnItemClickListener {

		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			startActivity(new Intent(WineSearchActivity.this, WineDetails.class)
					.putExtra(Const.WINEINFO, products.get(position)));

		}

	}
}

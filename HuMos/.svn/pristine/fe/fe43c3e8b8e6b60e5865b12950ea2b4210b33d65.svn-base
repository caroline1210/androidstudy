package com.ltd.mos.main;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.db.SaveApplicationParam;
import com.ltd.mos.util.Logic;

public class LocationActivity extends BaseActivity implements
		OnGetPoiSearchResultListener {
	private View view;
	private EditText content;
	private TextView currentLoaction;
	private ImageView clear;
	private ListView listView, lastListView;
	private PoiSearch mPoiSearch = null;
	private ArrayList<PoiInfo> list = new ArrayList<PoiInfo>();
	private LocationAdapter adapter, lastAdapter;
	public static ArrayList<PoiInfo> lastList = new ArrayList<PoiInfo>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);
		mPoiSearch = PoiSearch.newInstance();
		mPoiSearch.setOnGetPoiSearchResultListener(this);
		view = this.findViewById(R.id.view);
		Logic.getInstance().initHeadView(view, "切换位置", true, "",
				new ECallBack() {

					public void OnError(Object obj) {
						// TODO Auto-generated method stub

					}

					public void OnCreate(Object obj) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		content = (EditText) this.findViewById(R.id.content);
		content.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				mPoiSearch.searchInCity((new PoiCitySearchOption()).city("北京市")
						.keyword(Logic.getString(s)).pageNum(1));
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				// Geo搜索

			}

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		currentLoaction = (TextView) this.findViewById(R.id.currentLocation);
		clear = (ImageView) this.findViewById(R.id.clear);
		clear.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				lastList.clear();
				lastAdapter.notifyDataSetChanged();
			}
		});
		listView = (ListView) this.findViewById(R.id.listView);
		adapter = new LocationAdapter(list, this);
		listView.setAdapter(adapter);
		listView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				InputMethodManager localInputMethodManager = (InputMethodManager) (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
				getWindow().setSoftInputMode(
						WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
				localInputMethodManager.hideSoftInputFromWindow(
						content.getWindowToken(), 0);
				return false;
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				SaveApplicationParam.saveLandLocation(LocationActivity.this,
						list.get(arg2).address);
				lastList.add(list.get(arg2));
				finish();
			}
		});
		lastListView = (ListView) this.findViewById(R.id.lastListView);
		lastAdapter = new LocationAdapter(lastList, this);
		lastListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				SaveApplicationParam.saveLandLocation(LocationActivity.this,
						list.get(arg2).address);
				finish();
			}
		});
		lastListView.setAdapter(lastAdapter);
		if (currentLat != 0 && currentLng != 0) {
			if (mSearch == null) {
				initSearch();
			}
			LatLng ptCenter = new LatLng(currentLat, currentLng);
			ReverseGeoCodeOption option = new ReverseGeoCodeOption();
			option.location(ptCenter);
			mSearch.reverseGeoCode(option);
			WineHomeActivity.GETLOCATION = true;
		}
	}

	private GeoCoder mSearch = null;

	private void initSearch() {
		// 初始化搜索模块，注册事件监听
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
			public void onGetGeoCodeResult(GeoCodeResult result) {
				// TODO Auto-generated method stub
				if (result == null
						|| result.error != SearchResult.ERRORNO.NO_ERROR) {
					return;
				}
//				String strInfo = String.format("纬度：%f 经度：%f",
//						result.getLocation().latitude,
//						result.getLocation().longitude);
//				Toast.makeText(LocationActivity.this,
//						strInfo + ":" + result.getAddress(), Toast.LENGTH_LONG)
//						.show();
			}

			public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
				// TODO Auto-generated method stub
				if (result == null
						|| result.error != SearchResult.ERRORNO.NO_ERROR) {
					return;
				}
				String city = result.getAddressDetail().city;
				String address = result.getAddress();
				Toast.makeText(LocationActivity.this, city + " : " + address,
						Toast.LENGTH_LONG).show();
				currentLoaction.setText("当前位置：" + address);
			}
		});
	}

	public void onGetPoiDetailResult(PoiDetailResult result) {
		// TODO Auto-generated method stub
	}

	public void onGetPoiResult(PoiResult result) {
		// TODO Auto-generated method stub
		if (result.error != SearchResult.ERRORNO.NO_ERROR) {
		} else {
			list.clear();
			list.addAll(result.getAllPoi());
			adapter.notifyDataSetChanged();
			listView.setVisibility(View.VISIBLE);

		}
	}
}

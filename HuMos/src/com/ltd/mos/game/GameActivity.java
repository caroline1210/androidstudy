package com.ltd.mos.game;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.util.Logic;
import com.ltd.mos.util.SensorManagerHelper;
import com.ltd.mos.util.SensorManagerHelper.OnShakeListener;

public class GameActivity extends BaseActivity implements OnClickListener {

	private RadioGroup group;
	/** 酒游戏 */
	private WheelView left;
	private WheelView right;
	private WheelView middle;
	private Button wineStart;
	private int item1 = 1;
	private int item2 = 1;
	private int item3 = 1;
	HashMap<Integer, SoftReference<Bitmap>> bitmaps = new HashMap<Integer, SoftReference<Bitmap>>();
	private ViewGroup wineLayout;
	private int leftData[] = { R.drawable.game_zhiding, R.drawable.game_ziji,
			R.drawable.game_yiqi };
	private int middleData[] = { R.drawable.game_hebei,
			R.drawable.game_changshou, R.drawable.game_tiao };
	private int rightData[] = { R.drawable.game_suiyi, R.drawable.game_wu,
			R.drawable.game_qi };
	private String leftDesc[] = { "指定", "自己", "一起" };
	private String middleDesc[] = { "喝", "唱", "跳" };
	private String middleDesc2[] = { "杯", "首歌", "分钟" };
	private String rightDesc[] = { "随意", "5", "7" };

	private TextView wineDesc;
	private boolean WINE;
	/** 色子 */
	private int sizeNum;
	private TextView diceSize;// 每个色子总数目
	private ImageView basinBg, dice1, dice2, dice3, dice4, dice5, dice6;
	private TextView diceNum;// 色子个数
	private Button triangle, dice_start;
	private ListView listView;
	RadioButton diceRadioButton, wineRadioButton;
	private MyScrollLayout myScrollLayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);
		myScrollLayout = (com.ltd.mos.game.MyScrollLayout) this
				.findViewById(R.id.scrollLayout);
		myScrollLayout.SetOnViewChangeListener(new OnViewChangeListener() {

			public void OnViewChange(int index) {
				// TODO Auto-generated method stub
				if (index == 10) {// 自动进入， 暂时屏蔽
				} else if (index == 0) {
					diceRadioButton.setChecked(true);
					WINE = false;
				} else if (index == 1111) {
					listView.setVisibility(View.GONE);
				} else {
					WINE = true;
					wineRadioButton.setChecked(true);
				}
			}
		});
		View view = this.findViewById(R.id.view);
		Logic.getInstance().initHeadView(view, "酒斯基游戏", true, "",
				new ECallBack() {

					public void OnError(Object obj) {
						// TODO Auto-generated method stub

					}

					public void OnCreate(Object obj) {
						// TODO Auto-generated method stub
						finish();
					}
				});
		diceRadioButton = (RadioButton) this.findViewById(R.id.dice);
		diceRadioButton.setChecked(true);
		wineRadioButton = (RadioButton) this.findViewById(R.id.wine);
		WINE = false;
		group = (RadioGroup) this.findViewById(R.id.group);
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub.
				listView.setVisibility(View.GONE);
				switch (checkedId) {
				case R.id.dice:
					WINE = false;
					myScrollLayout.snapToScreen(0);
					break;
				case R.id.wine:
					WINE = true;
					myScrollLayout.snapToScreen(1);
					break;
				default:
					break;
				}
			}
		});
		SensorManagerHelper sensorHelper = new SensorManagerHelper(this);
		sensorHelper.setOnShakeListener(new OnShakeListener() {

			public void onShake() {
				// TODO Auto-generated method stub
				if (WINE || animRuning) {// 酒游戏界面不显示
					return;
				}
				startImage();
			}
		});
		initWineView();
		initDiceView();

	}

	Animation mAnimation;
	boolean animRuning = false;

	private void initDice(View view) {
		int wide = (int) (BaseActivity.SCREENWIDE - BaseActivity.dip2px(this,
				30)) * 3 / 4;
		RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
				.getLayoutParams();
		layoutParams.width = wide / 6;
		layoutParams.height = wide / 6;
		view.setLayoutParams(layoutParams);
	}

	private void initDiceView() {
		diceSize = (TextView) this.findViewById(R.id.wineNum);
		diceNum = (TextView) this.findViewById(R.id.diceNum);
		diceNum.setText("六粒");
		sizeNum = 5;
		diceNum.setOnClickListener(this);
		triangle = (Button) this.findViewById(R.id.triangle);
		triangle.setOnClickListener(this);
		basinBg = (ImageView) this.findViewById(R.id.basinBg);
		Logic.getInstance().initDiceGameView(this, basinBg);
		dice1 = (ImageView) this.findViewById(R.id.dice1);
		initDice(dice1);
		dice2 = (ImageView) this.findViewById(R.id.dice2);
		initDice(dice2);
		dice3 = (ImageView) this.findViewById(R.id.dice3);
		initDice(dice3);
		dice4 = (ImageView) this.findViewById(R.id.dice4);
		initDice(dice4);
		dice5 = (ImageView) this.findViewById(R.id.dice5);
		initDice(dice5);
		dice6 = (ImageView) this.findViewById(R.id.dice6);
		initDice(dice6);
		dice_start = (Button) this.findViewById(R.id.dice_start);
		dice_start.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (WINE || animRuning) {// 酒游戏界面不显示
					return;
				}
				startImage();
			}
		});
		listView = (ListView) this.findViewById(R.id.listView);
		SpinnerAdapter adapter = new SpinnerAdapter();
		listView.setAdapter(adapter);
		listView.setVisibility(View.GONE);
		listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				sizeNum = arg2;
				if (arg2 >= 0) {
					dice1.setVisibility(View.VISIBLE);
				} else {
					dice1.setVisibility(View.GONE);
				}
				if (arg2 >= 1) {
					dice2.setVisibility(View.VISIBLE);
				} else {
					dice2.setVisibility(View.GONE);
				}
				if (arg2 >= 2) {
					dice3.setVisibility(View.VISIBLE);
				} else {
					dice3.setVisibility(View.GONE);
				}
				if (arg2 >= 3) {
					dice4.setVisibility(View.VISIBLE);
				} else {
					dice4.setVisibility(View.GONE);
				}
				if (arg2 >= 4) {
					dice5.setVisibility(View.VISIBLE);
				} else {
					dice5.setVisibility(View.GONE);
				}
				if (arg2 >= 5) {
					dice6.setVisibility(View.VISIBLE);
				} else {
					dice6.setVisibility(View.GONE);
				}
				switch (arg2) {
				case 0:
					diceNum.setText("一粒");
					break;
				case 1:
					diceNum.setText("二粒");
					break;
				case 2:
					diceNum.setText("三粒");
					break;
				case 3:
					diceNum.setText("四粒");
					break;
				case 4:
					diceNum.setText("五粒");
					break;
				case 5:
					diceNum.setText("六粒");
					break;
				default:
					break;
				}
				listView.setVisibility(View.GONE);
			}
		});
		// 动画
		mAnimation = AnimationUtils
				.loadAnimation(this, R.anim.myown_design_bak);
		mAnimation.setAnimationListener(new AnimationListener() {
			public void onAnimationStart(Animation animation) {
				animRuning = true;
			}

			public void onAnimationEnd(Animation animation) {
				// TODO Auto-generated method stub
				int num = 0;
				int num1 = (int) (Math.random() * 6) + 1;
				int num2 = (int) (Math.random() * 6) + 1;
				int num3 = (int) (Math.random() * 6) + 1;
				int num4 = (int) (Math.random() * 6) + 1;
				int num5 = (int) (Math.random() * 6) + 1;
				int num6 = (int) (Math.random() * 6) + 1;
				if (sizeNum >= 0) {
					num += num1;
					initDicePic(dice1, num1);
				}
				if (sizeNum >= 1) {
					num += num2;
					initDicePic(dice2, num2);
				}
				if (sizeNum >= 2) {
					num += num3;
					initDicePic(dice3, num3);
				}
				if (sizeNum >= 3) {
					num += num4;
					initDicePic(dice4, num4);
				}
				if (sizeNum >= 4) {
					num += num5;
					initDicePic(dice5, num5);
				}
				if (sizeNum >= 5) {
					num += num6;
					initDicePic(dice6, num6);
				}
				Vibrate(GameActivity.this,200);
				diceSize.setText("点数：" + num);
				animRuning = false;
			}

			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void startImage() {
		if (sizeNum >= 0) {
			dice1.startAnimation(mAnimation);
		}
		if (sizeNum >= 1) {
			dice2.startAnimation(mAnimation);
		}
		if (sizeNum >= 2) {
			dice3.startAnimation(mAnimation);
		}
		if (sizeNum >= 3) {
			dice4.startAnimation(mAnimation);
		}
		if (sizeNum >= 4) {
			dice5.startAnimation(mAnimation);
		}
		if (sizeNum >= 5) {
			dice6.startAnimation(mAnimation);
		}

	}

	private void initDicePic(View view, int num) {
		switch (num) {
		case 1:
			view.setBackgroundResource(R.drawable.d1);
			break;
		case 2:
			view.setBackgroundResource(R.drawable.d2);
			break;
		case 3:
			view.setBackgroundResource(R.drawable.d3);
			break;
		case 4:
			view.setBackgroundResource(R.drawable.d4);
			break;
		case 5:
			view.setBackgroundResource(R.drawable.d5);
			break;
		case 6:
			view.setBackgroundResource(R.drawable.d6);
			break;

		default:
			break;
		}
	}

	private void initWineView() {
		if (SCREENWIDE == 0 || SCREENWIDE == 0) {
			getScreenInfo();
		}
		wineLayout = (ViewGroup) this.findViewById(R.id.wineLayout);
//		Logic.getInstance().initWineGameView(wineLayout);
		wineDesc = (TextView) this.findViewById(R.id.wine_desc);
		wineStart = (Button) this.findViewById(R.id.wine_start);
		wineStart.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				wheel1 = false;
				wheel2 = false;
				wheel3 = false;
				mixWheel(left, -(int) (Math.random() * 10) - 100);
				mixWheel(middle, -(int) (Math.random() * 10) - 100);
				mixWheel(right, -(int) (Math.random() * 10) - 100);
			}
		});
		left = (WheelView) this.findViewById(R.id.leftView);
		right = (WheelView) this.findViewById(R.id.rightView);
		middle = (WheelView) this.findViewById(R.id.middle);
		middle.setVisibleItems(3);
		middle.setViewAdapter(new CountryAdapter(this, getList(middleData)));
		middle.setCyclic(true);
		left.setVisibleItems(3);
		left.setViewAdapter(new CountryAdapter(this, getList(leftData)));
		left.setCyclic(true);
		right.setVisibleItems(3);
		right.setViewAdapter(new CountryAdapter(this, getList(rightData)));
		right.setCyclic(true);

		left.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
			}
		});

		left.addScrollingListener(new OnWheelScrollListener() {
			public void onScrollingStarted(WheelView wheel) {
			}

			public void onScrollingFinished(WheelView wheel) {
				item1 = wheel.getCurrentItem();
				setWheel(1, item1);
			}
		});
		middle.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
			}
		});

		middle.addScrollingListener(new OnWheelScrollListener() {
			public void onScrollingStarted(WheelView wheel) {
			}

			public void onScrollingFinished(WheelView wheel) {
				item2 = wheel.getCurrentItem();
				setWheel(2, item2);
			}
		});
		right.addChangingListener(new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
			}
		});

		right.addScrollingListener(new OnWheelScrollListener() {
			public void onScrollingStarted(WheelView wheel) {
			}

			public void onScrollingFinished(WheelView wheel) {
				item3 = wheel.getCurrentItem();
				setWheel(3, item3);
			}
		});

	}

	boolean wheel1, wheel2, wheel3;

	private void setWheel(int type, int position) {
		if (type == 1) {
			wheel1 = true;
		} else if (type == 2) {
			wheel2 = true;
		} else if (type == 3) {
			wheel3 = true;
		}
		if (wheel1 && wheel2 && wheel3) {
			Vibrate(this, 200);
			wineDesc.setText(leftDesc[item1] + middleDesc[item2]
					+ rightDesc[item3] + middleDesc2[item2]);
		}
	}

	private ArrayList<Integer> getList(int[] data) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i : data) {
			list.add(i);
		}
		return list;
	}

	/**
	 * Adapter for countries
	 */
	private class CountryAdapter extends AbstractWheelTextAdapter {
		// Countries names
		ArrayList<Integer> list = new ArrayList<Integer>();

		/**
		 * Constructor
		 */
		protected CountryAdapter(Context context, ArrayList<Integer> list) {
			super(context, R.layout.country_layout, NO_RESOURCE);
			this.list = list;
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			View view = super.getItem(index, cachedView, parent);

			ImageView img = (ImageView) view.findViewById(R.id.flag);
			img.setImageResource(list.get(index));
			return view;
		}

		public int getItemsCount() {
			return list.size();
		}

		protected CharSequence getItemText(int index) {
			return Logic.getString(list.get(index));
		}
	}

	private void mixWheel(WheelView wv, int distance) {
		wv.scroll(distance, 2000);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.diceNum:
		case R.id.triangle:
			if (listView.getVisibility() == View.VISIBLE) {
				listView.setVisibility(View.GONE);
			} else {
				listView.setVisibility(View.VISIBLE);
			}
			break;

		default:
			break;
		}
	}

	class SpinnerAdapter extends BaseAdapter {
		private String[] data = { "一粒", "二粒", "三粒", "四粒", "五粒", "六粒" };

		public int getCount() {
			// TODO Auto-generated method stub
			return data.length;
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return data[position];
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder viewHolder;
			if (convertView == null) {
				convertView = LinearLayout.inflate(GameActivity.this,
						R.layout.diceitem, null);
				viewHolder = new ViewHolder();
				viewHolder.content = (TextView) convertView
						.findViewById(R.id.content);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.content.setText(data[position]);
			return convertView;
		}

		class ViewHolder {
			TextView content;
		}

	}

}

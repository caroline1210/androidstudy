package com.ltd.mos.personal;

import java.util.ArrayList;

import com.ltd.mos.R;
import com.ltd.mos.bean.QuestionBean;
import com.ltd.mos.util.Logic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class QuestionAdapter extends BaseAdapter {

	ArrayList<QuestionBean> list = new ArrayList<QuestionBean>();
	Context c;

	public QuestionAdapter(Context c, ArrayList<QuestionBean> list) {
		// TODO Auto-generated constructor stub
		this.c = c;
		this.list = list;
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
		ViewHolder holder = new ViewHolder();
		if (convertView == null) {
			convertView = View.inflate(c, R.layout.question_item, null);
			holder.tv_order = (TextView) convertView.findViewById(R.id.tv_question_order);
			holder.tv_content = (TextView) convertView.findViewById(R.id.tv_question_content);
			holder.tv_question = (TextView) convertView
					.findViewById(R.id.tv_question);
//			holder.tv_question1 = (TextView) convertView
//			.findViewById(R.id.tv_question1);
//			holder.tv_question2 = (TextView) convertView
//			.findViewById(R.id.tv_question2);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_order.setText(Logic.getString(list.get(position).getOrdre()));
		holder.tv_content.setText(Logic.getString(list.get(position).getQuestionContent()));
		holder.tv_question.setText(Logic.getString(list.get(position).getQuestion1()));
//		holder.tv_question1.setText(Logic.getString(list.get(position).getQuestion1()));
//		holder.tv_question2.setText(Logic.getString(list.get(position).getQuestion1()));
		
		return convertView;
	}

	class ViewHolder {
		TextView tv_order,tv_content,tv_question,tv_question1,tv_question2;
	}
}

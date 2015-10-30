package com.ltd.mos.personal;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.ltd.mos.R;
import com.ltd.mos.base.BaseActivity;
import com.ltd.mos.base.ECallBack;
import com.ltd.mos.bean.QuestionBean;
import com.ltd.mos.util.Logic;

//意见反馈
public class QuestionAct extends BaseActivity {

	private View view;
	private ArrayList<QuestionBean> list = new ArrayList<QuestionBean>();
	private QuestionAdapter adapter;
	private ListView listView;
	private String[] tv_order = { "Q1", "Q2", "Q3", "Q4", "Q5", "Q6", "Q7",
			"Q8", "Q9" };
	private String[] tv_content = { ":所购酒水多久可以送到？", ":怎么样保真？", ":收货时为什么要扫一扫？",
			":在餐厅吃饭，可以下单购酒吗？", ":下单后如何支付？", "：如何保证葡萄酒的品质？", ":送酒爽约了，怎么办？",
			":我为什么被禁用了？", ":可以在平台上买烟吗？" };
	private String[] tv_question1 = {
			"A:通常15分钟之内送到，也许更快，但“要快感，更要安全感”保真最重要。",
			"A:酒斯基认证联盟诚信伙伴都经过第三方相关部门认证，如出现酒品问题酒斯基先行赔付假一赔二，并提供相关详细证据到有关管理部门，对商铺进行相应处罚。",
			"A:扫一扫对消费者有保障，除了核实是否是您订购的酒品外，还自动记录商品配送渠道信息，以便于出现问题进行追溯，同时有积分返还！",
			"A：可以购酒，“禁止自带酒水”属于餐饮业违反《合同法》和《消费者权益保护法》的霸王条款，所以消费者可以放心在平台上下单购酒。",
			"A:货到付款。除了现金支付、刷卡（网上支付暂未开通）。请在送酒之前和送货员确认支付方式",
			"A：在酒斯基平台上销售的葡萄酒配送点均有电子恒温酒柜或满足葡萄酒的储存条件，客户所选购的葡萄酒从酒柜中拿出后以最短的时间送到 ，不会影响口感。",
			"A：如果有店铺接单后酒没送到，可以在平台上直接投诉酒未送到，酒斯基客服会在最短的时间内给您答复。",
			"A:您有以下行为会被禁用:\n1、谎报地点：指非真实性的叫单行为。\n2、爽约:下单后未与商家协商一致单方面取消订单。\n3、拒付费：酒品送到后拒付费或者支付小于订单费用的行为。\n4、扰乱平台：网评上恶意中伤、虚假投诉商家等行为。",
			"A：酒斯基平台不提供香烟，如有需要请在备注留言或配送之前电话说明。", };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question);

		list = getList(tv_order, tv_content, tv_question1);

		adapter = new QuestionAdapter(this, list);
		listView = (ListView) findViewById(R.id.lv_feedbackdetails);
		listView.setAdapter(adapter);

		view = findViewById(R.id.feedback_one_view);

		Logic logic = Logic.getInstance();
		logic.initHeadView(view, "常见问题", true, "", new ECallBack() {

			public void OnError(Object obj) {
				// TODO Auto-generated method stub

			}

			public void OnCreate(Object obj) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	private ArrayList<QuestionBean> getList(String[] order,
			String[] questionContent, String[] question1) {
		ArrayList<QuestionBean> list = new ArrayList<QuestionBean>();
		for (int i = 0; i < order.length; i++) {
			QuestionBean question = new QuestionBean();
			question.setOrdre(order[i]);
			if (questionContent != null) {
				question.setQuestionContent(questionContent[i]);
			}
			if (question1 != null) {
				question.setQuestion1(question1[i]);
			}
			list.add(question);
		}
		return list;
	}

}

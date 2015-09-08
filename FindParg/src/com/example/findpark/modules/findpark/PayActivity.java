package com.example.findpark.modules.findpark;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.findpark.R;
import com.example.findpark.common.tool.FU;
import com.example.findpark.modules.BaseActivity;
import com.example.findpark.modules.findpark.bean.PayCacheBean;

public class PayActivity extends BaseActivity {

	private TextView need_money_tv, starttime_tv, endtime_tv,
			price_tv,time_tv,totalPrice_tv;
	private EditText real_money_et;
	private RadioButton rb1, rb2, rb3, rb4,rb_cash_pay,rb_ali_pay,rb_weixin_pay;
	private RadioGroup rg, rg_paymethod;
	private Button btn_pay_confirm;
	private PayCacheBean pcb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_pay);
		pcb = PayCacheBean.getInstance();
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		setPageTitle(R.string.checkstand_page_title);
		need_money_tv = (TextView) findViewById(R.id.need_money_tv);
		starttime_tv = (TextView) findViewById(R.id.starttime_pay_tv);
		endtime_tv = (TextView) findViewById(R.id.endtime_pay_tv);
		price_tv = (TextView) findViewById(R.id.price_pay_tv);
		time_tv = (TextView) findViewById(R.id.time_pay_tv);
		totalPrice_tv = (TextView) findViewById(R.id.totalPrice_pay_tv);
		
		real_money_et = (EditText) findViewById(R.id.real_money_et);
		btn_pay_confirm = (Button) findViewById(R.id.btn_pay_confirm);
		rg = (RadioGroup) findViewById(R.id.default_money_area);
		rb1 = (RadioButton) findViewById(R.id.rb1);
		rb2 = (RadioButton) findViewById(R.id.rb2);
		rb3 = (RadioButton) findViewById(R.id.rb3);
		rb4 = (RadioButton) findViewById(R.id.rb4);
		rg_paymethod = (RadioGroup) findViewById(R.id.rg_paymethod);
		rb_cash_pay = (RadioButton) findViewById(R.id.rb_cash_pay);
		rb_ali_pay = (RadioButton) findViewById(R.id.rb_ali_pay);
		rb_weixin_pay = (RadioButton) findViewById(R.id.rb_union_pay);
		
	}

	@Override
	public void initData() {
		pcb.needMoney = "3.00";
		
		need_money_tv.setText("￥" + pcb.needMoney);
		
		starttime_tv.setText("开始时间：" + "10:00:00");
		endtime_tv.setText("结束时间："	+ "15:30:00");
		price_tv.setText("价格：" + "3元/时");
		time_tv.setText("计时：" + "5时30分");
		totalPrice_tv.setText("计价：" + "3元");
		
		real_money_et.setText(pcb.needMoney);
		real_money_et.setSelection(pcb.needMoney.length());
		initRbData();
	}

	private void initRbData() {
		rb1.setText(FU.parseDleStr(pcb.needMoney));// 需付金额整

		String moneyStr = FU.parseDleStr(pcb.needMoney);
		String bMoneyStr = "0";
		if (moneyStr.contains(".")) {
			bMoneyStr = moneyStr.split("\\.")[0];
		} else {
			bMoneyStr = moneyStr;
		}
		if (bMoneyStr.length() >= 3) {
			String shiMoney = bMoneyStr.substring(0, bMoneyStr.length() - 1)
					+ "0";
			rb2.setText(FU.parseDleStr(FU.parseDle(shiMoney) + 10));// 需付金额+10
			rb3.setText(FU.parseDleStr(FU.parseDle(shiMoney) + 20));// 需付金额+20
			String baiMoney = bMoneyStr.substring(0, bMoneyStr.length() - 2)
					+ "00";
			rb4.setText(FU.parseDleStr(FU.parseDle(baiMoney) + 100));// 需付金额+100
		} else if (bMoneyStr.length() == 2) {
			String shiMoney = bMoneyStr.substring(0, bMoneyStr.length() - 1)
					+ "0";
			rb2.setText(FU.parseDleStr(FU.parseDle(shiMoney) + 10));// 需付金额+10
			rb3.setText(FU.parseDleStr(FU.parseDle(shiMoney) + 20));// 需付金额+20
			rb4.setText(FU.parseDleStr("100"));// 需付金额+100
		} else if (bMoneyStr.length() == 1) {
			rb2.setText(FU.parseDleStr("10"));// 需付金额+10
			rb3.setText(FU.parseDleStr("20"));// 需付金额+20
			rb4.setText(FU.parseDleStr("100"));// 需付金额+100
		}
	}

	@Override
	public void initListener() {
		real_money_et.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void afterTextChanged(Editable arg0) {
				Double chargeMoney = 0.00;
				chargeMoney = FU.parseDle(arg0.toString())
						- FU.parseDle(pcb.needMoney);
				pcb.chargeMoney = FU.parseDleStr(chargeMoney);
				btn_pay_confirm.setText("已找零" + FU.parseDleStr(chargeMoney)
						+ "元");
			}
		});
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				String moneyStr = "0.00";
				if (checkedId == rb1.getId()) {
					moneyStr = rb1.getText().toString();
				} else if (checkedId == rb2.getId()) {
					moneyStr = rb2.getText().toString();
				} else if (checkedId == rb3.getId()) {
					moneyStr = rb3.getText().toString();
				} else if (checkedId == rb4.getId()) {
					moneyStr = rb4.getText().toString();
				}
				real_money_et.setText(moneyStr);
			}
		});
		btn_pay_confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				goToAct(PaySuccess.class, true);
			}
		});
	}

}

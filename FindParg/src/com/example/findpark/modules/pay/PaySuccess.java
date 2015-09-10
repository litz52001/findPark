package com.example.findpark.modules.pay;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.findpark.R;
import com.example.findpark.common.set.AppInit;
import com.example.findpark.common.tool.FU;
import com.example.findpark.modules.BaseActivity;
import com.example.findpark.modules.findpark.bean.PayCacheBean;

public class PaySuccess extends BaseActivity {

	private PayCacheBean pcb;
	private Button btn_finish;
	private TextView money_tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_pay_success);
		pcb = PayCacheBean.getInstance();
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		left_img.setVisibility(View.GONE);
		btn_finish = (Button) findViewById(R.id.btn_finish);
		money_tv = (TextView) findViewById(R.id.money_tv); 
	}

	@Override
	public void initData() {
		setPageTitle(R.string.paysucces_page_title);
		
		pcb.needMoney = "3.00";
		money_tv.setText("￥"+FU.parseDleStr(pcb.needMoney));
		
	}

	@Override
	public void initListener() 
	{
		
		btn_finish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AppInit.isStop = false;
				finish();
			}
		});
	}
	
}

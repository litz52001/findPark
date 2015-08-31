package com.example.findparg.modules.home;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.findparg.R;
import com.example.findparg.modules.BaseActivity;

public class HomeActivity extends BaseActivity {

	private View home_cash_area,home_refund_area,home_count_area,home_redeem_area;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_home);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		home_cash_area = findViewById(R.id.home_cash_area);
		home_refund_area = findViewById(R.id.home_refund_area);
		home_count_area = findViewById(R.id.home_count_area);
		home_redeem_area = findViewById(R.id.home_redeem_area);
	}

	@Override
	public void initData() {
	}

	@Override
	public void initListener() {
		home_cash_area.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				goToAct(CheckstandActivity.class, false);
			}
		});
		
		home_refund_area.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				goToAct(RefundActivity.class, false);
			}
		});
		home_count_area.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				goToAct(CountMainActivity.class, false);
			}
		});
		home_redeem_area.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				goToAct(CouponActivity.class, false);
			}
		});
		
	}

}

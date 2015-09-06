package com.example.findpark.modules.home;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.findpark.R;
import com.example.findpark.modules.BaseActivity;
import com.example.findpark.modules.findpark.FindCarActivity;
import com.example.findpark.modules.findpark.FindParkActivity;
import com.example.findpark.modules.findpark.ReserveActivity;

public class HomeActivity extends BaseActivity {

	private View home_find_area,home_yuding_area,home_pay_area,home_setting_area;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_home);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		home_find_area = findViewById(R.id.home_find_area);
		home_yuding_area = findViewById(R.id.home_yuding_area);
		home_pay_area = findViewById(R.id.home_pay_area);
		home_setting_area = findViewById(R.id.home_setting_area);
	}

	@Override
	public void initData() {
		
	}

	@Override
	public void initListener() {
		home_find_area.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goToAct(FindParkActivity.class, false);
			}
		});
		
		home_yuding_area.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goToAct(ReserveActivity.class, false);
			}
		});
		home_pay_area.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				goToAct(CountMainActivity.class, false);
				
			}
		});
		home_setting_area.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showToast("开发中");
			}
		});
		
	}

}

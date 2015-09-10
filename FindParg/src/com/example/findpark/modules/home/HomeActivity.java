package com.example.findpark.modules.home;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.findpark.R;
import com.example.findpark.common.set.AppInit;
import com.example.findpark.modules.BaseActivity;
import com.example.findpark.modules.findpark.FindParkActivity;
import com.example.findpark.modules.pay.PayActivity;
import com.example.findpark.modules.reserve.ReserveActivity;

public class HomeActivity extends BaseActivity {

	private View home_find_area, home_yuding_area, home_pay_area,
			home_setting_area, home_cash_area;
	private TextView stop_time;
	private long exitTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_home);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		setPageTitle("停车demo");
		home_cash_area = findViewById(R.id.home_cash_area);
		home_find_area = findViewById(R.id.home_find_area);
		home_yuding_area = findViewById(R.id.home_yuding_area);
		home_pay_area = findViewById(R.id.home_pay_area);
		home_setting_area = findViewById(R.id.home_setting_area);

		stop_time = (TextView) findViewById(R.id.stop_time);
	}

	@Override
	protected void onResume() {
		if (AppInit.isStop) {
			stop_time.setVisibility(View.VISIBLE);
		} else {
			stop_time.setVisibility(View.GONE);
		}

		super.onResume();
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
				if (AppInit.isStop)
					goToAct(PayActivity.class, false);
				else
					showToast("未停放车辆");
			}
		});
		home_setting_area.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showToast("开发中");
			}
		});
		home_cash_area.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showToast("开发中");
			}
		});

		setLeftListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				exit();
			}
		});
	}

	// 实现返回键的点击事件
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit(); // 在这里进行点击判断
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void exit() {
		if ((System.currentTimeMillis() - exitTime) > 2000) {
			// 点击间隔大于两秒，做出提示
			showToast("再次点击退出应用");
			exitTime = System.currentTimeMillis();
		} else {
			// 连续点击量两次，进行应用退出的处理
			 System.exit(0);
		}
	}
}

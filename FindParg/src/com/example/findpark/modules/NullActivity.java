package com.example.findpark.modules;

import android.os.Bundle;
import com.example.findpark.R;
import com.example.findpark.modules.BaseActivity;

public class NullActivity extends BaseActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_home);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
	}

	@Override
	public void initData() {
	}

	@Override
	public void initListener() {
		
	}

}

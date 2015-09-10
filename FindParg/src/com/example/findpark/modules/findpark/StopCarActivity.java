package com.example.findpark.modules.findpark;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.findpark.R;
import com.example.findpark.common.set.AppInit;
import com.example.findpark.modules.BaseActivity;

/**
 * 停车位
 * @author Litz
 */

public class StopCarActivity extends BaseActivity {

	private Button btn_search;
	private ImageView img_findcar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.act_stopcar);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		setPageTitle("停车导航");
		btn_search = (Button)findViewById(R.id.btn_stop);
		img_findcar = (ImageView)findViewById(R.id.img_stopcar);
	}

	@Override
	public void initData() {
		
	}

	@Override
	public void initListener() {
		btn_search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDate();
			}
		});
		
		img_findcar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDate();
			}
		});
		
	}
	

	public void  showDate()
	{
		showTipDialog("停车成功，开始计时！",new OnClickListener(){
			@Override
			public void onClick(View v) {
				AppInit.isStop = true;
				finish();
			}
		});
	}
	
}

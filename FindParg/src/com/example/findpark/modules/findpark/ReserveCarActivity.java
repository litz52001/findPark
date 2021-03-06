package com.example.findpark.modules.findpark;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.findpark.R;
import com.example.findpark.common.set.AppInit;
import com.example.findpark.modules.BaseActivity;
import com.example.findpark.modules.findpark.bean.ParkBean;
import com.example.findpark.widget.wheel.DateTimeDialog;
import com.example.findpark.widget.wheel.DateTimeDialog.OnChosenListener;

/**
 * 预约车位
 * @author Litz
 */

public class ReserveCarActivity extends BaseActivity {

	private ImageView img_findcar;
	
	private ParkBean parkBean;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.act_findcar);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		setPageTitle("预约车位");
		img_findcar = (ImageView)findViewById(R.id.img_findcar);
	}

	@Override
	public void initData() {
		parkBean = (ParkBean)getIntent().getSerializableExtra("parkBean");
	}

	@Override
	public void initListener() {
		img_findcar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDate();
			}
		});
	}

	public void  showDate()
	{
		DateTimeDialog date = new DateTimeDialog(mContext);
		date.setTitle("预约时间");
		date.setOnChosenListener(new OnChosenListener() {
			@Override
			public void onFinish(String year, String month, String day, String hour,
					String min) {
				String date = year+"-"+month +"-"+day +" "+ hour +":"+min;
				showTipDialog("预约成功！ \n"+date,new OnClickListener(){
					@Override
					public void onClick(View v) {
						AppInit.parkList.add(parkBean);
						finish();
					}
				});
			}
		});
		date.show();
	}
	
}

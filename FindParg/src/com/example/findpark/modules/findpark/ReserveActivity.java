package com.example.findpark.modules.findpark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.findpark.R;
import com.example.findpark.common.set.AppInit;
import com.example.findpark.modules.BaseActivity;
import com.example.findpark.modules.findpark.adapter.FindParkAdapter;
import com.example.findpark.modules.findpark.bean.ParkBean;

/**
 * 我的预约
 * @author Litz
 */
public class ReserveActivity extends BaseActivity {

	private ListView resvereLV;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.act_resvere);
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public void initView() {
		setPageTitle("我的预约");
		resvereLV = (ListView)findViewById(R.id.resvereLV);
		
	}

	@Override
	public void initData() {
		
		FindParkAdapter parkAdapter = new FindParkAdapter(this, AppInit.parkList);
		resvereLV.setAdapter(parkAdapter);
		
		if(AppInit.parkList.size() == 0)
		{
			showToast("未预约车位");
		}
		
	}

	@Override
	public void initListener() {
		
		resvereLV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ParkBean parkBean = AppInit.parkList.get(arg2);
				Intent intent = new Intent(mContext,ParkDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("parkBean", parkBean);
				intent.putExtras(bundle);
				startActivity(intent);
				
			}
		});
		
	}
	
	

}

package com.example.findpark.modules.findpark;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.findpark.R;
import com.example.findpark.modules.BaseActivity;
import com.example.findpark.modules.findpark.adapter.FindParkAdapter;
import com.example.findpark.modules.findpark.bean.ParkBean;
import com.google.gson.reflect.TypeToken;

/**
 * 找停车场
 * @author Litz
 */
public class FindParkActivity extends BaseActivity {

	private ListView parkLV;
	private List<ParkBean> parkList = new ArrayList<ParkBean>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.act_findpark);
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public void initView() {
		setPageTitle("停车场");
		parkLV = (ListView)findViewById(R.id.parkLV);
		
		
	}

	@Override
	public void initData() {
		String data = dataManager.getData("findpark");
		Type listType = new TypeToken<List<ParkBean>>(){}.getType(); 
		parkList = gson.fromJson(data, listType);
		
		FindParkAdapter parkAdapter = new FindParkAdapter(this, parkList);
		parkLV.setAdapter(parkAdapter);
		
	}

	@Override
	public void initListener() {
		
		parkLV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ParkBean parkBean = parkList.get(arg2);
				
				Intent intent = new Intent(mContext,ParkDetailActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("parkBean", parkBean);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
	}
	
	

}

package com.example.findpark.modules.findpark;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
 * 我的预约
 * @author Litz
 */
public class ReserveActivity extends BaseActivity {

	private ListView resvereLV;
	private List<ParkBean> resvereList = new ArrayList<ParkBean>();
	
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
		String data = dataManager.getData("findpark");
		Type listType = new TypeToken<List<ParkBean>>(){}.getType(); 
		List<ParkBean> parkList = gson.fromJson(data, listType);
		
		FindParkAdapter parkAdapter = new FindParkAdapter(this, parkList);
		resvereLV.setAdapter(parkAdapter);
		
	}

	@Override
	public void initListener() {
		
		resvereLV.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				goToAct(ParkDetailActivity.class, false);
			}
		});
		
	}
	
	

}

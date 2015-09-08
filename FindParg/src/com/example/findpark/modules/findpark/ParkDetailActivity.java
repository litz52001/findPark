package com.example.findpark.modules.findpark;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviPara;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.example.findpark.R;
import com.example.findpark.common.tool.DataManager;
import com.example.findpark.modules.BaseActivity;
import com.example.findpark.modules.findpark.bean.ParkBean;

/**
 * 停车场详情
 * @author Litz
 */
public class ParkDetailActivity extends BaseActivity {

	private Button navigationBtn,reserveBtn,stopBtn;
	private ImageView detail_img;
	private TextView detail_name,detail_num,detail_price,detail_time,detail_context;
	private ParkBean parkBean;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.act_parkdetail);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void initView() {
		detail_img = (ImageView)findViewById(R.id.detail_img);
		detail_name = (TextView)findViewById(R.id.detail_name);
		detail_num = (TextView)findViewById(R.id.detail_num);
		detail_price = (TextView)findViewById(R.id.detail_price);
		detail_time = (TextView)findViewById(R.id.detail_time);
		detail_context = (TextView)findViewById(R.id.detail_context);
		
		navigationBtn = (Button)findViewById(R.id.navigationBtn);
		reserveBtn = (Button)findViewById(R.id.reserveBtn);
		stopBtn = (Button)findViewById(R.id.stopBtn);
	}

	@SuppressLint("NewApi")
	@Override
	public void initData() {
		parkBean = (ParkBean)getIntent().getSerializableExtra("parkBean");
		detail_img.setBackground(new BitmapDrawable(new DataManager(mContext).getBltmap(parkBean.getImgUrl())));
		detail_name.setText(parkBean.getName());
		detail_num.setText("车位情况: "+parkBean.getSurplus()+"/"+parkBean.getAllPark());
		detail_price.setText("价格: "+parkBean.getPrice() + "  "+parkBean.getRemark());
		detail_time.setText("开放时间: "+parkBean.getTime());
		detail_context.setText("地址详情: "+parkBean.getAddres());
	}

	@Override
	public void initListener() {
		navigationBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getNavigation();
			}
		});
		reserveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(mContext,FindCarActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("parkBean", parkBean);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
		
		stopBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				 goToAct(StopCarActivity.class, false);
			}
		});
	}
	
	/**
	 * 导航
	 */
	public void getNavigation() {
		double mLat1 = 39.915;
		double mLon1 = 116.404;
		double mLat2 = 32.032;
		double mLon2 = 116.799;

		int lat = (int) (mLat1 * 1E6);
		int lon = (int) (mLon1 * 1E6);
		GeoPoint pt1 = new GeoPoint(lat, lon);
		lat = (int) (mLat2 * 1E6);
		lon = (int) (mLon2 * 1E6);
		GeoPoint pt2 = new GeoPoint(lat, lon);

		NaviPara para = new NaviPara();
		para.startPoint = pt1;
		para.startName = "从这里开始";
		para.endPoint = pt2;
		para.endName = "到这里结束";

		try {

			BaiduMapNavigation.openBaiduMapNavi(para, this);

		} catch (BaiduMapAppNotSupportNaviException e) {
			e.printStackTrace();
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setMessage("您尚未安装百度地图app或app版本过低，点击确认安装？");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					BaiduMapNavigation.GetLatestBaiduMapApp(ParkDetailActivity.this);
				}
			});

			builder.setNegativeButton("取消", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});

			builder.create().show();
		}
	}
	
}

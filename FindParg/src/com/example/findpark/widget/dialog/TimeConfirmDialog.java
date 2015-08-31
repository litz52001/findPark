package com.example.findpark.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.findpark.R;
import com.example.findpark.common.tool.ComMethod;
import com.example.findpark.common.tool.DeviceAttribute;
import com.example.findpark.widget.wheel.DateTimeDialog;
import com.example.findpark.widget.wheel.DateTimeDialog.OnChosenListener;

/**
 * 时间设置弹出框
 * 
 * @author super
 */
public class TimeConfirmDialog {

	private Dialog dialog;
	private View view;
	private LayoutInflater inflater;
	private Button button_confirm;
	private Context dContext;
	private RelativeLayout starttime_row, endtime_row;
	private TextView startime_tv, endtime_tv;
	private DateTimeDialog myTimeDialog;
	private String startTime, endTime;

	public TimeConfirmDialog(Context context, OnClickListener btnLsner) {
		this.dContext = context;
		dialog = new Dialog(context, R.style.dialog);
		inflater = LayoutInflater.from(context);
		view = inflater.inflate(R.layout.time_dialog_layout, null);
		button_confirm = (Button) view.findViewById(R.id.button_confirm);
		dialog.setContentView(view);
		dialog.setCancelable(true);
		Window dialogWindow = dialog.getWindow();
		WindowManager.LayoutParams p = dialogWindow.getAttributes();
		p.height = (int) (DeviceAttribute.getScreenWidth(dContext) * 0.65);
		p.width = (DeviceAttribute.getScreenWidth(dContext) - 30); // 宽度设置为屏幕的0.65
		dialogWindow.setAttributes(p);
		button_confirm.setOnClickListener(btnLsner);
		initView();
		initLsner();
	}

	private void initView() {
		starttime_row = (RelativeLayout) view.findViewById(R.id.starttime_row);
		endtime_row = (RelativeLayout) view.findViewById(R.id.endtime_row);
		startime_tv = (TextView) view.findViewById(R.id.startime_tv);
		startime_tv.setText(ComMethod.getCurTimeMin());
		endtime_tv = (TextView) view.findViewById(R.id.endtime_tv);
		endtime_tv.setText(ComMethod.getCurTimeMin());
		myTimeDialog = new DateTimeDialog(dContext);
		
		endTime = startTime = ComMethod.getCurTimeMin();
	}

	private void initLsner() {
		starttime_row.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!myTimeDialog.isShowing()) {
					myTimeDialog.setOnChosenListener(new OnChosenListener() {

						@Override
						public void onFinish(String year, String month,
								String day, String hour, String min) {
							startTime = year + "-" + month + "-" + day + " "
									+ hour + ":" + min;
							startime_tv.setText(startTime);
						}
					});
					myTimeDialog.show();
				}
			}
		});
		endtime_row.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!myTimeDialog.isShowing()) {
					myTimeDialog.setOnChosenListener(new OnChosenListener() {

						@Override
						public void onFinish(String year, String month,
								String day, String hour, String min) {
							endTime = year + "-" + month + "-" + day + " "
									+ hour + ":" + min;
							endtime_tv.setText(endTime);
						}
					});
					myTimeDialog.show();
				}
			}
		});
	}
	
	public String getTimeToStr(){
		return startTime + "\n 至 " + endTime;
	}

	public void showDialog() {
		dialog.show();
	}

	public void dismissDialog() {
		dialog.dismiss();
	}

}

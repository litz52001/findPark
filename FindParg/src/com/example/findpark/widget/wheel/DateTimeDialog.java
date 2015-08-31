package com.example.findpark.widget.wheel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Calendar;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.findpark.R;
import com.example.findpark.common.tool.ComMethod;

/**
 * @author super
 */
public class DateTimeDialog extends Dialog {
	public Button softInfo;
	public Button softInfoButton;
	public static boolean flag = true;
	private NumericWheelAdapter year_adapter, month_adapter, day_adapter,
			hour_adapter, min_adapter;
	private Button btn_sure;
	private OnChosenListener listener;
	private TextView dialog_title_text;
	private WheelView yearWheel, monthWheel, dayWheel, hourWheel, minWheel;
	
	private int curYear = Calendar.getInstance().get(Calendar.YEAR);
	private int curMonth = Calendar.getInstance().get(Calendar.MONTH);
	private int curDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	private int curHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	private int curMin = Calendar.getInstance().get(Calendar.MINUTE);

	public DateTimeDialog(final Context context) {
		super(context, R.style.dialog);
		setContentView(R.layout.time_layout);
		dialog_title_text = (TextView) findViewById(R.id.dialog_title_text);
		btn_sure = (Button) findViewById(R.id.button_confirm);
		btn_sure.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String year = year_adapter.getValues();
				String month = ComMethod.formatTimeNum(month_adapter.getValues());
				String day = ComMethod.formatTimeNum(day_adapter.getValues());
				String hour = ComMethod.formatTimeNum(hour_adapter.getValues());
				String min = ComMethod.formatTimeNum(min_adapter.getValues());
				listener.onFinish(year, month, day, hour, min);
				dismiss();
			}
		});

		yearWheel = (WheelView) this.findViewById(R.id.year);
		yearWheel.setCyclic(true);
		monthWheel = (WheelView) this.findViewById(R.id.month);
		monthWheel.setCyclic(true);
		dayWheel = (WheelView) this.findViewById(R.id.day);
		dayWheel.setCyclic(true);
		hourWheel = (WheelView) this.findViewById(R.id.hour);
		hourWheel.setCyclic(true);
		minWheel = (WheelView) this.findViewById(R.id.min);
		minWheel.setCyclic(true);

		year_adapter = new NumericWheelAdapter(2012, 2100);
		month_adapter = new NumericWheelAdapter(1, 12);
		day_adapter = new NumericWheelAdapter(1, 31);
		hour_adapter = new NumericWheelAdapter(0, 23);
		min_adapter = new NumericWheelAdapter(0, 59);

		yearWheel.setAdapter(year_adapter);
		monthWheel.setAdapter(month_adapter);
		dayWheel.setAdapter(day_adapter);
		hourWheel.setAdapter(hour_adapter);
		minWheel.setAdapter(min_adapter);
		yearWheel.addChangingListener(new OnWheelChangedListener() {
			
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, newValue);
				int monthInt = cal.get(Calendar.MONTH);
				try {
					monthInt = Integer.parseInt(month_adapter.getValues());
				} catch (Exception e) {
					e.printStackTrace();
				}
				cal.set(Calendar.MONTH, monthInt);
				int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
				int selectedDay = curDay;
				try {
					selectedDay = Integer.parseInt(day_adapter.getValues());
				} catch (Exception e) {
					e.printStackTrace();
				}
				day_adapter = new NumericWheelAdapter(1, dateOfMonth);
				dayWheel.setAdapter(day_adapter);
				dayWheel.setCurrentItem(selectedDay-1);
			}
		});
		monthWheel.addChangingListener(new OnWheelChangedListener() {
			
			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				Calendar cal = Calendar.getInstance();
				int yearInt = cal.get(Calendar.YEAR);
				try {
					yearInt = Integer.parseInt(year_adapter.getValues());
				} catch (Exception e) {
					e.printStackTrace();
				}
				cal.set(Calendar.YEAR, yearInt);
				cal.set(Calendar.MONTH, newValue);
				int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
				int selectedDay = curDay;
				try {
					selectedDay = Integer.parseInt(day_adapter.getValues());
				} catch (Exception e) {
					e.printStackTrace();
				}
				day_adapter = new NumericWheelAdapter(1, dateOfMonth);
				dayWheel.setAdapter(day_adapter);
				dayWheel.setCurrentItem(selectedDay-1);
			}
		});

		yearWheel.setCurrentItem(curYear - 2012);
		monthWheel.setCurrentItem(curMonth);
		dayWheel.setCurrentItem(curDay);
		hourWheel.setCurrentItem(curHour);
		minWheel.setCurrentItem(curMin);
	}

	/**
	 * set listener
	 */
	public void setOnChosenListener(OnChosenListener li) {
		listener = li;
	}

	/**
	 * callback listen interface
	 */
	public interface OnChosenListener {
		public void onFinish(String year, String month, String day, String hour, String min);
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void dismiss() {
		super.dismiss();
	}
	
	public void setDialogTitle(String str){
		dialog_title_text.setText(str);
	}

}

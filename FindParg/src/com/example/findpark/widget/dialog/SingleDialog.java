package com.example.findpark.widget.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.findpark.R;

/**
 * @description:单选按钮弹出框
 * @author cai
 * @time:2015年3月6日下午3:35:44
 */
public class SingleDialog {

	private Dialog dialog;
	private View view;
	private LayoutInflater inflater;
	private Button button_confirm;
	private TextView text_msg;

	public SingleDialog(Context context) {
		dialog = new Dialog(context, R.style.dialog);
		inflater = LayoutInflater.from(context);
		view = inflater.inflate(R.layout.single_dialog_layout, null);
		button_confirm = (Button) view.findViewById(R.id.button_confirm);
		text_msg = (TextView) view.findViewById(R.id.text_msg);
		dialog.setContentView(view);
		Window dialogWindow = dialog.getWindow();
		WindowManager m = ((Activity) context).getWindowManager();
		Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
		WindowManager.LayoutParams p = dialogWindow.getAttributes();
		p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.65
		dialogWindow.setAttributes(p);

	}

	/**
	 * showDialog
	 * 
	 * @param message
	 *            提示语
	 * @param listener_confirm
	 *           按钮事件
	 */
	public void showDialog(String message, 
			View.OnClickListener listener_confirm
			) {
		text_msg.setText(message);
		if(listener_confirm != null)
			button_confirm.setOnClickListener(listener_confirm);
		else
			button_confirm.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dismissDialog();
				}
			});
		dialog.show();
	}

	public void dismissDialog() {
		dialog.dismiss();
	}

	public boolean isShow() {
		if (dialog.isShowing()) {
			return true;
		} else {
			return false;
		}
	}

	public Dialog getDialog() {
		return dialog;
	}

	public void setCancelable(boolean is) {
		dialog.setCancelable(is);
	}

}

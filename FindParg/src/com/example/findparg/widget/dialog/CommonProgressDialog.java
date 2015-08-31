package com.example.findparg.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findparg.R;


/**
 * @description:公共ProgressDialog
 * @author cai                      
 * @time:2015年3月6日上午11:23:45
 */
public class CommonProgressDialog {

	private Context mContext;
	private Dialog dialog;
	private View view;
	private LayoutInflater inflater;
	private ImageView dialog_image;
	private TextView dialog_text;
	private Animation dialogAnimation;
	private AnimationDrawable animationDrawable;

	public CommonProgressDialog(Context context) {
		this(context, null);
	}

	public CommonProgressDialog(Context context, AttributeSet attrs) {
		super();
		mContext = context;
		dialog = new Dialog(context, R.style.dialog);
		inflater = LayoutInflater.from(context);
		view = inflater.inflate(R.layout.common_progress_dialog_layout, null);
		dialog_image = (ImageView) view.findViewById(R.id.dialog_image);
		dialog_text = (TextView) view.findViewById(R.id.dialog_text);
		dialogAnimation = AnimationUtils.loadAnimation(mContext,
				R.anim.dialog_rotate);
		dialogAnimation.setInterpolator(new LinearInterpolator());//不停顿
		dialog.setContentView(view);

		// 从xml中得到GifView的句柄
		// GifView gif = (GifView) view.findViewById(R.id.gif);
		// 设置Gif图片源
		// gif.setGifImage(R.drawable.icon_jiazai);

		// animationDrawable = (AnimationDrawable) dialog_image.getBackground();
	}

	public void onWindowFocusChanged(boolean hasFocus) {

	}

	public void showDialog(String msg) {
		if (TextUtils.isEmpty(msg)) {
			dialog_text.setText("数据加载中...");
		} else {
			dialog_text.setText(msg);
		}
		dialog_image.clearAnimation();
		dialog_image.startAnimation(dialogAnimation);
		// animationDrawable.start();
		try {
			dialog.show();
		} catch (Exception e) {
		}
	}

	public void setMessage(String msg) {
		dialog_text.setText(msg);
	}

	public void dialogDismiss() {
		try {
			dialog.dismiss();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isShow() {
		if (dialog.isShowing()) {
			return true;
		} else {
			return false;
		}
	}

	public void setCancelable(boolean is) {
		dialog.setCancelable(is);
	}

	public Dialog getDialog() {
		return dialog;
	}

}

package com.example.findparg.modules;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.example.findparg.R;
import com.example.findparg.widget.dialog.CommonProgressDialog;
import com.example.findparg.widget.dialog.SingleDialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 基础activity类
 * 
 * @author super
 */
public abstract class BaseActivity extends Activity {
	public Context mContext;
	/**
	 * log打印需要用到的标签
	 */
	public static String Tag = "BaseActivity";

//	public StringNetRequest netRequest;
	protected List<String> printData = new ArrayList<String>();

	public RelativeLayout center_rly;
	public LinearLayout search_ly, right_ly;
	public ImageView left_img, right_img, clear_img;
	public EditText center_edit;
	public TextView page_title, search_txt, right_txt;
//	public LightPosDb db;
	private CommonProgressDialog commonProgressDialog;
	private SingleDialog sd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
//		AppInit.activityStack.add(this);
		Tag = this.getClass().getSimpleName();
//		netRequest = StringNetRequest.getInstance(mContext);
//		netRequest.setMainContext(mContext);
		commonProgressDialog = new CommonProgressDialog(mContext);
		sd = new SingleDialog(mContext);
//		db = LightPosDb.getInstance(mContext);
		initTopBar();
		initView();
		initData();
		initListener();
	}

	private void initTopBar() {
		left_img = (ImageView) findViewById(R.id.left_img);
		if (left_img != null) {
			left_img.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
		page_title = (TextView) findViewById(R.id.page_title);
		right_ly = (LinearLayout) findViewById(R.id.right_ly);
		right_txt = (TextView) findViewById(R.id.right_txt);
		right_img = (ImageView) findViewById(R.id.right_img);

		center_rly = (RelativeLayout) findViewById(R.id.center_rl);
		clear_img = (ImageView) findViewById(R.id.center_clear);
		center_edit = (EditText) findViewById(R.id.center_edit);

		search_ly = (LinearLayout) findViewById(R.id.search_ly);
		search_txt = (TextView) findViewById(R.id.txt_search);

	}

	/**
	 * title 显示搜索模块
	 */
	public void showSearchMode(OnClickListener click) {
		page_title.setVisibility(View.GONE);
		center_rly.setVisibility(View.VISIBLE);

		clear_img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				hideSearchMode();
			}
		});

		search_ly.setOnClickListener(click);

		center_edit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				String str = s.toString();
				if (!TextUtils.isEmpty(str)) {
					search_txt.setText(str);
					search_ly.setVisibility(View.VISIBLE);
				} else {
					search_txt.setText("");
					search_ly.setVisibility(View.GONE);
				}
			}
		});

	}

	/**
	 * 跳转时清空和隐藏
	 */
	public void hideSearchMode() {
		center_edit.setText("");
		search_txt.setText("");
		search_ly.setVisibility(View.GONE);
	}

	/**
	 * 隐藏 右边按钮
	 */
	public void hideLeftBtn() {
		left_img.setVisibility(View.GONE);
	}

	/**
	 * 设置title 右边文字
	 */
	public void showRightText(int res) {
		if (right_txt != null)
			right_txt.setText(res);
		right_txt.setVisibility(View.VISIBLE);
		right_img.setVisibility(View.GONE);
		right_ly.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置title 右边按钮图标事件
	 */
	public void showRightImg(int res) {
		if (right_img != null)
			right_img.setBackgroundResource(res);

		right_img.setVisibility(View.VISIBLE);
		right_txt.setVisibility(View.GONE);
		right_ly.setVisibility(View.VISIBLE);
	}

	/**
	 * 左侧按钮监听
	 */
	public void setLeftListener(OnClickListener listener) {
		if (left_img != null)
			left_img.setOnClickListener(listener);
	}

	/**
	 * 右侧按钮监听
	 */
	public void setRightListener(OnClickListener listener) {
		if (right_ly != null)
			right_ly.setOnClickListener(listener);
	}

	/**
	 * 页面标题
	 */
	public void setPageTitle(int res) {
		if (page_title != null)
			page_title.setText(res);
	}
	
	/**
	 * 页面标题
	 */
	public void setPageTitle(String res) {
		if (page_title != null)
			page_title.setText(res);
	}

	public void hideOldSysBoard(EditText et) {
		Class<EditText> cls = EditText.class;
		try {
			Method setShowSoftInputOnFocus = cls.getMethod(
					"setShowSoftInputOnFocus", boolean.class);
			setShowSoftInputOnFocus.setAccessible(false);
			setShowSoftInputOnFocus.invoke(et, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转到对应的activity
	 */
	public void goToAct(Class<?> cls, boolean isFinishSelf) {
		Intent it = new Intent(mContext, cls);
		startActivity(it);
		if (isFinishSelf) {
			this.finish();
		}
	}

	/**
	 * 初始化视图
	 */
	public abstract void initView();

	/**
	 * 初始化数据
	 */
	public abstract void initData();

	/**
	 * 初始化监听事件
	 */
	public abstract void initListener();

	/**
	 * toast短提示
	 * 
	 * @param text
	 *            提示语
	 */
	public void showToast(String text) {
		Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 让控件获取焦点
	 * 
	 * @param view
	 *            需要获取焦点的控件
	 */
	public void setFocus(View view) {
		view.setFocusable(true);
		view.setFocusableInTouchMode(true);
		view.requestFocus();
		view.requestFocusFromTouch();
	}

	public void showDialog() {
		try {
			if (commonProgressDialog.isShow()) {
				return;
			} else {
				commonProgressDialog.showDialog(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showDialog(String msg) {
		try {
			if (commonProgressDialog.isShow()) {
				commonProgressDialog.setMessage(msg);
			} else {
				commonProgressDialog.showDialog(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setCancelable(boolean is) {
		commonProgressDialog.setCancelable(is);
	}

	public void dismissDialog() {
		try {
			if (commonProgressDialog.isShow()) {
				commonProgressDialog.dialogDismiss();
			} else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showTipDialog(String msg) {
		showTipDialog(msg, null);
	}

	public void showTipDialog(String msg, View.OnClickListener lsner) {
		try {
			if (!sd.isShow()) {
				sd.showDialog(msg, lsner);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

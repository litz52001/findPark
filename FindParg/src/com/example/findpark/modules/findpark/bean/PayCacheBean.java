package com.example.findpark.modules.findpark.bean;

import android.text.TextUtils;

public class PayCacheBean {
	
	private static PayCacheBean instance = null;
	
	public static PayCacheBean getInstance(){
		if(instance == null){
			instance = new PayCacheBean();
		}
		return instance;
	}
	
	public String totalMoney = "";//总金额
	public String memberDis = "";//会员折扣金额
	public String couponDis = "";//优惠券减金额
	public String needMoney = "";//需付金额
	public String payMoney = "";//实付金额   or 退款金额
	public String chargeMoney = "";//找零金额
	public String point = "";//积分
	public String tradeNo = ""; 
	
	public void clear(){
		totalMoney = "";//总金额
		memberDis = "";//会员折扣金额
		couponDis = "";//优惠券减金额
		needMoney = "";//需付金额
		payMoney = "";//实付金额
		chargeMoney = "";//找零金额
		point = "";
		tradeNo= "";
	}
	
	public boolean hasData(){
		if(!TextUtils.isEmpty(totalMoney))
			return true;
		return false;
	}

}

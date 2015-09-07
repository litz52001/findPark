package com.example.findpark.modules.findpark.bean;

import java.io.Serializable;

/**
 * 停车场属性
 * @author Litz
 *
 */
public class ParkBean implements Serializable{
	
	public String name;//名字
	public String location;//距离
	public String allPark;//总共车位
	public String surplus;//剩余车位
	public String remark;//备注
	public String imgUrl;//图片链接
	public String price;//价格
	public String time;//开放时间
	public String addres;//地址
	
	 
	public ParkBean(String name, String location, String allPark,
			String surplus, String remark, String imgUrl, String price,
			String time, String addres) {
		super();
		this.name = name;
		this.location = location;
		this.allPark = allPark;
		this.surplus = surplus;
		this.remark = remark;
		this.imgUrl = imgUrl;
		this.price = price;
		this.time = time;
		this.addres = addres;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAllPark() {
		return allPark;
	}
	public void setAllPark(String allPark) {
		this.allPark = allPark;
	}
	public String getSurplus() {
		return surplus;
	}
	public void setSurplus(String surplus) {
		this.surplus = surplus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}

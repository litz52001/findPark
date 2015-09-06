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
	
	public ParkBean(String name, String location, String allPark,
			String surplus, String remark, String imgUrl) {
		super();
		this.name = name;
		this.location = location;
		this.allPark = allPark;
		this.surplus = surplus;
		this.remark = remark;
		this.imgUrl = imgUrl;
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

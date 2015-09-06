package com.example.findpark.modules.findpark.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findpark.R;
import com.example.findpark.common.tool.DataManager;
import com.example.findpark.modules.findpark.bean.ParkBean;

public class ReserveAdapter extends BaseAdapter{

	private Context mContext;
	public List<ParkBean> reserveList;
	
	public ReserveAdapter(Context context,List<ParkBean> parkList) {
		this.mContext = context;
		this.reserveList = parkList;
	}

	@Override
	public int getCount() {
		return reserveList.size()>0 ? reserveList.size():0;
	}

	@Override
	public Object getItem(int arg0) {
		return reserveList.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ParkBean item = reserveList.get(position);
		ViewHolder viewHolder = null;
		
		 if(convertView == null)
		 {
			 convertView = LayoutInflater.from(mContext).inflate(
						R.layout.act_reserve_item, null);
			 viewHolder = new ViewHolder();
			 viewHolder.park_name = (TextView)convertView.findViewById(R.id.park_name);
			 viewHolder.park_location = (TextView)convertView.findViewById(R.id.park_location);
//			 viewHolder.park_allPark = (TextView)convertView.findViewById(R.id.park_);
			 viewHolder.park_surplus = (TextView)convertView.findViewById(R.id.park_num);
			 viewHolder.park_remark = (TextView)convertView.findViewById(R.id.park_remark);
			 viewHolder.park_imgUrl = (ImageView)convertView.findViewById(R.id.park_img);
			 convertView.setTag(viewHolder);
		 }else{
			 viewHolder =  (ViewHolder)convertView.getTag();
		 }
		 
		 viewHolder.park_name.setText(item.getName());
		 viewHolder.park_location.setText(item.getLocation());
//		 viewHolder.park_allPark.setText(item.get);
		 viewHolder.park_surplus.setText(item.getSurplus());
		 viewHolder.park_remark.setText(item.getRemark());
		 
		 viewHolder.park_imgUrl.setImageBitmap(new DataManager(mContext).getBltmap(item.getImgUrl()));
		
		return convertView;
	}

	final class ViewHolder {
		public TextView park_name;
		public TextView park_location;
		public TextView park_allPark;
		public TextView park_surplus;
		public TextView park_remark;
		public ImageView park_imgUrl;
	}
	
}

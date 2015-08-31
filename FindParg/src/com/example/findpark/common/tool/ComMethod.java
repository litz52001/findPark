package com.example.findpark.common.tool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

//import com.wjl.smartpos.modules.coupon.bean.CouponBean;




import android.content.Context;

/**
 * 公共方法
 * @author super
 */
public class ComMethod {
	/**
	 * 加载Assert文本文件，转换成String类型
	 * 
	 * @param context
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static String loadAssetsText(Context context, String fileName)
			throws IOException {
		InputStream inputStream = context.getAssets().open(fileName,
				Context.MODE_PRIVATE);
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		byte[] bytes = new byte[4096];
		int len = 0;
		while ((len = inputStream.read(bytes)) > 0) {
			byteStream.write(bytes, 0, len);
		}

		return new String(byteStream.toByteArray(), "UTF-8");
	}

	private static boolean isExistDataCache(Context context, String cachefile) {
		boolean exist = false;
		File data = context.getFileStreamPath(cachefile);
		if (data.exists())
			exist = true;
		return exist;
	}

	/**
	 * 保存对象
	 * 
	 * @param ser
	 * @param file
	 * @throws IOException
	 */
	public static boolean saveObject(Context context, Serializable ser,
			String file) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = context.openFileOutput(file, Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ser);
			oos.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				oos.close();
			} catch (Exception e) {
			}
			try {
				fos.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * 读取对象
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static Serializable readObject(Context context, String file) {
		if (!isExistDataCache(context, file))
			return null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = context.openFileInput(file);
			ois = new ObjectInputStream(fis);
			return (Serializable) ois.readObject();
		} catch (FileNotFoundException e) {
		} catch (Exception e) {
			e.printStackTrace();
			// 反序列化失败 - 删除缓存文件
			if (e instanceof InvalidClassException) {
				File data = context.getFileStreamPath(file);
				data.delete();
			}
		} finally {
			try {
				ois.close();
			} catch (Exception e) {
			}
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * 判断list是否为空
	 * 
	 * @param list
	 * @return true 空 false 不为空
	 */
	public static <T> boolean isListEmpty(List<T> list) {
		if (list == null || list.size() <= 0)
			return true;
		return false;
	}

	/**
	 * 删除ArrayList中重复元素
	 * 
	 * @param <T>
	 * @param list
	 */
	public static <T> void removeDuplicate(List<T> list) {
		HashSet<T> h = new HashSet<T>(list);
		list.clear();
		list.addAll(h);
	}

//	/**
//	 * 删除优惠券列表中重复的优惠券
//	 */
//	public static void removeDuplicateObj(List<CouponBean> list) {
//		for (int i = 0; i < list.size() - 1; i++) {
//			for (int j = list.size() - 1; j > i; j--) {
//				if (list.get(j).getCouponNo().equals(list.get(i).getCouponNo())) {
//					list.remove(j);
//				}
//			}
//		}
//	}

	/**
	 * 时间比大小
	 * 
	 * @param t1
	 *            开始时间
	 * @param t2
	 *            结束时间
	 * @return 0 相等;-1 t1在t2之前;1 t1在t2之后
	 */
	public static int timeCompare(String t1, String t2) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(formatter.parse(t1));
			c2.setTime(formatter.parse(t2));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int result = c1.compareTo(c2);
		return result;
	}
	
	/**
	 * 返回当前系统时间 格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurTime() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}
	
	/**
	 * 返回当前系统时间 格式：yyyy-MM-dd HH:mm
	 * @return
	 */
	public static String getCurTimeMin() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = format.format(date);
		return time;
	}
	
	/***
	 * 日期时间数字如果小于10则加0显示 列如 9 显示 09
	 */
	public static String formatTimeNum(int timeNum){
		if(timeNum>=10)
			return String.valueOf(timeNum);
		else {
			return "0"+timeNum;
		}
	}
	
	/***
	 * 日期时间数字如果小于10则加0显示 列如 9 显示 09
	 */
	public static String formatTimeNum(String timeNum){
		int timeNumInt = 0;
		try {
			timeNumInt = Integer.parseInt(timeNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (timeNumInt >= 10)
			return String.valueOf(timeNum);
		else 
			return "0" + timeNum;
	}
	
	/***
     * 添加字符串 打印居中显示
     * @param str
     * @return
     */
    public static String printCenterStr(String str)
    {
    	int totalLength = 32;
    	int strlength = getStringRealLength(str);
    	StringBuffer sbBuffer = new StringBuffer();
    	int length = totalLength - strlength;
    	int x = length/2;
    	for (int i = 0; i < x; i++) {
    		sbBuffer.append(" ");
		}
    	sbBuffer.append(str);
    	
    	return sbBuffer.toString();
    }

    /**
     * 计算中文长度
     * @param str
     * @return
     */
    public static int getStringRealLength(String str){  
        String str1;
		try {
			str1 = new String(str.getBytes("GB2312"),"iso-8859-1");
			return str1.length(); 
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
        return str.length();  
    }  
    
}

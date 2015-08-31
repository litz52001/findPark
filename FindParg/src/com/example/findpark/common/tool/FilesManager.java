/**
 * 
 */
package com.example.findpark.common.tool;

import java.io.File;

import android.content.Context;

/**
 * 文件管理
 * @author super
 */
public class FilesManager {
	/**
	 * 图片下载文件夹名称
	 */
	public static final String imageFileName = "imageFile";
    
	/**
	 * 创建图片目录文件夹
	 */
	public static void openFile(Context context) {
		
		File temp = getDownImageFolder(context);
		if (!temp.exists()) {
			temp.mkdir();
		}
	}

	/**
	 * 获取下载图片存放路径文件夹file
	 */
	public static File getDownImageFolder(Context context){
		return new File(getDownLoadImageFolderName(context));
	}
	
	/**
	 * 获取下载图片文件夹存放路径
	 */
	public static String getDownLoadImageFolderName(Context context){
		return context.getFilesDir().getAbsolutePath() + File.separator
				+ imageFileName;
	}
	
	/**
	 * 获取下载图片本地硬缓存地址全路径（data/data目录下）
	 * 
	 * @param url
	 *            图片url
	 */
	public static String getDownLoadImageDir(Context context, String url) {
		int index = url.lastIndexOf("/");
//		return context.getFilesDir().getAbsolutePath() + File.separator
//				+ imageFileName + File.separator
//				+ url.substring(index + 1, url.length());
		return context.getFilesDir().getAbsolutePath()+ File.separator
				+ url.substring(index + 1, url.length());

	}

}

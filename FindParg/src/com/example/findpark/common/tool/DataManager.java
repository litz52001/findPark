package com.example.findpark.common.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DataManager {

	private Context mContext;

	public DataManager(Context context) {
		this.mContext = context;
	}

	public String getData(String fileName) {
		String line = "";
		String result = "";
		try {
			InputStream in = mContext.getAssets().open("data/"+fileName + ".json");
			InputStreamReader inputReader = new InputStreamReader(in);
			BufferedReader bf = new BufferedReader(inputReader);
			while ((line = bf.readLine()) != null) {
				result += line;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 从Assets中读取图片
	 */
	public Bitmap getBltmap(String imgName) {
		Bitmap bmp = null;
		InputStream in = null;
		AssetManager assetManager = mContext.getAssets();

		try {
			in = assetManager.open("img/" + imgName + ".png");
			bmp = BitmapFactory.decodeStream(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bmp;
	}

}

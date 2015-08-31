package com.example.findparg.common.tool;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * 图片管理工具类
 * @author super 
 */
public class ImageManager {
	/**
	 * 资源图片缓存软引用
	 */
	private Map<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();
	private static ImageManager imageManage;
	private Context mContext;

	private ImageManager(Context context) {
		mContext = context;
	}

	public static ImageManager getInstance(Context context) {
		if (imageManage == null) {
			imageManage = new ImageManager(context);
		}
		return imageManage;
	}

	/**
	 * 以最省内存的方式读取本地资源的图片
	 * 
	 * @param resId
	 * @return BitMap
	 */
	public Bitmap readResBitMap(int resId) {
		Bitmap bitmap = null;
		SoftReference<Bitmap> softBitmap = null;
		softBitmap = imageCache.get(resId + "");
		if (softBitmap != null) {
			bitmap = softBitmap.get();
			if (bitmap == null) {
				BitmapFactory.Options opt = new BitmapFactory.Options();
				opt.inPreferredConfig = Bitmap.Config.RGB_565;
				opt.inPurgeable = true;
				opt.inInputShareable = true;
				InputStream is = mContext.getResources().openRawResource(resId);
				bitmap = BitmapFactory.decodeStream(is, null, opt);
				softBitmap = new SoftReference<Bitmap>(bitmap);
				if (bitmap != null) {
					imageCache.put(resId + "", softBitmap);

				}
			}
			return bitmap;
		}
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		InputStream is = mContext.getResources().openRawResource(resId);
		bitmap = BitmapFactory.decodeStream(is, null, opt);
		softBitmap = new SoftReference<Bitmap>(bitmap);
		if (bitmap != null) {
			imageCache.put(resId + "", softBitmap);

		}
		return bitmap;
	}

	/**
	 * 以最省内存的方式读取文件资源的图片
	 * 
	 * @param imageFile
	 * @return
	 */
	public Bitmap readFileBitMap(String imageFile) {
		Bitmap bitmap = null;
		SoftReference<Bitmap> softBitmap = null;
		softBitmap = imageCache.get(imageFile);
		if (softBitmap != null) {
			bitmap = softBitmap.get();
			if (bitmap == null) {
				try {
					// BitmapFactory.Options opts = new BitmapFactory.Options();
					// opts.inJustDecodeBounds = true;
					// BitmapFactory.decodeFile(imageFile, opts);
					// opts.inSampleSize = computeSampleSize(opts, -1, 128 *
					// 128);
					// opts.inJustDecodeBounds = false;
					bitmap = BitmapFactory.decodeFile(imageFile);
					softBitmap = new SoftReference<Bitmap>(bitmap);
					if (bitmap != null) {
						imageCache.put(imageFile, softBitmap);
					}
				} catch (OutOfMemoryError e) {
					Log.e("OutOfMemoryError----->", "OutOfMemoryError");
				}

			}
			return bitmap;
		}
		// BitmapFactory.Options opts = new BitmapFactory.Options();
		// opts.inJustDecodeBounds = true;
		// BitmapFactory.decodeFile(imageFile, opts);
		// opts.inSampleSize = computeSampleSize(opts, -1, 128 * 128);
		// opts.inJustDecodeBounds = false;
		try {
			bitmap = BitmapFactory.decodeFile(imageFile);
			softBitmap = new SoftReference<Bitmap>(bitmap);
			if (bitmap != null) {
				imageCache.put(imageFile, softBitmap);
			}
		} catch (OutOfMemoryError e) {
			Log.e("OutOfMemoryError----->", "OutOfMemoryError");
		}
		return bitmap;
	}

	/**
	 * 读取图片（普通读取方式）
	 */
	public Bitmap readFileBitMapNomal(String imageFile) {
		Bitmap bitmap = null;
		// BitmapFactory.Options opts = new BitmapFactory.Options();
		// opts.inJustDecodeBounds = true;
		// BitmapFactory.decodeFile(imageFile, opts);
		// opts.inSampleSize = computeSampleSize(opts, -1, 128 * 128);
		// opts.inJustDecodeBounds = false;
		try {
			bitmap = BitmapFactory.decodeFile(imageFile);
		} catch (OutOfMemoryError e) {
			Log.e("OutOfMemoryError----->", "OutOfMemoryError");
		}
		return bitmap;
	}

	/**
	 * 回收图片内存
	 */
	public void releaseImage(String path) {
		if (imageCache.containsKey(path)) {
			SoftReference<Bitmap> reference = imageCache.get(path);
			Bitmap bitmap = reference.get();
			if (bitmap != null) {
				if (!bitmap.isRecycled()) {
					Log.e("bitmap recycle", "now bitmap recycle " + path);
					bitmap.recycle();
					bitmap = null;
					System.gc();
				}
				imageCache.remove(path);
			}
		}
	}

	/**
	 * 获取最优缩放比
	 */
	public int computeSampleSize(BitmapFactory.Options options,

	int minSideLength, int maxNumOfPixels) {

		int initialSize = computeInitialSampleSize(options, minSideLength,

		maxNumOfPixels);

		int roundedSize;

		if (initialSize <= 8) {

			roundedSize = 1;

			while (roundedSize < initialSize) {

				roundedSize <<= 1;

			}

		} else {

			roundedSize = (initialSize + 7) / 8 * 8;

		}

		return roundedSize;
	}

	private int computeInitialSampleSize(BitmapFactory.Options options,

	int minSideLength, int maxNumOfPixels) {

		double w = options.outWidth;

		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 :

		(int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));

		int upperBound = (minSideLength == -1) ? 128 :

		(int) Math.min(Math.floor(w / minSideLength),

		Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			return lowerBound;
		}
		if ((maxNumOfPixels == -1) &&

		(minSideLength == -1)) {

			return 1;

		} else if (minSideLength == -1) {

			return lowerBound;

		} else {

			return upperBound;

		}
	}

}

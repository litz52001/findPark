package com.example.findpark.common.tool;

import java.text.DecimalFormat;

public class FU {

	public static String parseDleStr(String dleStr) {
		DecimalFormat df = new DecimalFormat("0.00");
		Double dle = 0.00;
		try {
			dle = Double.parseDouble(dleStr);
		} catch (Exception e) {
			return "0.00";
		}
		return df.format(dle);
	}
	
	public static String parseDleStr(Double dle) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(dle);
	}
	
	public static Double parseDle(String dleStr) {
		Double dle = 0.00;
		try {
			dle = Double.parseDouble(dleStr);
		} catch (Exception e) {
		}
		return dle;
	}

}

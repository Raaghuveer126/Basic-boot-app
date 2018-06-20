package com.framework.api.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String formatDate(Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Date modifyDate(Date date, int years, int months, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.YEAR, years);
		c.add(Calendar.MONTH, months);
		c.add(Calendar.DATE, days);
		return c.getTime();
	}
}

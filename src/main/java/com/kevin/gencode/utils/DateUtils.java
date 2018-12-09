package com.kevin.gencode.utils;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 时间工具类
 * @author Administrator
 */
public class DateUtils {
	
	/**
	 * 格式化时间
	 * @param date
	 * @param format
	 * @return
	 */
	public static String genDate(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
}
package com.kevin.gencode.utils;
import java.util.UUID;
/**
 * 生成UUI做主键用的一个工具类
 * @author kevin
 *
 */
public class UUIDUtils {
	/**
	 * 生成UUID
	 * @return
	 */
	public static String genUUID(){
		UUID uuid  =  UUID.randomUUID(); 
		String s = uuid.toString();
		s = s.replace("-", "");
		return s;
	}
}

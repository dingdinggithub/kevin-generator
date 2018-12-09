package com.kevin.gencode.utils;
import java.util.HashMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class Config {
	private static final Logger logger = Logger.getLogger(Config.class);
  
	private static HashMap<String, String> configMap = null;

	public static String getConfig(String name) {
		return configMap.get(name);
	}

	public static int getConfig(String pname, int def) {
		String rel = getConfig(pname);
		try {
			return Integer.valueOf(rel);
		} catch (NumberFormatException e) {
			logger.error(e);
		}
		return def;
	}

	public static String getConfig(String pname, String def) {
		String rel = getConfig(pname);
		if (!StringUtils.isBlank(rel)) {
			return rel;
		}
		return def;
	}


	public void setConfigMap(HashMap<String, String> map) {
		configMap = map;
	}
}

package com.kevin.gencode.utils;
import java.io.File;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
/**
 * Dom4j工具类
 * @author Administrator
 */
public class Dom4jUtils {
	
	/**
	 * @param targetName：文件绝对路径+文件名称
	 * @return
	 */
	public static Element getRoot(String targetName){
		Element root;
		try {
			SAXReader reader = new SAXReader();         
			Document document = reader.read(new File(targetName));
			root = document.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
			return null;
		}
	    return root;
	}
}

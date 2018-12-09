package com.kevin.gencode.utils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.dom4j.Element;

import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * freemarker工具工具类
 * @author Administrator
 */
public class FreemarkerUtils {
	
	private static Configuration conf;
	
	/**
	 * 模板路径
	 * @param pname 模板路径
	 */
	public FreemarkerUtils(String pname){
		conf = new Configuration(Configuration.VERSION_2_3_21);
		conf.setClassForTemplateLoading(FreemarkerUtils.class, pname);
	}
	
	/**
	 * @param data          模板数据
	 * @param targetName    输出文件（绝对路径+文件名称）
	 * @throws Exception
	 */
	public static void printToFile(String templateFile, Map<String,Object> data, String targetName) throws Exception{
		Template template = conf.getTemplate(templateFile);
		//输出流
        Writer out = new FileWriter(new File(targetName+".xml"));
        
        template.process(data, out);
        out.close();
        
        //读取xml，将xml中读取的值放进Map集合中
        Map<String,Object> object = new HashMap<String,Object>();
        
        //读取xml文件读取
	    Element root = Dom4jUtils.getRoot(targetName+".xml");
	    
	    object.put("name", root.elementText("name"));//.getText();
	    object.put("filePath", root.elementText("filePath"));
	    object.put("fileName", root.elementText("fileName"));
	    object.put("content", root.elementText("content"));
	    
	    saveToPath(object);
	}
	
	private static void saveToPath(Map<String,Object> object) throws IOException{
		File file = new File(object.get("filePath")+"/"+object.get("fileName"));
		/*if(!file.exists()){
			file.mkdir();
		}*/
		FileUtils.writeByteArrayToFile(file, object.get("content").toString().getBytes(), false);
	}
}
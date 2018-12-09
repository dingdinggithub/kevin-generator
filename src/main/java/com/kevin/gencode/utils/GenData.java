package com.kevin.gencode.utils;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Element;
import com.google.common.collect.Maps;
import com.kevin.constants.SystemConstants;
import com.kevin.gencode.entity.Filed;
public class GenData {
	
	@SuppressWarnings("rawtypes")
	public static Map<String,Object> genCodeData(String path,Element genroot){
		boolean flag = false;
		Map<String, Object> maps = Maps.newHashMap();
		
		//项目的基本属性
		maps.put("projectName", genroot.attributeValue("name"));       //项目描述
	    maps.put("packageName",genroot.attributeValue("basepackage")); //基本包名
	    maps.put("projectPath", genroot.attributeValue("path"));       //项目基本路径
		
	    //读取xml文件获取根元素
		Element root = Dom4jUtils.getRoot(path);
	    
	    //生成基本数据,类的注释
	    maps.put("functionName",root.attributeValue("description"));  				//类的部分描述
	    maps.put("functionAuthor",root.attributeValue("author")); 				//作者
	    maps.put("functionVersion", root.attributeValue("since")); 			//版本
	    maps.put("functionDate",DateUtils.genDate(new Date(), SystemConstants.DateConstant.TIME));
	    
	    
	    maps.put("ClassName", root.attributeValue("name"));    //类名 如：User
	    maps.put("tableName", root.attributeValue("table"));    //表名 如：tb_users
	    maps.put("classDescription", root.attributeValue("description"));   //类的描述 如 用户
	    
	    //封装实体对象所需要的字段
	    List<Filed> fileds = new ArrayList<Filed>();
	    Filed filed = null;
	    Iterator iterator = root.elementIterator();
	    while(iterator.hasNext()){
	    	
	    	Element element = (Element) iterator.next();
	    	
	    	if("id".equals(element.getName())){//获取主键
	    		Filed filed1 = new Filed();
	    		String name = element.attributeValue("name");
		    	String type = element.attributeValue("type");
		    	String description =  element.attributeValue("description");
		    	String column = element.attributeValue("column");
		    	filed1.setName(name);
		    	if(type.equals("date")){
		    		flag = true;
		    	}
		    	filed1.setJavaType(type);
		    	filed1.setDescription(description);
		    	filed1.setColumn(column);
	    		maps.put("id", filed1);
	    	}else{ //除主键外的所有列表
	    		filed = new Filed();
		    	String name = element.attributeValue("name");
		    	String type = element.attributeValue("type");
		    	String description =  element.attributeValue("description");
		    	String column = element.attributeValue("column");
		    	String dateFmt = element.attributeValue("dateFmt");
		    	String maxlength = element.attributeValue("maxlength");
		    	filed.setName(name);
		    	if(type.equals("date")){
		    		flag = true;
		    		filed.setDateFmt(dateFmt);
		    	}
		    	filed.setJavaType(type);
		    	filed.setDescription(description);
		    	filed.setColumn(column);
		    	filed.setMaxlength(maxlength);
		    	
		    	System.err.println(name+"---"+type+"----"+description);
		    	
		    	fileds.add(filed);
	    	}
	    	
	    }
	    if(flag){
	    	maps.put("i","java.util.Date");
	    }
	    maps.put("import", flag);  //导入时间jar包
	    maps.put("filedList",fileds );
	    return maps;
	}
	
	public static void main(String[] args) {
		String path = GenData.class.getResource("/").getPath();
		Element root = Dom4jUtils.getRoot(path+"/"+SystemConstants.SETTINFS_FILE);
		System.err.println(root.attributeValue("name"));
	}
}

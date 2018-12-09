package com.kevin.start;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Element;

import com.kevin.constants.SystemConstants;
import com.kevin.gencode.utils.Dom4jUtils;
import com.kevin.gencode.utils.FreemarkerUtils;
import com.kevin.gencode.utils.GenData;
@SuppressWarnings({"rawtypes","static-access"})
public class Start {
	
	private static void genCode(){
		//获取项目的绝对路径
		String path = GenData.class.getResource("/").getPath();
		//读取核心配置文件 
		Element genroot = Dom4jUtils.getRoot(path+"/"+SystemConstants.SETTINFS_FILE);
		//读取table.xml文件
		Iterator iterator = genroot.elementIterator();
	    while(iterator.hasNext()){
	    	Element element = (Element) iterator.next();
	    	String src = element.attributeValue("src");
	    	Map<String,Object> datas = GenData.genCodeData(path+"/"+src, genroot);
	    	//模板技术生成代码
	    	genCodeByTemplate(datas);
	    }
	}
	
	private static void genCodeByTemplate(Map<String,Object> maps){
		try {
			//1、对象实体
			new FreemarkerUtils("/template/persistent").printToFile("entity.xml", maps, "d:/kevin/entity");
			//2、Dao层
			new FreemarkerUtils("/template/persistent").printToFile("dao.xml", maps, "d:/kevin/Mapper");
			new FreemarkerUtils("/template/persistent").printToFile("baseDao.xml", maps, "d:/kevin/BaseMapper");
			//3、Mapper
			new FreemarkerUtils("/template/persistent").printToFile("mapper.xml", maps, "d:/kevin/mapper");
			//4、service
			new FreemarkerUtils("/template/service").printToFile("ibaseservice.xml", maps, "d:/kevin/baseservice");
			new FreemarkerUtils("/template/service").printToFile("iservice.xml", maps, "d:/kevin/service");
			//5、serviceImpl
			new FreemarkerUtils("/template/service").printToFile("service.xml", maps, "d:/kevin/serviceImpl");
			//6、business
			new FreemarkerUtils("/template/business").printToFile("ibusiness.xml", maps, "d:/kevin/business");
			new FreemarkerUtils("/template/business").printToFile("business.xml", maps, "d:/kevin/businessImpl");
			//7.DTO
			new FreemarkerUtils("/template/dto/request").printToFile("insertReqDTO.xml", maps, "d:/kevin/insertReqDTO");
			new FreemarkerUtils("/template/dto/request").printToFile("pageReqDTO.xml", maps, "d:/kevin/pageReqDTO");
			new FreemarkerUtils("/template/dto/request").printToFile("updateReqDTO.xml", maps, "d:/kevin/updateReqDTO");
			new FreemarkerUtils("/template/dto/request").printToFile("batchInsertReqDTO.xml", maps, "d:/kevin/batchInsertReqDTO");
			new FreemarkerUtils("/template/dto/request").printToFile("batchDeleteReqDTO.xml", maps, "d:/kevin/batchDeleteReqDTO");
			new FreemarkerUtils("/template/dto/request").printToFile("batchUpdateReqDTO.xml", maps, "d:/kevin/batchUpdateReqDTO");

			new FreemarkerUtils("/template/dto/response").printToFile("pageResDTO.xml", maps, "d:/kevin/pageResDTO");
			//8、controller
			new FreemarkerUtils("/template/controller").printToFile("baseController.xml", maps, "d:/kevin/baseController");
			new FreemarkerUtils("/template/controller").printToFile("controller.xml", maps, "d:/kevin/controller");
			//页面层
			//9.model展示页面
			new FreemarkerUtils("/template/page").printToFile("modelList.xml", maps, "d:/kevin/page");
			//list
			/*new FreemarkerUtils("/template").printToFile("list.xml", maps, "d:/credType/list");
			new FreemarkerUtils("/template").printToFile("addView.xml", maps, "d:/credType/addView");
			new FreemarkerUtils("/template").printToFile("editView.xml", maps, "d:/credType/editView");
			new FreemarkerUtils("/template").printToFile("detailView.xml", maps, "d:/credType/detail");*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * 页面展示层model
     * @param maps
     */
    private static void genFontCodeByTemplate(Map<String,Object> maps){
        try {
            new FreemarkerUtils("/template/page").printToFile("modelList.xml", maps, "d:/kevin/page");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * //生成代码
	 */
	public static void main(String[] args) {
		genCode();
	}
}

package com.kevin.gencode.entity;
/**
 * 实体对象中的字段
 * @author Administrator
 */
public class Filed {
	private String name;     //字段名称
	private Object javaType; //字段类型
	private String column;
	private String description;  //字段描述
	private String dateFmt; //日期格式
	private String maxlength;//输入内容最大长度
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Object getJavaType() {
		return javaType;
	}

	public void setJavaType(Object javaType) {
		if(javaType.equals("int")){
			this.javaType = "Integer";
		}else if(javaType.equals("long")){
			this.javaType = "Long";
		}else if(javaType.equals("string")){
			this.javaType = "String";
		}else if(javaType.equals("date")){
			this.javaType = "LocalDateTime";
		}else if(javaType.equals("decimal")){
			this.javaType = "BigDecimal";
		}else if(javaType.equals("double")){
			this.javaType = "Double";
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColumn() {
		return column;
	}
	
	public void setColumn(String column) {
		this.column = column;
	}

	public String getDateFmt() {
		return dateFmt;
	}

	public void setDateFmt(String dateFmt) {
		this.dateFmt = dateFmt;
	}

	public String getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}
	
}

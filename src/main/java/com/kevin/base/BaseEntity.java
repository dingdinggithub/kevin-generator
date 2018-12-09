package com.kevin.base;
/**
 * 基本实体类
 * @author Administrator
 */
public abstract class BaseEntity<T> implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Page<T> page;

	public Page<T> getPage() {
		return page;
	}

	public void setPage(Page<T> page) {
		this.page = page;
	}
}

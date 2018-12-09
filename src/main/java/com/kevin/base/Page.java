package com.kevin.base;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.kevin.gencode.utils.Config;
import com.kevin.gencode.utils.CookieUtils;


/**
 * 分页实体类，这里会生成前台分页代码
 * @author Administrator
 * @param <T>
 */
public class Page<T> {
	
	/**
	 * 当前页
	 */
	private int pageNum = 1;
	
	/**
	 * 每页显示几条记录
	 */
	private int numPerPage = Integer.valueOf(Config.getConfig("pagesize"));
	
	/**
	 * 每页显示的记录数
	 */
	private List<T> list;
	
	/**
	 * 总记录数
	 */
	private long total = 0;  
	
	/**
	 * 总页数
	 */
	private int pageTotal = 0;
	
	/**
	 * 显示页面长度
	 */
	private int length = 8;
	
	/**
	 * 前后显示页面长度
	 */
	private int slider = 1;
	
	/**
	 * limit start,pageSize;
	 */
	private int start;
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param record 总记录数
	 */
	public Page(HttpServletRequest request ,HttpServletResponse response,int record) {
		//获取当前页的参数
		String no = request.getParameter("pageNum");
		if(null == no || "".equals(no)) {
			CookieUtils.setCookie(response, "pageNum", 1+"");
			this.setPageNum(1);
		}else{
			//判断当前页是否为合法数字，防止非法攻击
			if(StringUtils.isNumeric(no)) {
				CookieUtils.setCookie(response, "pageNum", no);
				this.setPageNum(Integer.parseInt(no));
			}
		}
		
		//自定义分页参数pageSize
		String numPerPage = request.getParameter("numPerPage");
		if(null != numPerPage && !"".equals(numPerPage)) {
			//判断当前页是否为合法数字，防止非法攻击
			if(StringUtils.isNumeric(numPerPage)) {
				CookieUtils.setCookie(response, "numPerPage", numPerPage);
				this.setNumPerPage(Integer.parseInt(numPerPage));
			}
		}
		
		//删除修改操作,从cookie中获取当前页面
		String repage = request.getParameter("repage");
		if(null != repage) {
			repage = CookieUtils.getCookie(request, "pageNum");
			if(StringUtils.isNumeric(repage)) {
				this.setPageNum(Integer.parseInt(repage));
			}
		}
		
		//获取总记录数
		this.setTotal(record);
		this.setStart(getPageNum());
		this.setPageTotal(record);
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * @param record 总记录数
	 */
	public void setPageTotal(int record) {
		this.pageTotal = record%numPerPage == 0 ? record/numPerPage: record/numPerPage+1;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int pageNum) {
		this.start = (pageNum-1)*numPerPage;
	}
}

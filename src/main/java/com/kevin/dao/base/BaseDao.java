package com.kevin.dao.base;
import java.util.List;
import java.util.Map;
/**
 * BaseDao：Dao的基类，封装了增删查改代码
 * @author Administrator
 * @param <T>
 */
public interface BaseDao<T> {
	
	/**
	 * 保存实体
	 * @param Entity 实体对象
	 */
	public void save(T Entity);
	
	/**
	 * 保存实体
	 * @param entity 实体对象
	 * @return 返回主键
	 */
	public int insert(T entity);
	
	/**
	 * 修改实体
	 * @param entity 实体对象
	 */
	public void update(T entity);
	
	/**
	 * 删除实体
	 * @param entity 实体对象
	 */
	public void delete(String id);
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id);
	
	/**
	 * 根据条件 查询
	 * @param maps
	 * @return
	 */
	public List<T> findByMap(Map<String,Object> maps);
	
	/**
	 * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
	 * @param entity 实体对象
	 * @return
	 */
	public List<T> find(T entity);
	
	/**
	 * 获得总记录数
	 * @return
	 */
	public int listCount(T entity);
	
	/**
	 * 查询所有记录
	 * @return
	 */
	public List<T> list();
}

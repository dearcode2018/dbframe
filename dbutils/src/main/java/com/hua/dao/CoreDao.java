/**
 * 描述: 
 * CoreDao.java
 * @author qy.zheng
 *  version 1.0
 */
package com.hua.dao;

import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

/**
 * 描述: 
 * @author qy.zheng
 * CoreDao
 */
public interface CoreDao<T> {

	/**
	 dbutil CoreDao
	 
	 
	 */

	/**
	 * 
	 * 描述: 新增实体 - 无动态参数
	 * @author qye.zheng
	 * @param sql
	 * @return
	 */
	public int insert(final String sql);
	
	/**
	 * 
	 * 描述: 新增实体
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	public int insert(final String sql, final Object[] params);
	
	/**
	 * 
	 * 描述: 新增实体
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	public int insert(final String sql, final List<Object> params);
	
	/**
	 * 
	 * 描述: 新增或更新 - 无动态参数
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	public int insertOrUpdate(final String sql);
	
	/**
	 * 
	 * 描述: 新增或更新
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	public int insertOrUpdate(final String sql, final Object[] params);
	
	/**
	 * 
	 * 描述: 新增或更新
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	public int insertOrUpdate(final String sql, final List<Object> params);
	
	/**
	 * 
	 * 描述: 删除 - 无动态参数
	 * @author qye.zheng
	 * @param sql
	 * @param id
	 * @return
	 */
	public int delete(final String sql);
	
	/**
	 * 
	 * 描述: 单一删除
	 * @author qye.zheng
	 * @param sql
	 * @param id
	 * @return
	 */
	public int delete(final String sql, final Object id);
	
	/**
	 * 
	 * 描述: 批量删除
	 * @author qye.zheng
	 * @param sql
	 * @param ids
	 * @return
	 */
	public int delete(final String sql, final Object[] ids);
	
	/**
	 * 
	 * 描述: 批量删除
	 * @author qye.zheng
	 * @param sql
	 * @param ids
	 * @return
	 */
	public int delete(final String sql, final List<Object> ids);
	
	/**
	 * 
	 * 描述: 更新实体 - 无动态参数
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(final String sql);
	
	/**
	 * 
	 * 描述: 更新实体
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(final String sql, final Object[] params);
	
	/**
	 * 
	 * 描述: 更新实体
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(final String sql, final List<Object> params);
	
	/**
	 * 
	 * 描述: 批量dml操作，insert/delete/update
	 * @author  qye.zheng
	 * @param sql
	 * @param params
	 * @return 返回每个场景下影响的行数
	 */
	public int[] batch(final String sql, final Object[][] params);
	
	/**
	 * 
	 * 描述: 批量插入数据 - 
	 * @author  qye.zheng
	 * @param sql
	 * @param resultSetHandler
	 * @param params
	 * @return
	 */
	public T insertBatch(final String sql, final ResultSetHandler<T> resultSetHandler, final Object[][] params);
	
	/**
	 * 
	 * 描述: 获取单个对象 - 无动态参数
	 * @author qye.zheng
	 * @param sql
	 * @param id
	 * @return
	 */
	public T get(final String sql);
	
	/**
	 * 
	 * 描述: 获取单个对象
	 * @author qye.zheng
	 * @param sql
	 * @param id
	 * @return
	 */
	public T get(final String sql, final Object id);
	
	/**
	 * 
	 * 描述: 搜索多行记录 - 无动态参数
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> search(final String sql);
	
	/**
	 * 
	 * 描述: 搜索多行记录
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> search(final String sql, final Object[] params);
	
	/**
	 * 
	 * 描述: 搜索多行记录
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> search(final String sql, final List<Object> params);
	
	/**
	 * 
	 * 描述: 统计记录数 - 无动态参数
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	public Long count(final String sql);
	
	/**
	 * 
	 * 描述: 统计记录数
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	public Long count(final String sql, final Object[] params);
	
	/**
	 * 
	 * 描述: 统计记录数
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	public Long count(final String sql, final List<Object> params);
	
}

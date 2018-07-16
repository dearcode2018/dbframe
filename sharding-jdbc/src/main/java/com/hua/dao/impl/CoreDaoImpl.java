/**
 * 描述: 
 * CoreDaoImpl.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.hua.dao.CoreDao;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * CoreDaoImpl
 */
/*
 * spring-dao 继承 JdbcDaoSupport，注入数据源即可(DataSource)
 * spring-jdbc 是采用直接注入 JdbcTemplate对象的方式
 */
public class CoreDaoImpl<T> extends JdbcDaoSupport implements CoreDao<T> {

	/* 使用 DaoSupport 的 logger */
	protected final Log log = logger;
	
	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public int insert(final Integer index, String sql)
	{
		return getJdbcTemplate().update(sql);
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @param params
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public int insert(final Integer index, String sql, Object[] params)
	{
		return getJdbcTemplate().update(sql, params);
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @param params
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public int insert(final Integer index, String sql, List<Object> params)
	{
		return getJdbcTemplate().update(sql, params);
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public int insertOrUpdate(final Integer index, String sql)
	{
		return getJdbcTemplate().update(sql);
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @param params
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public int insertOrUpdate(final Integer index, String sql, Object[] params)
	{
		return getJdbcTemplate().update(sql, params);
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @param params
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public int insertOrUpdate(final Integer index, String sql, List<Object> params)
	{
		return getJdbcTemplate().update(sql, params);
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public int delete(final Integer index, String sql)
	{
		return getJdbcTemplate().update(sql);
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @param id
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public int delete(final Integer index, String sql, Object id)
	{
		return getJdbcTemplate().update(sql, id);
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @param ids
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public int delete(final Integer index, String sql, Object[] ids)
	{
		return getJdbcTemplate().update(sql, ids);
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @param ids
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public int delete(final Integer index, String sql, List<Object> ids)
	{
		return getJdbcTemplate().update(sql, ids);
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public int update(final Integer index, String sql)
	{
		return getJdbcTemplate().update(sql);
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @param params
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public int update(final Integer index, String sql, Object[] params)
	{
		return getJdbcTemplate().update(sql, params);
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @param params
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public int update(final Integer index, String sql, List<Object> params)
	{
		return getJdbcTemplate().update(sql, params);
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @param params
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public int[] batch(final Integer index, String sql, Object[][] params)
	{
		return null;
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public T get(final Integer index, String sql)
	{
		return null;
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @param id
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public T get(final Integer index, String sql, Object id)
	{
		return null;
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public List<T> search(final Integer index, String sql)
	{
		//return getJdbcTemplate().queryForList(sql);
		return null;
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @param params
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public List<T> search(final Integer index, String sql, Object[] params)
	{
		return null;
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @param params
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public List<T> search(final Integer index, String sql, List<Object> params)
	{
		return null;
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public Long count(final Integer index, String sql)
	{
		return getJdbcTemplate().queryForObject(sql, Long.class);
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @param params
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public Long count(final Integer index, String sql, Object[] params)
	{
		return getJdbcTemplate().queryForObject(sql, Long.class, params);
	}

	/**
	 * @description 
	 * @param index 分库索引 (从1开始)
	 * @param sql
	 * @param params
	 * @return
	 * @author qye.zheng
	 */
	@Override
	public Long count(final Integer index, String sql, List<Object> params)
	{
		return getJdbcTemplate().queryForObject(sql, Long.class, params);
	}
	
}

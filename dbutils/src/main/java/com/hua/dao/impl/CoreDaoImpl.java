/**
 * 描述: 
 * CoreDaoImpl.java
 * @author qy.zheng
 *  version 1.0
 */
package com.hua.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hua.dao.CoreDao;
import com.hua.handler.SingleResultHandler;
import com.hua.log.BaseLog;
import com.hua.util.DbUtilsHelper;
import com.hua.util.EmptyUtil;

/**
 * 描述:
 * 
 * @author qy.zheng CoreDaoImpl
 */
public class CoreDaoImpl<T> extends BaseLog implements CoreDao<T>
{

	/**
	 * 选择持有的方式，而不是继承的方式，可以有效将该类的 过时方法过滤掉，从而对其进行进一步的封装.
	 */
	/* dbutil QueryRunner 全局单例 */
	private static QueryRunner queryRunner = new QueryRunner();

	private Connection conn = null;

	private ResultSetHandler<T> beanHandler;

	private ResultSetHandler<List<T>> beanListHandler;

	/**
	 * 子类可以指定，但是需要声明为CoreDao<K, E>方式
	 */
	private ResultSetHandler<BigDecimal> singleResultHandler = new SingleResultHandler<BigDecimal>();

	/**
	 * 构造方法 描述:
	 * 
	 * @author qye.zheng
	 */
	public CoreDaoImpl(final Class<T> clazz)
	{
		beanHandler = new BeanHandler<T>(clazz);
		beanListHandler = new BeanListHandler<T>(clazz);
	}
	
	/**
	 * 
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 * @param beanHandler
	 * @param beanListHandler
	 */
	public CoreDaoImpl(final ResultSetHandler<T> beanHandler, final ResultSetHandler<List<T>> beanListHandler)
	{
		this.beanHandler = beanHandler;
		this.beanListHandler = beanListHandler;
	}

	/**
	 * 描述: 新增实体 - 无动态参数
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int insert(final String sql)
	{
		return executeUpdate(sql, null, null);
	}

	/**
	 * 描述: 新增实体
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int insert(final String sql, final Object[] params)
	{
		return executeUpdate(sql, params, null);
	}

	/**
	 * 描述: 新增实体
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int insert(final String sql, final List<Object> params)
	{
		return executeUpdate(sql, null, params);
	}

	/**
	 * 描述: 新增或更新 - 无动态参数
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @return
	 */
	@Override
	public int insertOrUpdate(final String sql)
	{
		return executeUpdate(sql, null, null);
	}

	/**
	 * 描述: 新增或更新
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int insertOrUpdate(final String sql, final Object[] params)
	{
		return executeUpdate(sql, params, null);
	}

	/**
	 * 描述: 新增或更新
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int insertOrUpdate(final String sql, final List<Object> params)
	{
		return executeUpdate(sql, null, params);
	}

	/**
	 * 描述: 删除 - 无动态参数
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param id
	 * @return
	 */
	@Override
	public int delete(final String sql)
	{
		return executeUpdate(sql, null, null);
	}

	/**
	 * 描述: 单一删除
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param id
	 * @return
	 */
	@Override
	public int delete(final String sql, final Object id)
	{
		return executeUpdate(sql, new Object[]
		{ id }, null);
	}

	/**
	 * 描述: 批量删除
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param ids
	 * @return
	 */
	@Override
	public int delete(final String sql, final Object[] ids)
	{
		return executeUpdate(sql, ids, null);
	}

	/**
	 * 描述: 批量删除
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param ids
	 * @return
	 */
	@Override
	public int delete(final String sql, final List<Object> ids)
	{
		return executeUpdate(sql, null, ids);
	}

	/**
	 * 描述: 更新实体 - 无动态参数
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int update(final String sql)
	{
		return executeUpdate(sql, null, null);
	}

	/**
	 * 描述: 更新实体
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int update(final String sql, final Object[] params)
	{
		return executeUpdate(sql, params, null);
	}

	/**
	 * 描述: 更新实体
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int update(final String sql, final List<Object> params)
	{
		return executeUpdate(sql, null, params);
	}

	/**
	 * 
	 * 描述: 批量dml操作，insert/delete/update
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return 返回每个场景下影响的行数
	 */
	@Override
	public int[] batch(final String sql, final Object[][] params)
	{
		return null;
	}

	/**
	 * 
	 * 描述: 批量插入数据 -
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param resultSetHandler
	 * @param params
	 * @return
	 */
	@Override
	public T insertBatch(final String sql,
			final ResultSetHandler<T> resultSetHandler, final Object[][] params)
	{
		return null;
	}

	/**
	 * 描述: 以map的形式返回bean - 无动态参数
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param id
	 * @return
	 */
	@Override
	public T get(final String sql)
	{
		return get(sql, null);
	}

	/**
	 * 描述: 以map的形式返回bean
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param id
	 * @return
	 */
	@Override
	public T get(final String sql, final Object id)
	{
		// 获取连接对象
		conn = DbUtilsHelper.getConnection();
		T t = null;
		try
		{
			log.info("get =====> begin query...");
			t = queryRunner.query(conn, sql, beanHandler, id);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return t;
	}

	/**
	 * 描述: 搜索多行记录 - 无动态参数
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public List<T> search(final String sql)
	{
		return search(sql, null, null);
	}

	/**
	 * 描述: 搜索多行记录
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public List<T> search(final String sql, final Object[] params)
	{
		return search(sql, params, null);
	}

	/**
	 * 描述: 搜索多行记录
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public List<T> search(final String sql, final List<Object> params)
	{
		return search(sql, null, params);
	}

	/**
	 * 描述: 统计记录数 - 无动态参数
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public Long count(final String sql)
	{
		return count(sql, null, null);
	}

	/**
	 * 描述: 统计记录数
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public Long count(final String sql, final Object[] params)
	{
		return count(sql, params, null);
	}

	/**
	 * 描述: 统计记录数
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public Long count(final String sql, final List<Object> params)
	{
		return count(sql, null, params);
	}

	/**
	 * 
	 * 描述: 修改动作(增删改) 统一入口
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param arrayParams
	 * @param listParams
	 * @return
	 */
	private int executeUpdate(final String sql, final Object[] arrayParams,
			final List<Object> listParams)
	{
		// 获取连接对象
		conn = DbUtilsHelper.getConnection();

		// 影响行数
		int affect = 0;
		try
		{
			if (!EmptyUtil.isEmpty(arrayParams))
			{
				// 数组参数
				affect = queryRunner.update(conn, sql, arrayParams);
			} else if (!EmptyUtil.isEmpty(listParams))
			{
				// 集合参数
				affect = queryRunner.update(conn, sql, listParams.toArray());
			} else
			{
				// 两种参数均为空
				affect = queryRunner.update(conn, sql);
			}

			log.info("executeUpdate =====> 提交事务");
			// 提交事务
			DbUtilsHelper.commit();

			return affect;
		} catch (SQLException e)
		{
			log.error("executeUpdate =====> 回滚事务", e);

			// 回滚事务
			DbUtilsHelper.rollback();
		} finally
		{
			DbUtilsHelper.close(conn);
		}

		return affect;
	}

	/**
	 * 
	 * 描述: 搜索统一入口
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param arrayParams
	 * @param listParams
	 * @return
	 */
	private List<T> search(final String sql, final Object[] arrayParams,
			final List<Object> listParams)
	{
		// 获取连接对象
		conn = DbUtilsHelper.getConnection();
		List<T> resultList = null;
		try
		{
			if (!EmptyUtil.isEmpty(arrayParams))
			{
				// 数组参数
				resultList = queryRunner.query(conn, sql, beanListHandler,
						arrayParams);
			} else if (!EmptyUtil.isEmpty(listParams))
			{
				// 集合参数
				resultList = queryRunner.query(conn, sql, beanListHandler,
						listParams.toArray());
			} else
			{
				// 两种参数均为空
				resultList = queryRunner.query(conn, sql, beanListHandler);
			}

			return resultList;
		} catch (SQLException e)
		{
			log.error("search =====> ", e);
		} finally
		{
			DbUtilsHelper.close(conn);
		}

		return resultList;
	}

	/**
	 * 
	 * 描述: 统计 统一入口
	 * 
	 * @author qye.zheng
	 * @param sql
	 * @param arrayParams
	 * @param listParams
	 * @return
	 */
	private Long count(final String sql, final Object[] arrayParams,
			final List<Object> listParams)
	{
		// 大数字对象
		BigDecimal bigDecimal = null;
		// 获取连接对象
		conn = DbUtilsHelper.getConnection();
		Long result = null;
		try
		{
			if (!EmptyUtil.isEmpty(arrayParams))
			{
				// 数组参数
				bigDecimal = queryRunner.query(conn, sql, singleResultHandler,
						arrayParams);
			} else if (!EmptyUtil.isEmpty(listParams))
			{
				// 集合参数
				bigDecimal = queryRunner.query(conn, sql, singleResultHandler,listParams.toArray());
			} else
			{
				// 两种参数均为空
				bigDecimal = queryRunner.query(conn, sql, singleResultHandler);
			}
			if (null != bigDecimal)
			{
				result = bigDecimal.longValue();
			}
		} catch (SQLException e)
		{
			log.error("count =====> ", e);
		} finally
		{
			DbUtilsHelper.close(conn);
		}

		return result;
	}

}

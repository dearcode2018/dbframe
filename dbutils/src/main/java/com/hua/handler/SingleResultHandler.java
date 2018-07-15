/**
 * SingleResultHandler.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

import com.hua.constant.JdbcConstant;

/**
 * SingleResultHandler
 * 描述: 统计处理器
 * 查询返回单个结果值
 * @author  qye.zheng
 */
public final class SingleResultHandler<T> implements ResultSetHandler<T>
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public SingleResultHandler()
	{
	}

	/**
	 * 描述: 
	 * @author  qye.zheng
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T handle(final ResultSet rs) throws SQLException
	{
		T t = null;
		if (rs.next())
		{
			t = (T) rs.getObject(JdbcConstant.FIRST_COLUMN_INDEX);
		}
		
		return t;
	}

}

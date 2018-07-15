/**
 * DbUtilsHelper.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import org.apache.commons.dbutils.DbUtils;

import com.hua.bean.jdbc.ConnectionParam;

/**
 * DbUtilsHelper
 * 描述: 
 * @author  qye.zheng
 */
public final class DbUtilsHelper
{

	/* 连接参数 */
	private static final ConnectionParam param;
	
	/* 全局共享单一连接 */
	private static Connection conn;
	
	static
	{
		// 获取 [连接参数] 对象
		param = ConnectionParam.getInstance();
		// 加载 jdbc 驱动
		DbUtils.loadDriver(param.getDriver());
		// 获取连接
		try
		{
			conn = DriverManager.getConnection(param.getUrl(), 
					param.getUsername(), param.getPassword());
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private DbUtilsHelper()
	{
	}

	/**
	 * 
	 * 描述: 获取连接 
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		
		return conn;
	}
	
	/**
	 * 
	 * 描述: 提交事务
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static boolean commit() 
	{
		try
		{
			if (null != conn)
			{
				// 提交事务
				conn.commit();
			}
			
			return true;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: 提交事务并且关闭连接 
	 * @author  qye.zheng
	 * @return
	 */
	public static boolean commitAndClose()
	{
		boolean flag = false;
		try
		{
			if (null != conn)
			{
				// 提交事务
				conn.commit();
			}
			conn.close();
			flag = true;
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally {
		}
		
		return flag;
	}
	
	/**
	 * 
	 * 描述: 回滚事务
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static boolean rollback() 
	{
		return rollback(null);
	}
	
	/**
	 * 
	 * 描述: 回滚事务
	 * @author qye.zheng
	 * 
	 * @param savepoint 保存点
	 * @return
	 */
	public static boolean rollback(final Savepoint savepoint)
	{
		try
		{
			if (null != conn)
			{
				// 回滚事务
				if (null == savepoint) 
				{
					// 保存点 为空
					conn.rollback();
				} else 
				{
					conn.rollback(savepoint);
				}
			}
			
			return true;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 
	 * 描述: 关闭连接
	 * @author  qye.zheng
	 * @param conn
	 */
	public static void close(final Connection conn)
	{
		/*
		 借用DbUtils quietly 方式可以埋藏异常，以平静的方式
		 操作.
		 */
		DbUtils.closeQuietly(conn);
	}
	
	/**
	 * 
	 * 描述: 关闭声明
	 * @author  qye.zheng
	 * @param conn
	 */
	public static void close(final Statement statement)
	{
		/*
		 借用DbUtils quietly 方式可以埋藏异常，以平静的方式
		 操作.
		 */
		DbUtils.closeQuietly(statement);
	}
	
	/**
	 * 
	 * 描述: 关闭结果集
	 * @author  qye.zheng
	 * @param conn
	 */
	public static void close(final ResultSet resultSet)
	{
		/*
		 借用DbUtils quietly 方式可以埋藏异常，以平静的方式
		 操作.
		 */
		DbUtils.closeQuietly(resultSet);
	}
	
	/**
	 * 
	 * 描述: 关闭连接、声明、结果集
	 * @author  qye.zheng
	 * @param conn
	 * @param statement
	 * @param resultSet
	 */
	public static void close(final Connection conn, final Statement statement, final ResultSet resultSet)
	{
		DbUtils.closeQuietly(conn, statement, resultSet);
	}
}

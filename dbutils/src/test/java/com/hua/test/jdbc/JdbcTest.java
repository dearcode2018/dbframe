/**
 * 描述: 
 * JdbcTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import com.hua.test.BaseTest;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * JdbcTest
 */
public final class JdbcTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSqlHelper() {
		PreparedStatement ps = null;
		//ps.setDate(parameterIndex, x)
		ResultSet rs = null;
		// ResultSet列标从1开始
		//rs.getLong();
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSqlUtil() {
		String str = "a_b_c_d_%_0%";
		//System.out.println(SqlUtil.likeQuery(str));
	}
}

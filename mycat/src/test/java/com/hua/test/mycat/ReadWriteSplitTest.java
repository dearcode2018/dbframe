/**
 * 描述: 
 * ReadWriteSplitTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.mycat;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.ext.Gender;
import com.hua.orm.entity.o2o.Person;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;
import com.hua.util.JdbcUtil;
import com.hua.util.SqlUtil;


/**
 * 描述: 读写分离
 * 
 * @author qye.zheng
 * ReadWriteSplitTest
 */
public final class ReadWriteSplitTest extends BaseTest {

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testReadWriteSplit() {
		try {
			/**
			 新增或更新的sql语句应该进行动态拼接，若某个字段为空，
			 则不能加入sql语句.
			 例如 insert into  tb_name ()
			 values () 这2部分应该是进行拼接而得到的.
			 */
			person = new Person();
			person.setName("王东平dfd");
			person.setPhotoUrl("http://www.w3c.org/file/wangdongping.png");
			person.setGender(Gender.MALE);
			person.setNation("回族");
			person.setBirthday(DateTimeUtil.parseDate("1971-05-14"));
			person.setAddress("广西省北海市江北区沿江东路33号");
			
			sql = "INSERT INTO person (id, name, photoUrl, gender, nation, birthday, address, sharding_id) VALUES (?,?,?,?,?,?,?,?)";
			
			arrayParams = new Object[8];
			int i = 0;
			arrayParams[i++] = 23;
			arrayParams[i++] = person.getName();
			arrayParams[i++] = person.getPhotoUrl();
			arrayParams[i++] = person.getGender().getValue();
			arrayParams[i++] = person.getNation();
			arrayParams[i++] = person.getBirthday();
			arrayParams[i++] = person.getAddress();
			arrayParams[i++] = 10010;
			final Connection conn = JdbcUtil.getConnection();
			/*
			 * 事务隔离级别
			 * TransactionIsolation
			 *  int TRANSACTION_NONE = 0; 无事务
			 *  int TRANSACTION_READ_UNCOMMITTED = 1; 不可
			 *   int TRANSACTION_READ_COMMITTED   = 2;
			 *   int TRANSACTION_REPEATABLE_READ  = 4;
			 *   int TRANSACTION_SERIALIZABLE     = 8;
			 */
			// 设置事务隔离级别 无事务
			//conn.setTransactionIsolation(Connection.TRANSACTION_NONE);
			
			// 获取声明对象
			final PreparedStatement ps = JdbcUtil.getPreparedStatement(conn, sql);
			// 数组参数
			SqlUtil.setValue(ps, arrayParams);
			// 影响行数
			int affect = 0;
			affect = ps.executeUpdate();
			
			// 提交事务
			JdbcUtil.commit(conn);
			
			// 回滚事务
			//JdbcUtil.rollback(conn);
			
			// 关闭 db 资源
			JdbcUtil.close(null, ps);
			
		} catch (Exception e) {
			log.error("testReadWriteSplit =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}

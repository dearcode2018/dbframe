/**
 * 描述: 
 * ConnectionTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.jdbc;

//静态导入
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.hua.test.BaseTest;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * ConnectionTest
 */
//@DisplayName("测试类名称")
//@Tag("测试类标签")
//@Tags({@Tag("测试类标签1"), @Tag("测试类标签2")})
public final class ConnectionTest extends BaseTest {
	
	// 连接路径含有数据库类型信息
	private String url = "jdbc:mysql://127.0.01:3306/db_01?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
	private String username = "root";
	private String password = "root";
	private String driverClassName = "com.mysql.cj.jdbc.Driver";
	
	/**
	 * 驱动可以通过注册
	 * 1) Class.forName(String) 
	 * 2) DriverManager.registerDriver(java.sql.Driver) 构造一个驱动对象传入
	 * 3) 环境变量: jdbc.drivers <- System.setProperty()
	 * 如果classpath下只有一个驱动，则无需显示指定，默认加载这个，如果存在多个，DriverManager会按顺序自动逐个尝试，成功则返回连接
	 * 因此，没有用到驱动连接，不要引入依赖. 通常，有些数据源连接要提供驱动信息，相当于数据库方言了.
	 * 
	 *  逐个尝试过程: 
		DriverManager.getConnection("jdbc:mysql://127.0.01:3306/db_01?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai")
		    trying com.microsoft.sqlserver.jdbc.SQLServerDriver
		    trying com.mysql.cj.jdbc.Driver
		getConnection returning com.mysql.cj.jdbc.Driver
	 */
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testConnection() {
		try {
			// 加载 jdbc 驱动
			Class.forName(driverClassName);
			int loginTimeoutSecond = 5;
			/*
			 * 登录超时时间 (全局通用)
			 * 可以多次设置，每次构造连接的时候都可以设置
			 * 多个线程会共用此变量
			 */
			DriverManager.setLoginTimeout(loginTimeoutSecond);
			// 输出异常日志到控制台，便于查看
			DriverManager.setLogWriter(new PrintWriter(System.out));
			/*
			 * 注册驱动，可以注册多种类型的驱动，每次构造连接的时候，注册一次 
			 * 次方法是同步方法，避免多线程对该变量的干扰
			 */
			//DriverManager.registerDriver(null);
			Connection connection = DriverManager.getConnection(url, username, password);
			assertTrue(null != connection);
			// com.mysql.cj.jdbc.ConnectionImpl@3f2ef586
			System.out.println(connection);
		} catch (Exception e) {
			log.error("testConnection =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 异常的场景
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testConnectionEffective() {
		try {
			int loginTimeoutSecond = 10;
			/*
			 * 登录超时时间 (全局通用)
			 * 可以多次设置，每次构造连接的时候都可以设置
			 * 多个线程会共用此变量
			 */
			DriverManager.setLoginTimeout(loginTimeoutSecond);
			/*
			 * 卸载指定驱动，在某种情况可能会提高效率
			 * 这样在创建连接的时候，把不是当前希望的数据库类型给排除掉，从而提高底层创建连接的效率
			 * 
			 */
			Enumeration<Driver> drivers = DriverManager.getDrivers();
			while (drivers.hasMoreElements()) {
				final Driver driver = drivers.nextElement();
				// 撤销掉不是当前希望的连接对象，等到需要的时候再注册
				if (!driver.getClass().getName().equals(driverClassName)) {
				// if (!(driver instanceof com.mysql.cj.jdbc.Driver))
				//if (driver.getClass() != Class.forName(driverClassName)) {
					DriverManager.deregisterDriver(driver);
				}
			}
			Connection connection = DriverManager.getConnection(url, username, password);
			assertTrue(null != connection);
			// com.mysql.cj.jdbc.ConnectionImpl@xx
			System.out.println(connection);
			
		} catch (Exception e) {
			log.error("testConnectionEffective =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testSystemProperty() {
		try {
			// 设置指定的驱动
			System.setProperty("jdbc.drivers", driverClassName);
			int loginTimeoutSecond = 1;
			/*
			 * 登录超时时间 (全局通用)
			 * 可以多次设置，每次构造连接的时候都可以设置
			 * 多个线程会共用此变量
			 */
			DriverManager.setLoginTimeout(loginTimeoutSecond);
			/*
			 * 注册驱动，可以注册多种类型的驱动，每次构造连接的时候，注册一次 
			 * 次方法是同步方法，避免多线程对该变量的干扰
			 */
			Connection connection = DriverManager.getConnection(url, username, password);
			assertTrue(null != connection);
			// com.mysql.cj.jdbc.ConnectionImpl@xx
			System.out.println(connection);
			
		} catch (Exception e) {
			log.error("testSystemProperty =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testClassForName() {
		try {
			// 加载 jdbc 驱动
			Class.forName(driverClassName);
			int loginTimeoutSecond = 1;
			/*
			 * 登录超时时间 (全局通用)
			 * 可以多次设置，每次构造连接的时候都可以设置
			 * 多个线程会共用此变量
			 */
			DriverManager.setLoginTimeout(loginTimeoutSecond);
			/*
			 * 注册驱动，可以注册多种类型的驱动，每次构造连接的时候，注册一次 
			 * 次方法是同步方法，避免多线程对该变量的干扰
			 */
			Connection connection = DriverManager.getConnection(url, username, password);
			assertTrue(null != connection);
			// com.mysql.cj.jdbc.ConnectionImpl@xx
			System.out.println(connection);
			
		} catch (Exception e) {
			log.error("testClassForName =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testRegisterDriver() {
		try {
			/*
			 * 最大登录时间，0-无限制
			 * 在数据库无法连接的情况下，会有快速失败的流程，该变量只是设置能连接的情况下的最大登录时间
			 */
			int loginTimeoutSecond = 10;
			/*
			 * 登录超时时间 (全局通用)
			 * 可以多次设置，每次构造连接的时候都可以设置
			 * 多个线程会共用此变量
			 */
			DriverManager.setLoginTimeout(loginTimeoutSecond);
			/*
			 * 注册驱动，可以注册多种类型的驱动，注册多个驱动，连接的时候，会自动尝试合适的驱动
			 * 此方法是同步方法
			 */
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			Connection connection = DriverManager.getConnection(url, username, password);
			assertTrue(null != connection);
			// com.mysql.cj.jdbc.ConnectionImpl@xx
			System.out.println(connection);
			
		} catch (Exception e) {
			log.error("testRegisterDriver =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 异常的场景
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testException() {
		try {
			// 加载 jdbc 驱动
			//Class.forName(driverClassName);
			int loginTimeoutSecond = 10;
			/*
			 * 登录超时时间 (全局通用)
			 * 可以多次设置，每次构造连接的时候都可以设置
			 * 多个线程会共用此变量
			 */
			DriverManager.setLoginTimeout(loginTimeoutSecond);
			// 输出异常日志到控制台，便于查看
			DriverManager.setLogWriter(new PrintWriter(System.out));
			/*
			 * 注册驱动，可以注册多种类型的驱动，每次构造连接的时候，注册一次 
			 * 次方法是同步方法，避免多线程对该变量的干扰
			 */
			Connection connection = DriverManager.getConnection(url, username, password);
			assertTrue(null != connection);
			// com.mysql.cj.jdbc.ConnectionImpl@xx
			System.out.println(connection);
			
		} catch (Exception e) {
			log.error("testException =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testGetDrivers() {
		try {
			/*
			 * 获取classpath下的所有SPI
			 * DriverManager在实例化的时候，已经执行了静态代码块
			 * 获取了classpath下的所有 java.sql.Driver 的SPI
			 */
			Enumeration<Driver> drivers = DriverManager.getDrivers();
			while (drivers.hasMoreElements()) {
				System.out.println(drivers.nextElement());
			}
			
		} catch (Exception e) {
			log.error("testGetDrivers =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
	@Test
	public void testGetDriver() {
		try {
			// 根据连接路径，获取驱动，路径含有数据库类型信息
			Driver driver = DriverManager.getDriver(url);
			assertTrue(null != driver);
			System.out.println(driver);
		} catch (Exception e) {
			log.error("testGetDriver =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	//@DisplayName("test")
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
	@DisplayName("testTemp")
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
	@DisplayName("testCommon")
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
	@DisplayName("testSimple")
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
	@DisplayName("testBase")
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]开始之前运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("beforeMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@BeforeEach
	public void beforeMethod() {
		System.out.println("beforeMethod()");
		// 输出异常日志到控制台，便于查看
		DriverManager.setLogWriter(new PrintWriter(System.out));
	}
	
	/**
	 * 
	 * 描述: [每个测试-方法]结束之后运行
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("afterMethod")
	@Tag(" [每个测试-方法]结束之后运行")
	@AfterEach
	public void afterMethod() {
		System.out.println("afterMethod()");
	}
	
	/**
	 * 
	 * 描述: 测试忽略的方法
	 * @author qye.zheng
	 * 
	 */
	@Disabled
	@DisplayName("ignoreMethod")
	@Test
	public void ignoreMethod() {
		System.out.println("ignoreMethod()");
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@DisplayName("noUse")
	@Disabled("解决ide静态导入消除问题 ")
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
		assertArrayEquals(expecteds, actuals, message);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(true, message);
		assertTrue(true, message);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(expecteds, actuals, message);
		assertNotSame(expecteds, actuals, message);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(actuals, message);
		assertNotNull(actuals, message);
		
		fail();
		fail("Not yet implemented");
		
		dynamicTest(null, null);
		
		assumeFalse(false);
		assumeTrue(true);
		assumingThat(true, null);
	}

}

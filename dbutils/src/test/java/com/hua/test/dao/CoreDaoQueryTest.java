/**
 * 描述: 
 * CoreDaoQueryTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.dao;

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

import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.junit.Ignore;
import org.junit.Test;

import com.hua.dao.o2o.PersonDao;
import com.hua.dao.o2o.impl.PersonDaoImpl;
import com.hua.handler.PersonBeanHandler;
import com.hua.handler.PersonBeanListHandler;
import com.hua.orm.entity.o2o.Person;
import com.hua.test.BaseTest;
import com.hua.util.SqlUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * CoreDaoQueryTest
 */
public final class CoreDaoQueryTest extends BaseTest {

	private ResultSetHandler<Person> beanHandler = new PersonBeanHandler();
	
	private ResultSetHandler<List<Person>> beanListHandler = new  PersonBeanListHandler();
	
	private PersonDao personDao = new PersonDaoImpl(beanHandler, beanListHandler);
	
	private Person person = new Person();
	
	private List<Person> persons;
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testQuery() {
		try {
			
			
		} catch (Exception e) {
			log.error("testQuery =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSelect() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSelect =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGet() {
		try {
			id = "36";
			person.setId(id);
			sql = "SELECT a.oid , a.name as name, a.gender, a.photoUrl, a.nation, a.birthday, a.address FROM person a WHERE a.oid = ?";
			
			person = personDao.get(sql, person.getId());
		
			//log.info("testGet =====> " + person);
			
			System.out.println(person.toString());
			
		} catch (Exception e) {
			log.error("testGet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCountArrayParam() {
		try {
			sql = "SELECT COUNT(*) FROM person a WHERE a.nation IN(?,?)";
			arrayParams = new Object[] {"汉族", "回族"};
			
			count = personDao.count(sql, arrayParams);
			
			log.info("testCountArrayParam =====> count = " + count);
			
		} catch (Exception e) {
			log.error("testCountArrayParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCountListParam() {
		try {
			sql = "SELECT COUNT(*) FROM person a WHERE a.nation IN(?,?)";
			arrayParams = new Object[] {"汉族", "回族"};
			listParams.add("汉族");
			listParams.add("回族");
			count = personDao.count(sql, listParams);
			
			log.info("testCountListParam =====> count = " + count);
			
		} catch (Exception e) {
			log.error("testCountListParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSearchArrayParam() {
		try {
			arrayParams = new Object[] {"汉族", "Female"};
			sql = "SELECT a.oid, a.name, a.photoUrl, a.gender, a.nation, a.birthday, a.address FROM person a WHERE a.nation = ? and a.gender = ?";
			
			persons = personDao.search(sql, arrayParams);
			
			log.info("testSearchArrayParam =====> " + persons.size());
			
		} catch (Exception e) {
			log.error("testSearchArrayParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSearchListParam() {
		try {
			listParams.add("汉族");
			listParams.add("Female");
			
			sql = "SELECT a.oid, a.name, a.photoUrl, a.gender, a.nation, a.birthday, a.address FROM person a WHERE a.nation = ? and a.gender = ?";
			
			persons = personDao.search(sql, listParams);
			log.info("testSearchListParam =====> " + persons.size());
			
		} catch (Exception e) {
			log.error("testSearchListParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLike() {
		try {
			
			listParams.add(SqlUtil.likeQuery("广东省"));
			
			sql = "SELECT a.oid, a.name, a.photoUrl, a.gender, a.nation, a.birthday, a.address FROM person a WHERE a.address LIKE ?";
			
			persons = personDao.search(sql, listParams);
			
			log.info("testLike =====> " + persons.size());
			
		} catch (Exception e) {
			log.error("testLike =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testAggregate() {
		try {
			
			
		} catch (Exception e) {
			log.error("testAggregate =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testWhere() {
		try {
			
			
		} catch (Exception e) {
			log.error("testWhere =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGroupBy() {
		try {
			
			
		} catch (Exception e) {
			log.error("testGroupBy =====> ", e);
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

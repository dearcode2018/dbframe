/**
 * 描述: 
 * SplitUtilTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.split;

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

import org.junit.Ignore;
import org.junit.Test;

import com.hua.test.BaseTest;
import com.hua.util.SplitUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SplitUtilTest
 */
public final class SplitUtilTest extends BaseTest {

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetName() {
		try {
			// 连续数字的长度，从0或从1开始
			Integer length = null;
			// 参考值
			Long referenceValue = null;
			// 是否从1开始，一般默认是从0开始
			boolean startOne = true;
			String result = null;
			String namePrefix = null;
			boolean addPrefixZero = false;
			
			referenceValue = 2355L;
			namePrefix = "T_MEMBER_LABEL_";
			length = 50;
			addPrefixZero = true;
			
			result = SplitUtil.getName(namePrefix, addPrefixZero, length, startOne, referenceValue);
			log.info("testGetName =====> result = " + result);
		} catch (Exception e) {
			log.error("testGetName =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetSuffixIndex() {
		try {
			// 连续数字的长度，从0或从1开始
			Integer length = null;
			// 参考值
			Long referenceValue = null;
			// 是否从1开始，一般默认是从0开始
			boolean startOne = true;
			String result = null;
			boolean addPrefixZero = false;
			
			startOne = true;
			// 1 - 50
			length = 50;
			referenceValue = 23450L;
			addPrefixZero = true;
			result = SplitUtil.getSuffixIndex(length, addPrefixZero, startOne, referenceValue);
			
			log.info("testGetSuffixIndex =====> result = " + result);
		} catch (Exception e) {
			log.error("testGetSuffixIndex =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSuffixIndex() {
		try {
			// 连续数字的长度，从0或从1开始
			Integer length = null;
			// 目标值
			Long value = null;
			// 是否从1开始，一般默认是从0开始
			boolean startOne = true;
			
			startOne = true;
			// 1 - 50
			length = 50;
			value = 23450L;
			
			startOne = false;
			// 0 - 49
			length = 50;
			value = 234449L;
			
			Integer bitCount = String.valueOf(length).length();
			String splitNumStr = "1";
			while (splitNumStr.length() <= bitCount )
			{
				splitNumStr = splitNumStr + "0";
			}
			Integer splitNum = Integer.valueOf(splitNumStr);
			Integer result = null;
			result = Long.valueOf(value % splitNum % length).intValue();
			if (startOne)
			{ // 从 1 开始 需要自增1，默认是从0开始
				result++;
			}
			
			log.info("testSuffixIndex =====> result = " + result);
			
		} catch (Exception e) {
			log.error("testSuffixIndex =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetIndex() {
		try {
			Integer cardId = null;
			// 分库索引
			Integer index = null;
			
			cardId = 123;
			
			System.out.println(SplitUtil.getSuffixIndex(3, true, true, cardId));
				
			
		} catch (Exception e) {
			log.error("testGetIndex =====> ", e);
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

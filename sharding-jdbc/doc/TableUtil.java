/**
 * 描述: 
 * TableUtil.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.util;

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


/**
 * 描述: 
 * 
 * @author qye.zheng
 * TableUtil
 */
public final class TableUtil extends BaseTest {

	/** 会员标签表前缀 */
	private static final String TB_PREFFIX = "t_member_label_";
	
	/** 会员标签表X */
	private static final String TB_LABEL_X = "t_member_label_x";
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGenerateTableT_MEMBER_LABEL() {
		try {
			int max = 50;
			String filePath = ClassPathUtil.getClassSubpath("/file/T_MEMBER_LABEL.sql");
			String ddl = FileUtil.getString(filePath);
			String result = null;
			for (int i = 1; i <= max; i++)
			{
				result = ddl.replace(":prefix", getTwoBitNumber(i));
				System.out.println(result);
			}
			
		} catch (Exception e) {
			log.error("testGenerateTableT_MEMBER_LABEL =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGenerateDataOfMemberLabel() {
		try {
			/*
			 * sql 实例
			 * insert into t_member_label_33 select * from t_member_label_17;
			 * 
			 * update t_member_label_33 set SELLER_ID = 245632;
			 */
			Integer sellerId = null;
			
			 sellerId = 245632;
			 sellerId = 142838;
			 sellerId = 142838;
			 
			String tableName = getTable(sellerId);
			log.info("testGenerateDataOfMemberLabel =====> tableName = " + tableName);
			
			String sql1 = "insert into " + tableName + " select * from t_member_label_17;";
			String sql2 = "update " + tableName + " set SELLER_ID = " + sellerId + ";";
			
			System.out.println(sql1);
			System.out.println(sql2);
			
		} catch (Exception e) {
			log.error("testGenerateDataOfMemberLabel =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGetTableName() {
		try {
			/*
			 * 
			 * insert into t_member_label_33 select * from t_member_label_17;
			 * 
			 * update t_member_label_33 set SELLER_ID = 245632;
			 */
			Integer sellerId = 245632;
			String tableName = getTable(sellerId);
			log.info("testGetTableName =====> tableName = " + tableName);
			
			String sql1 = "insert into " + tableName + " select * from t_member_label_17;";
			String sql2 = "update " + tableName + " set SELLER_ID = " + sellerId + ";";
			
			System.out.println(sql1);
			System.out.println(sql2);
			
		} catch (Exception e) {
			log.error("testGetTableName =====> ", e);
		}
	}
	
	/**
	 * 
	 * @description 获取会员标签表名
	 * @param sellerId 销售人员id
	 * @return
	 * @author qianye.zheng
	 */
	private static final String getTable(final Integer sellerId)
	{
		if (null != sellerId && sellerId > 0)
		{
			final String suffix = getTwoBitNumber((sellerId % 100 % 50) + 1);
			
			return TB_PREFFIX + suffix;
		} else
		{
			return TB_LABEL_X;
		}
	}	
	
	/**
	 * 
	 * @description 
	 * @param num
	 * @return
	 * @author qianye.zheng
	 */
	private static final String getTwoBitNumber(final Integer num)
	{
		String result = String.valueOf(num);
		if (result.length() < 2)
		{
			result = "0" + result;
		}
		
		return result;
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGenerateTableT_MEMBER_LABEL2() {
		try {
			int i = 1;
			String ddl = "\n/* 若存在先删除 */" 
			+ "\nDROP TABLE IF EXISTS `T_MEMBER_LABEL_0`" + i
			+ "\n/* 创建 会员标签表 */"
			+ "\nCREATE TABLE `T_MEMBER_LABEL_0" + i + "` ("
	+ "\n`MEB_ID` int NOT NULL COMMENT '会员id',"
	+ "\n`SELLER_ID` int COMMENT '销售人员id',"	
	+ "\n`NAME` varchar(128) comment '会员姓名',"
	+ "\n`PHONE` varchar(16) comment '会员手机号',"
	+ "\n`IMAGE_ID` varchar(32) COMMENT '会员头像id',"
	+ "\n`CREATE_DT` timestamp null COMMENT '会员创建时间',"			
	+ "\n`FIRST_CONSUME_YEAR` char(5) COMMENT '首消年份 (其他)',"		
	+ "\n`REG_UNCHKIN_INTERVAL` int COMMENT '会员注册至今没首住的时间间隔（天）',"
	+ "\n`AVG_CHKIN_INTERVAL` int COMMENT '平均入住间隔（天）',"
	+ "\n`FIRST_CHKIN_AGO` int COMMENT '首次入住距今时间（天）',"
	+ "\n`CHKIN_CNT` int COMMENT '入住次数',"
	+ "\n`POTENTIAL_DRAIN_MEB` bit COMMENT '潜在流失客户',"
	+ "\n`POTENTIAL_MEB` bit COMMENT '潜力客户',"
	+ "\n`SILENT_MEB` bit COMMENT '沉默客户(过去一年没有入住的客户)',"
	+ "\n`SILENT_HF_MEB` bit COMMENT '沉默高频客户(一年内多次开房，近3个月无开房)',"
	+ "\n`HIGH_VALUE_MEB` bit COMMENT '高价值客户(消费金额大、频次高)',"
	+ "\n`HIGH_END_MEB` bit COMMENT '高端客户',"
	+ "\n`PROM_SENSITIVE_MEB` bit COMMENT '促销敏感客户',"
	+ "\n`VACATION_PUPIL_MEB` bit COMMENT '定制客户类-寒暑假学生客',"
	+ "\n`PREFER_PREPAY_MEB` bit COMMENT '偏好预付客户',"
	+ "\nPRIMARY KEY (`MEB_ID`)"
+ "\n) ENGINE=InnoDB COMMENT '会员标签' DEFAULT CHARSET=UTF8;";
			
			
			System.out.println(ddl);
			
		} catch (Exception e) {
			log.error("testGenerateTableT_MEMBER_LABEL2 =====> ", e);
		}
	}
	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void generateSqlCount() {
		try {
			/*
			select sum(cnt) from (
				select count(*) as cnt from t_member_label_01 
				union ALL
				select count(*) as cnt from t_member_label_02 
				union ALL
				select count(*) as cnt from t_member_label_03 
				union ALL
				select count(*) as cnt from t_member_label_04 
				union ALL
				select count(*) as cnt from t_member_label_05 
				union ALL
				select count(*) as cnt from t_member_label_06 
				union ALL
				select count(*) as cnt from t_member_label_07 
				union ALL
				select count(*) as cnt from t_member_label_08 
				union ALL
				select count(*) as cnt from t_member_label_09 
				union ALL
				select count(*) as cnt from t_member_label_10 
				union ALL
				select count(*) as cnt from t_member_label_11 
				union ALL
				select count(*) as cnt from t_member_label_12 
				union ALL
				select count(*) as cnt from t_member_label_13 
				union ALL
				select count(*) as cnt from t_member_label_14 
				union ALL
				select count(*) as cnt from t_member_label_15 
				union ALL
				select count(*) as cnt from t_member_label_16 
				union ALL
				select count(*) as cnt from t_member_label_17 
				union ALL
				select count(*) as cnt from t_member_label_18 
				union ALL
				select count(*) as cnt from t_member_label_19 
				union ALL
				select count(*) as cnt from t_member_label_20 
				union ALL
				select count(*) as cnt from t_member_label_21 
				union ALL
				select count(*) as cnt from t_member_label_22 
				union ALL
				select count(*) as cnt from t_member_label_23 
				union ALL
				select count(*) as cnt from t_member_label_24 
				union ALL
				select count(*) as cnt from t_member_label_25 
				union ALL
				select count(*) as cnt from t_member_label_26 
				union ALL
				select count(*) as cnt from t_member_label_27 
				union ALL
				select count(*) as cnt from t_member_label_28 
				union ALL
				select count(*) as cnt from t_member_label_29 
				union ALL
				select count(*) as cnt from t_member_label_30 
				union ALL
				select count(*) as cnt from t_member_label_31 
				union ALL
				select count(*) as cnt from t_member_label_32 
				union ALL
				select count(*) as cnt from t_member_label_33 
				union ALL
				select count(*) as cnt from t_member_label_34 
				union ALL
				select count(*) as cnt from t_member_label_35 
				union ALL
				select count(*) as cnt from t_member_label_36 
				union ALL
				select count(*) as cnt from t_member_label_37 
				union ALL
				select count(*) as cnt from t_member_label_38 
				union ALL
				select count(*) as cnt from t_member_label_39 
				union ALL
				select count(*) as cnt from t_member_label_40 
				union ALL
				select count(*) as cnt from t_member_label_41 
				union ALL
				select count(*) as cnt from t_member_label_42 
				union ALL
				select count(*) as cnt from t_member_label_43 
				union ALL
				select count(*) as cnt from t_member_label_44 
				union ALL
				select count(*) as cnt from t_member_label_45 
				union ALL
				select count(*) as cnt from t_member_label_46 
				union ALL
				select count(*) as cnt from t_member_label_47 
				union ALL
				select count(*) as cnt from t_member_label_48 
				union ALL
				select count(*) as cnt from t_member_label_49 
				union ALL
				select count(*) as cnt from t_member_label_50 
				union ALL
				select count(*) as cnt from t_member_label_x) t;
			 */
			StringBuilder builder = StringUtil.getBuilder();
			builder.append("select sum(cnt) from (\n");
			for (int i = 1; i <= 50; i++)
			{
				builder.append("select count(*) as cnt from " + TB_PREFFIX + getTwoBitNumber(i) + " \nunion ALL\n");
			}
			builder.append("select count(*) as cnt from " + TB_LABEL_X  + ") t;");
			System.out.println(builder.toString());
		} catch (Exception e) {
			log.error("generateSqlCount =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void generateSqlDdl1() {
		try {
			/*
			 * alter table t_member_label_01 modify PHONE varchar(32) comment '会员手机号';
			 */
			StringBuilder builder = StringUtil.getBuilder();
			for (int i = 1; i <= 50; i++)
			{
				builder.append("alter table " + TB_PREFFIX + getTwoBitNumber(i) + " modify PHONE varchar(32) comment '会员手机号';\n");
			}
			builder.append("alter table " + TB_LABEL_X + " modify PHONE varchar(32) comment '会员手机号';\n");
			System.out.println(builder.toString());
		} catch (Exception e) {
			log.error("generateSqlDdl1 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Deprecated
	@Test
	public void generateSqlCount_() {
		try {
			
			for (int i = 1; i <= 50; i++)
			{
				System.out.println("select count(*) from " + TB_PREFFIX + i + ";");
			}
			System.out.println("select count(*) from " + TB_LABEL_X + ";");
			
		} catch (Exception e) {
			log.error("generateSqlCount_ =====> ", e);
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

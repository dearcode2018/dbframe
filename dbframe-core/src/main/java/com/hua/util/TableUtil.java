/**
 * TableUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

import java.util.Date;

/**
 * TableUtil
 * 描述: 数据表工具类
 * @author  qye.zheng
 */
public final class TableUtil
{
	
	private static final String INPUT_PATH = ProjectUtil.getAbsolutePath("/doc/sql-template.sql", true);
	
	private static final String OUTPUT_PATH = ProjectUtil.getAbsolutePath("/doc/sql-output.sql", true);
	
	private static final String REPLACE_SYMBOL = ":suffix";
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private TableUtil()
	{
	}
	
	/**
	 * 输出ddl语句和查询语句
	 * 
	 * 表名: 固定前缀 + 数字后缀 (从0或1开始，用0填补空缺位)
	 * 
	 * 
	 *  /doc/sql-template.sql
	 * sql 语句模板，可以放置 ddl 或 dql 等语句，需要用数字索引替换的用 冒号+suffix 代替
	 * 
	 */
	
	/**
	 * 把模板语句写入 /doc/sql-template.sql 文件
	 * @description 将ddl语句输出到文件 路径: /doc/sql-output.sql
	 * @param addPrefixZero 是否添加前缀0
	 * @param startIndex 开始索引
	 * @param amount 表的数量
	 * @author qianye.zheng
	 */
	public static final void generateSql(final boolean addPrefixZero, final Integer startIndex, final Integer amount)
	{
		// 从模板中获取输出
		final String input = input();
		final StringBuilder resultBuilder = StringUtil.getBuilder();
		// 前缀
		resultBuilder.append("/* 自动生成sql语句，时间: " + DateTimeUtil.format(new Date()) + " */\n");
		final Integer endIndex = startIndex + amount;
		if (addPrefixZero)
		{ // 补 0 
			final Integer bitCount = String.valueOf(amount).length();
			for (int i = startIndex; i < endIndex; i++)
			{
				resultBuilder.append("\n");
				// 替换所有
				resultBuilder.append(input.replace(REPLACE_SYMBOL,  StringUtil.addPrefixZero(bitCount, i)));
			}
		} else
		{ // 无需 补 0，自然增长
			for (int i = startIndex; i < endIndex; i++)
			{
				resultBuilder.append("\n");
				// 替换所有
				resultBuilder.append(input.replace(REPLACE_SYMBOL, String.valueOf(i)));
			}
		}
		
		// 输出到指定文件
		output(resultBuilder.toString());
	}
	
	/**
	 * @description 将count语句输出到文件 路径: /doc/sql-output.sql
	 * @param addPrefixZero 是否添加前缀0
	 * @param startIndex 开始索引
	 * @param amount 表的数量
	 * @param 数据表名前缀
	 * @author qianye.zheng
	 */
	public static final void generateCountSql(final boolean addPrefixZero, final Integer startIndex, final Integer amount, final String tbPrefix)
	{
		final StringBuilder resultBuilder = StringUtil.getBuilder();
		resultBuilder.append("select sum(cnt) from (\n");
		final Integer endIndex = startIndex + amount - 1;
		if (addPrefixZero)
		{ // 补 0 
			final Integer bitCount = String.valueOf(amount).length();
			for (int i = startIndex; i < endIndex; i++)
			{
				resultBuilder.append("select count(*) as cnt from " + tbPrefix + StringUtil.addPrefixZero(bitCount, i) + " \nunion ALL\n");
			}
			// 追加最后一个
			resultBuilder.append("select count(*) as cnt from " + tbPrefix + endIndex + ") t;");
		} else
		{ // 无需 补 0，自然增长
			for (int i = startIndex; i < endIndex; i++)
			{
				resultBuilder.append("select count(*) as cnt from " + tbPrefix + i + " \nunion ALL\n");
			}
			// 追加最后一个
			resultBuilder.append("select count(*) as cnt from " + tbPrefix + endIndex + ") t;");
		}
		
		// 输出到指定文件
		output(resultBuilder.toString());
	}
	
	/**
	 * 
	 * @description 从模板文件获取输入
	 * @return
	 * @author qianye.zheng
	 */
	private static final String input()
	{
		return FileUtil.getString(INPUT_PATH);
	}
	
	/**
	 * 
	 * @description 输出到文件
	 * @param value 输出内容
	 * @author qianye.zheng
	 */
	private static final void output(final String value)
	{
		FileUtil.writeString(OUTPUT_PATH, value);
	}
	
}

/**
 * SplitUtil.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.util;

/**
 * SplitUtil
 * 描述: 拆分工具
 * @author  qye.zheng
 */
public final class SplitUtil
{

	/**
	 * 分库分表，可以根据1个值或2个值来确立，先确定库，然后再确定表。
	 */
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	private SplitUtil()
	{
	}

	/**
	 * 
	 * @description  获取数据库、数据表的名称
	 * @param namePrefix 数据库、数据表的前缀名称
	 * @param length 数据库/表后缀连续数字的长度，从0或从1开始
	 * @param startOne 是否从1开始，一般默认是从0开始
	 * @param referenceValue 单一分库分表参考值
	 * @return
	 * @author qianye.zheng
	 */
	public static final String getName(final String namePrefix, final boolean addPrefixZero, final Integer length, 
			final boolean startOne, final Integer referenceValue)
	{
		return getName(namePrefix, addPrefixZero, length, startOne, referenceValue.longValue());
	}	
	
	/**
	 * 
	 * @description  获取数据库、数据表的名称
	 * @param namePrefix 数据库、数据表的前缀名称
	 * @param addPrefixZero 是否补0
	 * @param length 数据库/表后缀连续数字的长度，从0或从1开始
	 * @param startOne 是否从1开始，一般默认是从0开始
	 * @param referenceValue 单一分库分表参考值
	 * @return
	 * @author qianye.zheng
	 */
	public static final String getName(final String namePrefix, final boolean addPrefixZero, final Integer length, 
			final boolean startOne, final Long referenceValue)
	{
		if (StringUtil.isEmpty(namePrefix))
		{
			throw new IllegalArgumentException("数据库或数据表的前缀名称不能为空");
		}
		
		return namePrefix + getSuffixIndex(length, addPrefixZero, startOne, referenceValue);
	}
	
	/**
	 * 
	 * @description 获取数据库、数据表的后缀索引
	 * @param length 数据库/表后缀连续数字的长度，从0或从1开始
	 * @param addPrefixZero 是否补0
	 * @param startOne 是否从1开始，一般默认是从0开始
	 * @param referenceValue 参考数值
	 * @return
	 * @author qianye.zheng
	 */
	public static final String getSuffixIndex(final Integer length, final boolean addPrefixZero, final boolean startOne, final Integer referenceValue)
	{
		return getSuffixIndex(length, addPrefixZero, startOne, referenceValue.longValue());
	}
	
	/**
	 * 
	 * @description 获取数据库、数据表的后缀索引
	 * @param length 数据库/表后缀连续数字的长度，从0或从1开始
	 * @param addPrefixZero 是否补0
	 * @param startOne 是否从1开始，一般默认是从0开始
	 * @param referenceValue 参考数值
	 * @return
	 * @author qianye.zheng
	 */
	public static final String getSuffixIndex(final Integer length, final boolean addPrefixZero, final boolean startOne, final Long referenceValue)
	{
		final Integer bitCount = String.valueOf(length).length();
		String splitNumStr = "1";
		while (splitNumStr.length() <= bitCount )
		{
			splitNumStr = splitNumStr + "0";
		}
		final Integer splitNum = Integer.valueOf(splitNumStr);
		Integer num = null;
		num = Long.valueOf(referenceValue % splitNum % length).intValue();
		if (startOne)
		{ // 从 1 开始 需要自增1，默认是从0开始
			num++;
		}
		String result = null;
		if (addPrefixZero)
		{ // 需要补 0 
			result = StringUtil.addPrefixZero(bitCount, num);
		} else
		{
			result = String.valueOf(num);
		}
		
		return result;
	}
	
}

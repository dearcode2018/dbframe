/**
  * @filename PersonSingleKeyTableShardingAlgorithm.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.sharding;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import com.hua.util.StringUtil;

 /**
 * @type PersonSingleKeyTableShardingAlgorithm
 * @description 单一键分表算法
 * @author qianye.zheng
 */
public class PersonSingleKeyTableShardingAlgorithm implements
		SingleKeyTableShardingAlgorithm<Integer>
{

	/* 分表的数量 */
	private Integer SIZE = 4;
	
	/* 从多少开始，一般从0或1开始 */
	private static final Integer START = 1;
	
	/* 后缀长度，例如 xx_01 后缀长度为2 */
	private static final Integer SUFFIX_LENGTH = 2;
	
	/**
	 * @description sql关键字中匹配符为 = 时表的映射
	 * @param availableTargetNames
	 * @param shardingValue
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public String doEqualSharding(Collection<String> availableTargetNames,
			ShardingValue<Integer> shardingValue)
	{
		
		/*
		 * 遍历数据源名称，返回后缀符合条件的数据表名称
		 */
		// 后缀
		String suffix = null;
		for (String tableName : availableTargetNames)
		{
			suffix = StringUtil.addZero(SUFFIX_LENGTH, START + shardingValue.getValue() % SIZE);
			if (tableName.endsWith(suffix))
			{
				System.out.println("doEqualSharding.dataSouceName = " + tableName);
				
				return tableName;
			}
		}
		
		throw new IllegalArgumentException();
	}

	/**
	 * @description sql关键字中匹配符为 in 时表的映射
	 * @param availableTargetNames
	 * @param shardingValue
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public Collection<String> doInSharding(
			Collection<String> availableTargetNames,
			ShardingValue<Integer> shardingValue)
	{
		/*
		 * 遍历数据源名称，返回后缀符合条件的数据表名称
		 */
		// 后缀
		String suffix = null;
		final Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
		for (Integer value : shardingValue.getValues())
		{
			suffix = StringUtil.addZero(SUFFIX_LENGTH, START + value % SIZE);
			for (String tableName : availableTargetNames)
			{
				if (tableName.endsWith(suffix))
				{
					result.add(tableName);
				}
			}
		}
		
		return result;
	}

	/**
	 * @description sql关键字中匹配符为 between 时表的映射
	 * @param availableTargetNames
	 * @param shardingValue
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public Collection<String> doBetweenSharding(
			Collection<String> availableTargetNames,
			ShardingValue<Integer> shardingValue)
	{
		/*
		 * 遍历数据源名称，返回后缀符合条件的数据表名称
		 */
		// 后缀
		String suffix = null;
		final Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
		final Range<Integer> range =  shardingValue.getValueRange();
		for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++)
		{
			for (String tableName : availableTargetNames)
			{
				suffix = StringUtil.addZero(SUFFIX_LENGTH, START + i % SIZE);
				if (tableName.endsWith(suffix))
				{
					result.add(tableName);
				}
			}
		}
		
		return result;
	}

}

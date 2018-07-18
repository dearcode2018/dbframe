/**
  * @filename PersonSingleKeyDatabaseShardingAlgorithm.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.sharding;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;
import com.hua.util.StringUtil;

 /**
 * @type PersonSingleKeyDatabaseShardingAlgorithm
 * @description 单一键分库算法
 * @author qianye.zheng
 */
public class PersonSingleKeyDatabaseShardingAlgorithm implements
		SingleKeyDatabaseShardingAlgorithm<Integer>
{

	/* 分库的数量 */
	private static final Integer SIZE = 3;
	
	/* 从多少开始，一般从0或1开始 */
	private static final Integer START = 1;
	
	/* 后缀长度，例如 xx_01 后缀长度为2 */
	private static final Integer SUFFIX_LENGTH = 2;
	
	/**
	 * @description sql关键字中匹配符为 = 时数据源(库)的路由
	 * @param availableTargetNames 数据源名称集合
	 * @param shardingValue
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public String doEqualSharding(Collection<String> availableTargetNames,
			ShardingValue<Integer> shardingValue)
	{
		/*
		 * 遍历数据源名称，返回后缀符合条件的数据源名称
		 */
		// 后缀
		String suffix = null;
		for (String dataSouceName : availableTargetNames)
		{
			suffix = StringUtil.addPrefixZero(SUFFIX_LENGTH, START + shardingValue.getValue() % SIZE);
			if (dataSouceName.endsWith(suffix))
			{
				System.out.println("doEqualSharding.dataSouceName = " + dataSouceName);
				
				return dataSouceName;
			}
		}
		
		throw new IllegalArgumentException();
	}

	/**
	 * @description sql关键字中匹配符为 in 时库的路由
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
		 * 遍历数据源名称，返回后缀符合条件的数据源名称
		 */
		// 后缀
		String suffix = null;
		final Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
		for (Integer value : shardingValue.getValues())
		{
			for (String dataSouceName : availableTargetNames)
			{
				suffix = StringUtil.addPrefixZero(SUFFIX_LENGTH, START + value % SIZE);
				if (dataSouceName.endsWith(suffix))
				{
					result.add(dataSouceName);
				}
			}
		}
		
		return result;
	}

	/**
	 * @description sql关键字中匹配符为 between 时库的路由
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
		 * 遍历数据源名称，返回后缀符合条件的数据源名称
		 */
		// 后缀
		String suffix = null;
		final Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
		final Range<Integer> range =  shardingValue.getValueRange();
		for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++)
		{
			for (String dataSouceName : availableTargetNames)
			{
				suffix = StringUtil.addPrefixZero(SUFFIX_LENGTH, START + i % SIZE);
				if (dataSouceName.endsWith(suffix))
				{
					result.add(dataSouceName);
				}
			}
		}
		
		return result;
	}

}

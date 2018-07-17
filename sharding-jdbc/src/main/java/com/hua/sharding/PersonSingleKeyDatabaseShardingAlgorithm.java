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

 /**
 * @type PersonSingleKeyDatabaseShardingAlgorithm
 * @description 单一键分库算法
 * @author qianye.zheng
 */
public class PersonSingleKeyDatabaseShardingAlgorithm implements
		SingleKeyDatabaseShardingAlgorithm<Integer>
{

	/* 分库的数量 */
	private Integer SIZE = 3;
	
	/**
	 * @description sql关键字中匹配符为 = 时表的路由
	 * @param availableTargetNames
	 * @param shardingValue
	 * @return
	 * @author qianye.zheng
	 */
	@Override
	public String doEqualSharding(Collection<String> availableTargetNames,
			ShardingValue<Integer> shardingValue)
	{
		for (String tableName : availableTargetNames)
		{
			if (tableName.endsWith(String.valueOf(shardingValue.getValue() % SIZE)))
			{
				return tableName;
			}
		}
		
		throw new IllegalArgumentException();
	}

	/**
	 * @description sql关键字中匹配符为 in 时表的路由
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
		final Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
		for (Integer value : shardingValue.getValues())
		{
			for (String tableName : availableTargetNames)
			{
				if (tableName.endsWith(String.valueOf(value % SIZE)))
				{
					result.add(tableName);
				}
			}
		}
		
		return result;
	}

	/**
	 * @description sql关键字中匹配符为 between 时表的路由
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
		final Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
		final Range<Integer> range =  shardingValue.getValueRange();
		for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++)
		{
			for (String tableName : availableTargetNames)
			{
				if (tableName.endsWith(String.valueOf(i % SIZE)))
				{
					result.add(tableName);
				}
			}
		}
		
		return result;
	}

}

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
		for (String tableName : availableTargetNames)
		{
			if (tableName.endsWith("0".concat(String.valueOf(shardingValue.getValue() % SIZE))))
			{
				
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
		final Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
		for (Integer value : shardingValue.getValues())
		{
			for (String tableName : availableTargetNames)
			{
				if (tableName.endsWith("0".concat(String.valueOf(value % SIZE))))
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
		final Collection<String> result = new LinkedHashSet<String>(availableTargetNames.size());
		final Range<Integer> range =  shardingValue.getValueRange();
		for (Integer i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++)
		{
			for (String tableName : availableTargetNames)
			{
				if (tableName.endsWith("0".concat(String.valueOf(i % SIZE))))
				{
					result.add(tableName);
				}
			}
		}
		
		return result;
	}

}

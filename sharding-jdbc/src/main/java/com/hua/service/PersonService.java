/**
  * @filename PersonService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hua.entity.Person;
import com.hua.mapper.PersonMapper;

 /**
 * @type PersonService
 * @description 
 * @author qianye.zheng
 */
@Service
public class PersonService
{
	
	@Resource
	private PersonMapper personMapper;
	
	/**
	 * 
	 * @description 
	 * @param person
	 * @author qianye.zheng
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(final Person person)
	{
		personMapper.insert(person);
	}
	
	/**
	 * 
	 * @description 
	 * @param ids
	 * @author qianye.zheng
	 */
	public Long arrayForeach(Long[] ids)
	{
		Long count = personMapper.arrayForeach(ids);
		
		return count;
	}
	
	
	/**
	 * 
	 * @description 
	 * @param ids
	 * @author qianye.zheng
	 */
	public Long arrayForeachUserId(Integer[] ids)
	{
		Long count = personMapper.arrayForeachUserId(ids);
		
		return count;
	}
	
	
	/**
	 * 
	 * @description 
	 * @param startValue
	 * @param endValue
	 * @return
	 * @author qianye.zheng
	 */
	public Long countBetween(final Integer startValue, final Integer endValue)
	{
		return personMapper.countBetween(startValue, endValue);
	}
	
	
	
	
	
	
	
	
	
	
}

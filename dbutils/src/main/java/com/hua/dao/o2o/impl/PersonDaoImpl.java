/**
 * 描述: 
 * PersonDaoImpl.java
 * @author qye.zheng
 * 
 *  version 1.0
 */
package com.hua.dao.o2o.impl;

import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

import com.hua.dao.impl.CoreDaoImpl;
import com.hua.dao.o2o.PersonDao;
import com.hua.orm.entity.o2o.Person;

/**
 * 描述: 
 * @author qye.zheng
 * 
 * PersonDaoImpl
 */
public final class PersonDaoImpl extends CoreDaoImpl<Person> implements
		PersonDao
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public PersonDaoImpl()
	{
		super(Person.class);
	}
	
	/**
	 * 
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 * @param beanHandler
	 * @param beanListHandler
	 */
	public PersonDaoImpl(final ResultSetHandler<Person> beanHandler, final ResultSetHandler<List<Person>> beanListHandler)
	{
		super(beanHandler, beanListHandler);
	}
	
}

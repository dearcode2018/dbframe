/**
 * PersonBeanListHandler.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

import com.hua.constant.ext.Gender;
import com.hua.orm.entity.o2o.Person;


/**
 * PersonBeanListHandler
 * 描述: 
 * @author  qye.zheng
 */
public final class PersonBeanListHandler implements
		ResultSetHandler<List<Person>>
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public PersonBeanListHandler()
	{
	}

	/**
	 * 描述: 
	 * @author  qye.zheng
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Person> handle(final ResultSet rs) throws SQLException
	{
		/*
		 或许可以通过 RowProcessor方式来统一处理(暂未明确)
		 */
		final List<Person> list = new ArrayList<Person>();
		Person person = null;
		while (rs.next())
		{
			person = new Person();
			person.setId(rs.getString("id"));
			person.setName(rs.getString("name"));
			person.setGender(Gender.getInstance(rs.getString("gender")));
			person.setNation(rs.getString("nation"));
			person.setPhotoUrl(rs.getString("photoUrl"));
			person.setBirthday(rs.getDate("birthday"));
			person.setAddress(rs.getString("address"));
			list.add(person);
		}
		
		return list;
	}

}

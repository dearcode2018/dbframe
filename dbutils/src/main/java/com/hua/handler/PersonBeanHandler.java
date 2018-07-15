/**
 * PersonBeanHandler.java
 * @author  qye.zheng
 * 	version 1.0
 */
package com.hua.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.ResultSetHandler;

import com.hua.constant.ext.Gender;
import com.hua.orm.entity.o2o.Person;

/**
 * PersonBeanHandler
 * 描述: 
 * @author  qye.zheng
 */
public final class PersonBeanHandler implements
		ResultSetHandler<Person>
{

	/**
	 * 构造方法
	 * 描述: 
	 * @author  qye.zheng
	 */
	public PersonBeanHandler()
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
	public Person handle(final ResultSet rs) throws SQLException
	{
		Person person = null;
		if (rs.next())
		{
			person = new Person();
			person.setId(rs.getString("id"));
			person.setName(rs.getString("name"));
			person.setGender(Gender.getInstance(rs.getString("gender")));
			person.setNation(rs.getString("nation"));
			person.setPhotoUrl(rs.getString("photoUrl"));
			person.setBirthday(rs.getDate("birthday"));
			person.setAddress(rs.getString("address"));
		}
		
		return person;
	}

}

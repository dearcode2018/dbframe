/**
 * 描述: 
 * Person.java
 * @author qye.zheng
 * 
 *  version 1.0
 */
package com.hua.entity;

import java.util.Date;

/**
 * 描述: 
 * @author qye.zheng
 * 
 * Person
 */
public final class Person
{
	
	private Integer id;
	
	private Integer userId;
	
	/* 姓名 */
	private String name;
	
	/* 证件照片url */
	private String photoUrl;
	
	/* 民族 */
	private String nation;
	
	/* 出生日期 yyyy-MM-dd */
	private Date birthday;
	
	/* 住址 */
	private String address;

	/**
	 * @return the id
	 */
	public final Integer getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(Integer id)
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public final String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the photoUrl
	 */
	public final String getPhotoUrl()
	{
		return photoUrl;
	}

	/**
	 * @param photoUrl the photoUrl to set
	 */
	public final void setPhotoUrl(String photoUrl)
	{
		this.photoUrl = photoUrl;
	}

	/**
	 * @return the nation
	 */
	public final String getNation()
	{
		return nation;
	}

	/**
	 * @param nation the nation to set
	 */
	public final void setNation(String nation)
	{
		this.nation = nation;
	}

	/**
	 * @return the birthday
	 */
	public final Date getBirthday()
	{
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public final void setBirthday(Date birthday)
	{
		this.birthday = birthday;
	}

	/**
	 * @return the address
	 */
	public final String getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public final void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * @return the userId
	 */
	public final Integer getUserId()
	{
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public final void setUserId(Integer userId)
	{
		this.userId = userId;
	}
	
}

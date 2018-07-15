/**
  * @filename DataSourceSplitAspect.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.aspect;

import org.aspectj.lang.JoinPoint;

import com.hua.datasource.DatabaseContextHolder;

 /**
 * @type DataSourceSplitAspect
 * @description 数据源AOP(DataSourceAspect)应该在动态数据源路由(DynamicDataSource)之前执行
 * @author qianye.zheng
 */
// 注意，这里应该标注为组件Component而不是Aspect
//@Aspect
//@Component
public class DataSourceSplitAspect
{
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	public void setDataSource(final JoinPoint jp)
	{
		//System.out.println(jp.getTarget().getClass().getPackage().getName());
		/*
		 * 使用第一个参数作为分库参数
		 */
		final int index = (Integer) jp.getArgs()[0];
		switch (index)
		{
			case 1:
				setDataSource01(jp);
				break;
			case 2:
				setDataSource02(jp);
				break;
			case 3:
				setDataSource03(jp);
				break;	
				
			// 使用默认的数据源
			default:
				break;
		}
	}
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	public void setDataSource01(final JoinPoint jp)
	{
		System.out.println("1.DataSourceAspect.setDataSource01()");
		DatabaseContextHolder.setCustomerType("dataSource_01");
	}
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	public void setDataSource02(final JoinPoint jp)
	{
		System.out.println("1.DataSourceAspect.setDataSource02()");
		DatabaseContextHolder.setCustomerType("dataSource_02");
	}
	
	/**
	 * 
	 * @description 
	 * @author qianye.zheng
	 */
	public void setDataSource03(final JoinPoint jp)
	{
		System.out.println("1.DataSourceAspect.setDataSource03()");
		DatabaseContextHolder.setCustomerType("dataSource_03");
	}
	
}

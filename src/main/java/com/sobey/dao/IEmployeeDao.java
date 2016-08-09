/*
 * 文件名：IEmployeeDao.java
 * 版权：Copyright by www.sobye.com
 * 描述：
 * 修改人：[liruilang]
 * 修改时间：2015年12月22日
 */

package com.sobey.dao;

import java.util.List;

import com.sobey.po.Employee;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author [liruilang]
 * @version 2015年12月22日-下午6:20:25
 * @see IEmployeeDao
 */

public interface IEmployeeDao extends IBaseDao<Employee> {
    void addBatchEmp(List<Employee> employees);
}

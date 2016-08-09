/*
 * 文件名：IEmployeeService.java
 * 版权：Copyright by www.sobye.com
 * 描述：
 * 修改人：[liruilang]
 * 修改时间：2015年12月22日
 */

package com.sobey.service;

import java.util.List;

import com.sobey.po.Employee;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author [liruilang]
 * @version 2015年12月22日-下午6:29:27
 * @see IEmployeeService
 */

public interface IEmployeeService {
    void add(Employee employee);
    List<Employee> searchEmp();
    void addBatchEmp(List<Employee> employees);
}

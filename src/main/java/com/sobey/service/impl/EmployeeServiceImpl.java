/*
 * 文件名：EmployeeServiceImpl.java
 * 版权：Copyright by www.sobye.com
 * 描述：
 * 修改人：[liruilang]
 * 修改时间：2015年12月22日
 */

package com.sobey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sobey.dao.IEmployeeDao;
import com.sobey.po.Employee;
import com.sobey.service.IEmployeeService;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author [liruilang]
 * @version 2015年12月22日-下午6:31:00
 * @see EmployeeServiceImpl
 */
@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService{

    @Autowired
    private IEmployeeDao employeeDao;
    
    @Override
    public void add(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    @Cacheable(value="myCache",key="0")
    public List<Employee> searchEmp() {
        return employeeDao.query();
    }

    @Override
    public void addBatchEmp(List<Employee> employees) {
       employeeDao.addBatchEmp(employees);
    }

}

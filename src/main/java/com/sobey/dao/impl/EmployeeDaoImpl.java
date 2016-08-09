/*
 * 文件名：EmployeeDaoImpl.java
 * 版权：Copyright by www.sobye.com
 * 描述：
 * 修改人：[liruilang]
 * 修改时间：2015年12月22日
 */

package com.sobey.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Repository;

import com.sobey.dao.IEmployeeDao;
import com.sobey.po.Employee;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author [liruilang]
 * @version 2015年12月22日-下午6:22:09
 * @see EmployeeDaoImpl
 */
@Repository("employeeDao")
public class EmployeeDaoImpl extends BaseDaoImpl<Employee> implements IEmployeeDao{

    @Override
    public void save(Employee t) {
        super.save(t);
    }

    @Override
    public void delete(Serializable id) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(Employee t) {
        // TODO Auto-generated method stub
    }

    @Override
    public List<Employee> query() {
        List<Employee> employees = super.query();
        return employees;
    }

    @Override
    public Employee get(Serializable id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addBatchEmp(final List<Employee> employees) {
        Session session = this.getSession();
        Work work = new Work() {
            @Override
            public void execute(Connection connection)
                throws SQLException {
                String sql = "insert into lrl_employee(emp_name,emp_password) values(?,?)";
                int count = 0;
                PreparedStatement prepareStatement = connection.prepareStatement(sql);
                for (Employee employee : employees) {
                    prepareStatement.setString(1, employee.getName());
                    prepareStatement.setString(2, employee.getPassword());
                    prepareStatement.addBatch();
                    if(++count % 200 ==0){
                        int[] executeBatch = prepareStatement.executeBatch();
                        System.out.println(ArrayUtils.toString(executeBatch));
                    }
                }
                int[] executeBatch = prepareStatement.executeBatch();
                System.out.println(ArrayUtils.toString(executeBatch));
                prepareStatement.close();
            }
        };
        session.doWork(work);
    }

    
}

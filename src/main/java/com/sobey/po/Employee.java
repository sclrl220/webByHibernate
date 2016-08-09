/*
 * 文件名：Employee.java
 * 版权：Copyright by www.sobye.com
 * 描述：
 * 修改人：[liruilang]
 * 修改时间：2015年12月22日
 */

package com.sobey.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author [liruilang]
 * @version 2015年12月22日-下午5:58:18
 * @see Employee
 */
@Entity
@Table(name="lrl_employee")
public class Employee implements Serializable{
    private Integer empId;
    private String name;
    private String password;
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getEmpId() {
        return empId;
    }
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
    @Column(name="emp_name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Column(name="emp_password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "Employee [empId=" + empId + ", name=" + name + ", password=" + password + "]";
    }
}

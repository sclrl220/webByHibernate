/*
 * 文件名：EmployeeControler.java 版权：Copyright by www.sobye.com 描述： 修改人：[liruilang] 修改时间：2015年12月22日
 */

package com.sobey.controler;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sobey.po.Employee;
import com.sobey.service.IEmployeeService;


/**
 * 用户Controler
 * 
 * @author [liruilang]
 * @version 2015年12月22日-下午6:32:50
 * @see EmployeeControler
 */
@Controller
@RequestMapping("/emp")
public class EmployeeControler {

    private static final Logger LOG = Logger.getLogger(EmployeeControler.class);

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("/add")
    public String addEmp(Employee employee) {
        employee.setName("zhangsanssss");
        employee.setPassword("111");
        employeeService.add(employee);
        return "ok";
    }

    @RequestMapping("/batch")
    public String batchAddEmp() {
        List<Employee> employees = new ArrayList<Employee>();
        Employee e = null;
        for (int i = 0; i < 10000; i++ ) {
            e = new Employee();
            e.setName("name" + i);
            e.setPassword("password" + i);
            employees.add(e);
        }
        employeeService.addBatchEmp(employees);
        return "ok";
    }

    @RequestMapping("/list")
    public String searchEmps() {
        LOG.info("查询所有数据");
        List<Employee> searchEmp = employeeService.searchEmp();
        System.out.println(ArrayUtils.toString(searchEmp));

        return "ok";
    }
    
    @RequestMapping("/pageCache")
    public String testPageCache(ModelMap map){
        LOG.info("全部页面缓存...");
        map.put("date", new Date());
        return "ok";
    }
    
    @RequestMapping("/pageFragmentCache")
    public String testPageFragmentCache(ModelMap map){
        LOG.info("局部页面缓存...");
        map.put("date", new Date());
        return "ok";
    }
}

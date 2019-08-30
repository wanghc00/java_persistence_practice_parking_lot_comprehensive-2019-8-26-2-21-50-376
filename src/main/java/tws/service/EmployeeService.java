/*
 * Copyright (C), 1992-2019
 * Package tws.service 
 * FileName: EmployeeService.java
 * Author:   wang-hc
 * Date:     2019年8月30日 上午9:39:18
 * Description: //模块目的、功能描述      
 * History: //修改记录
 *===============================================================================================
 *   author：          time：                             version：           desc：
 *   wang-hc           2019年8月30日上午9:39:18                     1.0                  
 *===============================================================================================
 */
package tws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tws.entity.Employee;
import tws.repository.EmployeeMapper;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeMapper employeeMapper;
    
    public List<Employee> returnZYBankEmployee() {
        List<Employee> employees = employeeMapper.selectAll();
        for (Employee employee : employees) {
            employee.setName("中原银行" + employee.getName());
        }
        return employees;
    }
    
    
    public List<Employee> getEmployeesByPage(int pageSize, int page) {
        List<Employee> allEmployees = employeeMapper.selectAll();
        List<Employee> result = new ArrayList<Employee>();
        int startIndex = page*(pageSize-1);
        result = employeeMapper.selectByPage(startIndex, pageSize);
        return result;
    }
    
    
//    public List<Employee> getEmployeesByPage(int pageSize, int page) {
//        List<Employee> allEmployees = employeeMapper.selectAll();
//        List<Employee> result = new ArrayList<Employee>();
//        int allPageSize = allEmployees.size()/pageSize;
//        if (allEmployees.size()%pageSize != 0) {
//            allPageSize += 1;
//        }
//        int startIndex = page*(pageSize-1);
//        for (int i = startIndex; i < allEmployees.size() && i< (startIndex+pageSize); i++) {
//            result.add(allEmployees.get(i));
//        }
//        return result;
//    }

}

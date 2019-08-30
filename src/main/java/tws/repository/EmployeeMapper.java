package tws.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tws.entity.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> selectAll();
    List<Employee> selectByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
   void insert(@Param("employee") Employee employee);
   
}

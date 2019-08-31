package tws.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tws.entity.Employee;
import tws.entity.ParkingLot;

import java.util.List;

@Mapper
public interface EmployeeMapper {
   List<Employee> selectAll();
   List<Employee> selectByPage(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
   List<ParkingLot> selectParkingLotsById(@Param("id") int id);
   void insert(@Param("employee") Employee employee);
   void updateEmployeeInfo(@Param("employee") Employee employee);
   void deleteEmployee(@Param("id") int id);
   
}

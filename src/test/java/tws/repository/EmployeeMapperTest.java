package tws.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import tws.entity.Employee;
import tws.entity.ParkingLot;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@MybatisTest
public class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Before
    public void deleteEmployeeTable() {
        jdbcTemplate.execute("delete employee;");
        jdbcTemplate.execute("delete parkingLot;");
    }
    
    @After
    public void tearDown() throws Exception {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "employee");
    }

    @Test
    public void shouldFetchAllEmployees() {
        // given
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(1,'zhangsan', 21);");
        // when
        List<Employee> employeeList = employeeMapper.selectAll();
        // then
        assertEquals(1, employeeList.size());
    }
    
    @Test
    public void should_Fetch_EmployeeInfo_when_selectAll_given_1_zhangsan_21() {
        //given
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(1,'zhangsan',21);");
        
        //when
        List<Employee> employees = employeeMapper.selectAll();
        
        //then
        assertEquals(1, employees.get(0).getId());
        assertEquals("zhangsan", employees.get(0).getName());
    }
    
    @Test
    public void should_fetch_employeeInfo_when_selectByPage_given_employees() {
        //given
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(1,'zhangsan1',18);");
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(2,'zhangsan2',18);");
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(3,'zhangsan3',18);");
        
        //when
        List<Employee> employees = employeeMapper.selectByPage(2, 2);
        
        //then
        assertEquals("zhangsan3", employees.get(0).getName());
    }    
    
    @Test
    public void should_fetch_parkinglotInfo_when_selectParkingLotsById_given_employees_and_parkinglots() {
        //given
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(1,'zhangsan1',18);");
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(2,'zhangsan2',18);");
        jdbcTemplate.execute("insert into parkingLot (id, capacity, employeeId) values ('1', 10, 1);");
        jdbcTemplate.execute("insert into parkingLot (id, capacity, employeeId) values ('2', 20, 1);");
        jdbcTemplate.execute("insert into parkingLot (id, capacity, employeeId) values ('3', 30, 2);");
        
        //when
        List<ParkingLot> parkingLots = employeeMapper.selectParkingLotsById(1);
        
        //then
        assertEquals(2, parkingLots.size());
    } 
    
    @Test
    public void should_fetch_employeeInfo_when_insert_given_employee() {
        //given
        Employee employee = new Employee(1,"zhangsan1", 18);
        
        //when
        employeeMapper.insert(employee);
        
        //then
        assertEquals(1, employeeMapper.selectAll().size());
    }
    
    
    
}

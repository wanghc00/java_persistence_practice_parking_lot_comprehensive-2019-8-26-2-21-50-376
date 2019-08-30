package tws.repository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import tws.entity.Employee;

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
        jdbcTemplate.execute("insert ");
    }
    
    
}

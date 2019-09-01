package tws.repository;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import tws.controller.EmployeeController;
import tws.entity.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
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
    
    @Test
    public void should_get_employees_when_get() throws Exception {
        
        ResultActions result = mockMvc.perform(get("/employees"));
        result.andExpect(status().isOk())
        .andDo(print());
        
    }
    
    @Test
    public void postMehodTest() throws Exception {
        //given
        Employee employee =  new Employee(1, "001", 18);
        String postString = objectMapper.writeValueAsString(employee);
        
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postString)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(postString));
    }
    
    @Test
    public void should_return_employee_page() throws Exception {
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(0,'jerry', 17);");
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(1,'tom', 15);");
        jdbcTemplate.execute("INSERT INTO EMPLOYEE VALUES(2,'sherry', 18);");
        List<Employee> employees = employeeMapper.selectAll();
        MultiValueMap<String, String> pageParam = new LinkedMultiValueMap<>();
        pageParam.add("pageSize", "1");
        pageParam.add("page", "1");
        String getString = objectMapper.writeValueAsString(employees.subList(0, 1));
        this.mockMvc.perform(get("/employees/").params(pageParam))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(getString));
    }
    
    
}

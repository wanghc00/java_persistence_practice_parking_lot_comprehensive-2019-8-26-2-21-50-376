package tws.repository.ControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import tws.entity.ParkingLot;
import tws.repository.EmployeeMapper;
import tws.repository.ParkingLotMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private ParkingLotMapper parkingLotMapper;
    
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
    public void should_return_prkingLots() throws Exception {
        jdbcTemplate.execute("insert into parkingLot (id, capacity, employeeId) values ('1', 10, 1);");
        jdbcTemplate.execute("insert into parkingLot (id, capacity, employeeId) values ('2', 20, 2);");
        List<ParkingLot> parkingLots = parkingLotMapper.selectAllParkingLots();
        String getString = objectMapper.writeValueAsString(parkingLots);
        this.mockMvc.perform(get("/parkinglots")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(getString));
    }
    
    @Test
    public void should_return_create_status_201() throws Exception {
        ParkingLot parkingLot = new ParkingLot("1", 10);
        String postString = objectMapper.writeValueAsString(parkingLot);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/parkinglots")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postString))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    public void should_return_create_status_BadRequest_given_same_id() throws Exception {
        jdbcTemplate.execute("INSERT INTO parkingLot VALUES('0',13,10,0);");
        ParkingLot parkingLot = new ParkingLot("0",13);
        String postString = objectMapper.writeValueAsString(parkingLot);
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/parkinglots")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postString))
                .andDo(print()).andExpect(status().isBadRequest());
    }
    
}

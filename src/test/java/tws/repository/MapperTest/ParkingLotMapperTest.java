package tws.repository.MapperTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import tws.entity.ParkingLot;
import tws.repository.ParkingLotMapper;

@RunWith(SpringRunner.class)
@MybatisTest
public class ParkingLotMapperTest {
    
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
    
    @After
    public void tearDown() throws Exception {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "employee");
        jdbcTemplate.execute("delete employee;");
        jdbcTemplate.execute("delete parkingLot;");
    }
    
    @Test
    public void should_fetch_parkinglotInfo_when_insertParkingLot_given_parkingLot() {
        //given
        ParkingLot parkingLot = new ParkingLot("01", 10);
        
        //when
        parkingLotMapper.insertParkingLot(parkingLot);
        
        //then
        assertEquals(1, parkingLotMapper.selectAllParkingLots().size());
    }
    
    @Test
    public void should_fetch_null_when_deleteParkingLot_given_id() {
        //given
        jdbcTemplate.execute("insert into parkingLot (id, capacity, employeeId) values ('1', 10, 1);");
        
        //when
        parkingLotMapper.deleteParkingLot("1");
        
        //then
        assertEquals(0, parkingLotMapper.selectAllParkingLots().size());
    }
    
    @Test
    public void should_fetch_parkinglotInfo_when_updateParkingLotInfo_given_id() {
        //given
        jdbcTemplate.execute("insert into parkingLot (id, capacity) values ('1', 10);");
        ParkingLot parkingLot = new ParkingLot("1", 20);
        
        //when
        parkingLotMapper.updateParkingLotInfo(parkingLot);
        
        //then
        assertEquals(20, parkingLotMapper.selectAllParkingLots().get(0).getCapacity());
    }
    
    @Test
    public void should_fetch_parkinglotInfo_when_selectAllParkingLots_given_parkingLots() {
        //given
        jdbcTemplate.execute("insert into parkingLot (id, capacity) values ('1', 10);");
        jdbcTemplate.execute("insert into parkingLot (id, capacity) values ('2', 20);");
        jdbcTemplate.execute("insert into parkingLot (id, capacity) values ('3', 30);");
        
        //when
        List<ParkingLot> parkingLots = parkingLotMapper.selectAllParkingLots();
        
        //then
        assertEquals(30, parkingLots.get(2).getCapacity());
    }
}

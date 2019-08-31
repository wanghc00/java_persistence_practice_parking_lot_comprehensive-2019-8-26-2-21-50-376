package tws.repository;

import static org.junit.Assert.assertEquals;

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
    

    
    
    
    
    
    
    
    
    

}

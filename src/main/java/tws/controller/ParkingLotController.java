package tws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tws.entity.ParkingLot;
import tws.repository.ParkingLotMapper;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {
    @Autowired
    ParkingLotMapper parkingLotMapper;
    
    @PostMapping
    public void insertParkingLot(@RequestBody ParkingLot parkingLot) {
        parkingLotMapper.insertParkingLot(parkingLot);
    }
    
    @GetMapping
    public List<ParkingLot> getParkingLots(){
        return parkingLotMapper.selectAllParkingLots();
    }
    
}

package tws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    @PutMapping("/{id}")
    public ResponseEntity<ParkingLot> updateParkingLotInfo(@PathVariable String id,@RequestBody ParkingLot parkingLot){
        List<ParkingLot> parkingLots = parkingLotMapper.selectAllParkingLots();
        for (ParkingLot p : parkingLots) {
            if (p.getId().equals(id)) {
                parkingLotMapper.updateParkingLotInfo(parkingLot);
                return ResponseEntity.ok(parkingLot);
            }
        }
        return new ResponseEntity<ParkingLot>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmloyee(@PathVariable String id){
        List<ParkingLot> parkingLots = parkingLotMapper.selectAllParkingLots();
        for (ParkingLot p : parkingLots) {
            if (p.getId().equals(id)) {
                parkingLotMapper.deleteParkingLot(id);
                return ResponseEntity.ok(null);
            }
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
    
}

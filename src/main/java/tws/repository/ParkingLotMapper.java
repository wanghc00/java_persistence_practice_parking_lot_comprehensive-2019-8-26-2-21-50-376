package tws.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import tws.entity.Employee;
import tws.entity.ParkingLot;

@Mapper
public interface ParkingLotMapper {
    void insertParkingLot(@Param("parkingLot") ParkingLot parkingLot);
    List<ParkingLot> selectAllParkingLots();
    void deleteParkingLot(@Param("id") String id);
    void updateParkingLotInfo(@Param("parkingLot")ParkingLot parkingLot);
}

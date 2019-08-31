package tws.entity;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private String id;
    private int capacity;
    private int availablePositionCount;
    private int employeeId;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
    }
    
    public ParkingLot(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailablePositionCount() {
        return capacity - cars.size();
    }

    public ParkingTicket park(Car car) {
        ParkingTicket ticket = new ParkingTicket();
        cars.put(ticket, car);
        return ticket;
    }

    public Car fetch(ParkingTicket ticket) {
        if (cars.containsKey(ticket)) {
            Car car = cars.get(ticket);
            cars.remove(ticket);
            return car;
        }
        return null;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}

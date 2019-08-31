package tws.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tws.entity.Employee;
import tws.entity.ParkingLot;
import tws.repository.EmployeeMapper;
import tws.service.EmployeeService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;
    
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("")
    public ResponseEntity<Employee> insert(@RequestBody Employee employee) {
        employeeMapper.insert(employee);
        return ResponseEntity.created(URI.create("/employees/" + employee.getId())).body(employee);
    }
    
    //?pagesize=1&page=1
    @GetMapping("")
    public ResponseEntity<List<Employee>> getEmployees(
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "page", required = false) Integer page){
        if (pageSize!=null && page!=null && page>=1) {
            return ResponseEntity.ok(employeeService.getEmployeesByPage(pageSize, page));
        }
        return ResponseEntity.ok(employeeMapper.selectAll());
    }
    
    @GetMapping("/{id}/parkinglots")
    public ResponseEntity<List<ParkingLot>> getEmployeesById(@PathVariable int id){
        return ResponseEntity.ok(employeeMapper.selectParkingLotsById(id));
    }
     
    @GetMapping("/ZYBankEmployee")
    public ResponseEntity<List<Employee>> getAllZYBankEmployee() {
        return ResponseEntity.ok(employeeService.returnZYBankEmployee());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeInfo(@PathVariable int id,@RequestBody Employee employee){
        List<Employee> employees = new ArrayList<Employee>();
        employees = employeeMapper.selectAll();
        for (Employee e : employees) {
            if (e.getId() == id) {
                employeeMapper.updateEmployeeInfo(employee);
                return ResponseEntity.ok(employee);
            }
        }
        return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmloyee(@PathVariable int id){
        List<Employee> employees = new ArrayList<Employee>();
        employees = employeeMapper.selectAll();
        for (Employee e : employees) {
            if (e.getId() == id) {
                employeeMapper.deleteEmployee(id);
                return ResponseEntity.ok(null);
            }
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
}

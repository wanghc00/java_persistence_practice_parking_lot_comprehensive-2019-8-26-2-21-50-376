package tws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tws.entity.Employee;
import tws.repository.EmployeeMapper;
import tws.service.EmployeeService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;
    
    @Autowired
    private EmployeeService employeeService;

//    @GetMapping("")
//    public ResponseEntity<List<Employee>> getAll() {
//        return ResponseEntity.ok(employeeMapper.selectAll());
//    }

    @PostMapping("")
    public ResponseEntity<Employee> insert(@RequestBody Employee employee) {
        employeeMapper.insert(employee);
        return ResponseEntity.created(URI.create("/employees/" + employee.getId())).body(employee);
    }
    
    //?pagesize=1&page=1
    @GetMapping("")
    public ResponseEntity<List<Employee>> getEmployeesByPge(
            @RequestParam(name = "pageSize", required = false) Integer pageSize,
            @RequestParam(name = "page", required = true) Integer page){
        if (pageSize!=null && page!=null) {
            return ResponseEntity.ok(employeeService.getEmployeesByPage(pageSize, page));
        }
        return ResponseEntity.ok(employeeMapper.selectAll());
    }
    
    
    
    
    
    @GetMapping("/ZYBankEmployee")
    public ResponseEntity<List<Employee>> getAllZYBankEmployee() {
        return ResponseEntity.ok(employeeService.returnZYBankEmployee());
    }
}

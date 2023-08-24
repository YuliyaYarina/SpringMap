package pro.sky.skyprospringmapdemo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.skyprospringmapdemo.service.Employee;
import pro.sky.skyprospringmapdemo.service.EmployeeService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleException(Exception e) {
        ResponseEntity<String> responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        System.out.println(responseEntity);
        return responseEntity;
    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lactName) {
        return employeeService.add(firstName, lactName);
    }

    @GetMapping(path = "/get")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lactName) {
        return employeeService.find(firstName, lactName);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lactName) {
        return employeeService.remove(firstName, lactName);
    }

    @GetMapping(path = "/getAll")
    public Collection<Employee> getEmployees(){
        return employeeService.getAll();
    }

}

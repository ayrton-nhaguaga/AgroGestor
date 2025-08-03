package com.ayrton.AgroGestor.controller;

import com.ayrton.AgroGestor.dto.EmployeeDTO;
import com.ayrton.AgroGestor.enums.EmployeeSpeciality;
import com.ayrton.AgroGestor.model.Employee;
import com.ayrton.AgroGestor.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDTO dto){
        Employee employee = employeeService.createEmployee(dto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>>getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Employee>> getById(@PathVariable String id){
        Optional<Employee> employee = employeeService.getById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<Employee>> getByNameIgnoreCase(@Valid @RequestParam String name){
        List<Employee> employees = employeeService.getByNameIgnoreCase(name);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/email")
    public ResponseEntity<List<Employee>> getByEmailIgnoreCase(@Valid @RequestParam String email){
        List<Employee> employees = employeeService.getByEmailIgnoreCase(email);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/speciality")
    public ResponseEntity<List<Employee>> getBySpeciality(@RequestParam EmployeeSpeciality speciality){
        List<Employee> employees = employeeService.getBySpeciality(speciality);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Optional<Employee>> updateEmployee(@PathVariable String id, @Valid @RequestBody EmployeeDTO dto){
        Optional<Employee> employee = employeeService.updateEmployee(id, dto);

        if (!employee.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}/mark-absence")
    public ResponseEntity<Employee> markAbsence(@PathVariable String id) {
        Employee updatedEmployee = employeeService.markAbsences(id);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable String id){
        if (employeeService.deleteEmployee(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

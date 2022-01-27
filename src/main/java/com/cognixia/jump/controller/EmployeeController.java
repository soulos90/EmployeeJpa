package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.service.EmployeeService;
import com.cognixia.jump.model.Employee;
import com.cognixia.jump.model.EmployeeAge;

@RequestMapping("/api")
@RestController
public class EmployeeController {
	@Autowired
	EmployeeService service;
	
	@GetMapping("/employees")
	public List<Employee> getEmployees(){
		return service.getEmployees();
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable int id){
		Employee employee = service.getEmployeeById(id);
		if(employee == null) {
			return ResponseEntity.status(404).body("Couldn't find employee with id: " + id);
		}
		
		return ResponseEntity.status(200).body(employee);
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id){
		
		Employee employee = service.deleteEmployeeById(id);
		
		if(employee == null) {
			return ResponseEntity.status(404).body("Couldn't find employee with id: " + id);
		}
		
		return ResponseEntity.status(200).body(employee);
	}
	
	@PostMapping("/employee")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee){
		Employee created = service.createEmployee(employee);
		
		return ResponseEntity.status(201)
				.header("employee id", created.getId() + "")
				.body(created);
	}
	
	@PutMapping("/employee")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee){
		Employee updated = service.updateEmployee(employee);
		
		if(updated == null) {
			
			return ResponseEntity.status(404)
					.body("Couldn't find employee with id = " + employee.getId());
		}
		
		return ResponseEntity.status(200)
				 .body(updated);
	}
	
	@GetMapping("/employee/department/{department}")
	public List<Employee> findEmployeesByDepartment(@PathVariable String department){
		return service.getByDepartment(department);
	}
	
	@GetMapping("/employees/age")
	public List<EmployeeAge> getEmployeesAges(){
		return service.getEmployeesAges();
	}
}

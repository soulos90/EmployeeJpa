package com.cognixia.jump.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.repository.EmployeeRepository;
import com.cognixia.jump.model.Employee;
import com.cognixia.jump.model.EmployeeAge;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repo;
	
	public List<Employee> getEmployees(){
		return repo.findAll();
	}

	public Employee getEmployeeById(int id) {
		Optional<Employee> found = repo.findById(id);
		if(found.isEmpty()) {
			return null;
		}
		return found.get();
	}

	public Employee deleteEmployeeById(int id) {
		if( repo.existsById(id) ) {
			Employee toDelete = getEmployeeById(id);
			
			repo.deleteById(id);
			
			return toDelete;
		
		}
		
		return null;
	}

	public Employee createEmployee(Employee employee) {
		employee.setId(-1);
		
		Employee added = repo.save(employee);
		
		return added;
	}

	public Employee updateEmployee(Employee employee) {
		if( repo.existsById(employee.getId())) {
			Employee updated = repo.save(employee);
			return updated;
		}
		return null;
	}

	public List<Employee> getByDepartment(String department) {
		return repo.findByDepartment(department);
	}

	public List<EmployeeAge> getEmployeesAges() {
		List<EmployeeAge> retVal = new ArrayList<EmployeeAge>();
		for(Employee emp : repo.findAll()) {
			retVal.add(new EmployeeAge(emp));
		}
		System.out.println(retVal);
		return retVal;
	}
}

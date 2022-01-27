package com.cognixia.jump.model;

import java.time.LocalDate;
import java.time.Period;

public class EmployeeAge extends Employee {

	private static final long serialVersionUID = 5774621491910285793L;
	
	private Integer age; 
	
	public EmployeeAge(Employee employee) {
		setId(employee.getId());
		
		setFirstName(employee.getFirstName()); 
		
		setLastName(employee.getLastName());
		
		setEmail(employee.getEmail());
		
		setDepartment(employee.getDepartment());
		
		setDob(employee.getDob());
		
		age = Period.between(getDob(),LocalDate.now()).getYears();
	}

	@Override
	public String toString() {
		
		return super.toString().split("]")[0] + ", age=" + age + "]";
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	
}

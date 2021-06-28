package com.example.demo.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.exception.APIException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;

public interface EmployeeService {

	public List<Employee> getAllEmployee();
	
	public String createEmployee(Employee emp);
	
	public String createAllEmployee(List<Employee> emp);
	
	public ResponseEntity<Employee> getEmployeeById(long id) throws ResourceNotFoundException;
	
	public ResponseEntity<Employee> updateEmployee(long id, Employee empDetails) throws ResourceNotFoundException;
	
	public ResponseEntity<Employee> deleteEmployee(long id) throws APIException;
	
	public Employee getEmployeeByName(String name);
}

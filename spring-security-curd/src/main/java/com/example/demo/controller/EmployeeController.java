package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.example.demo.exception.APIException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.services.EmployeeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	// create get all employee

	@GetMapping("/employees")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployee();
	}

	// create employee

	@PostMapping("/employeesSave")
	public String createEmployee(@Valid @RequestBody Employee emp) {
		return employeeService.createEmployee(emp);
	}

	@PostMapping("/allEmployeesSave")
	public String createAllEmployee(@RequestBody List<Employee> emp) {
		return employeeService.createAllEmployee(emp);

	}
	
	
	@ApiOperation(value="Find employee by id",
			notes="Provide an id to lookup specific employee details",
			response=Employee.class
			)
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@ApiParam(value="ID value to retrieve employee", required=true)   @PathVariable("id") long id) throws ResourceNotFoundException {
		return employeeService.getEmployeeById(id);
	}

	// update employee

	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee empDetails)
			throws ResourceNotFoundException {
		return employeeService.updateEmployee(id, empDetails);
	}

	// delete employee

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") long id) throws APIException {
		return employeeService.deleteEmployee(id);
	}

	
	@GetMapping("/employeesByName/{name}")
	public Employee getEmployeeByName(@PathVariable("name") String name) {
		return employeeService.getEmployeeByName(name);
	}

	/*
	 * @GetMapping("/employees/{id}") public Employee
	 * getEmployeeById(@PathVariable("id") long id){ Optional<Employee> optional =
	 * empRepository.findById(id); Employee employee=null; if(optional.isPresent())
	 * { employee = optional.get(); } else { throw new
	 * RuntimeException("Employee not found for id: "+ id); } return employee; }
	 */
}

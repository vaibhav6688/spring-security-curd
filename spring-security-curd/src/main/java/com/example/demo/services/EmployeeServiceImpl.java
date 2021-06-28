package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.exception.APIException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployee() {
		log.info("get all employee method");
		return employeeRepository.findAll();
	}

	@Override
	public String createEmployee(Employee emp) {
		Employee e= employeeRepository.save(emp);
		log.info("Record is saved successfully" + e.getId());
		 //return String.valueOf("Record is saved successfully" + e.getId());
		 return String.valueOf(e.getId());
	}

	@Override
	public String createAllEmployee(List<Employee> emp) {
		 employeeRepository.saveAll(emp);
		 return "All Records are saved successfully";
	}

	@Override
	public ResponseEntity<Employee> getEmployeeById(long id) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found for id: "+ id));
		return ResponseEntity.ok().body(employee);
	}

	@Override
	public ResponseEntity<Employee> updateEmployee(long id, Employee empDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not found for id: "+ id));
		employee.setFirstName(empDetails.getFirstName());
		employee.setLastName(empDetails.getLastName());
		employee.setEmailId(empDetails.getEmailId());
		employeeRepository.save(employee);
		return ResponseEntity.ok().body(employee);
	}

	@Override
	public ResponseEntity<Employee> deleteEmployee(long id) throws APIException {
		employeeRepository.findById(id).orElseThrow(()-> new APIException("Employee not found for id: "+ id));
		employeeRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@Override
	public Employee getEmployeeByName(String name) {
		return employeeRepository.findByfirstName(name);
	}

}

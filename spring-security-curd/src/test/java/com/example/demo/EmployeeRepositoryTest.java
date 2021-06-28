package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@DataJpaTest
public class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@BeforeEach
	void setUp() {
		
		Employee employee = Employee.builder().id(1L)
											  .firstName("vaibhav")
											  .lastName("jain")
											  .emailId("vjain@gmail.com")
											  .build();
		
		entityManager.persist(employee);
	}
 
	@Test
	public void findByIdTest() {
		Employee employee = employeeRepository.findById(1L).get();
		
		assertEquals(employee.getFirstName(), "vaibhav");
	}
}

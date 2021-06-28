package com.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.services.EmployeeService;

@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	@BeforeEach
	void setUp() {
		Employee employee = Employee.builder().firstName("ashutosh").lastName("kumar dubey")
				.emailId("ashudubey@gmail.com").build();

		Mockito.when(employeeRepository.findByfirstName("ashutosh")).thenReturn(employee);
	}

	@Test
	//@DisplayName("Get data based on employee by name")
	public void getEmployeeByNameTest() {
		String name = "ashutosh";

		Employee foundName = employeeService.getEmployeeByName(name);

		assertEquals(name, foundName.getFirstName());
	}

}

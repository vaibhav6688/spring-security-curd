package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.services.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTesting {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objMapper;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepo;

	@Test
	public void createEmployeeTest() throws JsonProcessingException, Exception
	{
		Employee inputEmployee = new Employee(11, "Hemant", "kumar", "hkumar@gmail.com");

		String uri = "/employeesSave";

	MvcResult mvcResult = mockMvc.perform(post(uri).contentType("application/json").content(objMapper.writeValueAsString(inputEmployee)))
				.andExpect(status().isOk())
				.andDo(print()).andReturn();
	
	String response = mvcResult.getResponse().getContentAsString();
	
	Long id = Long.valueOf(response);
	//Employee id = employeeService.getEmployeeById(id);
	
	Employee savedEmp = employeeRepo.findById(id).get();
	
	assertThat(savedEmp.getId()).isEqualTo(inputEmployee.getId());
	

	}
}

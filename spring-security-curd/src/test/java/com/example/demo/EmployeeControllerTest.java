package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.controller.EmployeeController;
import com.example.demo.model.Employee;
import com.example.demo.services.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objMapper;

	@MockBean
	private EmployeeService employeeService;

	private Employee employee;

	@BeforeEach
	void setUp() {
		employee = Employee.builder().id(1).firstName("Vaibhav").lastName("Jain").emailId("vaiain@gmial.com").build();

	}

	@Test
	void createEmployeeTest() throws JsonProcessingException, Exception {
		Employee inputEmployee = Employee.builder().firstName("Vaibhav").lastName("Jain").emailId("vaiain@gmial.com")
				.build();

		Mockito.when(employeeService.createEmployee(inputEmployee)).thenReturn(employee.toString());

		String uri = "/employeesSave";

		mockMvc.perform(post(uri).contentType("application/json").content(objMapper.writeValueAsString(inputEmployee)))
				.andExpect(status().isOk())
				// .andExpect(content().string("1"))
				.andDo(print());

	}

	@Test
	public void getAllEmployeeTest() throws Exception {
		List<Employee> emp = new ArrayList<>();

		emp.add(new Employee(10, "vikas", "mundra", "vmundra@gmail.com"));
		emp.add(new Employee(11, "gaurav", "bhandari", "gbhandari@gmail.com"));
		emp.add(new Employee(12, "tushar", "agrawal", "tagrawal@gmail.com"));

		Mockito.when(employeeService.getAllEmployee()).thenReturn(emp);

		String uri = "/employees";

		MvcResult mvcResult = mockMvc.perform(get(uri)).andExpect(status().isOk()).andReturn();

		String actualJsonResponxe = mvcResult.getResponse().getContentAsString();

		String expectJsonResponse = objMapper.writeValueAsString(emp);

		assertThat(actualJsonResponxe).isEqualToIgnoringWhitespace(expectJsonResponse);
	}
}

package com.bnt.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bnt.entity.Employee;
import com.bnt.exception.EmployeeNotFoundException;
import com.bnt.service.EmployeeService;

@SpringBootTest
class EmployeeControllerTest {

	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	public Employee getEmployee() {
		Employee employee = new Employee();
		employee.setEmployeeId(1L);
		employee.setFirstName("Sai");
		employee.setLastName("John");
		employee.setEmail("sai12@gmail.com");
		employee.setPassword("sai@12");
		return employee;

	}

	@Test
	void testRegister() {
		Employee employee = getEmployee();

		when(employeeService.register(employee)).thenReturn(employee);

		Employee result = employeeController.register(employee);

		assertNotNull(result);
		assertEquals(employee.getEmployeeId(), result.getEmployeeId());
		assertEquals(employee.getFirstName(), result.getFirstName());
	}

	@Test
	void testGetAllEmployees() {
		Employee employee = getEmployee();
		List<Employee> employees = new ArrayList<>();
		employees.add(employee);

		when(employeeService.getAllEmployees()).thenReturn(employees);

		List<Employee> result = employeeController.getAllEmployees();
		assertEquals(employees, result);

		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	void testGetEmployeeById() {
		Employee employee = getEmployee();
		when(employeeService.getEmployeeById(employee.getEmployeeId())).thenReturn(employee);

		ResponseEntity<Employee> response = employeeController.getEmployeeById(employee.getEmployeeId());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(employee.getEmployeeId(), response.getBody().getEmployeeId());
		assertEquals("Sai", response.getBody().getFirstName());
	}

	@Test
	void testGetEmployeeById_EmployeeNotFoundException() {

		Long employeeId = 1L;

		when(employeeService.getEmployeeById(employeeId)).thenThrow(EmployeeNotFoundException.class);

		assertThrows(EmployeeNotFoundException.class, () -> {
			employeeController.getEmployeeById(employeeId);
		});
	}

}

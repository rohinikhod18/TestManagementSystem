package com.bnt.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.bnt.repository.EmployeeRepository;
import com.bnt.repository.TestRepository;

@SpringBootTest
class TestsAssignControllerTest {

	@InjectMocks
	public TestsAssignController testController;
	@Mock
	public EmployeeRepository employeeRepository;
	@Mock
	public TestRepository testRepository;
	@Mock
	public WebClient.Builder webClientBuilder;

    
}

package com.bnt.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.bnt.entity.AssignTest;
import com.bnt.entity.AssignTestResponse;
import com.bnt.entity.TestsDTO;
import com.bnt.exception.EmployeeNotFoundException;
import com.bnt.exception.TestIdNotExistException;
import com.bnt.repository.EmployeeRepository;
import com.bnt.repository.TestRepository;


@Service
public class TestsAssignServiceImpl implements TestsAssignService{
	
	@Autowired 
	public EmployeeRepository employeeRepository;

	@Autowired
	public TestRepository assignTestRepository;
	
	public WebClient webClient;

	public TestsAssignServiceImpl(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
	}
	
	private static final Logger logger = LoggerFactory.getLogger(TestsAssignServiceImpl.class);

	@Override
	public AssignTest assignTest(Long test_id,  Long employeeId) {
		AssignTest assignTest = new AssignTest();
		try {

			if (employeeRepository.existsById(employeeId)) {
				assignTest.setEmployeeId(employeeId);
				assignTest.setTestId(test_id);
				logger.info("Getting employee by ID: {}", assignTest.toString());
				assignTestRepository.save(assignTest);
			}

		} catch (EmployeeNotFoundException ex) {
			logger.error("Employee not found with ID: {}", employeeId, ex);
			throw new EmployeeNotFoundException("Error occurred while getById employee" + ex);
		}
		return assignTest;
	}
	
	@Override
	public AssignTestResponse callServer(Long employeeId) {
		AssignTestResponse response = new AssignTestResponse();
		List<TestsDTO> testDto = new ArrayList<>();
		try {			
			List<AssignTest> assignTest = assignTestRepository.findByEmployeeId(employeeId);
			for (AssignTest test : assignTest) {
				TestsDTO td = webClient.get().uri("/tests/{test_id}", test.getTestId()).retrieve()
						.bodyToMono(TestsDTO.class).block();
				testDto.add(td);
			}
			response.setEmployeeId(employeeId);
			response.setTests(testDto);
		} catch (TestIdNotExistException ex) {
			logger.error("Tests not found with ID: {}", testDto, ex);
			throw new TestIdNotExistException("Error occurred while getById Tests" + ex);
		}
		return response;
	}

}

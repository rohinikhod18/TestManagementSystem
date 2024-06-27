package com.bnt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.entity.AssignTest;
import com.bnt.entity.AssignTestResponse;
import com.bnt.service.TestsAssignService;

@RestController
@RequestMapping("/assignTests")
public class TestsAssignController {

	private static final Logger logger = LoggerFactory.getLogger(TestsAssignController.class);

	@Autowired
	public TestsAssignService service;

	@PostMapping("/{employeeId}/{test_id}")
	public ResponseEntity<AssignTest> assignTest(@PathVariable("test_id") Long test_id,
			@PathVariable("employeeId") Long employeeId) {

		AssignTest assignTest = service.assignTest(test_id, employeeId);
		logger.info("Getting employee by ID: {}", assignTest.toString());
		return ResponseEntity.status(HttpStatus.OK).body(assignTest);
	}

	@GetMapping("/call-server/{employeeId}")
	public ResponseEntity<AssignTestResponse> callServer(@PathVariable("employeeId") Long employeeId) {
		
		AssignTestResponse response = service.callServer(employeeId);
		logger.info("Getting employee by ID: {}", response.toString());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}

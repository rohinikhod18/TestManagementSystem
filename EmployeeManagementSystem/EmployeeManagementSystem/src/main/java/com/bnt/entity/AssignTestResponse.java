package com.bnt.entity;

import java.util.List;

public class AssignTestResponse {

	private Long employeeId;
	private List<TestsDTO> tests;
	
	public AssignTestResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AssignTestResponse(Long employeeId, List<TestsDTO> tests) {
		super();
		this.employeeId = employeeId;
		this.tests = tests;
	}

	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public List<TestsDTO> getTests() {
		return tests;
	}
	public void setTests(List<TestsDTO> tests) {
		this.tests = tests;
	}
	
}

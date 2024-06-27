package com.bnt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "assignTest")
public class AssignTest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long assignTestId;

	private long employeeId;
	
	private long testId;

	public Long getAssignTestId() {
		return assignTestId;
	}

	public void setAssignTestId(Long assignTestId) {
		this.assignTestId = assignTestId;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getTestId() {
		return testId;
	}

	public void setTestId(long testId) {
		this.testId = testId;
	}

	@Override
	public String toString() {
		return "AssignTest [assignTestId=" + assignTestId + ", employeeId=" + employeeId + ", testId=" + testId + "]";
	}


}

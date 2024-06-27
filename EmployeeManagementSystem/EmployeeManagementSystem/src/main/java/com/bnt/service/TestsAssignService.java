package com.bnt.service;

import com.bnt.entity.AssignTest;
import com.bnt.entity.AssignTestResponse;

public interface TestsAssignService {
	
	public AssignTest assignTest(Long test_id,  Long employeeId);
	
	public AssignTestResponse callServer(Long employeeId);

}

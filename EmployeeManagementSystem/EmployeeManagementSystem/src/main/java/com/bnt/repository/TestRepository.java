package com.bnt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.entity.AssignTest;

@Repository
public interface TestRepository extends JpaRepository<AssignTest, Long> {

	List<AssignTest> findByEmployeeId(Long employeeId);
}


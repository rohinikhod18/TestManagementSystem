package com.bnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.entity.Tests;

@Repository
public interface TestRepository extends JpaRepository<Tests, Long>{

}
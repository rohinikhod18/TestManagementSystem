package com.bnt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.entity.QuestionsTests;
import com.bnt.entity.Tests;

@Repository
public interface QuestionTestRepository extends JpaRepository<QuestionsTests, Long> {

	List<QuestionsTests> findByTests(Tests tests);

}

package com.mycom.student_management.controller;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface InfoJpaRepository extends JpaRepository<StudentInfo, String> {
	
	@Query(value = "CALL getScoresByScoreDesc", nativeQuery = true)
	List<StudentInfo> getScoresByScoreDesc();
}

package com.mycom.student_management.jparepository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mycom.student_management.entity.StudentInfo;


@Repository
public interface InfoJpaRepository extends JpaRepository<StudentInfo, String> {
	
	@Query(value = "CALL getScoresByScoreDesc", nativeQuery = true)
	List<StudentInfo> getScoresByScoreDesc();
	
}

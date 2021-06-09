package com.mycom.student_management.jparepository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mycom.student_management.entity.StudentScore;


@Repository
public interface ScoreJpaRepository extends JpaRepository<StudentScore, Integer> {
	
	@Query(value = "CALL getScoresByScoreDesc", nativeQuery = true)
	List<StudentScore> getScoresByScoreDesc();
	
	@Query(value = "select student_no from student_score where student_no=:no", nativeQuery = true)
	String hoho(@Param("no") String student_no);
	
	
}

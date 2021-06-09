package com.mycom.student_management.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycom.student_management.entity.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, String> {
	User findByUsername(String username);
}

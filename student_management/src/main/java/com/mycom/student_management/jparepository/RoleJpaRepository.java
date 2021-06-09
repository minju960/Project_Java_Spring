package com.mycom.student_management.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mycom.student_management.entity.Role;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Integer> {
	
	// 하나 이상의 롤을 가질 수 있으므로 (e.g. 어드민, 에디터)
	Optional<List<Role>> findByUsername(String username);
	
}

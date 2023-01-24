package com.scube.invoicing.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.scube.invoicing.entity.RoleEntity;
import com.scube.invoicing.entity.UserInfoEntity;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {

	UserInfoEntity findByUsername(String username);

	UserInfoEntity findById(String userId);

	UserInfoEntity findByMobilenumber(String mobilenumber);
	
	List<UserInfoEntity> findByRoleAndStatus(RoleEntity nameCode,String status);

	
	@Query(value = "SELECT count(id) FROM emp_info where created_at between DATE_SUB(now(), INTERVAL 7 DAY) and now();", nativeQuery = true)
	int findCountForWeekNewAddedUser();
	
}

package com.mhp.staffservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mhp.staffservice.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, String>{
	
	@Query(value = "SELECT staff_Id FROM staff ORDER BY staff_Id DESC LIMIT 1", nativeQuery = true)
	String findLastStaffId();

}

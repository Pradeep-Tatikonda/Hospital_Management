package com.mhp.staffservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhp.staffservice.model.StaffAddress;

@Repository
public interface StaffAddressRepository extends JpaRepository<StaffAddress, Long>{

}

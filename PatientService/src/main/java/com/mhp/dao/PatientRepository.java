package com.mhp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mhp.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String>{
	
	@Query(value = "SELECT patient_id FROM patients ORDER BY patient_id DESC LIMIT 1", nativeQuery = true)
	String findLastPatientId();
	
	
	Optional<Patient> findByPatientPhoneNumber(String patientPhoneNumber);

}

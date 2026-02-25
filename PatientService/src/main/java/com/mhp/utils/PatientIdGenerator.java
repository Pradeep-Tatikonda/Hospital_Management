package com.mhp.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.mhp.dao.PatientRepository;

@Component
public class PatientIdGenerator {
	
	private final PatientRepository patientRepository;

    public PatientIdGenerator(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    
    public String generateNextPatientId() {
    	String prefix = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    	String lastId = patientRepository.findLastPatientId();
    	
    	int nextNumber = 1;
    	String suffix = String.format("%06d", nextNumber);
    	
    	if (lastId !=null) {
    		String lastIdDate = lastId.substring(0,8);
    		if (prefix.equals(lastIdDate)) {
    			String numberPart = lastId.substring(8);
    			nextNumber = Integer.parseInt(numberPart)+1;
    			suffix = String.format("%06d", nextNumber);
    			
    		}else {  			
    			suffix = String.format("%06d", 1);
    		}
    	}
    	
    	return prefix+suffix;
    }

}

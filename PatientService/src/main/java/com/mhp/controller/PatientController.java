package com.mhp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mhp.dto.RegisterPatientRequestDto;
import com.mhp.dto.RegisterPatientResponseDto;
import com.mhp.service.PatientService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/patients")
@Slf4j
@Validated
public class PatientController {
	
	private final PatientService patientService;
	
	public PatientController(PatientService patientService) {
		
		this.patientService = patientService;
	
	}
	
	// commit
	@PostMapping("/register")
	public ResponseEntity<?> registerPatient(@Valid @RequestBody RegisterPatientRequestDto registerPatientRequestDto, BindingResult result){
		
		if (result.hasErrors()) {
			
			log.debug("No of error fields: {} " + result.getFieldErrorCount());
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result.getFieldErrors());
			
		}
		
		RegisterPatientResponseDto registerPatient = patientService.registerPatient(registerPatientRequestDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(registerPatient);
	}
	
	@GetMapping("/patient-info")
	public ResponseEntity<RegisterPatientResponseDto> getPatientByPhoneNumber(@RequestParam @Size(max = 10, min = 10) String phoneNumber){
		
		return ResponseEntity.status(HttpStatus.OK).body(patientService.getPatientByPhoneNumber(phoneNumber));
	}

}

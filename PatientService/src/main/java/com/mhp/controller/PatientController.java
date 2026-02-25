package com.mhp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mhp.dto.RegisterPatientRequestDto;
import com.mhp.dto.RegisterPatientResponseDto;
import com.mhp.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {
	
	private final PatientService patientService;
	
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<RegisterPatientResponseDto> registerPatient(@RequestBody RegisterPatientRequestDto registerPatientRequestDto){
		RegisterPatientResponseDto registerPatient = patientService.registerPatient(registerPatientRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(registerPatient);
	}

}

package com.mhp.service;

import com.mhp.dto.RegisterPatientRequestDto;
import com.mhp.dto.RegisterPatientResponseDto;

public interface PatientService {

	public RegisterPatientResponseDto registerPatient(RegisterPatientRequestDto registerPatientRequestDto);

}

package com.mhp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mhp.dto.RegisterPatientRequestDto;
import com.mhp.dto.RegisterPatientResponseDto;
import com.mhp.service.PatientService;
import com.mhp.utils.Gender;

@WebMvcTest(PatientController.class)
class PatientControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private PatientService patientService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void testPatientRegisterSuccess() throws Exception {
		
		RegisterPatientRequestDto request = new RegisterPatientRequestDto();
		request.setPatientName("Deep");
		request.setPatientPhoneNumber("9581882173");
		request.setGender(Gender.MALE);
		request.setPatientEmail("wap@gmail.com");
		request.setDateOfBirth(LocalDate.of(2025, 10, 10));
		
		
		RegisterPatientResponseDto response = new RegisterPatientResponseDto();
        response.setPatientId("202501011001");
        response.setPatientName("Pradeep");
        
        Mockito.when(patientService.registerPatient(Mockito.any())).thenReturn(response);
        
        mockMvc.perform(post("/patients/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.patientName").value("Pradeep"));
		
	}
	
	@Test
    void testRegisterPatient_validationFail() throws Exception {

        RegisterPatientRequestDto request = new RegisterPatientRequestDto();
        request.setPatientName(""); // invalid
        request.setPatientPhoneNumber("123"); // invalid

        mockMvc.perform(post("/patients/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }
	
	@Test
	void testGetPatientByPhoneNumber_success() throws Exception{
		
		RegisterPatientResponseDto response = new RegisterPatientResponseDto();
        response.setPatientId("202501011001");
        response.setPatientName("Pradeep");
        response.setPatientPhoneNumber("9581882173");
        
		Mockito.when(patientService.getPatientByPhoneNumber("9581882173")).thenReturn(response);
		
		mockMvc.perform(get("/patients/patient-info").param("phoneNumber", "9581882173"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.patientName").value("Pradeep"));
	
	}
	
	 @Test
	    void testGetPatientByPhoneNumber_invalid() throws Exception {

	        mockMvc.perform(get("/patients/patient-info")
	                        .param("phoneNumber", "123"))
	                .andExpect(status().isConflict());
	    }

}

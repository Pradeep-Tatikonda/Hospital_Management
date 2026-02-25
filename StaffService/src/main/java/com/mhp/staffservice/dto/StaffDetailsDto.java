package com.mhp.staffservice.dto;

import java.time.LocalDate;

import com.mhp.staffservice.enums.Specialization;
import com.mhp.staffservice.enums.StaffType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffDetailsDto {

	private String staffId;

	private String firstName;

	private String lastName;

	private String phoneNumber;

	private String role;

	private String gender;

	private LocalDate dateOfJoining;

	private int experienceInYears;

	private String email;

	private Specialization specialization;

	private StaffType staffType;

	private boolean isEmployeeActive;

	private boolean canLogin;

	private StaffAddressDto staffAddressDto;

}

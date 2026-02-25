package com.mhp.staffservice.model;

import java.time.LocalDate;

import com.mhp.staffservice.enums.Specialization;
import com.mhp.staffservice.enums.StaffType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {
	
	@Id
	@Column(name = "staff_Id", nullable = false, unique = true)
	private String staffId;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(length = 10, nullable = false)
	private String phoneNumber;
	
	@Enumerated(EnumType.STRING)
	private StaffType staffType;
	
	@Column(nullable = false)
	private String role;
	
	@Enumerated(EnumType.STRING)
	private Specialization specialization;
	
	@Column(nullable = false)
	private LocalDate dateOfJoining;
	
	@Column(nullable = false)
	private int experienceInYears;
	
	@Column(nullable = false)
	private boolean canLogin;
	
	@Column(nullable = false)
	private boolean isEmployeeActive;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "staffAddressId")
	private StaffAddress staffAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "staffDetailsId")
	private StaffDetails staffDetails;
	
	

}

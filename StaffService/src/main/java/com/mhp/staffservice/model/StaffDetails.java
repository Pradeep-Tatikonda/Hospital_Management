package com.mhp.staffservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staff_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long staffDetailsId;

	private String email;

	private String password;

	public StaffDetails(String email, String password) {

		this.email = email;

		this.password = password;
	}

	public StaffDetails(String email) {

		this.email = email;

	}

}

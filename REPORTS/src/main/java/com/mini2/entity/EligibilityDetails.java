package com.mini2.entity;

import java.time.LocalDate;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@EntityScan
@Table(name = "ELIGIBILITY_DETAILS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EligibilityDetails 
{
	@Id
	@Column(name = "ELIGIBLITY_ID")
	private Integer eligibleId;
	
	@Column(name = "NAME")
	private String Name;
	
	@Column(name = "MOBILENUMBER")
	private Long mobileNumber;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "SSN")
	private Integer ssn;
	
	@Column(name = "PLAN_NAME")
	private String planName;
	
	@Column(name = "PLAN_STATUS")
	private String status;
	
	@Column(name = "PLAN_START_DATE")
	private LocalDate planStartDate;
	
	@Column(name = "PLAN_END_DATE")
	private LocalDate planEndDate;
	
	@Column(name = "CREATED_DATE")
	private LocalDate createDate;
	
	@Column(name = "UPDATED_DATE")
	private LocalDate updatedDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
}
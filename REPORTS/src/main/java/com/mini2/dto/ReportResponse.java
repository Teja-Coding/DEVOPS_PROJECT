package com.mini2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponse 
{
	private Integer eligibleId;

	private String Name;

	private Long mobileNumber;

	private String email;

	private String gender;

	private Integer ssn;
}

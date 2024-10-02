package com.mini2.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequest 
{
	private String planName;

	private String status;

	private LocalDate planStartDate;

	private LocalDate planEndDate;
}
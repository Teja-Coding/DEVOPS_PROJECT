package com.mini5.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "DC_CASE")
public class DcCaseEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Long CaseNum;
	
	private Integer appId;
	
	private Integer planId;
}
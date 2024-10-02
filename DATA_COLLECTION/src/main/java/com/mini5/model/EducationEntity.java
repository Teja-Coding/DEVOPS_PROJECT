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
@Table(name = "EDUCATION")
public class EducationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eduId;

	private Long caseNum;

	private String highestQualification;

	private Integer graduationYear;

	private String university;
}
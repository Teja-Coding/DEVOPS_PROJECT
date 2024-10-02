package com.mini3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivateAccount 
{
	private String email;
	
	private String tempPassword;
	
	private String newPassword;
	
	private String confirmPassword;
}
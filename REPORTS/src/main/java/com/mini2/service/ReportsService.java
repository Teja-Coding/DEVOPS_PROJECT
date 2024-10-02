package com.mini2.service;

import java.util.List;

import com.mini2.dto.ReportRequest;
import com.mini2.dto.ReportResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportsService
{
	List<String> getUniquePlanName();
	
	List<String> getUniquePlanStatus();
	
	List<ReportResponse> search(ReportRequest request);
	
	void generateExcel(HttpServletResponse response)throws Exception;
	
	void generatedPdf(HttpServletResponse response)throws Exception;
}
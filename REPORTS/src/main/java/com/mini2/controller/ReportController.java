package com.mini2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mini2.dto.ReportRequest;
import com.mini2.dto.ReportResponse;
import com.mini2.service.ReportsService;
import com.mini2.utils.AppConstrants;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping(value = "/dashboard/admin2")
public class ReportController
{
	private ReportsService reportService;

	public ReportController(ReportsService reportService)
	{
		this.reportService = reportService;
	}
	
	//GET
	@GetMapping(value = "/plans")
	ResponseEntity<List<String>> getUniquePlans()
	{
		return new ResponseEntity<>(reportService.getUniquePlanName()
				,HttpStatus.OK);
	}
	
	//GET
	@GetMapping(value = "/status")
	ResponseEntity<List<String>> getUniqueStatus()
	{
		return new ResponseEntity<>(reportService.getUniquePlanStatus()
				,HttpStatus.OK);
	}
	
	//POST
	@PostMapping(value = "/search")
	ResponseEntity<List<ReportResponse>> search(@RequestBody ReportRequest request)
	{
		return new ResponseEntity<>(reportService.search(request)
				,HttpStatus.OK);
	}
	
	//GET
	@GetMapping(value = "/excel")
	void excelReport(HttpServletResponse response) throws Exception
	{
		response.setContentType(AppConstrants.APPLICATION_VND_MS_EXCEL);
		
		String headerKey=AppConstrants.CONTENT_DISPOSITION;
		String headerValue=AppConstrants.ATTACHMENT_FILENAME_DATA_XLS;
		
		response.setHeader(headerKey, headerValue);
		reportService.generateExcel(response);
	}
	
	//GET
	@GetMapping(value = "/pdf")
	void pdfReport(HttpServletResponse response) throws Exception
	{
		response.setContentType(AppConstrants.APPLICATION_PDF);
		
		String headerKey=AppConstrants.CONTENT_DISPOSITION;
		String headerValue=AppConstrants.ATTACHMENT_FILE_NAME_DATA_PDF;
		
		response.setHeader(headerKey, headerValue);
		reportService.generatedPdf(response);
	}
}
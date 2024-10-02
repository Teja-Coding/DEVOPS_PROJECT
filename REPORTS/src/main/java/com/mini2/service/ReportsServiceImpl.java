package com.mini2.service;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.mini2.dto.ReportRequest;
import com.mini2.dto.ReportResponse;
import com.mini2.entity.EligibilityDetails;
import com.mini2.repository.RepostesRepository;
import com.mini2.utils.AppConstrants;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class ReportsServiceImpl implements ReportsService {
	private RepostesRepository reportsRepo;

	public ReportsServiceImpl(RepostesRepository reportsRepo) {
		this.reportsRepo = reportsRepo;
	}

	// Get All PlanNames
	@Override
	public List<String> getUniquePlanName() {

		return reportsRepo.findAllUniquePlanNames();
	}

	// Get All Plan Status
	@Override
	public List<String> getUniquePlanStatus() {

		return reportsRepo.findAllUniquePlanStatus();
	}

	// Get Reports
	@Override
	public List<ReportResponse> search(ReportRequest request) {
		List<ReportResponse> response = new ArrayList<>();
		if (isEmptyRequest(request)) {
	        List<EligibilityDetails> entities = reportsRepo.findAll();
	        entities.forEach(entity -> {
	            ReportResponse rr = new ReportResponse();
	            BeanUtils.copyProperties(entity, rr);
	            response.add(rr);
	        });
	        return response;
	    }
		
		EligibilityDetails queryBuilder = new EligibilityDetails();
		if(request!=null)
		{
		if (request.getPlanName() != null && !request.getPlanName().equals(AppConstrants.EMPTY_MSG))
			queryBuilder.setPlanName(request.getPlanName());
		if (request.getStatus() != null && !request.getStatus().equals(AppConstrants.EMPTY_MSG))
			queryBuilder.setStatus(request.getStatus());
		if (request.getPlanStartDate() != null && request.getPlanStartDate().equals(AppConstrants.EMPTY_MSG) )
			queryBuilder.setPlanStartDate(request.getPlanStartDate());
		if (request.getPlanEndDate() != null && request.getPlanEndDate().equals(AppConstrants.EMPTY_MSG))
			queryBuilder.setPlanEndDate(request.getPlanEndDate());
		}
		Example<EligibilityDetails> exampl = Example.of(queryBuilder);
		List<EligibilityDetails> entites = reportsRepo.findAll(exampl);
		ReportResponse rr = new ReportResponse();
		entites.forEach(entity -> {
			BeanUtils.copyProperties(entity, rr);
			response.add(rr);
		});
		
		return response;
	}

	private boolean isEmptyRequest(ReportRequest request) {
	    return request == null ||
	           (request.getPlanName() == null || request.getPlanName().isEmpty()) &&
	           (request.getStatus() == null || request.getStatus().isEmpty());
	}
	
	//Get EXCEL Data Sheet
	@Override
	public void generateExcel(HttpServletResponse response) throws Exception {
		List<EligibilityDetails> getAllRecords = reportsRepo.findAll();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue(AppConstrants.ELIGIBLE_ID);
		headerRow.createCell(1).setCellValue(AppConstrants.NAME);
		headerRow.createCell(2).setCellValue(AppConstrants.EMAIL);
		headerRow.createCell(3).setCellValue(AppConstrants.MOBILE_NUMBER);
		headerRow.createCell(4).setCellValue(AppConstrants.GENDER);
		headerRow.createCell(5).setCellValue(AppConstrants.SSN);
		int indexrow = 0;
		for (EligibilityDetails entity : getAllRecords) {
			HSSFRow dataRow = sheet.createRow(indexrow);
			dataRow.createCell(0).setCellValue(entity.getEligibleId());
			dataRow.createCell(1).setCellValue(entity.getName());
			dataRow.createCell(2).setCellValue(entity.getEmail());
			dataRow.createCell(3).setCellValue(entity.getMobileNumber());
			dataRow.createCell(4).setCellValue(entity.getGender());
			dataRow.createCell(5).setCellValue(entity.getSsn());
			indexrow++;
		}
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	// Get PDF Data
	@Override
	public void generatedPdf(HttpServletResponse response) throws Exception {

		List<EligibilityDetails> getAllRecords = reportsRepo.findAll();

		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(20);
		font.setColor(Color.BLUE);
		Paragraph p = new Paragraph(AppConstrants.SEARCH_REPORTS, font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f,3.0F, 1.5f });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
		font1.setColor(Color.WHITE);

		cell.setPhrase(new Phrase(AppConstrants.ELIGIBLE_ID,font1));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase(AppConstrants.NAME, font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase(AppConstrants.EMAIL, font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase(AppConstrants.MOBILE_NUMBER, font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase(AppConstrants.GENDER, font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase(AppConstrants.SSN, font1));
		table.addCell(cell);

		for (EligibilityDetails element : getAllRecords) {
			table.addCell(String.valueOf(element.getEligibleId()));
			table.addCell(element.getName());
			table.addCell(element.getEmail());
			table.addCell(String.valueOf(element.getMobileNumber()));
			table.addCell(element.getGender());
			table.addCell(String.valueOf(element.getSsn()));
		}
		document.add(table);
		document.close();
	}
}

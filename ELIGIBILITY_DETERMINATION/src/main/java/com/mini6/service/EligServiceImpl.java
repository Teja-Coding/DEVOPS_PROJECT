package com.mini6.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mini6.constrants.AppConstants;
import com.mini6.dto.EligResponse;
import com.mini6.model.ChildrenEntity;
import com.mini6.model.CitizenApiEntity;
import com.mini6.model.CoTriggerEntity;
import com.mini6.model.DcCasesEntity;
import com.mini6.model.EducationEntity;
import com.mini6.model.EligDtlsEntity;
import com.mini6.model.IncomeEntity;
import com.mini6.model.PlanEntity;
import com.mini6.repository.ChildRepository;
import com.mini6.repository.CitizenAppRepository;
import com.mini6.repository.CoTriggerRepository;
import com.mini6.repository.DcCasesRepository;
import com.mini6.repository.EducationRepository;
import com.mini6.repository.EligDtlsRepository;
import com.mini6.repository.IncomeRepository;
import com.mini6.repository.PlanRepository;

@Service
public class EligServiceImpl implements EligService {
	
	private ChildRepository childRepository;

	private CitizenAppRepository citizenAppRepository;

	private CoTriggerRepository coTriggerRepository;

	private DcCasesRepository dcCasesRepository;

	private EducationRepository educationRepository;

	private EligDtlsRepository eligDtlsRepository;

	private IncomeRepository incomeRepository;

	private PlanRepository planRepository;

	public EligServiceImpl(ChildRepository childRepository, CitizenAppRepository citizenAppRepository,
			CoTriggerRepository coTriggerRepository, DcCasesRepository dcCasesRepository,
			EducationRepository educationRepository, EligDtlsRepository eligDtlsRepository,
			IncomeRepository incomeRepository, PlanRepository planRepository) {
		this.childRepository = childRepository;
		this.citizenAppRepository = citizenAppRepository;
		this.coTriggerRepository = coTriggerRepository;
		this.dcCasesRepository = dcCasesRepository;
		this.educationRepository = educationRepository;
		this.eligDtlsRepository = eligDtlsRepository;
		this.incomeRepository = incomeRepository;
		this.planRepository = planRepository;
	}

	@Override
	public EligResponse determineEligibility(Long caseNum) {
		Optional<DcCasesEntity> caseEntity = dcCasesRepository.findById(caseNum);
		Integer planId = null;
		String planName = null;
		Integer appId = null;

		if (caseEntity.isPresent()) {
			planId = caseEntity.get().getPlanId();
			appId = caseEntity.get().getAppId();
		}

		Optional<PlanEntity> planEntity = planRepository.findById(planId);
		if (planEntity.isPresent()) {
			PlanEntity plan = planEntity.get();
			planName = plan.getPlanName();
		}
		Optional<CitizenApiEntity> citizenEntity = citizenAppRepository.findById(appId);
		Integer age = 0;
		CitizenApiEntity citizenApiEntity = null;
		if (citizenEntity.isPresent()) {
			citizenApiEntity = citizenEntity.get();
			LocalDate dob = citizenApiEntity.getDob();
			LocalDate now = LocalDate.now();
			age = Period.between(dob, now).getYears();
		}
		EligResponse eligResponse = executePlanConditions(caseNum, planName, age);
		EligDtlsEntity eligDtlsEntity=new EligDtlsEntity();
		BeanUtils.copyProperties(eligResponse, eligDtlsEntity);
		
		eligDtlsEntity.setCaseNum(caseNum);
		eligDtlsEntity.setHolderName(citizenApiEntity.getFullName());
		eligDtlsEntity.setHolderSsn(citizenApiEntity.getSsn());
		eligDtlsRepository.save(eligDtlsEntity);
		
		CoTriggerEntity coTriggerEntity=new CoTriggerEntity();
		coTriggerEntity.setCaseNum(caseNum);
		coTriggerEntity.setTrgStatus(AppConstants.PENDING);
		coTriggerRepository.save(coTriggerEntity);
		
		return eligResponse;
	}

	EligResponse executePlanConditions(Long caseNum, String planName, Integer age) {
		EligResponse response = new EligResponse();
		response.setPlanName(planName);

		IncomeEntity income = incomeRepository.findByCaseNum(caseNum);
		if (AppConstants.SNAP.equals(planName)) {
			Double empIncome = income.getEmpIncome();
			if (empIncome <= 300) {
				response.setPlanStatus(AppConstants.APPROVED);
			} else {
				response.setPlanStatus(AppConstants.DENIED);
				response.setDenialReason(AppConstants.HIGH_INCOME);
			}
		} 
		else if (AppConstants.CCAP.equals(planName)) {
			Boolean ageCondition = true;
			Boolean kidCountCondition = false;
			List<ChildrenEntity> childs = childRepository.findByCaseNum(caseNum);
			if (!childs.isEmpty()) {
				kidCountCondition = true;
				for (ChildrenEntity childrenEntity : childs) {
					Integer childAge = childrenEntity.getChildAge();
					if (childAge > 16) {
						ageCondition = false;
						break;
					}
				}
			}
			if (income.getEmpIncome() <= 300 && ageCondition && kidCountCondition) {
				response.setPlanStatus(AppConstants.APPROVED);
			} else {
				response.setPlanStatus(AppConstants.DENIED);
				response.setDenialReason(AppConstants.NOT_SATISFIED_BUSINES_RULES);
			}
		}
		else if(AppConstants.MEDICAID.equals(planName))
		{
			Double empIncome = income.getEmpIncome();
			Double propertyIncome = income.getPropertyIncome();
			if(empIncome<=300 && propertyIncome==0)
			{
				response.setPlanStatus(AppConstants.APPROVED);
			}
			else 
			{
				response.setPlanStatus(AppConstants.DENIED);
				response.setDenialReason(AppConstants.HIGH_INCOME);
			}
		}
		else if(AppConstants.MEDICARE.equals(planName))
		{
			if(age>=65)
			{
				response.setPlanStatus(AppConstants.APPROVED);
			}
			else
			{
				response.setPlanStatus(AppConstants.DENIED);
				response.setDenialReason(AppConstants.AGE_NOT_MATCHED);
			}
		}
		else if(AppConstants.NJW.equals(planName))
		{
			EducationEntity educationEntity = educationRepository.findByCaseNum(caseNum);
			Integer graduationYear = educationEntity.getGraduationYear();
			Integer currentyear = LocalDate.now().getYear();
			if(income.getEmpIncome()<=0 && graduationYear<currentyear)
			{
				response.setPlanStatus(AppConstants.APPROVED);
			}
			else 
			{
				response.setPlanStatus(AppConstants.DENIED);
				response.setDenialReason(AppConstants.RULES_NOT_SATISFIED);
			}
		}
		if(response.getPlanStatus()==AppConstants.APPROVED)
		{
			response.setPlanStartDate(LocalDate.now());
			response.setPlanEndDate(LocalDate.now().plusMonths(6));
			response.setBenefitAmt(350.00);
		}
		return response;
	}
}

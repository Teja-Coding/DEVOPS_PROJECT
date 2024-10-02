package com.mini5.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mini5.dto.ChildBinding;
import com.mini5.dto.ChildRequestBinding;
import com.mini5.dto.EducationBinding;
import com.mini5.dto.IncomeBinding;
import com.mini5.dto.PlanSelctionBinding;
import com.mini5.dto.Summary;
import com.mini5.model.ChildernEntity;
import com.mini5.model.CititzenEntity;
import com.mini5.model.DcCaseEntity;
import com.mini5.model.EducationEntity;
import com.mini5.model.IncomeEntity;
import com.mini5.model.PlanEntity;
import com.mini5.repository.ChildRepository;
import com.mini5.repository.CitizenAppRepository;
import com.mini5.repository.DcCaseRepositroy;
import com.mini5.repository.EducationRepository;
import com.mini5.repository.IncomeRepository;
import com.mini5.repository.PlanRepository;

@Service
public class DataCollectionServiceImpl implements DataCollectionService 
{
	@Autowired
	private ChildRepository childRepository;
	
	@Autowired
	private CitizenAppRepository citizenAppRepository;
	
	@Autowired
	private DcCaseRepositroy dcCaseRepositroy;
	
	@Autowired
	private EducationRepository educationRepository;
	
	@Autowired
	private IncomeRepository incomeRepository;
	
	@Autowired
	private PlanRepository planRepository;
	
	//------------------------------------------------------
	@Override
	public Long loadCaseNum(Integer appId)
	{
		Optional<CititzenEntity> apId = citizenAppRepository.findById(appId);
		if(apId.isPresent())
		{
			DcCaseEntity dcCaseEntity=new DcCaseEntity();
			dcCaseEntity.setAppId(appId);
			dcCaseEntity=dcCaseRepositroy.save(dcCaseEntity);
			return dcCaseEntity.getCaseNum();
		}
		return 0l;
	}

	//---------------------------------------------------------
	@Override
	public Map<Integer, String> getPlans() 
	{
		List<PlanEntity> entitys = planRepository.findAll();
		Map<Integer, String> plansMap= new HashMap<>();
		for(PlanEntity entity:entitys)
			plansMap.put(entity.getPlanId(), entity.getPlanName());
		return plansMap;
	}

	//-----------------------------------------------------------
	@Override
	public Long savePlanSelection(PlanSelctionBinding palSelctionBinding) 
	{
		Optional<DcCaseEntity> findbyId = dcCaseRepositroy.findById(palSelctionBinding.getCaseNum());
		if(findbyId.isPresent())
		{
			DcCaseEntity dcCaseEntity = findbyId.get();
			dcCaseEntity.setPlanId(palSelctionBinding.getPlanId());
			dcCaseRepositroy.save(dcCaseEntity);
			return palSelctionBinding.getCaseNum();
		}
		return null;
	}

	//---------------------------------------------------------------
	@Override
	public Long saveIncomeData(IncomeBinding incomeBinding) {
		IncomeEntity incomeEntity=new IncomeEntity();
		BeanUtils.copyProperties(incomeBinding, incomeEntity);
		incomeRepository.save(incomeEntity);
		return incomeBinding.getCaseNum();
	}

	//---------------------------------------------------------------
	@Override
	public Long saveEducation(EducationBinding educationBinding) 
	{
		EducationEntity educationEntity=new EducationEntity();
		BeanUtils.copyProperties(educationBinding,educationEntity);
		educationRepository.save(educationEntity);
		return educationBinding.getCaseNum();
	}

	//-----------------------------------------------------------------
	@Override
	public Long saveChildernData(ChildRequestBinding childRequestBinding)
	{
		List<ChildBinding> childs=childRequestBinding.getChilds();
		Long caseNum = childRequestBinding.getCaseNum();
		for(ChildBinding childBinding:childs)
		{
			ChildernEntity entity=new ChildernEntity();
			BeanUtils.copyProperties(childBinding, entity);
			entity.setCaseNum(caseNum);
			childRepository.save(entity);
		}
		return childRequestBinding.getCaseNum();
	}

	//-----------------------------------------------------------------
	@Override
	public Summary getSummery(Long caseNum) 
	{
		String palnName="";
		
		IncomeEntity incomeEntity = incomeRepository.findByCaseNum(caseNum);
		EducationEntity educationEntity = educationRepository.findByCaseNum(caseNum);
		List<ChildernEntity> chliEntities = childRepository.findByCaseNum(caseNum);
		Optional<DcCaseEntity> findbyId = dcCaseRepositroy.findById(caseNum);
		if(findbyId.isPresent())
		{
			Integer planId = findbyId.get().getPlanId();
			Optional<PlanEntity> paln = planRepository.findById(planId);
			if(paln.isPresent())
				paln.get().getPlanName();
		}
		
		Summary summary=new Summary();
		summary.setPalnName(palnName);
		
		EducationBinding educationBinding =new EducationBinding();
		BeanUtils.copyProperties(educationEntity, educationBinding);
		summary.setEduaction(educationBinding);
		
		IncomeBinding incomeBinding=new IncomeBinding();
		BeanUtils.copyProperties(incomeEntity, incomeBinding);
		summary.setIncome(incomeBinding);
		
		List<ChildBinding> childs=new ArrayList<>();
		for(ChildernEntity entity:chliEntities)
		{
			ChildBinding childBinding=new ChildBinding();
			BeanUtils.copyProperties(entity, childBinding);
			childs.add(childBinding);
		}
		summary.setChilderns(childs);
		return summary;
	}

}

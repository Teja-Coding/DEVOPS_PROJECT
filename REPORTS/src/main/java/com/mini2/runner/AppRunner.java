package com.mini2.runner;

import java.time.LocalDate;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.mini2.entity.EligibilityDetails;
import com.mini2.repository.RepostesRepository;

@Component
public class AppRunner implements ApplicationRunner{

	private RepostesRepository repo;
	
	public AppRunner(RepostesRepository repo)
	{
		this.repo = repo;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception 
	{
		EligibilityDetails entry1=new EligibilityDetails();
		entry1.setEligibleId(1);
		entry1.setName("Jhon");
		entry1.setMobileNumber(8967236473l);
		entry1.setEmail("Jhone123@gmail.com");
		entry1.setGender("Male");
		entry1.setSsn(76446333);
		entry1.setPlanName("SNAP");
		entry1.setStatus("Approved");
		entry1.setPlanStartDate(LocalDate.of(2020,02,10));
		entry1.setPlanEndDate(LocalDate.of(2021,02,10));
		repo.save(entry1);
		
		EligibilityDetails entry2=new EligibilityDetails();
		entry2.setEligibleId(2);
		entry2.setName("Smith");
		entry2.setMobileNumber(9387936213l);
		entry2.setEmail("Smith23@gmail.com");
		entry2.setGender("Male");
		entry2.setSsn(8973612);
		entry2.setPlanName("CCAP");
		entry2.setStatus("Denied");
		entry2.setPlanStartDate(LocalDate.of(2019,05,15));
		entry2.setPlanEndDate(LocalDate.of(2022,06,20));
		repo.save(entry2);
		
		EligibilityDetails entry3=new EligibilityDetails();
		entry3.setEligibleId(3);
		entry3.setName("Robert");
		entry3.setMobileNumber(7856324587l);
		entry3.setEmail("Robert34@gmail.com");
		entry3.setGender("Male");
		entry3.setSsn(98456732);
		entry3.setPlanName("Medical");
		entry3.setStatus("Closed");
		entry3.setPlanStartDate(LocalDate.of(2021,04,30));
		entry3.setPlanEndDate(LocalDate.of(2023,06,30));
		repo.save(entry3);
	}
}

package com.min42.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.min42.service.SsnService;

@Service
public class SsnServiceImpl implements SsnService {

	@Override
	public String giveState(Long ssn) {
		// TODO Auto-generated method stub
		
		Map<String, String> map = new HashMap();

		String numAsString = String.valueOf(ssn);

		if (numAsString.charAt(0) == '4' || numAsString.charAt(0) == '1') {
			map.put(numAsString, "new jersey");
		} else if (numAsString.charAt(0) == '2' || numAsString.charAt(0) == '3') {
			map.put(numAsString, "Ohio");
		} else if (numAsString.charAt(0) == '5' || numAsString.charAt(0) == '6') {
			map.put(numAsString, "Califonia");
		} else if (numAsString.charAt(0) == '7' || numAsString.charAt(0) == '8') {
			map.put(numAsString, "Texas");
		} else if (numAsString.charAt(0) == '9' || numAsString.charAt(0) == '0') {
			map.put(numAsString, "Boston");
		}
		return map.get(numAsString);
	}
}

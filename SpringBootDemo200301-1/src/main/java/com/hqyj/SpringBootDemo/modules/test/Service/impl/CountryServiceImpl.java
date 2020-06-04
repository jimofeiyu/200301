package com.hqyj.SpringBootDemo.modules.test.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hqyj.SpringBootDemo.modules.test.Service.CountryService;
import com.hqyj.SpringBootDemo.modules.test.dao.CountryDao;
import com.hqyj.SpringBootDemo.modules.test.entity.Country;

@Service
public class CountryServiceImpl implements CountryService{
	
	
	@Autowired
	private CountryDao countryDao;
	
	@Override
	public Country getCountryByCountryId(int countryId) {
		return countryDao.getCountryByCountryId(countryId);
		
	}
	@Override
	public Country getCountryByCountryName(String countryName) {
		return countryDao.getCountryByCountryName(countryName);
	}
	


}

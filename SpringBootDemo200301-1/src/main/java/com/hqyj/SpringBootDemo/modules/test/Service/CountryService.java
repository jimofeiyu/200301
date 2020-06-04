package com.hqyj.SpringBootDemo.modules.test.Service;

import com.hqyj.SpringBootDemo.modules.test.entity.Country;

public interface CountryService {
      Country getCountryByCountryId( int countryId);
      
      Country getCountryByCountryName(String countryName);
      
      

}

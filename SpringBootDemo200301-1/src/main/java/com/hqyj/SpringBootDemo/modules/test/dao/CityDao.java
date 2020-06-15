package com.hqyj.SpringBootDemo.modules.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.hqyj.SpringBootDemo.modules.common.vo.SearchVo;
import com.hqyj.SpringBootDemo.modules.test.entity.City;

import io.lettuce.core.dynamic.annotation.Param;



@Mapper
public interface CityDao {
	/**
	 * 配置文件方式
	 * application.properties
	 * mybatis.type-aliases-package=com.hqyj.demo.modules.*.entity
	 * mybatis.mapper-locations=classpath:mapper/*Mapper.xml
	 * 读取cityMapper.xml，方法名和mapper中设置的id一致
	 */
	
	List<City> getCityesByCountryId(int countryId);
	
	/**
	 * #{countryId} ---- prepared statement, select * from m_city where country_id = ?
	 * ${countryId} ---- statement, select * from m_city where country_id = 1
	 */

	@Select("select*from m_city where country_id=#{countryId}")
	List<City> getCityesByCountryId2(int countryId);
	
	@Select("select *from m_city c left join m_country co on c.country_id = co.country_id where c.city_name=#{cityName} and c.local_city_name=#{localCityName}")
    City getCitiesByName(@Param("cityName")String cityName,String locaCityName);
	
	@Select("<script>"+
	            "select *from m_city"
	            + "<where> "
				+ "<if test='keyWord != \"\" and keyWord != null'>"
				+ " and (city_name like '%${keyWord}%' or local_city_name like '%${keyWord}%') "
				+ "</if>"
				+ "</where>"
				+ "<choose>"
				+ "<when test='orderBy != \"\" and orderBy != null'>"
				+ " order by ${orderBy} ${sort}"
				+ "</when>"
				+ "<otherwise>"
				+ " order by city_id desc"
				+ "</otherwise>"
				+ "</choose>"
				+ "</script>")
		List<City> getCitiesBySearchVo(SearchVo searchVo);
	
	
	
	    @Insert("insert into m_city (city_name, local_city_name, country_id, date_created) "
				+ "values (#{cityName}, #{localCityName}, #{countryId}, #{dateCreated})")
	    @Options(useGeneratedKeys = true,keyColumn = "city_id",keyProperty = "cityId")
	    void insertCity(City city);
        
	    @Update("upate m_city set local_city_name =#{localCityName} where city_id=#{cityId}")
	    void updateCity( City city);
	    
	    @DeleteMapping("delete from m_city where city_id = #{cityId}")
		void deleteCity(int  cityId);
	    
       
	

}

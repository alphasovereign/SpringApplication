package com.routing.example.app.repsitory.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.routing.example.app.repsitory.RoutesRepository;

@Repository
public class RoutesRepositoryImpl implements RoutesRepository {
	

	@Autowired
	private ResourceLoader resourceLoader;
	
	@Value("${external.city.source}")
	private String filePath;

	Logger logger = LoggerFactory.getLogger(RoutesRepositoryImpl.class);

	/**
	 * Read the city routes data from External/INTERNAL Source.
	 */
	@Override
	public List<String> getRoutes() {

		// Check the type of source. if the source is not from CLASSPATH, then prepend file type.
		filePath = filePath.startsWith("CLASSPATH") ? filePath :
			"file:".concat(filePath);
		
		logger.info("CITY ROUTE SOURCE REFERRING TO :{}",filePath);
	    try
	    {
			Resource resource = resourceLoader.getResource(filePath);
			InputStream inputStream = resource.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8));
			List<String>  cityRouteList = reader.lines().collect(Collectors.toList());
			
	        logger.info("ROUTE DATA PULLED FROM EXTERNAL SOURCE SUCCESSFULLY");
	        return cityRouteList;
	    } 
	    catch (Exception e) 
	    {
	        logger.error("EXCEPTION WHILE READING DATA FROM EXTERNAL SOURCE", e);
	    }
	    
	    return Collections.emptyList();
	}

}

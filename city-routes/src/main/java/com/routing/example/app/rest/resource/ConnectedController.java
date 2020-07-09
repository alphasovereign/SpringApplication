package com.routing.example.app.rest.resource;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.routing.example.app.service.impl.RouteServiceImpl;

@RestController
public class ConnectedController {

	Logger logger = LoggerFactory.getLogger(ConnectedController.class);
	
	@Autowired
	private RouteServiceImpl service;
	
	@GetMapping("/connected")
	public boolean getConnectedCities(@RequestParam Map<String,String> queryParams) {
		
		String origin = queryParams.get("origin");
		String destination = queryParams.get("destination") ;
		
		boolean doesRouteExistis = service.findRoute(origin, destination ) ? true: service.findRoute(destination, origin ) ;
		
		logger.info("REQUEST RECEIVED FOR ORIGIN:{}, DESTINATION:{}, AND THE ROUTE {} ",origin,destination, doesRouteExistis? "EXISTS":" DOES NOT EXIST");
		return true;
	}
}

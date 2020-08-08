package com.routing.example.app.rest.resource;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.routing.example.app.model.TT;
import com.routing.example.app.repsitory.MySqlRepo;
import com.routing.example.app.service.impl.RouteServiceImpl;

@RestController
public class ConnectedController {

	Logger logger = LoggerFactory.getLogger(ConnectedController.class);
	
	@Autowired
	private RouteServiceImpl service;
	
	@Autowired
	private MySqlRepo repository;
	
	/**
	 * Only API exposed to check whether there is any route exists.
	 * @param queryParams
	 * @return
	 */
	@GetMapping("/connected")
	public ResponseEntity<Boolean> getConnectedCities(@RequestParam Map<String,String> queryParams) {
		try {
			String origin = queryParams.get("origin");
			String destination = queryParams.get("destination") ;
	
			/**
			 * Display all the data
			 */
			Iterable<TT> localdata = repository.findAll();
			logger.info("Printing the info..");
			localdata.forEach(  System.out::print );
			logger.info("Completed Printing the info..");
			
			logger.info("REQUEST RECEIVED FOR ORIGIN:{}, DESTINATION:{}",origin,destination);
	
			//check for route in one direction, if the results are not positive, run the algorithm in reverse to find whether there exists a route 
			Boolean doesRouteExistis = service.findRoute(origin, destination ) ? true: service.findRoute(destination, origin ) ;
			
			logger.info("THE ROUTE FOR [ORIGIN:{}, DESTINATION:{}] -> {} ",origin,destination, doesRouteExistis? "EXISTS":" DOES NOT EXIST");
			return new ResponseEntity<Boolean>(doesRouteExistis,HttpStatus.OK);
		}catch (Exception e) {
			logger.error("EXCEPTION OCCURED WHILE PROCESSING",e);
			
			// Exception can handled based on different status codes, but for now sticking to generic error status code.
			return new ResponseEntity<Boolean>(Boolean.FALSE,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

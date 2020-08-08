package com.routing.example.app.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routing.example.app.constants.AppConstants.State;
import com.routing.example.app.model.Node;
import com.routing.example.app.repsitory.impl.RoutesRepositoryImpl;

@Service
public class RouteServiceImpl {


	@Autowired
	private RoutesRepositoryImpl routeRepostory;
	
	/**
	 * Hold the routes of all the cities
	 */
	private List<String> routes;
	
	/**
	 * Construct a route/graph map from given source. Graph holds the data for mapping across the different endpoints and navigation  
	 * @return
	 */
	private Map<Node,Set<Node>> getGraph(){
		
		Map<Node,Set<Node>> graph = new HashMap<>();
		Set<Node> nodes = new HashSet<>();
		
		// if the routes is already loaded from source, do not load it again.
		routes = routes == null ? routeRepostory.getRoutes()   : routes;
		
		// loop over each route and construct the graph 
		routes.stream().forEach( route ->{
			

			if( route.split(",").length != 2 ) throw new RuntimeException("Invalid Input Format");
			//get from and to city names from each line of txt file			
			String fromCity =  route.split(",")[0].trim();
			String toCity = route.split(",")[1].trim();

			// check whether there is any node exists for a given city, if not create it.
			Node fromCityNode = nodes.stream().filter( n -> fromCity.equalsIgnoreCase(n.getName())   ).findAny().
					orElse(  new Node(State.Unvisited, fromCity)) ;
			
			Node toCityNode = nodes.stream().filter( n -> toCity.equalsIgnoreCase(n.getName())   ).findAny().
					orElse(  new Node(State.Unvisited, toCity)  );
			
			nodes.add(fromCityNode);
			nodes.add(toCityNode);
			
			// the below are the list of routes/end cities from a given city
			Set<Node> routesFromCity =  Optional.ofNullable( graph.get(  fromCityNode  )).orElse( new HashSet<>() );    
			
			routesFromCity.add( toCityNode );
			
			//map the from city with all the cities it can connect to.
			graph.put(fromCityNode, routesFromCity );
		  }  
		);

		
		return graph;
	}
	

	/**
	 * Given source and destination, construct the route graph and find the route
	 * @param start
	 * @param destination
	 * @return
	 */
	public boolean findRoute(String origin, String destination) {
		
		Map<Node, Set<Node>> graph = getGraph() ;
		return findRoute(graph  , origin, destination);
		
	}

	/**
	 * Make a recursive call to check through all the given nodes.
	 * This is a kind of breadth first search (BFS ), if no destination found with immediate nodes then go to next level of depth.
	 * @param graph
	 * @param start
	 * @param destination
	 * @return
	 */
	private static boolean findRoute(Map<Node, Set<Node>> graph, String origin, String destination) {

		 // get the source node as of current state.
		 Optional<Node> fromCityNode = graph.keySet().stream().filter(  k -> origin.equalsIgnoreCase(k.getName())  ).findAny(); 
		 
		 //if this current is not visited already then check for all its adjacent cities
		 if( fromCityNode.isPresent() && fromCityNode.get().getState().equals(  State.Unvisited )) {

			 fromCityNode.get().setState(  State.Visited  );
			 //get all the adjacent cities from this source city
			 Set<Node> mileStones = graph.get(fromCityNode.get());
			 
			 //check whether the destination exists in the adjacent cities
			 Optional<Node> destinationNode = mileStones.stream().filter(  n -> n.getName().equalsIgnoreCase(  destination )   ).findAny();
			 
			 //if the destination is not found, make a recursive call.
			 return destinationNode.isPresent() ?  true : mileStones.stream().anyMatch(  n ->  findRoute(graph, n.getName(), destination)   ); 
			 
		 }
		 return false;
	}
}

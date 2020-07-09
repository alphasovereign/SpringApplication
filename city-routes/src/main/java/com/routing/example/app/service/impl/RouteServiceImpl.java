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
	
	private List<String> routes;
	/**
	 * 
	 * @return
	 */
	private Map<Node,Set<Node>> getGraph(){
		
		Map<Node,Set<Node>> graph = new HashMap<>();
		Set<Node> nodes = new HashSet<>();
		
		routes = routes == null ? routeRepostory.getRoutes()   : routes;
		
		routes.stream().forEach( route ->{
			
			String fromCity =  route.split(",")[0].trim();
			String toCity = route.split(",")[1].trim();

			Node fromCityNode = nodes.stream().filter( n -> fromCity.equalsIgnoreCase(n.getName())   ).findAny().
					orElse(  new Node(State.Unvisited, fromCity)) ;
			
			Node toCityNode = nodes.stream().filter( n -> toCity.equalsIgnoreCase(n.getName())   ).findAny().
					orElse(  new Node(State.Unvisited, toCity)  );
			
			nodes.add(fromCityNode);
			nodes.add(toCityNode);
			
			Set<Node> routesFromCity =  Optional.ofNullable( graph.get(  fromCityNode  )).orElse( new HashSet<>() );    
			
			routesFromCity.add( toCityNode );
			
			graph.put(fromCityNode, routesFromCity );
		  }  
		);

		
		return graph;
	}
	

	/**
	 * 
	 * @param start
	 * @param destination
	 * @return
	 */
	public boolean findRoute(String origin, String destination) {
		
		Map<Node, Set<Node>> graph = getGraph() ;
		return findRoute(graph  , origin, destination);
		
	}

	/**
	 * 
	 * @param graph
	 * @param start
	 * @param destination
	 * @return
	 */
	private static boolean findRoute(Map<Node, Set<Node>> graph, String origin, String destination) {

		
		 Optional<Node> fromCityNode = graph.keySet().stream().filter(  k -> origin.equalsIgnoreCase(k.getName())  ).findAny(); 
		 
		 
		 if( fromCityNode.isPresent() && fromCityNode.get().getState().equals(  State.Unvisited )) {

			 fromCityNode.get().setState(  State.Visited  );
			 Set<Node> mileStones = graph.get(fromCityNode.get());
			 Optional<Node> destinationNode = mileStones.stream().filter(  n -> n.getName().equalsIgnoreCase(  destination )   ).findAny();
			 
			 return destinationNode.isPresent() ?  true : mileStones.stream().anyMatch(  n ->  findRoute(graph, n.getName(), destination)   ); 
			 
		 }
		 return false;
	}
}

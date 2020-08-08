package com.routing.example.app.model;

import com.routing.example.app.constants.AppConstants.State;

/**
 * This holds the data of the city and its state, whether this has been visited or yet to be visisted.
 * @author alphasovereign
 *
 */
public class Node {
	
	public Node(State state, String name) {
		super();
		this.state = state;
		this.name = name;
	}
	private State state ;
	private String name;
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	// define the below to peek through the data during debug.
	@Override
	public String toString() {
		return "Node [state=" + state + ", name=" + name + "]";
	}
	

	/**
	 * override both equals and hashcode method, as this will be used as a key in hashmap object.
	 * It is intended to use only the city name for for uniqueness and equality of the object.
	 * rest of the state of the class is not considered.
	 */
	@Override
    public boolean equals(Object obj) {
		
		
        if (!(obj instanceof Node)) {
            return false;
        }
        
        if (getClass() != obj.getClass())
            return false;

        Node p = (Node) obj;
        return  this.name.equals(p.name);

    }
	
	@Override
    public int hashCode() {
		// consider the prime number to generate the unique hash code
        int result = 17;
        result = 31 * result + name.hashCode();
        return result;
    }


	
}

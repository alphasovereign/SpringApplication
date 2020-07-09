package com.routing.example.app.model;

import com.routing.example.app.constants.AppConstants.State;

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

	@Override
	public String toString() {
		return "Node [state=" + state + ", name=" + name + "]";
	}
	

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
        int result = 17;
        result = 31 * result + name.hashCode();
        return result;
    }


	
}

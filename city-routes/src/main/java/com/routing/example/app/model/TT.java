package com.routing.example.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class TT {

	@Override
	public String toString() {
		return "TT [tt_id=" + tt_id + ", name=" + name + "]";
	}

	@Id
	private int tt_id;
	
	private String name;

	public int getTt_id() {
		return tt_id;
	}

	public void setTt_id(int tt_id) {
		this.tt_id = tt_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

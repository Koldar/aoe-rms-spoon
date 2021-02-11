package com.thekoldar.aoe_rms_spoon.framework.models;

import com.thekoldar.aoe_rms_spoon.framework.IAoeObject;

public class StandardAoeObject implements IAoeObject {

	private int id;
	private String name;
	private String description;
	
	public StandardAoeObject(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	@Override
	public int getValue() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return String.format("%s (%d): %s", this.name, this.id, this.description);
	}
	
}

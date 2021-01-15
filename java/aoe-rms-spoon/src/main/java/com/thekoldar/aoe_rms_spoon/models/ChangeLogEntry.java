package com.thekoldar.aoe_rms_spoon.models;

import com.github.zafarkhaja.semver.Version;

public class ChangeLogEntry {

	private Version version;
	private String description;
	
	public ChangeLogEntry(Version version, String description) {
		super();
		this.version = version;
		this.description = description;
	}

	public Version getVersion() {
		return version;
	}

	public String getDescription() {
		return description;
	}
	
	
	
	
}

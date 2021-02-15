package com.thekoldar.aoe_rms_spoon.framework;

import com.github.zafarkhaja.semver.Version;

/**
 * Class representing a RMS change log entry. Used when adding an internal readme in the RMS script
 * @author massi
 *
 */
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

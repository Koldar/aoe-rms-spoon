package com.thekoldar.aoe_rms_spoon.age_versions.de;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.thekoldar.aoe_rms_spoon.framework.IImportantFiles;

/**
 * Represents all the important path and files Age of Empires 2: DE contains. In this way the develop may gain access to the files in a easy way 
 * 
 * @author massi
 *
 */
public class DefinitiveEditionImportantFiles implements IImportantFiles {

	/**
	 * path where age of empires is installed
	 */
	private Path ageOfEmpiresInstallationFolder;
	
	public DefinitiveEditionImportantFiles(Path ageofEmprisInstallationFolder) {
		this.ageOfEmpiresInstallationFolder = ageofEmprisInstallationFolder;
	}
	
	public String generatingObjects() {
		return "GeneratingObjects.inc";
	}
	
	/**
	 * folder where textures are stored. This folder contains the subfolders 1x and 2x. 2x are bigger but consume more space
	 * @return
	 */
	public Path getTerrainTextures() {
		return this.ageOfEmpiresInstallationFolder.resolve(Paths.get("resources", "_common", "terrain", "textrues"));
	}
}

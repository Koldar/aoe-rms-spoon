package com.thekoldar.aoe_rms_spoon.models.add_methods;

import com.thekoldar.aoe_rms_spoon.models.IRMSNode;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections.AbstractCliffGeneration;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections.AbstractConnectionGeneration;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections.AbstractElevationGeneration;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections.AbstractLandGeneration;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections.AbstractObjectsGeneration;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections.AbstractPlayerSetup;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.sections.AbstractTerrainGeneration;

public interface AddRMSSections extends IRMSNode {

	public default AbstractPlayerSetup playerSetup() {
		var result = this.getAgeVersion().playerSetup();
		this.addStatement(result);
		return result;
	}
	
	public default AbstractLandGeneration landGeneration() {
		var result = this.getAgeVersion().landGeneration();
		this.addStatement(result);
		return result;
	}
	
	public default AbstractTerrainGeneration terrainGeneration() {
		var result = this.getAgeVersion().terrainGeneration();
		this.addStatement(result);
		return result;
	}
	
	public default AbstractObjectsGeneration objectsGeneration() {
		var result = this.getAgeVersion().objectsGeneration();
		this.addStatement(result);
		return result;
	}
	
	public default AbstractConnectionGeneration connectionGeneration() {
		var result = this.getAgeVersion().connectionGeneration();
		this.addStatement(result);
		return result;
	}
	
	public default AbstractCliffGeneration cliffGeneration() {
		var result = this.getAgeVersion().cliffGeneration();
		this.addStatement(result);
		return result;
	}
	
	public default AbstractElevationGeneration elevationGeneration() {
		var result = this.getAgeVersion().elevationGeneration();
		this.addStatement(result);
		return result;
	}
	
}

package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractSetScalingToMapSize extends AbstractRMSNoArgumentCommand {

	protected AbstractSetScalingToMapSize() {
		super(RMSNodeType.SET_SCALING_TO_MAP_SIZE);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

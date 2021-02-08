package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractSetScalingToMapSize extends AbstractRMSNoArgumentCommand {

	protected AbstractSetScalingToMapSize() {
		super(RMSNodeType.SET_SCALING_TO_MAP_SIZE);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

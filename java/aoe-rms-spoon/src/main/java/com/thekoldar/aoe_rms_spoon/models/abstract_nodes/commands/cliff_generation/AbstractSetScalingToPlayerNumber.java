package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractSetScalingToPlayerNumber extends AbstractRMSNoArgumentCommand {

	protected AbstractSetScalingToPlayerNumber() {
		super(RMSNodeType.SET_SCALING_TO_PLAYER_NUMBER);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

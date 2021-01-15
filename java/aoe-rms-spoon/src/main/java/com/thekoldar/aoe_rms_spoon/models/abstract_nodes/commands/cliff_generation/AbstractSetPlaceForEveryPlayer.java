package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractSetPlaceForEveryPlayer extends AbstractRMSNoArgumentCommand {

	protected AbstractSetPlaceForEveryPlayer() {
		super(RMSNodeType.SET_SCALING_TO_PLAYER_NUMBER);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

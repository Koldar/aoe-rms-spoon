package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractSetScalingToPlayerNumber extends AbstractRMSNoArgumentCommand {

	protected AbstractSetScalingToPlayerNumber() {
		super(RMSNodeType.SET_SCALING_TO_PLAYER_NUMBER);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

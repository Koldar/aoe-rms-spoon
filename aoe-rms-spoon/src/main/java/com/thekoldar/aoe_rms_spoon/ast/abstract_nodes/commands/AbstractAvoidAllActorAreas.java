package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractAvoidAllActorAreas extends AbstractRMSNoArgumentCommand {

	protected AbstractAvoidAllActorAreas() {
		super(RMSNodeType.AVOID_ALL_ACTOR_AREAS);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

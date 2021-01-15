package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractAvoidAllActorAreas extends AbstractRMSNoArgumentCommand {

	protected AbstractAvoidAllActorAreas() {
		super(RMSNodeType.AVOID_ALL_ACTOR_AREAS);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

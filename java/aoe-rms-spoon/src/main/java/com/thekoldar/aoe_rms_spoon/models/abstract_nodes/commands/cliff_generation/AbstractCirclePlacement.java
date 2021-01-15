package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractCirclePlacement extends AbstractRMSNoArgumentCommand {

	
	protected AbstractCirclePlacement() {
		super(RMSNodeType.CIRCLE_PLACEMENT);
	}

	@Override
	public String getComment() {
		return "";
	}

}

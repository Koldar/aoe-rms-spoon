package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractDirectPlacement extends AbstractRMSNoArgumentCommand {

	protected AbstractDirectPlacement() {
		super(RMSNodeType.DIRECT_PLACEMENT);
	}
	

	@Override
	public String getComment() {
		return "";
	}

}

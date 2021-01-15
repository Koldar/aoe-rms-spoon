package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractNomadResources extends AbstractRMSNoArgumentCommand {

	protected AbstractNomadResources() {
		super(RMSNodeType.NOMAD_RESOURCES);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

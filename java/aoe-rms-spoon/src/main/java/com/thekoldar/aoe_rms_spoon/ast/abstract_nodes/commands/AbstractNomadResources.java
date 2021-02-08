package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractNomadResources extends AbstractRMSNoArgumentCommand {

	protected AbstractNomadResources() {
		super(RMSNodeType.NOMAD_RESOURCES);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

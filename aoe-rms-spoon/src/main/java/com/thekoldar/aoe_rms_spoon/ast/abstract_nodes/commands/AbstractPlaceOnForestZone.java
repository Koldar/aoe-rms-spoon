package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractPlaceOnForestZone extends AbstractRMSNoArgumentCommand {

	protected AbstractPlaceOnForestZone() {
		super(RMSNodeType.PLACE_ON_FOREST_ZONE);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractPlaceOnForestZone extends AbstractRMSNoArgumentCommand {

	protected AbstractPlaceOnForestZone() {
		super(RMSNodeType.PLACE_ON_FOREST_ZONE);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

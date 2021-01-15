package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractSetGaiaUnconvertible extends AbstractRMSNoArgumentCommand {

	protected AbstractSetGaiaUnconvertible() {
		super(RMSNodeType.SET_GAIA_UNCONVERTIBLE);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

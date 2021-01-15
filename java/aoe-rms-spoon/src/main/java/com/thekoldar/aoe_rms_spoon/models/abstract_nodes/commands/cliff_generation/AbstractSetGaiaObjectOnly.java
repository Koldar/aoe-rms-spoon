package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractSetGaiaObjectOnly extends AbstractRMSNoArgumentCommand {

	protected AbstractSetGaiaObjectOnly() {
		super(RMSNodeType.SET_GAIA_OBJECT_ONLY);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

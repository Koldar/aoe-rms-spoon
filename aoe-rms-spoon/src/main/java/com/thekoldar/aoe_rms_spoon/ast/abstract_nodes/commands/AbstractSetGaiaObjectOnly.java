package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractSetGaiaObjectOnly extends AbstractRMSNoArgumentCommand {

	protected AbstractSetGaiaObjectOnly() {
		super(RMSNodeType.SET_GAIA_OBJECT_ONLY);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

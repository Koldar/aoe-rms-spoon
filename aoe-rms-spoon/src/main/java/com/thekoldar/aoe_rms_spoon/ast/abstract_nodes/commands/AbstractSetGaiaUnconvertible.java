package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractSetGaiaUnconvertible extends AbstractRMSNoArgumentCommand {

	protected AbstractSetGaiaUnconvertible() {
		super(RMSNodeType.SET_GAIA_UNCONVERTIBLE);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

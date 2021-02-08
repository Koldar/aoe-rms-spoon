package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractRandomPlacement extends AbstractRMSNoArgumentCommand {

	protected AbstractRandomPlacement() {
		super(RMSNodeType.RANDOM_PLACEMENT);
	}

	@Override
	public String getComment() {
		return "";
	}

}

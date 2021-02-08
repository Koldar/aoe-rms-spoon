package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractGroupedByTeam extends AbstractRMSNoArgumentCommand {

	protected AbstractGroupedByTeam() {
		super(RMSNodeType.GROUPED_BY_TEAM);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

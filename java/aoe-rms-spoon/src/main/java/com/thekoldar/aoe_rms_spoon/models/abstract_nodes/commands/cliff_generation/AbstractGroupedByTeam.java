package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;

public abstract class AbstractGroupedByTeam extends AbstractRMSNoArgumentCommand {

	protected AbstractGroupedByTeam() {
		super(RMSNodeType.GROUPED_BY_TEAM);
	}
	
	
	@Override
	public String getComment() {
		return "";
	}

}

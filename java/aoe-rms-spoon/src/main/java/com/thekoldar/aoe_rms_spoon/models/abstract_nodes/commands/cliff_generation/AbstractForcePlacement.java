package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleRequiredIntArgumentCommand;

public abstract class AbstractForcePlacement extends AbstractRMSNoArgumentCommand {

	protected AbstractForcePlacement() {
		super(RMSNodeType.FORCE_PLACEMENT);
	}

	@Override
	public String getComment() {
		return "";
	}


}

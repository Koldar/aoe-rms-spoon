package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

public abstract class AbstractAssignTo extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractAssignTo() {
		super(RMSNodeType.ASSIGN_TO_PLAYER);
	}

	@Override
	public String getArgumentName() {
		return "player_number";
	}

	@Override
	public Object getDefaultValue() {
		return "not assigned to any players";
	}

	@Override
	public String getArgumentComment() {
		return "";
	}

	@Override
	public String getComment() {
		return "";
	}

}

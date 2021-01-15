package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

public abstract class AbstractLandId extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractLandId() {
		super(RMSNodeType.LAND_ID);
	}

	@Override
	public String getArgumentName() {
		return "id";
	}

	@Override
	public Object getDefaultValue() {
		return "no id";
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

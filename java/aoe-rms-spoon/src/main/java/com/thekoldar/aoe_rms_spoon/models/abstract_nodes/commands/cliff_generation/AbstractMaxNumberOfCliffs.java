package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

public abstract class AbstractMaxNumberOfCliffs extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractMaxNumberOfCliffs() {
		super(RMSNodeType.MAX_NUMBER_OF_CLIFFS);
	}

	@Override
	public String getArgumentName() {
		return "amount";
	}

	@Override
	public Object getDefaultValue() {
		return 8;
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

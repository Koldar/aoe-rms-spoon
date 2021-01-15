package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

public abstract class AbstractColorCorrection extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractColorCorrection() {
		super(RMSNodeType.COLOR_CORRECTION);
	}

	@Override
	public String getArgumentName() {
		return "color_correction_type";
	}

	@Override
	public Object getDefaultValue() {
		return "no color correction";
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

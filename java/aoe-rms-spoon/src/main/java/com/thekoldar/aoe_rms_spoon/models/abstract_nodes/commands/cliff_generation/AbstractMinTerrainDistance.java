package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

public abstract class AbstractMinTerrainDistance extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractMinTerrainDistance() {
		super(RMSNodeType.MIN_TERRAIN_DISTANCE);
	}

	@Override
	public String getArgumentName() {
		return "distance";
	}

	@Override
	public Object getDefaultValue() {
		return 2;
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

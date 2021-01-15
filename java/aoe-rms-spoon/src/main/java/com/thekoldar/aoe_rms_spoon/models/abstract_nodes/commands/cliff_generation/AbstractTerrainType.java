package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

public abstract class AbstractTerrainType extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractTerrainType() {
		super(RMSNodeType.TERRAIN_TYPE);
	}

	@Override
	public String getArgumentName() {
		return "terrain_type";
	}

	@Override
	public Object getDefaultValue() {
		return "GRASS";
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

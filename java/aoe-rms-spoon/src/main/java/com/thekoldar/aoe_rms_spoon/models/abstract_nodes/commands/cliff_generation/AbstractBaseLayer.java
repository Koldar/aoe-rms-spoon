package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

public abstract class AbstractBaseLayer extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractBaseLayer() {
		super(RMSNodeType.BASE_LAYER);
	}

	@Override
	public String getArgumentName() {
		return "terrain_type";
	}

	@Override
	public Object getDefaultValue() {
		return "no layered terrain";
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

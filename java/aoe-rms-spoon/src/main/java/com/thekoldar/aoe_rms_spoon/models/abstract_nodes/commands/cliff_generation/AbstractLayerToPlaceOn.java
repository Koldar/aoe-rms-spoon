package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleRequiredIntArgumentCommand;

public abstract class AbstractLayerToPlaceOn extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractLayerToPlaceOn() {
		super(RMSNodeType.LAYER_TO_PLACE_ON);
	}
	
	@Override
	public Object getDefaultValue() {
		return "any valid layer";
	}

	@Override
	public String getArgumentName() {
		return "terrain_type";
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

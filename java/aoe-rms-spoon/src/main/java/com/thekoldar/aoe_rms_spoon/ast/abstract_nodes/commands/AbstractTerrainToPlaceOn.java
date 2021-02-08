package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredIntArgumentCommand;

public abstract class AbstractTerrainToPlaceOn extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractTerrainToPlaceOn() {
		super(RMSNodeType.TERRAIN_TO_PLACE_ON);
	}
	
	

	@Override
	public Object getDefaultValue() {
		return "any valid terrain";
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

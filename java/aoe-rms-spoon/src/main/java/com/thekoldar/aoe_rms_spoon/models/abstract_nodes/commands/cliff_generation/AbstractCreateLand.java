package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleRequiredDictArgumentCommand;

public abstract class AbstractCreateLand extends AbstractRMSSingleRequiredDictArgumentCommand {

	protected AbstractCreateLand() {
		super(RMSNodeType.CREATE_LAND);
	}

	@Override
	public String getArgumentName() {
		return "terrain_specifics";
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

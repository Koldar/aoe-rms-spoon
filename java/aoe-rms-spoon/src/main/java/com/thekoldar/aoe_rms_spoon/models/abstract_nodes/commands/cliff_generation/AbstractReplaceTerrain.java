package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSRequiredIntRequiredIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleRequiredIntArgumentCommand;

public abstract class AbstractReplaceTerrain extends AbstractRMSRequiredIntRequiredIntArgumentCommand {

	protected AbstractReplaceTerrain() {
		super(RMSNodeType.REPLACE_TERRAIN);
	}
	
	@Override
	public String getArgument1Name() {
		return "terrain_type_old";
	}

	@Override
	public String getArgument1Comment() {
		return "";
	}

	@Override
	public String getArgument2Name() {
		return "terrain_type_new";
	}

	@Override
	public String getArgument2Comment() {
		return "";
	}

	@Override
	public String getComment() {
		return "";
	}

}

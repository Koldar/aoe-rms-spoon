package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;

import com.thekoldar.aoe_rms_spoon.models.CommandFormalArgument;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

public class AbstractTerrainMask extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractTerrainMask() {
		super(RMSNodeType.TERRAIN_MASK);
	}


	@Override
	public String getComment() {
		return "";
	}


	@Override
	public String getArgumentName() {
		return "terrain_mask";
	}


	@Override
	public Object getDefaultValue() {
		return 0;
	}


	@Override
	public String getArgumentComment() {
		return "";
	}

}

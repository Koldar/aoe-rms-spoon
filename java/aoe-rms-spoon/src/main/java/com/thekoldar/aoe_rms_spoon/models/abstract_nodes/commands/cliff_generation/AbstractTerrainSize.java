package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.models.CommandFormalArgument;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSCommand;

public abstract class AbstractTerrainSize extends AbstractRMSCommand {

	protected AbstractTerrainSize() {
		super(RMSNodeType.TERRAIN_SIZE);
	}
	
	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		return Lists.fixedSize.of(
				CommandFormalArgument.requiredInt("terrain_type", ""),
				CommandFormalArgument.optionalInt("radius", 1, ""),
				CommandFormalArgument.optionalInt("variance", 0, "")
		).toImmutable();
	}



	@Override
	public String getComment() {
		return "";
	}

}
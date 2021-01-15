package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;

import com.thekoldar.aoe_rms_spoon.models.CommandFormalArgument;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSCommand;

public abstract class AbstractTerrainState extends AbstractRMSCommand {

	protected AbstractTerrainState() {
		super(RMSNodeType.TERRAIN_STATE);
	}


	@Override
	public String getComment() {
		return "";
	}

	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		return Lists.mutable.of(
				CommandFormalArgument.requiredInt("mode", ""),
				CommandFormalArgument.requiredInt("parameter1", ""),
				CommandFormalArgument.optionalInt("parameter2", 0, ""),
				CommandFormalArgument.optionalInt("flags", 0, "")
		).toImmutable();
	}

}

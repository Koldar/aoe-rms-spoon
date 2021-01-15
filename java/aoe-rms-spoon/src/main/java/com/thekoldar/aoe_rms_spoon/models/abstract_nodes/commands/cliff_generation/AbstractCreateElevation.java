package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.models.CommandFormalArgument;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSection;

public abstract class AbstractCreateElevation extends AbstractRMSCommand {

	protected AbstractCreateElevation() {
		super(RMSNodeType.CREATE_ELEVATION);
	}

	@Override
	public String getComment() {
		return "";
	}

	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		return Lists.fixedSize.of(
				CommandFormalArgument.optionalInt("max_height", 0, ""),
				CommandFormalArgument.requiredDict("terrain_specifics", "")
		).toImmutable();
	}

}

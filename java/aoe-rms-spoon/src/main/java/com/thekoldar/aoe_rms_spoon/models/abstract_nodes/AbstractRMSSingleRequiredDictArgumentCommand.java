package com.thekoldar.aoe_rms_spoon.models.abstract_nodes;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.models.CommandFormalArgument;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;

public abstract class AbstractRMSSingleRequiredDictArgumentCommand extends AbstractRMSCommand {

	protected AbstractRMSSingleRequiredDictArgumentCommand(RMSNodeType type) {
		super(type);
	}

	public abstract String getArgumentName();
	
	public abstract String getArgumentComment();
	
	
	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		ImmutableList<CommandFormalArgument> result = Lists.immutable.of(
				CommandFormalArgument.requiredDict(this.getArgumentName(), this.getArgumentComment())
		);
		return result;
	}

}

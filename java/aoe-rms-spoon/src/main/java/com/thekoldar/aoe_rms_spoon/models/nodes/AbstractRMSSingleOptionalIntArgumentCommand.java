package com.thekoldar.aoe_rms_spoon.models.nodes;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.models.CommandFormalArgument;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;

public abstract class AbstractRMSSingleOptionalIntArgumentCommand<TAOE> extends AbstractRMSCommand<TAOE> {

	protected AbstractRMSSingleOptionalIntArgumentCommand(RMSNodeType type) {
		super(type);
	}

	public abstract String getArgumentName();
	
	public abstract int getDefaultValue();
	
	public abstract String getArgumentComment();
	
	
	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		ImmutableList<CommandFormalArgument> result = Lists.immutable.of(
				CommandFormalArgument.optionalInt(this.getArgumentName(), this.getDefaultValue(), this.getArgumentComment())
		);
		return result;
	}

}

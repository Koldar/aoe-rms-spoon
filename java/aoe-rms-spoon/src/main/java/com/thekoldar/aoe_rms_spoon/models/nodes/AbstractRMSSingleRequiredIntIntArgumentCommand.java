package com.thekoldar.aoe_rms_spoon.models.nodes;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.models.CommandFormalArgument;

public abstract class AbstractRMSSingleRequiredIntIntArgumentCommand<TAOE> extends AbstractRMSCommand<TAOE> {

	public abstract String getArgument1Name();
	
	public abstract String getArgument1Comment();
	
	public abstract String getArgument2Name();
	
	public abstract String getArgument2Comment();
	
	
	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		ImmutableList<CommandFormalArgument> result = Lists.immutable.of(
				CommandFormalArgument.requiredInt(this.getArgumentName(), this.getArgumentComment())
		);
		return result;
	}

}

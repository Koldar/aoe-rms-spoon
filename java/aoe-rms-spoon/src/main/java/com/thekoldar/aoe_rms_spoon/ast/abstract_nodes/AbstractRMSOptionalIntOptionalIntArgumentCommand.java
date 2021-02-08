package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;

public abstract class AbstractRMSOptionalIntOptionalIntArgumentCommand extends AbstractRMSCommand {

	protected AbstractRMSOptionalIntOptionalIntArgumentCommand(RMSNodeType type) {
		super(type);
	}

	public abstract String getArgument1Name();
	
	public abstract Object getArgument1DefaultValue();
	
	public abstract String getArgument1Comment();
	
	public abstract String getArgument2Name();
	
	public abstract Object getArgument2DefaultValue();
	
	public abstract String getArgument2Comment();
	
	
	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		ImmutableList<CommandFormalArgument> result = Lists.immutable.of(
				CommandFormalArgument.optionalInt(this.getArgument1Name(), this.getArgument1DefaultValue(), this.getArgument1Comment()),
				CommandFormalArgument.optionalInt(this.getArgument2Name(), this.getArgument2DefaultValue(), this.getArgument2Comment())
		);
		return result;
	}

}

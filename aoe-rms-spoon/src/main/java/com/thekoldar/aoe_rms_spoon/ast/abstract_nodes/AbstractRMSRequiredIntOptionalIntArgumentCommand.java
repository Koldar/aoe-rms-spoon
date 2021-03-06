package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;

/**
 * A generic command that requires 2 argument. The first is required while the second is optional
 * 
 * @author massi
 *
 */
public abstract class AbstractRMSRequiredIntOptionalIntArgumentCommand extends AbstractRMSCommand {

	protected AbstractRMSRequiredIntOptionalIntArgumentCommand(RMSNodeType type) {
		super(type);
	}

	public abstract String getArgument1Name();
	
	public abstract String getArgument1Comment();
	
	public abstract String getArgument2Name();
	
	public abstract Object getArgument2DefaultValue();
	
	public abstract String getArgument2Comment();
	
	
	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		ImmutableList<CommandFormalArgument> result = Lists.immutable.of(
				CommandFormalArgument.requiredInt(this.getArgument1Name(), this.getArgument1Comment()),
				CommandFormalArgument.optionalInt(this.getArgument2Name(), this.getArgument2DefaultValue(), this.getArgument2Comment())
		);
		return result;
	}

}

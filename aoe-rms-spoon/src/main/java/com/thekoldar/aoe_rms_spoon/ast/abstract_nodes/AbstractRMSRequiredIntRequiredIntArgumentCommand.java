package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;

/**
 * A RMS command that accepts 2 arguments, both required
 * @author massi
 *
 */
public abstract class AbstractRMSRequiredIntRequiredIntArgumentCommand extends AbstractRMSCommand {

	protected AbstractRMSRequiredIntRequiredIntArgumentCommand(RMSNodeType type) {
		super(type);
	}

	public abstract String getArgument1Name();
	
	public abstract String getArgument1Comment();
	
	public abstract String getArgument2Name();
	
	public abstract String getArgument2Comment();
	
	
	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		ImmutableList<CommandFormalArgument> result = Lists.immutable.of(
				CommandFormalArgument.requiredInt(this.getArgument1Name(), this.getArgument1Comment()),
				CommandFormalArgument.requiredInt(this.getArgument2Name(), this.getArgument2Comment())
		);
		return result;
	}

}

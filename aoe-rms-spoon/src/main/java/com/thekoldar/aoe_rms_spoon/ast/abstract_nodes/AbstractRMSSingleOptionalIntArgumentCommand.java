package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;

/**
 * A RMS command that has a single argument. The argument is optiojnal and requires an integer number
 * @author massi
 *
 */
public abstract class AbstractRMSSingleOptionalIntArgumentCommand extends AbstractRMSCommand {

	protected AbstractRMSSingleOptionalIntArgumentCommand(RMSNodeType type) {
		super(type);
	}

	public abstract String getArgumentName();
	
	public abstract Object getDefaultValue();
	
	public abstract String getArgumentComment();
	
	
	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		ImmutableList<CommandFormalArgument> result = Lists.immutable.of(
				CommandFormalArgument.optionalInt(this.getArgumentName(), this.getDefaultValue(), this.getArgumentComment())
		);
		return result;
	}

}

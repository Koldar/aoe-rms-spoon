package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;

/**
 * A RMS command that needs one single argument. The argument is optional and has a boolean type 
 * @author massi
 *
 */
public abstract class AbstractRMSSingleOptionalBooleanArgumentCommand extends AbstractRMSCommand {

	protected AbstractRMSSingleOptionalBooleanArgumentCommand(RMSNodeType type) {
		super(type);
	}

	public abstract String getArgumentName();
	
	public abstract Object getDefaultValue();
	
	public abstract String getArgumentComment();
	
	
	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		ImmutableList<CommandFormalArgument> result = Lists.immutable.of(
				CommandFormalArgument.optionalBoolean(this.getArgumentName(), this.getDefaultValue(), this.getArgumentComment())
		);
		return result;
	}

}

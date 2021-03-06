package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;

/**
 * A RMS command that requires a single reuired argument of "dictioanry" type (namly start and end with open curly bracket and close curly bracket)
 * 
 * @author massi
 *
 */
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

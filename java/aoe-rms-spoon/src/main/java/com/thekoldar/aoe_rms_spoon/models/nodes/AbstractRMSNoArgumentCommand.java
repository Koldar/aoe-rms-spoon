package com.thekoldar.aoe_rms_spoon.models.nodes;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.CommandFormalArgument;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.exceptions.AbstractRMSException;

public abstract class AbstractRMSNoArgumentCommand<TAOE> extends AbstractRMSCommand<TAOE> {

	protected AbstractRMSNoArgumentCommand(RMSNodeType type) {
		super(type);
	}

	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		ImmutableList<CommandFormalArgument> result = Lists.immutable.empty();
		return result;
	}

}

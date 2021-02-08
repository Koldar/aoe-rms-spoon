package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;

public abstract class AbstractEffectPercent extends AbstractRMSCommand {

	protected AbstractEffectPercent() {
		super(RMSNodeType.EFFECT_PERCENT);
	}

	
	@Override
	public String getComment() {
		return "";
	}

	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		return Lists.mutable.of(
				CommandFormalArgument.requiredInt("up_effect_type", ""),
				CommandFormalArgument.requiredInt("type", ""),
				CommandFormalArgument.requiredInt("up_attribute_type", ""),
				CommandFormalArgument.requiredInt("amount", "")
		).toImmutable();
	}

}

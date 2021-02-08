package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;

public abstract class AbstractAiInfoMapType extends AbstractRMSCommand {

	protected AbstractAiInfoMapType() {
		super(RMSNodeType.AI_INFO_MAP_TYPE);
	}

	@Override
	public String getComment() {
		return "";
	}

	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		return Lists.mutable.of(
				CommandFormalArgument.optionalInt("map_type", 0, ""),
				CommandFormalArgument.optionalBoolean("is_nomad", false, ""),
				CommandFormalArgument.optionalBoolean("is_michi", false, ""),
				CommandFormalArgument.optionalBoolean("showtype", false, "")
		).toImmutable();
	}
	
	
	

}

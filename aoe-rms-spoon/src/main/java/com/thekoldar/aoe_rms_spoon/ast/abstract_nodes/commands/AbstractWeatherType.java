package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.factory.Lists;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;

public abstract class AbstractWeatherType extends AbstractRMSCommand {

	protected AbstractWeatherType() {
		super(RMSNodeType.WEATHER_TYPE);
	}


	@Override
	public String getComment() {
		return "";
	}

	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		return Lists.mutable.of(
				CommandFormalArgument.optionalInt("precipitation_style", 0, ""),
				CommandFormalArgument.optionalInt("live_color", 0, ""),
				CommandFormalArgument.optionalInt("fog_color", 0, ""),
				CommandFormalArgument.optionalInt("water_direction", 0, "")
		).toImmutable();
	}

}

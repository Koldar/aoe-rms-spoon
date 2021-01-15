package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSOptionalIntOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

public abstract class AbstractLandPosition extends AbstractRMSOptionalIntOptionalIntArgumentCommand{

	protected AbstractLandPosition() {
		super(RMSNodeType.LAND_POSITION);
	}

	@Override
	public String getComment() {
		return "";
	}

	@Override
	public String getArgument1Name() {
		return "x";
	}

	@Override
	public Object getArgument1DefaultValue() {
		return "random";
	}

	@Override
	public String getArgument1Comment() {
		return "";
	}

	@Override
	public String getArgument2Name() {
		return "y";
	}

	@Override
	public Object getArgument2DefaultValue() {
		return "random";
	}

	@Override
	public String getArgument2Comment() {
		return "";
	}

}

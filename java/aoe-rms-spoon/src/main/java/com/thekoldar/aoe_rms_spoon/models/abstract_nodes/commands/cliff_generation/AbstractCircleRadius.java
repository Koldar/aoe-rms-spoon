package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSOptionalIntOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSRequiredIntOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

public abstract class AbstractCircleRadius extends AbstractRMSRequiredIntOptionalIntArgumentCommand{

	protected AbstractCircleRadius() {
		super(RMSNodeType.CIRCLE_RADIUS);
	}

	@Override
	public String getArgument1Name() {
		return "radius";
	}

	@Override
	public String getArgument1Comment() {
		return "";
	}

	@Override
	public String getArgument2Name() {
		return "variance";
	}

	@Override
	public Object getArgument2DefaultValue() {
		return 0;
	}

	@Override
	public String getArgument2Comment() {
		return "";
	}

	@Override
	public String getComment() {
		return "";
	}

	

}

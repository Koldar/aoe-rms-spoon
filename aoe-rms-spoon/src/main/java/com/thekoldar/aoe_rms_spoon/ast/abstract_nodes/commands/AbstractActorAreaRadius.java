package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

public abstract class AbstractActorAreaRadius extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractActorAreaRadius() {
		super(RMSNodeType.ACTOR_AREA_RADIUS);
	}

	@Override
	public String getArgumentName() {
		return "radius";
	}

	@Override
	public Object getDefaultValue() {
		return 1;
	}

	@Override
	public String getArgumentComment() {
		return "";
	}

	@Override
	public String getComment() {
		return "";
	}

}

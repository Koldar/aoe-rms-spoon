package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

public abstract class AbstractOtherZoneAvoidanceDistance extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractOtherZoneAvoidanceDistance() {
		super(RMSNodeType.OTHER_ZONE_AVOIDANCE_DISTANCE);
	}

	@Override
	public String getArgumentName() {
		return "tiles";
	}

	@Override
	public Object getDefaultValue() {
		return 0;
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
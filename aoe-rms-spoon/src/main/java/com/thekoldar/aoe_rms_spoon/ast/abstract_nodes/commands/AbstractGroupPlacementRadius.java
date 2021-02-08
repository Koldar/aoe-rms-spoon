package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

public abstract class AbstractGroupPlacementRadius extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractGroupPlacementRadius() {
		super(RMSNodeType.GROUP_PLACEMENT_RADIUS);
	}

	@Override
	public String getArgumentName() {
		return "radius";
	}

	@Override
	public Object getDefaultValue() {
		return 3;
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

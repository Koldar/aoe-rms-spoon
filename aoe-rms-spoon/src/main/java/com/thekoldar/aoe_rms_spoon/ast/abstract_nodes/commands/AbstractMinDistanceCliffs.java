package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

public abstract class AbstractMinDistanceCliffs extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractMinDistanceCliffs() {
		super(RMSNodeType.MIN_DISTANCE_CLIFFS);
	}

	@Override
	public String getArgumentName() {
		return "distance";
	}

	@Override
	public Object getDefaultValue() {
		return 2;
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

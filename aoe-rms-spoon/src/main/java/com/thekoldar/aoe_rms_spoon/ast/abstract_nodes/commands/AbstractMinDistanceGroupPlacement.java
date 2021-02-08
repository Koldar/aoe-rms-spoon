package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredIntArgumentCommand;

public abstract class AbstractMinDistanceGroupPlacement extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractMinDistanceGroupPlacement() {
		super(RMSNodeType.MIN_DISTANCE_GROUP_PLACEMENT);
	}
	
	@Override
	public Object getDefaultValue() {
		return 0;
	}



	@Override
	public String getArgumentName() {
		return "distance";
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

package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;

public class AbstractSetGaiaCivilization extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractSetGaiaCivilization() {
		super(RMSNodeType.BEHAVIOR_VERSION);
	}

	@Override
	public String getArgumentName() {
		return "version_number";
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

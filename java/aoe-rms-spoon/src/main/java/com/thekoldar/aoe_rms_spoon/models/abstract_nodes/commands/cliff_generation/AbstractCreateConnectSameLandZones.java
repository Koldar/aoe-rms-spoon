package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleRequiredDictArgumentCommand;

public class AbstractCreateConnectSameLandZones extends AbstractRMSSingleRequiredDictArgumentCommand {

	protected AbstractCreateConnectSameLandZones() {
		super(RMSNodeType.CREATE_CONNECT_SAME_LAND_ZONES);
	}

	@Override
	public String getArgumentName() {
		return "specifics";
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

package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredDictArgumentCommand;

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

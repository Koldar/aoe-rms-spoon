package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleRequiredDictArgumentCommand;

public class AbstractCreateConnectAllPlayerLand extends AbstractRMSSingleRequiredDictArgumentCommand {

	protected AbstractCreateConnectAllPlayerLand() {
		super(RMSNodeType.CREATE_CONNECT_ALL_PLAYERS_LAND);
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

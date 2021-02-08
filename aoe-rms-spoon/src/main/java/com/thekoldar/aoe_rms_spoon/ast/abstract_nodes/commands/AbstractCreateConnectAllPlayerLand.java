package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredDictArgumentCommand;

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

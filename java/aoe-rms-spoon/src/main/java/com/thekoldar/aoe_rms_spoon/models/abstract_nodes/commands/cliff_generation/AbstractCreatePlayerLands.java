package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.commands.cliff_generation;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSSingleRequiredDictArgumentCommand;

public abstract class AbstractCreatePlayerLands extends AbstractRMSSingleRequiredDictArgumentCommand {

	protected AbstractCreatePlayerLands() {
		super(RMSNodeType.CREATE_PLAYER_LANDS);
	}
	
	@Override
	public String getArgumentName() {
		return "terrain specifics";
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

package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSRequiredIntOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSRequiredIntRequiredIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredIntArgumentCommand;

public abstract class AbstractTerrainCost extends AbstractRMSRequiredIntOptionalIntArgumentCommand {

	protected AbstractTerrainCost() {
		super(RMSNodeType.TERRAIN_COST);
	}
	
	@Override
	public String getArgument1Name() {
		return "terrain_type";
	}

	@Override
	public String getArgument1Comment() {
		return "";
	}

	@Override
	public String getArgument2Name() {
		return "cost";
	}
	
	@Override
	public Object getArgument2DefaultValue() {
		return 1;
	}

	@Override
	public String getArgument2Comment() {
		return "";
	}

	@Override
	public String getComment() {
		return "";
	}

}

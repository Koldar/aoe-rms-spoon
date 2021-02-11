package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredDictArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

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
		return "Connections will be generated between the origins of all player lands, and all lands assigned to players. Connections may still pass through neutral lands if the cost is favorable.";
	}


	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureItContainsCommonNodesAnd(this, RMSNodeType.DEFAULT_TERRAIN_REPLACEMENT, RMSNodeType.REPLACE_TERRAIN, RMSNodeType.TERRAIN_COST, RMSNodeType.TERRAIN_SIZE);
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	

}

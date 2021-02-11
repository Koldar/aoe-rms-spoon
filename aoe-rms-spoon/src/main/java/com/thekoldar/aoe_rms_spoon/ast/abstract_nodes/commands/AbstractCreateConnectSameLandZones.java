package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredDictArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

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
		return "Behaves identically to create_connect_all_lands ";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureItContainsCommonNodesAnd(this, RMSNodeType.DEFAULT_TERRAIN_REPLACEMENT, RMSNodeType.REPLACE_TERRAIN, RMSNodeType.TERRAIN_COST, RMSNodeType.TERRAIN_SIZE);
		
		result.addWarning(this, RMSErrorCode.NOT_RECOMMENDED, "%s behaves exactly like %s, but it is less documented, hence is not typically used", this.getNodeType(), RMSNodeType.CREATE_CONNECT_ALL_LANDS);
		
		return result.merge(this.semanticCheckChildren(input));
	}

}

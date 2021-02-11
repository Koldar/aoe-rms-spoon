package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractDefaultTerrainReplacement extends AbstractRMSSingleRequiredIntArgumentCommand{

	protected AbstractDefaultTerrainReplacement() {
		super(RMSNodeType.DEFAULT_TERRAIN_REPLACEMENT);
	}

	@Override
	public String getArgumentName() {
		return "terrain_type";
	}

	@Override
	public String getArgumentComment() {
		return "terrain used to replace everythign on the connection";
	}

	@Override
	public String getComment() {
		return "Replace ALL terrain in the connection with the specified terrain.";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureItIsOnlyInstructionOfTypeInDict(this);
		var previousReplaceTerrain = this.getPreviousSiblingSatisfying((i, c) -> c.getNodeType().equals(RMSNodeType.REPLACE_TERRAIN)).collect(pair -> pair.getTwo()).toList();
		
		if (!previousReplaceTerrain.isEmpty()) {
			result.addError(this, RMSErrorCode.CONFLICTING_COMMANDS, "%s overrides any next %s call (in this case it overrides %d statements).", this.getNodeType(), RMSNodeType.REPLACE_TERRAIN, previousReplaceTerrain.size());
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	

}

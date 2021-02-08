package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractLandPercent extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractLandPercent() {
		super(RMSNodeType.LAND_PERCENT);
	}

	@Override
	public String getArgumentName() {
		return "percentage";
	}

	@Override
	public Object getDefaultValue() {
		return 100;
	}

	@Override
	public String getArgumentComment() {
		return "Percentage of the total map that the land should grow to cover.";
	}

	@Override
	public String getComment() {
		return "Percentage of the total map that the land should grow to cover.";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentIsPercentage(this.getArgument(0), 0);
		result.ensureArgumentIsLiteralInteger(this, 0);
		
		result.ensureArgumentGreaterThan(this.getArgument(0), 0, 0);
		if (this.hasAtLeastOneSiblingOfTypes(RMSNodeType.TOP_BORDER, RMSNodeType.BOTTOM_BORDER, RMSNodeType.LEFT_BORDER, RMSNodeType.RIGHT_BORDER)) {
			result.addWarning(RMSErrorCode.BEHAVIOR_MAY_BE_ALTERED, "If land growth is constrained by borders or other lands, lands may be smaller than specified.");
		}
		if (this.isUnderNodeWithTypes(RMSNodeType.CREATE_PLAYER_LANDS)) {
			this.infoCmd("the percentage is divided equally between all players");
		}
		
		return this.semanticCheckChildren(input);
	}

	
	
}

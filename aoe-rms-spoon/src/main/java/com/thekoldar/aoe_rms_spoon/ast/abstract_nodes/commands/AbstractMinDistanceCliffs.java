package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractMinDistanceCliffs extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractMinDistanceCliffs() {
		super(RMSNodeType.MIN_DISTANCE_CLIFFS);
	}

	@Override
	public String getArgumentName() {
		return "distance";
	}

	@Override
	public Object getDefaultValue() {
		return 2;
	}

	@Override
	public String getArgumentComment() {
		return "number, in \"cliff\" units";
	}

	@Override
	public String getComment() {
		return "Minimum distance in \"cliff\" units between separate cliffs.";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentGreaterThan0(this.getArgument(0));
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	

}

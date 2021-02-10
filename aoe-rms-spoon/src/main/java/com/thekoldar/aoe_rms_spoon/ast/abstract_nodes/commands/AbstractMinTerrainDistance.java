package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractMinTerrainDistance extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractMinTerrainDistance() {
		super(RMSNodeType.MIN_TERRAIN_DISTANCE);
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
		return "number in cliff units";
	}

	@Override
	public String getComment() {
		return "Minimum distance in \"cliff units\" that cliffs will avoid water terrains by.";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentGreaterThan0(this.getArgument(0));
		
		return result.merge(this.semanticCheckChildren(input));
	}
}

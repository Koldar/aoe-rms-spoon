package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractMinLengthOfCliff extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractMinLengthOfCliff() {
		super(RMSNodeType.MIN_LENGTH_OF_CLIFF);
	}

	@Override
	public String getArgumentName() {
		return "length";
	}

	@Override
	public Object getDefaultValue() {
		return 5;
	}

	@Override
	public String getArgumentComment() {
		return "length in \"cliff semgents\"";
	}

	@Override
	public String getComment() {
		return "Cliff lengths are chosen at random from between min and max (inclusive).  The unit is NOT tiles, but rather \"cliff segments\"";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentGreaterThan(this.getArgument(0), 0, 3);
		
		int min = this.getArgumentAsInt(0, input);
		int max = 9;
		if (this.hasAtLeastOneNextSiblingOfTypes(RMSNodeType.MAX_LENGTH_OF_CLIFF)) {
			max = ((AbstractExpressionNode)this.getSiblingOfTypes(RMSNodeType.MAX_LENGTH_OF_CLIFF).getAny()).getAsInt(input);
		}
		
		if (min > max) {
			result.addError(this, RMSErrorCode.INVALID_ARGUMENT, "in cliffs %s is greater than %s", RMSNodeType.MAX_LENGTH_OF_CLIFF, RMSNodeType.MAX_LENGTH_OF_CLIFF);
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

}

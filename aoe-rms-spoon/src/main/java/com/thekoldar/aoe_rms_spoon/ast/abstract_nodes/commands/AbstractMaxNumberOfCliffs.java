package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.LongSetPossible;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractMaxNumberOfCliffs extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractMaxNumberOfCliffs() {
		super(RMSNodeType.MAX_NUMBER_OF_CLIFFS);
	}

	@Override
	public String getArgumentName() {
		return "amount";
	}

	@Override
	public Object getDefaultValue() {
		return 8;
	}

	@Override
	public String getArgumentComment() {
		return "";
	}

	@Override
	public String getComment() {
		return "Set the maximum number of distinct cliffs to create.  The actual number of cliffs is chosen at random from between min and max inclusive.  Does not scale with map size, so you must do so manually using Conditionals.";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentIsBetween(this.getArgument(0), 0, 9999, true, true);
		
		LongSetPossible min = LongSetPossible.of(3);
		LongSetPossible max = LongSetPossible.of(this.getArgumentAsInt(0, input));
		if (this.hasAtLeastOneNextSiblingOfTypes(RMSNodeType.MIN_NUMBER_OF_CLIFFS)) {
			min = LongSetPossible.of(((AbstractExpressionNode)this.getSiblingOfTypes(RMSNodeType.MIN_NUMBER_OF_CLIFFS).getAny()).getAsLong(input));
		}
		
		if (min.areAtLeastOneGreaterThanAnyOf(max)) {
			result.addError(this, RMSErrorCode.INVALID_ARGUMENT, "in cliffs %s is greater than %s", RMSNodeType.MIN_NUMBER_OF_CLIFFS, RMSNodeType.MAX_NUMBER_OF_CLIFFS);
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

}

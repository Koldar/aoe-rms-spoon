package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.LongSetPossible;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractNumberOfObjects extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractNumberOfObjects() {
		super(RMSNodeType.NUMBER_OF_OBJECTS);
	}

	@Override
	public String getArgumentName() {
		return "amount";
	}

	@Override
	public Object getDefaultValue() {
		return 1;
	}

	@Override
	public String getArgumentComment() {
		return "";
	}

	@Override
	public String getComment() {
		return "Number of objects to create. ";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureItIsOnlyInstructionOfTypeInDict(this);
		result.ensureArgumentGreaterThan0(this.getArgument(0));
		
		if (this.hasAtLeastOneSiblingOfTypes(RMSNodeType.SET_SCALING_TO_MAP_SIZE)) {
			var n = LongSetPossible.of(this.getArgumentAsInt(0, input));
			if (n.isAtLeastOneGreaterThan(9320)) {
				result.addError(this, RMSErrorCode.INVALID_ARGUMENT, "When %s is set, the %s cannot go over %d", RMSNodeType.SET_SCALING_TO_MAP_SIZE, RMSNodeType.NUMBER_OF_OBJECTS, 9320);
			}
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	

}

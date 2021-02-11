package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.age_versions.common.constants.ClassConstants;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.IPossibleValue;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.LongSetPossible;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractResourceDelta extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractResourceDelta() {
		super(RMSNodeType.RESOURCE_DELTA);
	}

	@Override
	public String getArgumentName() {
		return "amount";
	}

	@Override
	public Object getDefaultValue() {
		return 0;
	}

	@Override
	public String getArgumentComment() {
		return "";
	}

	@Override
	public String getComment() {
		return "Modify the amount of food/wood/gold/stone in an object.";
	}
	
	/**
	 * compute the involve objhect in this create_object
	 * @param input
	 * @return
	 */
	public IPossibleValue<Long> getInvolvedObject(SemanticCheckInput input) {
		var createObject = (AbstractCreateObject)this.getFirstNodeFromPathSatisfying(n -> n.getNodeType().equals(RMSNodeType.CREATE_OBJECT)).getAny();
		return createObject.getInvolvedObject(input);
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureItIsOnlyInstructionOfTypeInDict(this);
		result.ensureArgumentIsBetween(this.getArgument(0), -1000, 2147483647, true, true);
		
		var involvedObject = this.getInvolvedObject(input);
		
		if (involvedObject.contains(ClassConstants.FARM.getValueAsLong())) {
			result.addError(this, RMSErrorCode.INVALID_ARGUMENT, "create_object does not work on farms (%d)", ClassConstants.FARM.getValue());
		}
		
		if (this.hasAtLeastOneSiblingOfTypes(RMSNodeType.SET_SCALING_TO_MAP_SIZE)) {
			var n = LongSetPossible.of(this.getArgumentAsInt(0, input));
			if (n.isAtLeastOneGreaterThan(9320)) {
				result.addError(this, RMSErrorCode.INVALID_ARGUMENT, "When %s is set, the %s cannot go over %d", RMSNodeType.SET_SCALING_TO_MAP_SIZE, RMSNodeType.NUMBER_OF_GROUPS, 9320);
			}
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

}

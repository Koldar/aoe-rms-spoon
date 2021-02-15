package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.LongSetPossible;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * The abstract version of the associated RMS command. The semantic analysis and code geneation are tuned with Age of empires 2: DE version. Still,
 * you can create a command performing code generation and semantic analysis of another version by extending this class. Then, you can create a new age of empries version by extending
 * {@link AbstractAoEVersion} abstract class.
 * 
 * @author massi
 *
 */
public abstract class AbstractNumberOfGroups extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractNumberOfGroups() {
		super(RMSNodeType.NUMBER_OF_GROUPS);
	}

	@Override
	public String getArgumentName() {
		return "amount";
	}

	@Override
	public Object getDefaultValue() {
		return "individual object. no groups";
	}

	@Override
	public String getArgumentComment() {
		return "";
	}

	@Override
	public String getComment() {
		return "Place the specified number of groups, which each consist of the number of individual objects chosen in number_of_objects. Total objects = number_of_objects x number_of_groups";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureItIsOnlyInstructionOfTypeInDict(this);
		result.ensureArgumentGreaterThan0(this.getArgument(0));
		
		if (this.hasAtLeastOneSiblingOfTypes(RMSNodeType.SET_SCALING_TO_MAP_SIZE)) {
			var n = LongSetPossible.of(this.getArgumentAsInt(0, input));
			if (n.isAtLeastOneGreaterThan(9320)) {
				result.addError(this, RMSErrorCode.INVALID_ARGUMENT, "When %s is set, the %s cannot go over %d", RMSNodeType.SET_SCALING_TO_MAP_SIZE, RMSNodeType.NUMBER_OF_GROUPS, 9320);
			}
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

}

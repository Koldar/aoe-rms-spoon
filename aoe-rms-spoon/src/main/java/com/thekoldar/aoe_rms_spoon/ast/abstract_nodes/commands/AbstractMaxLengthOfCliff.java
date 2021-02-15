package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
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
public abstract class AbstractMaxLengthOfCliff extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractMaxLengthOfCliff() {
		super(RMSNodeType.MAX_LENGTH_OF_CLIFF);
	}

	@Override
	public String getArgumentName() {
		return "length";
	}

	@Override
	public Object getDefaultValue() {
		return 8;
	}

	@Override
	public String getArgumentComment() {
		return "max length of the cliff. Measured in \"cliff segments\"";
	}

	@Override
	public String getComment() {
		return "Cliff lengths are chosen at random from between min and max (inclusive).  The unit is NOT tiles, but rather \"cliff segments\".";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentGreaterThan(this.getArgument(0), 3);
		
		LongSetPossible min = LongSetPossible.of(5);
		LongSetPossible max = LongSetPossible.of(this.getArgumentAsInt(0, input));
		if (this.hasAtLeastOneNextSiblingOfTypes(RMSNodeType.MIN_LENGTH_OF_CLIFF)) {
			min = LongSetPossible.of(((AbstractExpressionNode)this.getSiblingOfTypes(RMSNodeType.MIN_LENGTH_OF_CLIFF).getAny()).getAsLong(input));
		}
		
		if (min.areAtLeastOneGreaterThanAnyOf(max)) {
			result.addError(this, RMSErrorCode.INVALID_ARGUMENT, "in cliffs %s is greater than %s", RMSNodeType.MIN_LENGTH_OF_CLIFF, RMSNodeType.MAX_LENGTH_OF_CLIFF);
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

}

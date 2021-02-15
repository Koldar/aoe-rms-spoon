package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.IPossibleValue;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.LongSetPossible;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SetPossibleValue;

/**
 * The abstract version of the associated RMS command. The semantic analysis and code geneation are tuned with Age of empires 2: DE version. Still,
 * you can create a command performing code generation and semantic analysis of another version by extending this class. Then, you can create a new age of empries version by extending
 * {@link AbstractAoEVersion} abstract class.
 * 
 * @author massi
 *
 */
public abstract class AbstractMinNumberOfCliffs extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractMinNumberOfCliffs() {
		super(RMSNodeType.MIN_NUMBER_OF_CLIFFS);
	}

	@Override
	public String getArgumentName() {
		return "amount";
	}

	@Override
	public Object getDefaultValue() {
		return 3;
	}

	@Override
	public String getArgumentComment() {
		return "number";
	}

	@Override
	public String getComment() {
		return "Set the minimum of distinct cliffs to create.  The actual number of cliffs is chosen at random from between min and max inclusive.  Does not scale with map size, so you must do so manually using Conditionals.";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentIsBetween(this.getArgument(0), 0, 9999, true, true);
		
		LongSetPossible min = LongSetPossible.of(this.getArgumentAsInt(0, input));
		LongSetPossible max = LongSetPossible.of(8);
		if (this.hasAtLeastOneNextSiblingOfTypes(RMSNodeType.MAX_NUMBER_OF_CLIFFS)) {
			max = new LongSetPossible(((AbstractRMSCommand)this.getSiblingOfTypes(RMSNodeType.MAX_NUMBER_OF_CLIFFS).getAny()).getArgumentAsInt(0, input));
		}
		
		if (min.areAllGreaterThan(max)) {
			result.addError(this, RMSErrorCode.INVALID_ARGUMENT, "in cliffs %s is greater than %s", RMSNodeType.MIN_NUMBER_OF_CLIFFS, RMSNodeType.MAX_NUMBER_OF_CLIFFS);
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	

}

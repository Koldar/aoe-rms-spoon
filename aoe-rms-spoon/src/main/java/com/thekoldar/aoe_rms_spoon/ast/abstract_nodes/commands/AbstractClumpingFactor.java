package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSSemanticWarningException;
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
public abstract class AbstractClumpingFactor extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractClumpingFactor() {
		super(RMSNodeType.CLUMPING_FACTOR);
	}

	@Override
	public String getArgumentName() {
		return "percentage";
	}

	@Override
	public Object getDefaultValue() {
		return 20;
	}

	@Override
	public String getArgumentComment() {
		return "low values: elongated irregular lands; high values cerate rounder lands";
	}

	@Override
	public String getComment() {
		return "The extent to which land growth prefers to clump together near existing tiles";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureIsCommand(this);
		result.ensureArgumentIsLiteralInteger(this, 0);
		result.ensureArgumentIsBetween(this.getArgument(0), 0, 100, true, true);
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	

}

package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSSemanticErrorException;
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
public abstract class AbstractZone extends AbstractRMSSingleRequiredIntArgumentCommand{

	protected AbstractZone() {
		super(RMSNodeType.ZONE);
	}

	@Override
	public String getArgumentName() {
		return "zone_number";
	}

	

	@Override
	public String getArgumentComment() {
		return "";
	}

	@Override
	public String getComment() {
		return "";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureArgumentIsLiteralInteger(this, 0);
		result.ensureThereAreNoSiblingOfTheSameType(this);
		
		if (this.getArgumentAsInt(0, input).contains(99L)) {
			result.add(new RMSSemanticErrorException(RMSErrorCode.INVALID_ARGUMENT, "Invalid Zone 99, since it will crash the game"));
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	

}

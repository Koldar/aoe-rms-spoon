package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
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
public abstract class AbstractLandId extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractLandId() {
		super(RMSNodeType.LAND_ID);
	}

	@Override
	public String getArgumentName() {
		return "id";
	}

	@Override
	public Object getDefaultValue() {
		return "no id";
	}

	@Override
	public String getArgumentComment() {
		return "land id";
	}

	@Override
	public String getComment() {
		return "Assign a numeric label to a land, which can later be used to place objects specifically on that land with place_on_specific_land_id.  Unrelated to any zone numbers.";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		var first = this.getArgument(0);
		
		result.ensureArgumentIsLiteralInteger(this, 0);
		
		if (first.getAsLong(input).contains(-9L)) {
			result.add(new RMSSemanticErrorException(RMSErrorCode.INVALID_ARGUMENT, "Zone Id -9 should not be used since it is used by gaia"));
		}
		
		if (this.isUnderNodeWithTypes(RMSNodeType.CREATE_PLAYER_LANDS)) {
			
		}
		
		
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	

}

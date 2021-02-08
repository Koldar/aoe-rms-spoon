package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractNumberOfClumps extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractNumberOfClumps() {
		super(RMSNodeType.NUMBER_OF_CLUMPS);
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
		return "number of clumps (e.g., hills) that you want to create. If number_of_tiles is specified, each hill will have the same amount of number_of_tiles";
	}

	@Override
	public String getComment() {
		return "Number of individual hills to create.  If clumps are adjacent, the hills may merge.";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureArgumentIsLiteralInteger(this, 0);
		result.ensureArgumentGreaterThan(this.getArgument(0), 0, 1);
		
		return this.semanticCheckChildren(input);
	}
	
}

package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractCliffCurliness extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractCliffCurliness() {
		super(RMSNodeType.CLIFF_CURLINESS);
	}

	@Override
	public String getArgumentName() {
		return "percentage";
	}

	@Override
	public Object getDefaultValue() {
		return 36;
	}

	@Override
	public String getArgumentComment() {
		return "";
	}

	@Override
	public String getComment() {
		return "The chance that a cliff changes direction at each segment. Use low values for straight cliffs and high values for curly cliffs.";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentIsPercentage(this.getArgument(0), 0);
		
		return result.merge(this.semanticCheckChildren(input));
	}
	

}

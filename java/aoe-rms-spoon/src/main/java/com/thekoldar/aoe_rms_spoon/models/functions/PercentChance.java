package com.thekoldar.aoe_rms_spoon.models.functions;

import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.models.exceptions.AbstractRMSException;

public class PercentChance extends AbstractRMSNode {

	protected PercentChance() {
		super(RMSNodeType.PERCENT_CHANCE);
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		return null;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = CodeGenerationOutput.instance();
		
		var percent = this.getChildren().get(0).codeGeneration(input);
		var code = this.getChildren().get(1).codeGeneration(input);
		
		result.addLine("percent_chance");
		result.mergeIntoLastLine(percent, " ");
		result.mergeIntoLastLine(code, " ");
		
		return result;
	}

}

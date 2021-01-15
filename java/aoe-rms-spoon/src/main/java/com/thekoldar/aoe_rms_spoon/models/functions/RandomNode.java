package com.thekoldar.aoe_rms_spoon.models.functions;

import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.models.exceptions.AbstractRMSException;

public class RandomNode extends AbstractRMSNode {

	protected RandomNode() {
		super(RMSNodeType.RANDOM);
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = CodeGenerationOutput.instance();
		
		result.addLine("start_random");
		
		for (var c : this.children) {
			result.merge(c.codeGeneration(input));
		}
		
		result.addLine("end_random");
		
		return result;
	}

}

package com.thekoldar.aoe_rms_spoon.models.functions;

import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.models.exceptions.AbstractRMSException;

public class IfNode extends AbstractRMSNode {

	public IfNode() {
		super(RMSNodeType.IF);
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		return this.semanticCheckChildren(input);
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = CodeGenerationOutput.instance();
		 
		var condition = this.getChildren().get(0).codeGeneration(input);
		var thenBranch = this.getChildren().get(1).codeGeneration(input);
		var elseBranch = this.getChildren().size() > 2 ? this.getChildren().get(2).codeGeneration(input) : null;
		
		result.addLine("if");
		result.mergeIntoLastLine(condition, " ");
		result.merge(thenBranch);
		if (elseBranch != null) {
			result.addLine("else");
			result.merge(elseBranch);
		}
		result.addLine("endif");
		
		return result;
	}

}

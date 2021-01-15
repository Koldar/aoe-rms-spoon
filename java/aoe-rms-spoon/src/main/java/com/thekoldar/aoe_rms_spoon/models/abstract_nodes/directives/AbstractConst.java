package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.directives;


import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractDirective;

public abstract class AbstractConst extends AbstractDirective {

	private String name;
	private int value;
	
	protected AbstractConst(String name, int value) {
		super(RMSNodeType.CONST);
		this.name = name;
		this.value = value;
	}


	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) {
		return null;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = new CodeGenerationOutput();
		result.addLine(String.format("#const %s %d", this.name, this.value));
		return result;
	}

	
}

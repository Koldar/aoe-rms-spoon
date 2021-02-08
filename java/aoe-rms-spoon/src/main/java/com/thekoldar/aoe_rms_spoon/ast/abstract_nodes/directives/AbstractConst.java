package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.directives;


import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractDirective;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSSemanticWarningException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractConst extends AbstractDirective {

	private String name;
	private int value;
	
	protected AbstractConst(String name, int value) {
		super(RMSNodeType.CONST);
		this.name = name;
		this.value = value;
	}
	
	public String getLabel() {
		return String.format("%s = %d", this.name, this.value);
	}


	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		if (input.isConstDefined(name)) {
			result.add(new RMSSemanticWarningException(RMSErrorCode.CONST_REDEFINED, "You are redefining const %s. Old value was %d but the new one is %d", this.name, input.getConstValue(this.name), this.value));
		}
		input.knowThatConstIs(this.name, this.value);
		return result;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = new CodeGenerationOutput();
		result.addLine(String.format("#const %s %d", this.name, this.value));
		return result;
	}
	
	@Override
	public String toString() {
		return String.format("const %s=%d", this.name, this.value);
	}

	
}

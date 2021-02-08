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

public class AbstractDefine extends AbstractDirective {

	private String define;
	
	protected AbstractDefine(String define) {
		super(RMSNodeType.DEFINE);
		this.define = define;
	}

	public String getLabel() {
		return String.format("%s", this.define);
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		if (input.isDefined(this.define) ) {
			result.add(new RMSSemanticWarningException(RMSErrorCode.DEFINE_REDEFINED, "You have redefined the define %s", this.define));
		}
		input.knowThatDefineIsDefined(this.define);
		return result;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = new CodeGenerationOutput();
		result.addLine(String.format("#define %s", this.define));
		return result;
	}

	@Override
	public String toString() {
		return String.format("define %s", this.define);
	}
	
}

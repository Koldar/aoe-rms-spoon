package com.thekoldar.aoe_rms_spoon.models.abstract_nodes.directives;


import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractDirective;

public class AbstractIncludeDrs extends AbstractDirective {

	private String filepath;
	
	protected AbstractIncludeDrs(String filepath) {
		super(RMSNodeType.INCLUDE_DRS);
		this.filepath = filepath;
	}


	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) {
		return null;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = new CodeGenerationOutput();
		result.addLine(String.format("#include_drs %s", this.filepath));
		return result;
	}

	
}

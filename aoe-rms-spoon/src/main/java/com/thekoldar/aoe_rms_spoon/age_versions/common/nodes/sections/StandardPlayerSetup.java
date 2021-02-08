package com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.sections;

import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.sections.AbstractPlayerSetup;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public class StandardPlayerSetup extends AbstractPlayerSetup {


	public StandardPlayerSetup() {
		super();
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureParentIsRoot(this);
		
		return result.merge(this.semanticCheckChildren(input));
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		return super.codeGeneration(input);
	}
	
	
	


}

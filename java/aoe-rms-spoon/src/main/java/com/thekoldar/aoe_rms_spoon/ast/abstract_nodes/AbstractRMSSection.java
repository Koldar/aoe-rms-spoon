package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractRMSSection extends AbstractRMSNode {

	protected String sectionName;
	
	protected AbstractRMSSection(RMSNodeType type) {
		super(type);
		this.sectionName = this.getSectionName();
	}

	/**
	 * name of the section
	 * @return
	 */
	public String getSectionName() {
		return this.getNodeType().getRmsName();
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		return result.merge(this.semanticCheckChildren(input));
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = CodeGenerationOutput.instance();
		
		result.addLine();
		result.addLine("<" + this.sectionName + ">");
		result.addLine();
		
		for (var c: this.children) {
			result.merge(c.codeGeneration(input));
		}
		
		return result;
	}

}

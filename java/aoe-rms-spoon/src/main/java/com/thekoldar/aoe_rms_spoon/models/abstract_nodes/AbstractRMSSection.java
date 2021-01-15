package com.thekoldar.aoe_rms_spoon.models.abstract_nodes;

import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.exceptions.AbstractRMSException;

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
		return this.semanticCheckChildren(input);
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = CodeGenerationOutput.instance();
		
		result.addLine("<" + this.sectionName + ">");
		result.addLine();
		
		for (var c: this.children) {
			result.merge(c.codeGeneration(input));
		}
		
		return result;
	}

}

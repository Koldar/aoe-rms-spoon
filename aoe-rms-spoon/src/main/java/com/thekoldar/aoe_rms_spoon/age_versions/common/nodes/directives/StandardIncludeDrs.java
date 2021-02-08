package com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.directives;


import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractDirective;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.directives.AbstractIncludeDrs;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.framework.utils.Utils;

public class StandardIncludeDrs extends AbstractIncludeDrs {

	private String filepath;
	
	public StandardIncludeDrs(String filepath) {
		super(filepath);
		this.filepath = filepath;
	}
	
}

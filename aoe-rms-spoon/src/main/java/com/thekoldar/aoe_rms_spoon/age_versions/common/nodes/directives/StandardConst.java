package com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.directives;


import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractDirective;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.directives.AbstractConst;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * Age of empires Definitive Edition version of the node. It is instantiable
 * @author massi
 *
 */
public class StandardConst extends AbstractConst {

	private String name;
	private int value;
	
	public StandardConst(String name, int value) {
		super(name, value);
	}

	
}

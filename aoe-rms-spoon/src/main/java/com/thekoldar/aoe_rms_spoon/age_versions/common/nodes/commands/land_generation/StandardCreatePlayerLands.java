package com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation;

import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCreatePlayerLands;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * Age of empires Definitive Edition version of the node. It is instantiable
 * @author massi
 *
 */
public class StandardCreatePlayerLands extends AbstractCreatePlayerLands {

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		return result.merge(this.semanticCheckChildren(input));
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		return super.codeGeneration(input);
	}

	
	
}

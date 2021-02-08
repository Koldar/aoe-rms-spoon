package com.thekoldar.aoe_rms_spoon.age_versions.common.nodes.commands.land_generation;

import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands.AbstractCreatePlayerLands;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public class StandardCreatePlayerLands extends AbstractCreatePlayerLands {

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureWeHaveIncludedFile(this.getRoot(), "GeneratingObjects.inc", "This file is imporant if you want to create players in the positions specified by create_player_lands!");
		result.ensureIsDefined("GNR_NORMALTC", "The define is required if you want to position players in the positions specified by creatE_player_lands");
		result.ensureIsDefined("GNR_STARTVILLS", "The define is required if you want to position players in the positions specified by creatE_player_lands");
		result.ensureIsDefined("GNR_CLASSICSCOUT", "The define is required if you want to position players in the positions specified by creatE_player_lands");
		
		return result.merge(this.semanticCheckChildren(input));
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		return super.codeGeneration(input);
	}

	
	
}

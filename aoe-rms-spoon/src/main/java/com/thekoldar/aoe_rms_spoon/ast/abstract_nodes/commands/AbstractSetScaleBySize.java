package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;

import com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals.MapSizes;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractSetScaleBySize extends AbstractRMSNoArgumentCommand {

	protected AbstractSetScaleBySize() {
		super(RMSNodeType.SET_SCALE_BY_SIZE);
	}

	@Override
	public String getComment() {
		return "Scales number_of_tiles to the map size.  Unscaled value refers to a 100x100 map";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		if (this.getSiblingOfTypes(RMSNodeType.NUMBER_OF_TILES).isEmpty()) {
			result.addError(this, RMSErrorCode.EXPECTED_REQUIRED_COMMAND, "We expected to have %s defined (since %s was defined), but it was not!", RMSNodeType.NUMBER_OF_TILES, RMSNodeType.SET_SCALE_BY_SIZE);
		}
		if (!this.getSiblingOfTypes(RMSNodeType.SET_SCALE_BY_GROUPS).isEmpty()) {
			result.addError(this, RMSErrorCode.CONFLICTING_COMMANDS, "%s and %s are mutually exclusive!", RMSNodeType.SET_SCALE_BY_SIZE, RMSNodeType.SET_SCALE_BY_GROUPS);
		}
		
		return this.semanticCheckChildren(input);
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = super.codeGeneration(input);
		
		input.getSemanticInput().ifPresent(i -> {
			//get number of tiles
			var numberOfTiles = ((AbstractNumberOfTiles)this.getSiblingOfTypes(RMSNodeType.NUMBER_OF_TILES).getAny()).getArgumentAsInt(0, i);
			
			//add the corresponding values, since it has been scaled
			result.addStringToLastLine(String.format("/* %s */", 
				MapSizes.all()
					.collect(ms -> PrimitiveTuples.pair(ms, numberOfTiles * ms.getTilesPer100x100()))
					.collect(pair -> PrimitiveTuples.pair(pair.getOne(), (int)Math.round(pair.getTwo())))
					.collect(pair -> String.format("%s=%d", pair.getOne().getName(), pair.getTwo()))
					.makeString()
					));
		});
		
		return result;
	}
	
	
	

}

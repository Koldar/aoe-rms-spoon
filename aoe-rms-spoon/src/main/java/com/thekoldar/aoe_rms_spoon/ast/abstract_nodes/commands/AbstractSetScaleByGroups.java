package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.impl.tuple.Tuples;
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

public abstract class AbstractSetScaleByGroups extends AbstractRMSNoArgumentCommand {

	protected AbstractSetScaleByGroups() {
		super(RMSNodeType.SET_SCALE_BY_GROUPS);
	}

	@Override
	public String getComment() {
		return "Scales number_of_clumps to the map size.  Unscaled value refers to a 100x100 map";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		if (this.getSiblingOfTypes(RMSNodeType.NUMBER_OF_CLUMPS).isEmpty()) {
			result.addError(this, RMSErrorCode.EXPECTED_REQUIRED_COMMAND, "We expected to have %s defined (since %s was defined), but it was not!", RMSNodeType.NUMBER_OF_CLUMPS, RMSNodeType.SET_SCALE_BY_GROUPS);
		}
		if (!this.getSiblingOfTypes(RMSNodeType.SET_SCALE_BY_SIZE).isEmpty()) {
			result.addError(this, RMSErrorCode.CONFLICTING_COMMANDS, "%s and %s are mutually exclusive!", RMSNodeType.SET_SCALE_BY_SIZE, RMSNodeType.SET_SCALE_BY_GROUPS);
		}
		
		return this.semanticCheckChildren(input);
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = super.codeGeneration(input);
		
		input.getSemanticInput().ifPresent(i -> {
			var numberOfClumps = ((AbstractNumberOfClumps)this.getSiblingOfTypes(RMSNodeType.NUMBER_OF_CLUMPS).getAny()).getArgumentAsInt(0, i);
			
			//add the corresponding values, since it has been scaled
			result.addStringToLastLine(String.format("/* %s */", 
					MapSizes.all()
						.collect(ms -> Tuples.pair(
								ms, 
								numberOfClumps.getPossibleValues().collect(j -> (int)(Math.round(j * ms.getTilesPer100x100()))).makeString()
							)
						)
						.collect(pair -> String.format("%s=%s", pair.getOne().getName(), pair.getTwo()))
						.makeString()
						));
		});
	
		
		return result;
	}


}

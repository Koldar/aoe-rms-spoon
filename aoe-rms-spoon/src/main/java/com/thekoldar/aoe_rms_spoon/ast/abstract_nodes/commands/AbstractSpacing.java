package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.impl.tuple.primitive.PrimitiveTuples;

import com.thekoldar.aoe_rms_spoon.age_versions.common.conditionals.MapSizes;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractSpacing extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractSpacing() {
		super(RMSNodeType.SPACING);
	}

	@Override
	public String getArgumentName() {
		return "amount";
	}

	@Override
	public Object getDefaultValue() {
		return 1;
	}

	@Override
	public String getArgumentComment() {
		return "tiles between each level of the hill";
	}

	@Override
	public String getComment() {
		return "Number of tiles between each elevation level.  Numbers larger than 1 will produce rings of flat terrain on each level of a hill.";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureArgumentIsLiteralInteger(this, 0);
		result.ensureArgumentGreaterThan(this.getArgument(0), 0, 1);
		result.ensureIsUnder(this, RMSNodeType.CREATE_ELEVATION);
		
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

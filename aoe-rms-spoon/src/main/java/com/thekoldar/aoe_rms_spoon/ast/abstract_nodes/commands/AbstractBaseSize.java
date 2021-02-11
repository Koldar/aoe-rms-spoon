package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractBaseSize extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractBaseSize() {
		super(RMSNodeType.BASE_SIZE);
	}

	@Override
	public String getArgumentName() {
		return "radius";
	}

	@Override
	public Object getDefaultValue() {
		return 3;
	}

	@Override
	public String getArgumentComment() {
		return "number of tiles";
	}

	@Override
	public String getComment() {
		return "Square radius of the initially placed land origin, before any growth. base_size is the minimum distance that a land will be placed from the edge of the map. Land bases are placed sequentially, so if they are large and overlap, the land placed last will be the one visible in the overlapping region.";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureArgumentIsLiteralInteger(this, 0);
		result.ensureArgumentGreaterThan(this.getArgument(0), 0);
		result.ensureThereAreNoSiblingOfTheSameType(this);
		
		if (this.hasAtLeastOneSiblingOfTypes(RMSNodeType.LAND_PERCENT) && this.hasAtLeastOneSiblingOfTypes(RMSNodeType.NUMBER_OF_TILES)) {
			var isLandPercent0 = ((AbstractRMSCommand) this.getSiblingOfTypes(RMSNodeType.LAND_PERCENT).getAny()).getArgumentAsInt(0, input).containsOnly(0L);
			var isNumberOfTiles0 = ((AbstractRMSCommand) this.getSiblingOfTypes(RMSNodeType.NUMBER_OF_TILES).getAny()).getArgumentAsInt(0, input).containsOnly(0L);
			
			if (isLandPercent0 && isNumberOfTiles0) {
				this.infoCmd("base_size will generate a perfect square since land_pecent and numbher_of_tiles are both set to 0");
			}
			
			var isLandPercentMaybe0 = ((AbstractRMSCommand) this.getSiblingOfTypes(RMSNodeType.LAND_PERCENT).getAny()).getArgumentAsInt(0, input).contains(0L);
			var isNumberOfTilesMaybe0 = ((AbstractRMSCommand) this.getSiblingOfTypes(RMSNodeType.NUMBER_OF_TILES).getAny()).getArgumentAsInt(0, input).contains(0L);
			
			if (isLandPercentMaybe0 && isNumberOfTilesMaybe0) {
				this.infoCmd("base_size may generate a perfect square since land_pecent and numbher_of_tiles can both be set to 0");
			}
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	

}

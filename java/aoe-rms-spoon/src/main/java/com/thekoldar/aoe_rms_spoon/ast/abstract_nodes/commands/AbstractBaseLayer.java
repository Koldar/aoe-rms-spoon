package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractBaseLayer extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractBaseLayer() {
		super(RMSNodeType.BASE_LAYER);
	}

	@Override
	public String getArgumentName() {
		return "terrain_type";
	}

	@Override
	public Object getDefaultValue() {
		return "no layered terrain";
	}

	@Override
	public String getArgumentComment() {
		return "layer to use to cover";
	}

	@Override
	public String getComment() {
		return "Use this attribute in addition to base_terrain if (and only if) you specified a layer for the map base terrain at the beginning of <LAND_GENERATION>";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		
		if (this.isUnderNodeWithTypes(RMSNodeType.ELEVATION_GENERATION)) {
			//fetch the base_terrain in LAND_GENERATION section
			var isBaseTerrainInLandGenerationIsPresent = !this.getNodesOfTypes(RMSNodeType.BASE_TERRAIN).select(n -> n.isUnderNodeWithTypes(RMSNodeType.LAND_GENERATION)).isEmpty();
			if (!isBaseTerrainInLandGenerationIsPresent) {
				result.addError(RMSErrorCode.EXPECTED_REQUIRED_COMMAND, "When using %s it is required thant %s is present in %s as well!", this, RMSNodeType.BASE_TERRAIN, RMSNodeType.LAND_GENERATION);
			}
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

}

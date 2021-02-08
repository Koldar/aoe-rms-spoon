package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractSpacingToOtherTerrainTypes extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractSpacingToOtherTerrainTypes() {
		super(RMSNodeType.SPACING_TO_OTHER_TERRAIN_TYPES);
	}

	@Override
	public String getArgumentName() {
		return "distance";
	}

	@Override
	public Object getDefaultValue() {
		return 0;
	}

	@Override
	public String getArgumentComment() {
		return "distance, in tiles";
	}

	@Override
	public String getComment() {
		return "Minimum distance that this terrain will stay away from other terrain types. Only considers existing terrains at the time of generation - terrains generated later will need their own spacing. Terrains will not stay away from the same terrain type created previously. Also affects the distance that the terrain will stay away from cliffs (because cliffs generate their own terrain underneath them - terrain 16)";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentIsLiteralInteger(this, 0);
		
		if (this.hasAtLeastOneNextSiblingOfTypes(RMSNodeType.SET_FLAT_TERRAIN_ONLY)) {
			this.infoCmd("Since {} is used with {} the terrain will stay await from slopes as well", this, RMSNodeType.SET_FLAT_TERRAIN_ONLY);
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	

}

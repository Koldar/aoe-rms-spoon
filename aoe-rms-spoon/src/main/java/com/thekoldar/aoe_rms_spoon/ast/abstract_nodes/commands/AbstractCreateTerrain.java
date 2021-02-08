package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractCreateTerrain extends AbstractRMSCommand {

	protected AbstractCreateTerrain() {
		super(RMSNodeType.CREATE_TERRAIN);
	}

	@Override
	public String getComment() {
		return "Create a clump of terrain.  The exact behavior is dependent on the attributes specified.";
	}

	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		return Lists.fixedSize.of(
				CommandFormalArgument.optionalInt("terrain_type", "grass", ""),
				CommandFormalArgument.requiredDict("terrain_specifics", "")
		).toImmutable();
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();

		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureItContainsCommonNodesAnd(this, RMSNodeType.BASE_TERRAIN, RMSNodeType.BASE_LAYER, RMSNodeType.TERRAIN_MASK, RMSNodeType.SPACING_TO_OTHER_TERRAIN_TYPES, RMSNodeType.SET_FLAT_TERRAIN_ONLY, RMSNodeType.LAND_PERCENT, RMSNodeType.NUMBER_OF_TILES, RMSNodeType.NUMBER_OF_CLUMPS, RMSNodeType.CLUMPING_FACTOR, RMSNodeType.SET_SCALE_BY_SIZE, RMSNodeType.SET_SCALE_BY_GROUPS, RMSNodeType.SET_AVOID_PLAYER_START_AREAS, RMSNodeType.HEIGHT_LIMITS);
		
		//we need to ensure that base_terrain is present, since it does not default to grass
		result.ensureContainsNodeWithType(this, RMSNodeType.BASE_TERRAIN);
		
		return result.merge(this.semanticCheckChildren(input));
	}

	
	
}

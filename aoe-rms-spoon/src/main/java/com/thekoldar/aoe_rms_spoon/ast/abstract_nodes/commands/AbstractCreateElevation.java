package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSection;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractCreateElevation extends AbstractRMSCommand {

	protected AbstractCreateElevation() {
		super(RMSNodeType.CREATE_ELEVATION);
	}

	@Override
	public String getComment() {
		return "Create one or more hills of random height, up to the given height. Terrain on which the hill(s) should generate.  If you have multiple terrains you want hills on, then you need multiple create_elevation commands.";
	}

	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		return Lists.fixedSize.of(
				CommandFormalArgument.optionalInt("max_height", 0, ""),
				CommandFormalArgument.requiredDict("terrain_specifics", "")
		).toImmutable();
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureArgumentIsLiteralInteger(this, 0);
		result.ensureArgumentIsBetween(this.getArgument(0), 0, 1, 7, true, true);
		
		result.ensureItContainsCommonNodesAnd(this.getArgument(1), RMSNodeType.BASE_TERRAIN, RMSNodeType.BASE_LAYER, RMSNodeType.NUMBER_OF_TILES, RMSNodeType.NUMBER_OF_CLUMPS, RMSNodeType.SET_SCALE_BY_SIZE, RMSNodeType.SET_SCALE_BY_GROUPS, RMSNodeType.SPACING, RMSNodeType.ENABLE_BALANCED_ELEVATION);
		
		//we need number_of_tiles
		result.ensureContainsNodeWithType(this, RMSNodeType.NUMBER_OF_TILES);
		
		if (this.getNodesOfTypes(RMSNodeType.ENABLE_BALANCED_ELEVATION).isEmpty()) {
			result.addWarning(this, RMSErrorCode.BEHAVIOR_MAY_BE_ALTERED, "You have not specified %s. This means that a bias of hill placement towards the bottom (south) of the map is present whiel generating the map.", RMSNodeType.ENABLE_BALANCED_ELEVATION);
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

	
	
}

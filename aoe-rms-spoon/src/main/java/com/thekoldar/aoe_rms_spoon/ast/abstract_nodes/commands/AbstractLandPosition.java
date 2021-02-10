package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSOptionalIntOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractLandPosition extends AbstractRMSOptionalIntOptionalIntArgumentCommand{

	protected AbstractLandPosition() {
		super(RMSNodeType.LAND_POSITION);
	}

	@Override
	public String getComment() {
		return "land_position 50 50 is the center of the map. Ignores border restrictions";
	}

	@Override
	public String getArgument1Name() {
		return "x";
	}

	@Override
	public Object getArgument1DefaultValue() {
		return "random";
	}

	@Override
	public String getArgument1Comment() {
		return "X is the axis running from the top-left edge to bottom-right";
	}

	@Override
	public String getArgument2Name() {
		return "y";
	}

	@Override
	public Object getArgument2DefaultValue() {
		return "random";
	}

	@Override
	public String getArgument2Comment() {
		return "Y is the axis running from the bottom-left edge to top-right";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentIsBetween(this.getArgument(0), 0, 100, true, true);
		result.ensureArgumentIsBetween(this.getArgument(1), 0, 100, true, true);
		
		if (this.getArgumentAsInt(0, input).contains(100)) {
			var connectionGeneration = !this.getAllNodesInASTOfTypes(RMSNodeType.CONNECTION_GENERATION).isEmpty();
			if (connectionGeneration) {
				result.addWarning(RMSErrorCode.MAY_CRASH, "If a connection goes to the land with land_position y set to 100, the game will crash! Consider changing y to any other value");
			} 
		}
		
		if (this.hasAtLeastOneSiblingOfTypes(RMSNodeType.TOP_BORDER, RMSNodeType.BOTTOM_BORDER, RMSNodeType.LEFT_BORDER, RMSNodeType.RIGHT_BORDER)) {
			result.addWarning(RMSErrorCode.IGNORE_VALUE, "borders are ignored by land_position. If placed ouside the borders, the land will not grow beyond its base_size");
		}
		if (this.isUnderNodeWithTypes(RMSNodeType.CREATE_PLAYER_LANDS)) {
			result.addWarning(RMSErrorCode.DISABLE_VALUE, "land_position is ignored in create_player_lands");
		}
		
		if (this.isUnderNodeWithTypes(RMSNodeType.CREATE_LAND) && (this.hasAtLeastOneSiblingOfTypes(RMSNodeType.ASSIGN_TO, RMSNodeType.ASSIGN_TO_PLAYER))) {
			var isDirectPlacementActive = !this.getAllNodesInASTOfTypes(RMSNodeType.DIRECT_PLACEMENT).select(n -> n.isUnderNodeWithTypes(RMSNodeType.PLAYER_SETUP)).isEmpty();
			if (!isDirectPlacementActive) {
				result.addWarning(RMSErrorCode.DISABLE_VALUE, "land_position is disabled when using assign_to_player or assign_to in create_land, unless duirect_placement is specified in PLAYER_SETUP");
			}
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

	
	
}

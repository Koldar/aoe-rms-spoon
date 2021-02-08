package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSOptionalIntOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSRequiredIntOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractCircleRadius extends AbstractRMSRequiredIntOptionalIntArgumentCommand{

	protected AbstractCircleRadius() {
		super(RMSNodeType.CIRCLE_RADIUS);
	}

	@Override
	public String getArgument1Name() {
		return "radius";
	}

	@Override
	public String getArgument1Comment() {
		return "number of tiles representing the radius of the circle";
	}

	@Override
	public String getArgument2Name() {
		return "variance";
	}

	@Override
	public Object getArgument2DefaultValue() {
		return 0;
	}

	@Override
	public String getArgument2Comment() {
		return "0 is a perfect circle with no variance. 20 seems to be close to the standard amount of variance when not using circle_radius. Very large values will tend to force players towards the corners of the map";
	}

	@Override
	public String getComment() {
		return "Used in create_player_lands to position the player lands in a circle with equal distance to the center, with specified variance.";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentIsLiteralInteger(this, 0);
		result.ensureArgumentIsBetween(this.getArgument(0), 0, 1, 50, true, true);
		if (this.getChildren().size() > 1) {
			result.ensureArgumentIsLiteralInteger(this, 1);
			result.ensureArgumentIsBetween(this.getArgument(1), 1, 0, 100, true, true);	
		}
		
		
		result.ensureNodeToBeUnder(this, RMSNodeType.CREATE_PLAYER_LANDS);
		
		return result.merge(this.semanticCheckChildren(input));
	}

	
	

}

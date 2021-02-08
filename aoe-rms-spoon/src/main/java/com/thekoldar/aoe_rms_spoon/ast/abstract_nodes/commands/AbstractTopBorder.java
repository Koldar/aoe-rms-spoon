package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.api.factory.Sets;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractTopBorder extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractTopBorder() {
		super(RMSNodeType.TOP_BORDER);
	}

	@Override
	public String getArgumentName() {
		return "percentage";
	}

	@Override
	public Object getDefaultValue() {
		return 0;
	}

	@Override
	public String getArgumentComment() {
		return "top is north-west";
	}

	@Override
	public String getComment() {
		return "Specify a percentage of map width for land placement and growth to stay away from a given border.";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentIsBetween(this.getArgument(0), 0, 0, 97, true, true);
		
		this.infoCmd("Using a borders. This means that the genrated lands will not be square like but octagon like");
		
		if (this.isUnderNodeWithTypes(RMSNodeType.CREATE_PLAYER_LANDS)) {
			this.infoCmd("The norders will shift the circle that is used to place players on the map");
		}
		result.ensureThereIsAtLeastOneSiblingOfType(this, Sets.immutable.of(RMSNodeType.LEFT_BORDER, RMSNodeType.RIGHT_BORDER, RMSNodeType.BOTTOM_BORDER), "See http://aok.heavengames.com/cgi-bin/forums/display.cgi?action=ct&f=28,42496,0,365");
		
		return result.merge(this.semanticCheckChildren(input));
	}

	
	
}

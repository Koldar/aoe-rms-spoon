package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * Specify a percentage of map width for land placement and growth to stay away from a given border.
 * 
 * <ul>
 * 	<li>Left is south-west; right is north-east, top is north-west; bottom is south-east</li>
 *  <li>There is a hard-coded feature that makes lands look like octagons instead of squares when constrained by borders.</li>
 *  <li>Negative values can be used, as long as the land origin stays inside the map.  To ensure this, do one of the following: Specify a land_position within the map; Specify a sufficiently large base size (this may require manually scaling of base_size with map size)</li>
 *  <li>Borders shift the entire circle for player lands</li>
 *  <li>You cannot have multiple rings of player lands with different borders; they will all be in the same circle</li>
 * </ul>
 * 
 * @author massi
 */
public abstract class AbstractLeftBorder extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractLeftBorder() {
		super(RMSNodeType.LEFT_BORDER);
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
		return "top is south-west";
	}

	@Override
	public String getComment() {
		return "Specify a percentage of map width for land placement and growth to stay away from a given border.";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentIsBetween(this.getArgument(0), 0, 97, true, true);
		
		this.infoCmd("Using a borders. This means that the genrated lands will not be square like but octagon like");
		
		if (this.isUnderNodeWithTypes(RMSNodeType.CREATE_PLAYER_LANDS)) {
			this.infoCmd("The norders will shift the circle that is used to place players on the map");
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}

}

package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * The abstract version of the associated RMS command. The semantic analysis and code geneation are tuned with Age of empires 2: DE version. Still,
 * you can create a command performing code generation and semantic analysis of another version by extending this class. Then, you can create a new age of empries version by extending
 * {@link AbstractAoEVersion} abstract class.
 * 
 * @author massi
 *
 */
public abstract class AbstractAssignToPlayer extends AbstractRMSSingleOptionalIntArgumentCommand {

	protected AbstractAssignToPlayer() {
		super(RMSNodeType.ASSIGN_TO);
	}
	
	@Override
	public String getArgumentName() {
		return "player_number";
	}

	@Override
	public Object getDefaultValue() {
		return "not assigned to any players";
	}

	@Override
	public String getArgumentComment() {
		return "land id";
	}

	@Override
	public String getComment() {
		return "Assign a numeric label to a land, which can later be used to place objects specifically on that land with place_on_specific_land_id.  Unrelated to any zone numbers.";
	}

	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		var playerNumber = this.getArgumentAsInt(0, input);
		
		this.infoCmd("the number {} refers to the lobby orders. Colors do not matter", playerNumber);
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentIsBetween(this.getArgument(0), 1, 8, true, true);
		
		result.declareThatPlayerWithLobbyOrderNeedsToBePlaying(playerNumber);
		
		if (this.hasAtLeastOneSiblingOfTypes(RMSNodeType.LAND_POSITION)) {
			if (this.getAllNodesInASTOfTypes(RMSNodeType.DIRECT_PLACEMENT).select(n -> n.isUnderNodeWithTypes(RMSNodeType.PLAYER_SETUP)).isEmpty()) {
				result.addError(RMSErrorCode.IGNORE_VALUE, "land_position, sibling fo assign_to_player is ignored since direct_placement is not specified");
			}
		}
		
		if (this.hasAtLeastOnePreviousSiblingOfTypes(RMSNodeType.LAND_ID)) {
			result.addError(RMSErrorCode.IGNORE_VALUE, "assign_to resets the previous land_id");
		}
		
		return result.merge(this.semanticCheckChildren(input));
		
	}
}

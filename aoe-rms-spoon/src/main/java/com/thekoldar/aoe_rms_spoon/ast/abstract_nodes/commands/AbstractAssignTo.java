package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.CommandFormalArgument;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.LongSetPossible;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SetPossibleValue;

public abstract class AbstractAssignTo extends AbstractRMSCommand {

	protected AbstractAssignTo() {
		super(RMSNodeType.ASSIGN_TO);
	}

	@Override
	public String getComment() {
		return "Assign a numeric label to a land, which can later be used to place objects specifically on that land with place_on_specific_land_id.  Unrelated to any zone numbers.";
	}

	@Override
	public ImmutableList<CommandFormalArgument> getFormatArguments() {
		return Lists.fixedSize.of(
				CommandFormalArgument.requiredInt("assign_target", "alter the way number is interpreted"),
				CommandFormalArgument.requiredInt("number", "if assign_target is player, it refers to the player id in the lobby order; if it is color, it refers to the player color, if team, it is either -10 to give the land to any player, 0, to give terrain to players with no team, 1,2,3,4 to give land to the corresponding team while -1,-2,-3,-4 to give land to everyone except the associated team"),
				CommandFormalArgument.requiredInt("mode", ""),
				CommandFormalArgument.requiredInt("flags", "")
		).toImmutable();
	}


	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		var assignTarget = this.getArgument(0);
		var number = this.getArgument(1);
		var mode = this.getArgument(2);
		var flags = this.getArgument(3);
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		
		result.ensureIntArgumentIsOneOf(assignTarget, input.getConstValue("AT_PLAYER"), input.getConstValue("AT_COLOR"), input.getConstValue("AT_TEAM"));
		if (assignTarget.getAsLong(input) == input.getConstValue("AT_PLAYER")) {
			this.infoCmd("argument 1 of assign_to refers to lobby order");
			result.ensureArgumentIsBetween(number, 1, 8, true, true);
		}
		else if (assignTarget.getAsLong(input) == input.getConstValue("AT_COLOR")) {
			this.infoCmd("argument 1 of assign_to refers to player color (e.g., 1: blue, 2: red)");
			result.ensureArgumentIsBetween(number, 1, 8, true, true);
		}
		else if (assignTarget.getAsLong(input) == input.getConstValue("AT_TEAM")) {
			result.ensureIntArgumentIsOneOf(number, new LongSetPossible(-10, -4, -3, -2, -1, 0, 1, 2, 3, 4));
		}
		
		if (this.hasAtLeastOneSiblingOfTypes(RMSNodeType.LAND_POSITION)) {
			if (this.getAllNodesInASTOfTypes(RMSNodeType.DIRECT_PLACEMENT).select(n -> n.isUnderNodeWithTypes(RMSNodeType.PLAYER_SETUP)).isEmpty()) {
				result.addError(RMSErrorCode.IGNORE_VALUE, "land_position, sibling fo assign_to is ignored since direct_placement is not specified");
			}
		}
		
		
		if (this.hasAtLeastOnePreviousSiblingOfTypes(RMSNodeType.LAND_ID)) {
			result.addError(RMSErrorCode.IGNORE_VALUE, "assign_to resets the previous land_id");
		}
		
		result.ensureIntArgumentIsOneOf(mode, new LongSetPossible(0, -1));
		result.ensureIntArgumentIsOneOf(flags, new LongSetPossible(0, 1, 2, 3));
		
		
		return result.merge(this.semanticCheckChildren(input));
		
	}
	
	

}

package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.IPossibleValue;
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
public abstract class AbstractMinDistanceToPlayers extends AbstractRMSSingleRequiredIntArgumentCommand{

	protected AbstractMinDistanceToPlayers() {
		super(RMSNodeType.MIN_DISTANCE_TO_PLAYERS);
	}

	@Override
	public String getArgumentName() {
		return "distance";
	}

	@Override
	public String getArgumentComment() {
		return "";
	}

	@Override
	public String getComment() {
		return "Minimum and maximum distance (in tiles) from the origin of player lands, that the object can be placed";
	}

	/**
	 * compute the involve objhect in this create_object
	 * @param input
	 * @return
	 */
	public IPossibleValue<Long> getInvolvedObject(SemanticCheckInput input) {
		var createObject = (AbstractCreateObject)this.getFirstNodeFromPathSatisfying(n -> n.getNodeType().equals(RMSNodeType.CREATE_OBJECT)).getAny();
		return createObject.getInvolvedObject(input);
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureItIsOnlyInstructionOfTypeInDict(this);
		if (!this.getSiblingOfTypes(RMSNodeType.NUMBER_OF_GROUPS).isEmpty()) {
			this.infoCmd("The minimum distance to players refers to the center of the group, not to the individual elements");
		}
		
		if (!this.getSiblingOfTypes(RMSNodeType.PLACE_ON_SPECIFIC_LAND_ID).isEmpty()) {
			var land_id = ((AbstractRMSCommand)this.getSiblingOfTypes(RMSNodeType.PLACE_ON_SPECIFIC_LAND_ID).getAny()).getArgumentAsInt(0, input);
			this.infoCmd("The minimum distance to players refers to the land id %s", land_id);
		
			result.addWarning(this, RMSErrorCode.BEHAVIOR_MAY_BE_ALTERED, "Since you have %s alongside %s, due to a bug the minimum distance applies to all land, rather than only the one with id %s", this.getNodeType(), RMSNodeType.PLACE_ON_SPECIFIC_LAND_ID, land_id);
		}
		
		if (!this.getSiblingOfTypes(RMSNodeType.SET_PLACE_FOR_EVERY_PLAYER).isEmpty()) {
			result.addWarning(this, RMSErrorCode.BEHAVIOR_MAY_BE_ALTERED, "Since you have %s alongside %s, due to a bug the minimum distance applies to all land, rather than only the one owned by the players", this.getNodeType(), RMSNodeType.SET_PLACE_FOR_EVERY_PLAYER);
		}
		
		if (!this.getSiblingOfTypes(RMSNodeType.MAX_DISTANCE_TO_PLAYERS).isEmpty()) {
			var minDistance = this.getArgumentAsInt(0, input);
			var maxDistance = ((AbstractRMSCommand)this.getSiblingOfTypes(RMSNodeType.MAX_DISTANCE_TO_PLAYERS).getAny()).getArgumentAsInt(0, input);
			
			if (minDistance.areAtLeastOneEqualsToAnyOf(maxDistance)) {
				result.addWarning(this, RMSErrorCode.BEHAVIOR_MAY_BE_ALTERED, "There is a case when min distnace is equal o max distance. Due to a bug this will lead to object noticeably biased towrds being placed in the west");
			}
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}
}

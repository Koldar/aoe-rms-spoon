package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalBooleanArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleRequiredIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSErrorCode;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.IPossibleValue;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

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
		var 
		
		if (this.)
		
		
		var numberOfObjects = ((AbstractRMSCommand)this.getSiblingOfTypes(RMSNodeType.NUMBER_OF_OBJECTS).getAny()).getArgumentAsInt(0, input);
		var availableTiles = this.getArgumentAsInt(0, input).collect(u -> u * u);
		
		if (numberOfObjects.areAtLeastOneGreaterThanAnyOf(availableTiles)) {
			result.addWarning(this, RMSErrorCode.INVALID_ARGUMENT, "There is a scenario where the number of objects surpasses the available tiles set by %s. This will create a square filled of resources", this.getNodeType());
		}
		
		return result.merge(this.semanticCheckChildren(input));
	}
}

package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
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
public abstract class AbstractSetPlaceForEveryPlayer extends AbstractRMSNoArgumentCommand {

	protected AbstractSetPlaceForEveryPlayer() {
		super(RMSNodeType.SET_PLACE_FOR_EVERY_PLAYER);
	}
	
	@Override
	public String getComment() {
		return "Place the object(s) as a personal object for each player (actually for each player land).  Objects that cannot be owned by players (boar, gold, trees, etc.) also require set_gaia_object_only to be placed for every player";
	}
	
	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		if (!this.getAllNodesInASTOfTypes(RMSNodeType.LAND_ID).select(n -> n.isUnderNodeWithTypes(RMSNodeType.CREATE_PLAYER_LANDS)).isEmpty()) {
			result.addError(RMSErrorCode.IGNORE_VALUE, "the presence of land_id in create_player_lands disable the use of %s in object placement", this.getName());
		}
		
		result.ensureThereAreNoSiblingOfTheSameTypes(this, RMSNodeType.PLACE_ON_SPECIFIC_LAND_ID);
		
		return result.merge(this.semanticCheckChildren(input));
		
	}

}

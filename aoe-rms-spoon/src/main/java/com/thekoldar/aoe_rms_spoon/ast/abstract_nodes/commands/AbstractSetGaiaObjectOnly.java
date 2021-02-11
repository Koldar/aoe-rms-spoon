package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.IPossibleValue;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractSetGaiaObjectOnly extends AbstractRMSNoArgumentCommand {

	protected AbstractSetGaiaObjectOnly() {
		super(RMSNodeType.SET_GAIA_OBJECT_ONLY);
	}
	
	
	@Override
	public String getComment() {
		return "Use with set_place_for_every_player to place gaia (neutral) objects on a per-player basis.  Must be used when placing player's gold/stone/berries/deer/boar. Units and buildings will permanently join the player who first finds them, unless set_gaia_unconvertible is also specified. Gaia building architectural style can be specified with set_gaia_civilization";
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
		
		return result.merge(this.semanticCheckChildren(input));
	}

}

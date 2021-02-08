package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.commands;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNoArgumentCommand;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSSingleOptionalIntArgumentCommand;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

public abstract class AbstractOtherZoneAvoidanceDistance extends AbstractRMSSingleOptionalIntArgumentCommand{

	protected AbstractOtherZoneAvoidanceDistance() {
		super(RMSNodeType.OTHER_ZONE_AVOIDANCE_DISTANCE);
	}

	@Override
	public String getArgumentName() {
		return "tiles";
	}

	@Override
	public Object getDefaultValue() {
		return 0;
	}

	@Override
	public String getArgumentComment() {
		return "number";
	}

	@Override
	public String getComment() {
		return "Number of tiles away from a land of with different zone to stop land growth.  Used to create river maps and island maps.";
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		var result = input.createOutput();
		
		result.ensureThereAreNoSiblingOfTheSameType(this);
		result.ensureArgumentIsLiteralInteger(this, 0);
		
		return result.merge(this.semanticCheckChildren(input));
	}
	
	

}

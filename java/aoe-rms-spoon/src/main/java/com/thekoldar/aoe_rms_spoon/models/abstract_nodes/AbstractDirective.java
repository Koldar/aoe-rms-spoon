package com.thekoldar.aoe_rms_spoon.models.abstract_nodes;

import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.exceptions.AbstractRMSException;

/**
 * a RMS directive (e.g., const, define, include)
 * @author massi
 *
 * @param <TAOE>
 */
public abstract class AbstractDirective extends AbstractRMSNode {

	public AbstractDirective(RMSNodeType type) {
		super(type);
	}
	
	
}

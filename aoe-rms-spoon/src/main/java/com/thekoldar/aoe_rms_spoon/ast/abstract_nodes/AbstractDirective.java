package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * a RMS directive (e.g., const, define, include)
 * @author massi
 *
 */
public abstract class AbstractDirective extends AbstractRMSNode {

	public AbstractDirective(RMSNodeType type) {
		super(type);
	}
	
	
}

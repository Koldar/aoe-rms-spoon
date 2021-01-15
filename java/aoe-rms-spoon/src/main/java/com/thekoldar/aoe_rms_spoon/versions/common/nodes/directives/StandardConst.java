package com.thekoldar.aoe_rms_spoon.versions.common.nodes.directives;


import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractDirective;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.directives.AbstractConst;

public class StandardConst extends AbstractConst {

	private String name;
	private int value;
	
	public StandardConst(String name, int value) {
		super(name, value);
	}

	
}

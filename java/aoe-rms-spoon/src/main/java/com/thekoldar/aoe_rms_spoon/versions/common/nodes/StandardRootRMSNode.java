package com.thekoldar.aoe_rms_spoon.versions.common.nodes;

import com.thekoldar.aoe_rms_spoon.models.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.models.ChangeLogEntry;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRootNode;
import com.thekoldar.aoe_rms_spoon.models.exceptions.AbstractRMSException;

public class StandardRootRMSNode extends AbstractRootNode {


	
	public StandardRootRMSNode(AbstractAoEVersion version) {
		super(version);
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException {
		return this.semanticCheckChildren(input);
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		return this.codeGenerationChildren(input);
	}


}

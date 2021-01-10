package com.thekoldar.aoe_rms_spoon.versions.common.nodes;

import org.eclipse.collections.api.list.MutableList;

import com.thekoldar.aoe_rms_spoon.models.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.IRMSNode;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.nodes.AbstractRMSNode;

public class Comment<TAOE> extends AbstractRMSNode<TAOE> {

	protected Comment(String name, MutableList<IRMSNode<TAOE>> children, IRMSNode<TAOE> parent, RMSNodeType type) {
		super(name, children, parent, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TAOE getAgeVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

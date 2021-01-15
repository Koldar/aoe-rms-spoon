package com.thekoldar.aoe_rms_spoon.versions.common.nodes;

import java.util.Collection;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;

import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractRMSNode;

public class StandardComment extends AbstractRMSNode {

	private Collection<String> names;
	
	public StandardComment(String name) {
		this(Lists.fixedSize.of(name));
	}
	
	public StandardComment(RichIterable<String> names) {
		super(RMSNodeType.COMMENT);
		this.names = names.toList();
	}

	@Override
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) {
		return null;
	}

	@Override
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input) {
		var result = new CodeGenerationOutput();
		for (var line : this.names) {
			result.addLine("/* " + line + " */");
		}
		return result;
	}

	
}

package com.thekoldar.aoe_rms_spoon.age_versions.common.nodes;

import java.util.Collection;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;

import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

/**
 * Age of empires Definitive Edition version of the node. It is instantiable
 * @author massi
 *
 */
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
		
		if (input.enableComments()) {
			if (this.names.size() == 1) {
				result.addLine("/* " + this.names.iterator().next() + " */");
			} else {
				result.addLine("/* ");
				for (var line : this.names) {
					result.addLine(" * " + line);	
				}
				result.addLine(" */");
			}
		}
		return result;
	}

	
}

package com.thekoldar.aoe_rms_spoon.models.nodes;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.IRMSNode;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.models.exceptions.RMSSemanticErrorException;

public abstract class AbstractRMSNode<TAOE> implements IRMSNode<TAOE> {

	protected MutableList<IRMSNode<TAOE>> children;
	private IRMSNode<TAOE> parent;
	private RMSNodeType type;
	
	protected AbstractRMSNode(MutableList<IRMSNode<TAOE>> children, IRMSNode<TAOE> parent, RMSNodeType type) {
		super();
		this.children = children;
		this.parent = parent;
		this.type = type;
	}
	
	protected AbstractRMSNode(RMSNodeType type) {
		this(Lists.mutable.empty(), null, type);
	}

	public ImmutableList<IRMSNode<TAOE>> getChildren() {
		return this.children.toImmutable();
	}
	
	public void setParent(IRMSNode<TAOE> newParent) {
		this.parent = newParent;
	}

	public IRMSNode<TAOE> getParent() {
		return this.parent;
	}

	public RMSNodeType getNodeType() {
		return this.type;
	}
	
	@Override
	public IRMSNode<TAOE> addStatement(IRMSNode<TAOE> stmt) {
		this.children.add(stmt);
		stmt.setParent(this);
		return this;
	}
	
	public TAOE getAgeVersion() {
		IRMSNode<TAOE> tmp = this;
		while (true) {
			if (this.isRoot()) {
				return this.getAgeVersion();
			} else {
				tmp = this.getParent();
			}
		}
	}
	
	protected SemanticCheckOutput semanticCheckChildren(SemanticCheckInput input) throws AbstractRMSException {
		for (var c : this.children) {
			var output = c.semanticCheck(input);
			if (output == null) {
				continue;
			}
			if (!output.isCorrect()) {
				throw output.getFirstError();
			}
		}
		return null;
	}
	
	protected CodeGenerationOutput codeGenerationChildren(CodeGenerationInput input) {
		var result = new CodeGenerationOutput();
		for (var c : this.children) {
			var output = c.codeGeneration(input);
			result.merge(output);
		}
		return result;
	}

	
}

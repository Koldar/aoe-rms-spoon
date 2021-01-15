package com.thekoldar.aoe_rms_spoon.models.abstract_nodes;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thekoldar.aoe_rms_spoon.models.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.models.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.models.IRMSNode;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.models.SemanticCheckOutput;
import com.thekoldar.aoe_rms_spoon.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.models.exceptions.RMSSemanticErrorException;

/**
 * A default implementation of a {@link IRMSNode}
 * 
 * @author massi
 *
 */
public abstract class AbstractRMSNode implements IRMSNode {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractRMSNode.class);
	
	protected MutableList<IRMSNode> children;
	private IRMSNode parent;
	private RMSNodeType type;
	
	protected AbstractRMSNode(MutableList<IRMSNode> children, IRMSNode parent, RMSNodeType type) {
		super();
		this.children = children;
		this.parent = parent;
		this.type = type;
	}
	
	protected AbstractRMSNode(MutableList<IRMSNode> children, RMSNodeType type) {
		this(children, null, type);
	}
	
	protected AbstractRMSNode(RMSNodeType type) {
		this(Lists.mutable.empty(), type);
	}
 
	public ImmutableList<IRMSNode> getChildren() {
		return this.children.toImmutable();
	}
	
	public void setParent(IRMSNode newParent) {
		this.parent = newParent;
	}

	public IRMSNode getParent() {
		return this.parent;
	}

	public RMSNodeType getNodeType() {
		return this.type;
	}
	
	@Override
	public IRMSNode addStatement(IRMSNode stmt) {
		LOG.info("Add {} into {}", stmt.getNodeType(), this.getNodeType());
		this.children.add(stmt);
		stmt.setParent(this);
		return this;
	}
	
	public AbstractAoEVersion getAgeVersion() {
		IRMSNode tmp = this;
		while (true) {
			if (tmp.isRoot()) {
				return tmp.getAgeVersion();
			} else {
				tmp = tmp.getParent();
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

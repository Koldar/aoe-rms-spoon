package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.add_methods.Adds;
import com.thekoldar.aoe_rms_spoon.framework.AbstractAoEVersion;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationInput;
import com.thekoldar.aoe_rms_spoon.framework.code_generation.CodeGenerationOutput;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.AbstractRMSException;
import com.thekoldar.aoe_rms_spoon.framework.models.exceptions.RMSSemanticErrorException;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckOutput;

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
	protected Adds adds;
	
	protected AbstractRMSNode(MutableList<IRMSNode> children, IRMSNode parent, RMSNodeType type) {
		super();
		this.children = children;
		this.parent = parent;
		this.type = type;
		this.adds = new Adds(this);
	}
	
	@Override
	public String toString() {
		return this.type.toString();
	}
	
	@Override
	public String getLabel() {
		return "";
	}
	
	protected AbstractRMSNode(MutableList<IRMSNode> children, RMSNodeType type) {
		this(children, null, type);
	}
	
	protected AbstractRMSNode(RMSNodeType type) {
		this(Lists.mutable.empty(), type);
	}
	
	public Adds getAdds() {
		return this.adds;
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
		LOG.debug("Add {} into {}", stmt.getNodeType(), this.getNodeType());
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
		var result = input.createOutput();
		for (var c : this.children) {
			var output = c.semanticCheck(input);
			if (output == null) {
				continue;
			}
			if (!output.isCorrect()) {
				throw output.getFirstError().get();
			}
			result.merge(output);
		}
		return result;
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

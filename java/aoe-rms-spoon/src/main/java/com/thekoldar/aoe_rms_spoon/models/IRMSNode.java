package com.thekoldar.aoe_rms_spoon.models;

import java.util.function.Predicate;

import javax.annotation.Nullable;

import org.eclipse.collections.api.factory.Sets;
import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.models.exceptions.AbstractRMSException;

/**
 * a RMS node in the AST of the RMS we are bnuilding
 * @author massi
 *
 */
public interface IRMSNode
{
	
	public AbstractAoEVersion getAgeVersion();
	
	public ImmutableList<IRMSNode> getChildren();
	
	@Nullable
	public IRMSNode getParent();
	
	public void setParent(IRMSNode newParent);
	
	public RMSNodeType getNodeType();
	
	public default boolean isLeaf() {
		return !this.getChildren().iterator().hasNext();
	}
	
	public default boolean isRoot() {
		return this.getParent() == null;
	}
	
	/**
	 * Add a new children at the end of the children list of this node.
	 * 
	 * This has the side effect that the children parent is set to this
	 * @param stmt
	 * @return
	 */
	public abstract IRMSNode addStatement(IRMSNode stmt);
	
	
	/**
	 * poerform semantic analysis on the node
	 * @param input
	 * @return if null, the check is assumed to be ok anbd nothing will be updated
	 */
	@Nullable
	public SemanticCheckOutput semanticCheck(SemanticCheckInput input) throws AbstractRMSException;
	
	/**
	 * generate RMS code from this node
	 * @param input
	 * @return
	 */
	public CodeGenerationOutput codeGeneration(CodeGenerationInput input);
	
	public default boolean ensureWehaveIncludedFile(String file) {
		return this.ensureThereIsAtLeastOneNodeInTreeOfTypes(RMSNodeType.INCLUDE, RMSNodeType.INCLUDE_DRS);
	}
	
	public default boolean ensureThereIsAtLeastOneNodeInTreeOfTypes(RMSNodeType... expectedTypes) {
		return this.ensureThereIsAtLeastOneNodeInTreeThat((n) -> {
			return Sets.immutable.of(expectedTypes).contains(n);
		});
	}
	
	public default boolean ensureThereIsAtLeastOneNodeInTreeOfType(RMSNodeType expectedType) {
		return this.ensureThereIsAtLeastOneNodeInTreeThat((n) -> n.getNodeType().equals(expectedType));
	}
	
	public default boolean ensureThereIsAtLeastOneNodeInTreeThat(Predicate<IRMSNode> condition) {
		return this._ensureThereIsAtLeastOneNodeInTreeThat(condition);
	}
	
	private boolean _ensureThereIsAtLeastOneNodeInTreeThat(Predicate<IRMSNode> condition) {
		if (condition.test(this)) {
			return true;
		}
		for (var c : this.getChildren()) {
			if (c.ensureThereIsAtLeastOneNodeInTreeThat(condition)) {
				return true;
			}
		}
		return false;
	}
	
	
}

package com.thekoldar.aoe_rms_spoon.models;

import javax.annotation.Nullable;

import org.eclipse.collections.api.list.ImmutableList;

import com.thekoldar.aoe_rms_spoon.models.exceptions.AbstractRMSException;

/**
 * a RMS node in the AST of the RMS we are bnuilding
 * @author massi
 *
 */
public interface IRMSNode<TAOE>
{
	
	public TAOE getAgeVersion();
	
	public ImmutableList<IRMSNode<TAOE>> getChildren();
	
	@Nullable
	public IRMSNode<TAOE> getParent();
	
	public void setParent(IRMSNode<TAOE> newParent);
	
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
	public abstract IRMSNode<TAOE> addStatement(IRMSNode<TAOE> stmt);
	
	
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
	
	
}

package com.thekoldar.aoe_rms_spoon.ast.builders;

import javax.annotation.Nullable;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.expr.DictExpr;

/**
 * Allows you to fluently build a RMS dictionary value
 * @author massi
 *
 * @param <TNODE> parent of the dictionary we are building
 */
public class DictExprBuilder<TNODE extends IRMSNode> {
	
	/**
	 * Dictioanry we are building
	 */
	private DictExpr dict;
	/**
	 * the parent fo the dictioanry to build. If null, then we will not add the dictioanry to the parent after the dictionary has completed
	 */
	@Nullable
	private TNODE parent;
	
	/**
	 * A new builder that will <b>not</b> add the dictioanry to any parent after we have finished building it
	 */
	public DictExprBuilder() {
		this.parent = null;
		this.dict = new DictExpr();
	}
	
	/**
	 * A new builder that will add the dictioanry to the parent after we have finished building it
	 * @param parent parent to set
	 */
	public DictExprBuilder(TNODE parent) {
		this.parent = parent;
		this.dict = new DictExpr();
	}
	
	/**
	 * Adds a new statement in the dictionary
	 * @param node statement to add
	 * @return {@code this}
	 */
	public DictExprBuilder<TNODE> add(IRMSNode node) {
		this.dict.addStatement(dict);
		return this;
	}
	
	/**
	 * Declare that the dictionary has ended. This will add the dictionary to the parent (if any)
	 * @return the parent. Maybe  null
	 */
	public DictExpr dictEnd() {
		this.parent.addStatement(dict);
		return this.dict;
	}
}
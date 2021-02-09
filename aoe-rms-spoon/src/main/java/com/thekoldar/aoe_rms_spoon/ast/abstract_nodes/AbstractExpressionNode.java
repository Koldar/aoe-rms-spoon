package com.thekoldar.aoe_rms_spoon.ast.abstract_nodes;

import org.eclipse.collections.api.list.MutableList;

import com.thekoldar.aoe_rms_spoon.ast.ExprType;
import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.RMSNodeType;
import com.thekoldar.aoe_rms_spoon.ast.expr.DictExpr;
import com.thekoldar.aoe_rms_spoon.framework.semantic_analysis.SemanticCheckInput;

/**
 * A RMS expression. Expression node are expressions where we can know the actual value of the expression
 * @author massi
 *
 * @param <TAOE>
 */
public abstract class AbstractExpressionNode extends AbstractRMSNode {

	protected AbstractExpressionNode() {
		super(RMSNodeType.EXPR);
	}
	
	/**
	 * try to cast the expression to an int.
	 * 
	 * May fail if {@link #canBeCastedToInt()} is false
	 * @return
	 */
	public abstract int getAsInt(SemanticCheckInput input);
	
	/**
	 * try to cast the expression to an bool.
	 * 
	 * May fail if {@link #canBeCastedToBoolean()} is false
	 * @return
	 */
	public abstract boolean getAsBool(SemanticCheckInput input);
	
	/**
	 * try to cast the expression to an string.
	 * 
	 * May fail if {@link #canBeCastedToString()} is false
	 * @return
	 */
	public abstract String getAsString(SemanticCheckInput input);
	
	/**
	 * try to cast the expression to an dict.
	 * 
	 * May fail if {@link #canBeCastedToDict()} is false
	 * @return
	 */
	public abstract DictExpr getAsDict(SemanticCheckInput input);
	
}

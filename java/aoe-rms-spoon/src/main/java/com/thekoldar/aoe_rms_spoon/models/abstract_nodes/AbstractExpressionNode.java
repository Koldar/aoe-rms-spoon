package com.thekoldar.aoe_rms_spoon.models.abstract_nodes;

import org.eclipse.collections.api.list.MutableList;

import com.thekoldar.aoe_rms_spoon.models.ExprType;
import com.thekoldar.aoe_rms_spoon.models.IRMSNode;
import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;

/**
 * A RMS expression
 * @author massi
 *
 * @param <TAOE>
 */
public abstract class AbstractExpressionNode extends AbstractRMSNode {

	protected AbstractExpressionNode() {
		super(RMSNodeType.EXPR);
	}

	public abstract ExprType getType();
	
	public boolean canBeCastedTo(ExprType exprType) {
		switch (exprType) {
		case BOOLEAN:
			return this.canBeCastedToBoolean();
		case DICT:
			return this.canBeCastedToDict();
		case FLOAT:
			return this.canBeCastedToFloat();
		case INT:
			return this.canBeCastedToInt();
		case STRING:
			return this.canBeCastedToString();
		default:
			throw new IllegalArgumentException(String.format("Invalid expr type %s!", exprType));
		}
	}

	/**
	 * is the expression convertible into an int?
	 * @return
	 */
	public abstract boolean canBeCastedToInt();
	
	public abstract boolean canBeCastedToBoolean();

	public abstract boolean canBeCastedToFloat();
	
	public abstract boolean canBeCastedToString();
	
	public abstract boolean canBeCastedToDict();
	
}

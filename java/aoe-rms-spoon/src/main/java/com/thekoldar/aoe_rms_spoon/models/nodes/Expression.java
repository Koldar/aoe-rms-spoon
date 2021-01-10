package com.thekoldar.aoe_rms_spoon.models.nodes;

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
public abstract class Expression<TAOE> extends AbstractRMSNode<TAOE> {

	protected Expression(MutableList<IRMSNode<TAOE>> children, IRMSNode<TAOE> parent, RMSNodeType type) {
		super(name, children, parent, type);
		// TODO Auto-generated constructor stub
	}

	public abstract ExprType getType();

	
}

package com.thekoldar.aoe_rms_spoon.models.nodes;

import com.thekoldar.aoe_rms_spoon.models.RMSNodeType;

/**
 * a RMS directive (e.g., const, define, include)
 * @author massi
 *
 * @param <TAOE>
 */
public abstract class AbstractDirective<TAOE> extends AbstractRMSNode<TAOE> {

	public AbstractDirective(RMSNodeType type) {
		super(type);
	}
	
}

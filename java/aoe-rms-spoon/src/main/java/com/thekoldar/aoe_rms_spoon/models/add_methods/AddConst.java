package com.thekoldar.aoe_rms_spoon.models.add_methods;

import com.thekoldar.aoe_rms_spoon.models.IRMSNode;

public interface AddConst<TOUT> extends IRMSNode{

	public default TOUT constant(String name, int val) {
		this.addStatement(this.getAgeVersion().constant(name, val));
		return (TOUT)this;
	}
}

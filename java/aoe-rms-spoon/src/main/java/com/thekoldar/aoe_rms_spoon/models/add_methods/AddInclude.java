package com.thekoldar.aoe_rms_spoon.models.add_methods;

import com.thekoldar.aoe_rms_spoon.models.IRMSNode;

public interface AddInclude<TOUT> extends IRMSNode{

	public default TOUT include(String name) {
		this.addStatement(this.getAgeVersion().include(name));
		return (TOUT)this;
	}
}

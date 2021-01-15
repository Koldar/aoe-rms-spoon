package com.thekoldar.aoe_rms_spoon.models.add_methods;

import com.thekoldar.aoe_rms_spoon.models.IRMSNode;

public interface AddIncludeDrs<TOUT> extends IRMSNode{

	public default TOUT includeDrs(String name) {
		this.addStatement(this.getAgeVersion().includeDrs(name));
		return (TOUT)this;
	}
}

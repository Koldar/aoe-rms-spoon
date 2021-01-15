package com.thekoldar.aoe_rms_spoon.models.add_methods;

import com.thekoldar.aoe_rms_spoon.models.IRMSNode;

public interface AddDefine<TOUT> extends IRMSNode {

	public default TOUT define(String file) {
		this.addStatement(this.getAgeVersion().define(file));
		return (TOUT)this;
	}
}

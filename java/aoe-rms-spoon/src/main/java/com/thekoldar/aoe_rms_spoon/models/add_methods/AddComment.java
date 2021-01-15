package com.thekoldar.aoe_rms_spoon.models.add_methods;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.factory.Lists;

import com.thekoldar.aoe_rms_spoon.models.IRMSNode;

public interface AddComment<TOUT> extends IRMSNode {

	public default TOUT comment(RichIterable<String> comments) {
		this.addStatement(this.getAgeVersion().comment(comments));
		return (TOUT)this;
	}
	
	public default TOUT comment(String comment) {
		this.addStatement(this.getAgeVersion().comment(Lists.fixedSize.of(comment)));
		return (TOUT)this;
	}
}

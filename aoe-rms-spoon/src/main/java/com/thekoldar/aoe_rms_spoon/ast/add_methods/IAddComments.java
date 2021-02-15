package com.thekoldar.aoe_rms_spoon.ast.add_methods;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;

/**
 * the node can have comments within it
 * @author massi
 *
 * @param <TPARENT> type of the class implementing this interface
 */
public interface IAddComments<TPARENT extends IRMSNode> {
	
	public Adds getAdds();

	public default TPARENT comment(String comment) {
		return (TPARENT)this.getAdds().comment(comment);
	}

	public default TPARENT comment(RichIterable<String> comments) {
		return (TPARENT)this.getAdds().comment(comments);
	}
}

package com.thekoldar.aoe_rms_spoon.ast.add_methods;

import org.eclipse.collections.api.RichIterable;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.builders.IfBlockBuilder;
import com.thekoldar.aoe_rms_spoon.ast.builders.RandomBlockBuilder;

/**
 * Set of capabilities that shoudl normally be owned by every RMS AST node
 * @author massi
 *
 * @param <TPARENT> type of node that owns this capabilities
 */
public interface IAddStandard<TPARENT extends IRMSNode> extends IAddComments<TPARENT> {

	public Adds getAdds();
	
	public default TPARENT addIf(IRMSNode condition, IRMSNode then) {
		return (TPARENT)this.getAdds().addIf(condition, then);
	}

	public default TPARENT addIf(IRMSNode condition, IRMSNode then, IRMSNode elseBlock) {
		return (TPARENT)this.getAdds().addIf(condition, then, elseBlock);
	}
	
	public default IfBlockBuilder beginIf() {
		return IfBlockBuilder.instance((TPARENT)this);
	}
	
	public default RandomBlockBuilder startRandom() {
		return this.getAdds().startRandom();
	}
	
	public default TPARENT include(String include) {
		return (TPARENT) this.getAdds().include(include);
	}
	
	public default TPARENT includeDrs(String include) {
		return (TPARENT) this.getAdds().includeDrs(include);
	}
	
	public default TPARENT constant(String name, int val) {
		return (TPARENT) this.getAdds().constant(name, val);
	}
	
	public default TPARENT define(String name) {
		return (TPARENT) this.getAdds().define(name);
	}
	
	public default TPARENT defines(String... names) {
		TPARENT result = null;
		for (var n : names) {
			this.define(n);
		}
		return (TPARENT)this;
	}
	
	public default TPARENT dict(AbstractRMSNode... values) {
		return (TPARENT) this.getAdds().dict(values);
	}
}

package com.thekoldar.aoe_rms_spoon.ast.builders;

import com.thekoldar.aoe_rms_spoon.ast.IRMSNode;
import com.thekoldar.aoe_rms_spoon.ast.expr.DictExpr;

public class DictExprBuilder<TNODE extends IRMSNode> {
	
	private DictExpr dict;
	private TNODE parent;
	
	public DictExprBuilder(TNODE parent) {
		this.parent = parent;
		this.dict = new DictExpr();
	}
	
	public DictExprBuilder<TNODE> add(IRMSNode node) {
		this.dict.addStatement(dict);
		return this;
	}
	
	public TNODE dictEnd() {
		this.parent.addStatement(dict);
		return this.parent;
	}
}
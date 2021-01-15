package com.thekoldar.aoe_rms_spoon.models;

import com.thekoldar.aoe_rms_spoon.models.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.expr.BooleanExpr;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.expr.ConstRefExpr;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.expr.DefineRefExpr;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.expr.DictExpr;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.expr.FloatExpr;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.expr.IntExpr;
import com.thekoldar.aoe_rms_spoon.versions.common.nodes.expr.StringExpr;

public class RMSExprs {

	public static AbstractExpressionNode intVal(int value) {
		return new IntExpr(value);
	}
	
	public static AbstractExpressionNode boolVal(boolean value) {
		return new BooleanExpr(value);
	}
	
	public static AbstractExpressionNode floatVal(float value) {
		return new FloatExpr(value);
	}
	
	public static AbstractExpressionNode stringVal(String value) {
		return new StringExpr(value);
	}
	
	public static AbstractExpressionNode constVal(String value) {
		return new ConstRefExpr(value);
	}
	
	public static AbstractExpressionNode defineVal(String value) {
		return new DefineRefExpr(value);
	}
	
	public static DictExpr dict(IRMSNode... nodes) {
		var result = new DictExpr();
		for (var n : nodes) {
			result.addStatement(n);	
		}
		return result;
	}
	
	public static <TNODE extends IRMSNode> DictExprBuilder<TNODE> dictStart(TNODE parent) {
		return new DictExprBuilder<TNODE>(parent);
	}
}

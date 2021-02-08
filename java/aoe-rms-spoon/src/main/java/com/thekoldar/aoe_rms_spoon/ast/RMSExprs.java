package com.thekoldar.aoe_rms_spoon.ast;

import com.thekoldar.aoe_rms_spoon.ast.abstract_nodes.AbstractExpressionNode;
import com.thekoldar.aoe_rms_spoon.ast.builders.DictExprBuilder;
import com.thekoldar.aoe_rms_spoon.ast.expr.BooleanExpr;
import com.thekoldar.aoe_rms_spoon.ast.expr.ConstRefExpr;
import com.thekoldar.aoe_rms_spoon.ast.expr.DefineRefExpr;
import com.thekoldar.aoe_rms_spoon.ast.expr.DictExpr;
import com.thekoldar.aoe_rms_spoon.ast.expr.FloatExpr;
import com.thekoldar.aoe_rms_spoon.ast.expr.IntExpr;
import com.thekoldar.aoe_rms_spoon.ast.expr.StringExpr;
import com.thekoldar.aoe_rms_spoon.ast.functions.RandomNumberNode;

public class RMSExprs {
	
	public static AbstractExpressionNode rndVal(int min, int max) {
		var result = new RandomNumberNode();
		result.addStatement(RMSExprs.intVal(min));
		result.addStatement(RMSExprs.intVal(max));
		return result;
	}

	/**
	 * generate a new int literal
	 * @param value the int value
	 * @return expression node just created
	 */
	public static AbstractExpressionNode intVal(int value) {
		return new IntExpr(value);
	}
	
	/**
	 * Alias of {@link #intVal(int)}
	 * @param value the int value to embed in the script
	 * @return expression node just created
	 */
	public static AbstractExpressionNode iVal(int value) {
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
	
	/**
	 * create a dict having as children all the given nodes
	 * @param nodes
	 * @return
	 */
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

package com.thekoldar.aoe_rms_spoon.ast.symbols;

import com.thekoldar.aoe_rms_spoon.ast.expr.DefineRefExpr;

/**
 * A symbol defined via {@literal @define}
 * @author massi
 *
 */
public class RMSDefineSymbol implements IRMSSymbol {

	private String name;
	
	
	public RMSDefineSymbol(String name) {
		super();
		this.name = name;
	}
	
	public static RMSDefineSymbol get(String name) {
		return new RMSDefineSymbol(name);
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public RMSSymbolType getType() {
		return RMSSymbolType.DEFINE;
	}
	
	/**
	 * a reference view of this define
	 * @return
	 */
	public DefineRefExpr asRef() {
		return new DefineRefExpr(this.name);
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}

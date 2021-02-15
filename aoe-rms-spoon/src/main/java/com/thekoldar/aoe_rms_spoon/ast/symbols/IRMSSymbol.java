package com.thekoldar.aoe_rms_spoon.ast.symbols;

/**
 * A generic symbol in the semantci analysis symbol table
 * @author massi
 *
 */
public interface IRMSSymbol {

	/**
	 * name of the symbol
	 * @return name of the symbol
	 */
	public String getName();
	
	/**
	 * symbol
	 * @return the symbol
	 */
	public RMSSymbolType getType();
}

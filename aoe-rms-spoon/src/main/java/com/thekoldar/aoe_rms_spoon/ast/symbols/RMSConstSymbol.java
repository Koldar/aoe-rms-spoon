package com.thekoldar.aoe_rms_spoon.ast.symbols;

/**
 * A symbol defined via {@literal #const}
 * @author massi
 *
 */
public class RMSConstSymbol implements IRMSSymbol {

	private String name;
	private int value;
	
	
	
	public RMSConstSymbol(String name, int value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public static RMSConstSymbol get(String name, int val) {
		return new RMSConstSymbol(name, val);
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public RMSSymbolType getType() {
		return RMSSymbolType.CONST;
	}
	
	public long getValueAsLong() {
		return this.value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return String.format("%s=%$d", this.name, this.value);
	}
	
}

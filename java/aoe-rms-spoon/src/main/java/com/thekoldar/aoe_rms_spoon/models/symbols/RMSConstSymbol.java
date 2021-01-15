package com.thekoldar.aoe_rms_spoon.models.symbols;

public class RMSConstSymbol implements IRMSSymbol {

	private String name;
	private int value;
	
	
	
	public RMSConstSymbol(String name, int value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public RMSSymbolType getType() {
		return RMSSymbolType.CONST;
	}
	
	public int getValue() {
		return this.value;
	}
	
}

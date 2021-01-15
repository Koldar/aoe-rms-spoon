package com.thekoldar.aoe_rms_spoon.models.symbols;

public class RMSDefineSymbol implements IRMSSymbol {

	private String name;
	
	
	public RMSDefineSymbol(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	@Override
	public RMSSymbolType getType() {
		return RMSSymbolType.DEFINE;
	}
	
}
